import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class AverageRatings {


	public AverageRatings() {
		MovieDatabase.initialize();
		RaterDatabase.initialize();
	}

	public AverageRatings(String movieFile, String raterFile) {
		MovieDatabase.initialize(movieFile);
		RaterDatabase.initialize(raterFile);
	}

	//returns an ArrayList of Ratings
	//contains the average rating for every movie with at least n raters
	public ArrayList<Rating> getAverageRatings(int minimalRaters){
		ArrayList<String> movies = MovieDatabase.filterBy(new FilterAllTrue());
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
		Collections.sort(averagesToReturn);
		Collections.reverse(averagesToReturn);
	
		return averagesToReturn;
	}



	
}




