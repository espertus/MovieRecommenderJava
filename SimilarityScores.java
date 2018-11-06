import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class SimilarityScores {
	public SimilarityScores(){
		RaterDatabase.initialize();
		MovieDatabase.initialize();
	}

	//computes a similarity rating for each rater in the database to the provided rater
	private ArrayList<Rating> getSimilarities(String id){
		ArrayList<Rating> list = new ArrayList<Rating>();
		Rater me = RaterDatabase.getRater(id);
		for (Rater r : RaterDatabase.getRaters()) {
			if (r.getMyID().equals(id)) {
				continue;
			} 
			list.add(new Rating(r.getMyID(), dotProduct(me, r)));
		}
		Collections.sort(list, Collections.reverseOrder());
		return list;
	} 

	//id: Rater ID
	//numSimilar: how many similar raters will be used to find recommendation
	//minimalRaters: how many ratings a movie must have to be included in our search
	public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters){

		//get the similarity scores for all raters
		ArrayList<Rating> allRaters = getSimilarities(id);

		//cull the list to only the 'top' raters, per the numSimilarRaters
		ArrayList<Rating> onlyTopRaters = new ArrayList<Rating>();
		for (int i = 0; i < numSimilarRaters; i++) {
			onlyTopRaters.add(allRaters.get(i));
		}
		HashMap <String, Integer>countOfMovieInTopRaters = buildMapOfMovies(onlyTopRaters);
		ArrayList<Rating> recommendations = new ArrayList<Rating>();
		for (String s : countOfMovieInTopRaters.keySet()) {
			if (countOfMovieInTopRaters.get(s) < minimalRaters) {
				continue;
			} else {
				addRecommendation(onlyTopRaters, s, recommendations);
			}
		}
		Collections.sort(recommendations, Collections.reverseOrder());
		return recommendations;
	}

	//helper method for getSimilarRatings which maps how many times each movie has been rated by most similar raters
	private HashMap<String, Integer> buildMapOfMovies(ArrayList<Rating> onlyTopRaters){
		HashMap <String, Integer> countOfMovieInTopRaters = new HashMap<String,Integer>();

		for (Rating r : onlyTopRaters) {
			String raterID = r.getItem();
			Rater rater = RaterDatabase.getRater(raterID);
			ArrayList<String> moviesRated = rater.getItemsRated();
			for (String s : moviesRated) {
				if (!countOfMovieInTopRaters.containsKey(s)) {
					countOfMovieInTopRaters.put(s, 1);
				} else {
					countOfMovieInTopRaters.put(s, countOfMovieInTopRaters.get(s) + 1);
				}
			}
		}
		return countOfMovieInTopRaters;
	}
	//helper method for getSimilarRatings which adds a movie and its suggestion score
	private void addRecommendation(ArrayList<Rating> onlyTopRaters, String s, ArrayList<Rating> recommendations) {
		//score is multiplied by the other reviewer's similarity to score to give more weight to reviewers that are more like the user
		double weightedScore = 0;
		double countOfReviewers = 0;
		for (Rating r : onlyTopRaters) {
			Rater rater = RaterDatabase.getRater(r.getItem());
			ArrayList<String> moviesRated = rater.getItemsRated();	
			if (moviesRated.contains(s)) {
				double ratersSimilarityScore = r.getValue();
				weightedScore += rater.getRating(s) * ratersSimilarityScore;
				countOfReviewers++;
			} 
		}
		double average = weightedScore/countOfReviewers;
		Rating rating = new Rating(s, average);
		recommendations.add(rating);
	}

	//dotProduct provides a number which represents how close two raters are in preferences
	//it does this by normalizing their ratings of the same movie, multiplying them, and summing the products together
	private double dotProduct (Rater me, Rater r) {
		ArrayList<String> itemsIveRated = me.getItemsRated();
		double dotTotal = 0;
		for (String s : itemsIveRated) {
			double myRating = me.getRating(s) - 5;
			if (r.getRating(s) == 0) {
				continue;
			}
			double theirRating = r.getRating(s) - 5;
			if (theirRating != -5) {
				dotTotal += (myRating * theirRating);
			}
		}
		return dotTotal;
	}

	public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, Filter f	){
		ArrayList<Rating> notFiltered = getSimilarRatings(id, numSimilarRaters, minimalRaters);
		ArrayList<Rating> filtered = new ArrayList<Rating>();
		for (Rating r : notFiltered) {
			String itemID = r.getItem();
			if (f.satisfies(itemID)) {
				filtered.add(r);	
			}
		}
		return filtered;
	}
}
