import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class FourthRatings {


	public FourthRatings() {
		MovieDatabase.initialize();
		RaterDatabase.initialize();
	}

	public FourthRatings(String movieFile, String raterFile) {
		MovieDatabase.initialize(movieFile);
		RaterDatabase.initialize(raterFile);

	}
	//returns an ArrayList of Ratings
	//contains the average rating for every movie with at least n raters
	public ArrayList<Rating> getAverageRatings(int minimalRaters){
		ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
		HashSet <Rating> ratingsHS = new HashSet<Rating>();
		for (String s : movies) {
			double average = getAverageByID(s,minimalRaters);
			if (average == 0) {
				continue;

			} else {
				ratingsHS.add(new Rating(s, average));
			}
		}

		ArrayList<Rating> ratings = new ArrayList<Rating>();
		ratings.addAll(ratingsHS);
		return ratings;
	}

	//helper method for averageRating which returns the average movie rating ID if there are enough ratings
	public double getAverageByID(String nameID, int minimalRaters) {
		double totalScore = 0;
		int count = 0;
		RaterDatabase.initialize();
		ArrayList<Rater> ratings = RaterDatabase.getRaters();
		for (Rater r : ratings) {
			ArrayList<String> itemsRated = r.getItemsRated();
			for (String s : itemsRated) {
				if (s.equals(nameID)) {
					totalScore += r.getRating(nameID);
					count ++;
				}
			}

		}
		if (count >= minimalRaters) {
			double average = totalScore / count;
			return average;
		}
		return 0;
	}

	public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria){
		ArrayList<Rating> averagesOriginal = getAverageRatings(minimalRaters);
		ArrayList<Rating> averagesToReturn = new ArrayList<Rating>();

		for (Rating r : averagesOriginal) {
			String itemID = r.getItem();
			if (filterCriteria.satisfies(itemID)) {
				averagesToReturn.add(r);	
			}
		}
		return averagesToReturn;
	}

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
	//numSimilar: must be in top X most similar raters
	public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters){

		//get the similarity scores for all raters
		ArrayList<Rating> allRaters = getSimilarities(id);

		//cull the list to only the 'top' raters, per the numSimilarRaters
		ArrayList<Rating> onlyTopRaters = new ArrayList<Rating>();
		for (int i = 0; i < numSimilarRaters; i++) {
			onlyTopRaters.add(allRaters.get(i));
		}

		//hashmap showing how many times each movie has been rated by most similar raters
		HashMap <String, Integer> moviesAndCountInTopRaters = new HashMap<String,Integer>();
		for (Rating r : onlyTopRaters) {
			String raterID = r.getItem();
			Rater rater = RaterDatabase.getRater(raterID);
			ArrayList<String> moviesRated = rater.getItemsRated();
			for (String s : moviesRated) {
				if (!moviesAndCountInTopRaters.containsKey(s)) {
					moviesAndCountInTopRaters.put(s, 1);
				} else {
					moviesAndCountInTopRaters.put(s, moviesAndCountInTopRaters.get(s) + 1);
				}
			}
		}	

		ArrayList<Rating> recommendations = new ArrayList<Rating>();
		for (String s : moviesAndCountInTopRaters.keySet()) {
			if (moviesAndCountInTopRaters.get(s) < minimalRaters) {
				continue;
			} else {
				double cummulativeScore = 0;
				double countOfReviewers = 0;
				for (Rating r : onlyTopRaters) {
					String raterID = r.getItem();
					Rater rater = RaterDatabase.getRater(raterID);
					ArrayList<String> moviesRated = rater.getItemsRated();	
					if (moviesRated.contains(s)) {
						double weightedScore = r.getValue();
						cummulativeScore += rater.getRating(s) * weightedScore;
						countOfReviewers++;
					} 
				}
				double average = cummulativeScore/countOfReviewers;
				Rating rating = new Rating(s, average);
				recommendations.add(rating);
			}
		}
		Collections.sort(recommendations, Collections.reverseOrder());
		return recommendations;
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
//
//- Write the public method named getSimilarRatings, which has three parameters: a String named id representing a rater ID, an integer named numSimilarRaters, and an integer named minimalRaters. 

//This method should return an ArrayList of type Rating, of movies and their weighted average ratings using only the top numSimilarRaters with positive ratings and including only those movies that have at least minimalRaters ratings from those most similar raters (not just minimalRaters ratings overall). 

//For example, if minimalRaters is 3 and a movie has 4 ratings but only 2 of those ratings were made by raters in the top numSimilarRaters, that movie should not be included. These Rating objects should be returned in sorted order by weighted average rating from largest to smallest ratings. This method is very much like the getAverageRatings method you have written previously. In particular this method should:
//
//




