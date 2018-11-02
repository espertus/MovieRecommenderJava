import java.util.ArrayList;
import java.util.HashSet;

public class SecondRatings {
	private ArrayList<Movie> myMovies;
	private ArrayList<Rater> myRaters;

	public SecondRatings(ArrayList<Movie> myMovies, ArrayList<Rater> myRaters) {
		super();
		this.myMovies = myMovies;
		this.myRaters = myRaters;
	}

	public SecondRatings(String movieFile, String ratingsFile) {
		FirstRatings fr = new FirstRatings();
		myMovies = fr.loadMovies(movieFile);
		myRaters = fr.loadRaters(ratingsFile);
	}

	//returns the number of movies that are stored in myMovies
	public int getMovieSize() {
		return myMovies.size();
	}

	//returns the number of raters in myRaters
	public int getRaterSize() {
		return myRaters.size();
	}

	//returns an ArrayList of Ratings
	//contains the average rating for every movie with at least n raters
	public ArrayList<Rating> getAverageRatings(int minimalRaters){
		ArrayList<Rating> ratings = new ArrayList<Rating>();
		HashSet<Rating> ratingsHS = new HashSet<Rating>();
		for (Rater r : myRaters) {
			ArrayList<String> moviesInList = r.getItemsRated();
			for (String s : moviesInList) {
				double average = getAverageByID(s,minimalRaters);
				if (average == 0) {
					continue;
				
				} else {
					ratingsHS.add(new Rating(s, average));
				}
			}
		}
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
	//returns the title of a given movie ID
	public String getTitle (String id) {
		for (Movie m : myMovies) {
			if (m.getId().equals(id)){
				return m.getTitle();
			}
		}
		return "Title not found";
	}
	
	//returns the id of a movie given its Title
	public String getID(String title) {
		for (Movie m : myMovies) {
			if (m.getTitle().equals(title)){
				return m.getId();
			}
		}
		return "ID not found";
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
	
	public void testAverages() {
		//ArrayList<Rating> avgRatings = getAverageRatings(1);
		//System.out.println(avgRatings.size());
		System.out.println(getAverageByID("1798709", 0));
		System.out.println(getAverageRatings(0).toString());
		System.out.println(getTitle("1798709"));
	}
	
	@Override
	public String toString() {
		return "SecondRatings [myMovies=" + myMovies + ", myRaters=" + myRaters + "]";
	}
}
