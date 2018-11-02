import java.util.ArrayList;
import java.util.HashSet;

public class ThirdRatings {

	private ArrayList<Rater> myRaters;

	public ThirdRatings() {
		this("data/ratings.csv");
	}

	public ThirdRatings(String ratingsFile) {
		FirstRatings fr = new FirstRatings();
		myRaters = fr.loadRaters(ratingsFile);
	}

	//returns the number of raters in myRaters
	public int getRaterSize() {
		return myRaters.size();
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
		FirstRatings firstR = new FirstRatings();
		int numOfRatings = firstR.numOfRatingsByMovie(myRaters, Integer.parseInt(nameID));
		if (numOfRatings < minimalRaters) {
			return 0.0;
		} else {
			for (Rater r : myRaters) {
				//ArrayList<String> ratingsByCurrentRater = r.getItemsRated();
				double ratingOfCurrentMovie = r.getRating(nameID);
				if (ratingOfCurrentMovie < 0) {
					continue;
				} else {
					totalScore += ratingOfCurrentMovie;
				}
			}
			double averageScore = totalScore / numOfRatings;
			return averageScore;
		}

	}
	
	//return the lowest rated movie in an ArrayList of ratings
	public String getIdOfLowestRated(ArrayList<Rating> ratings) {
		double lowest = Double.MAX_VALUE;
		String id = null;
		for (Rating r : ratings) {
			if (r.getValue() < lowest) {
				lowest = r.getValue();
				id = r.getItem();
			}
		}
		return id;
	}
	
	public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria){
		ArrayList<Rating> averagesOriginal = getAverageRatings(minimalRaters);
		ArrayList<Rating> averagesToReturn = new ArrayList<Rating>();
		MovieDatabase.initialize();
		
		for (Rating r : averagesOriginal) {
			String itemID = r.getItem();
			if (filterCriteria.satisfies(itemID)) {
				averagesToReturn.add(r);	
		}
			
		}
		
		
		return averagesToReturn;
	}
	
	public void sortRatingsBubbleSort() {
		for (int i = 0; i < myRaters.size(); i++) {
			
		}
	}
	
	public boolean checkInSortedOrder(ArrayList<Rating> ratings) {
		for (int i = 0; i < ratings.size()-1; i++) {
			double currRating = ratings.get(i).getValue();
			double nextRating = ratings.get(i+1).getValue();
			if (currRating > nextRating) {
				return false;
			}
		}
		return true;
	}
	
	public ArrayList<Rater> getRaters(){
		return myRaters;
	}
	
	
	@Override
	public String toString() {
		return "SecondRatings: myRaters=" + myRaters + "]";
	}
}


