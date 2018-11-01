import java.util.ArrayList;

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
	
	public void testAverages() {
		//ArrayList<Rating> avgRatings = getAverageRatings(1);
	//System.out.println(avgRatings.size());
	System.out.println(myRaters.toString());
	System.out.println(getAverageByID("1798709", 0));
		
	}
	public ArrayList<Rating> getAverageRatings(int minimalRaters){
		ArrayList<Rating> ratings = new ArrayList<Rating>();
		for (Rater r : myRaters) {
			ArrayList<String> itemsRated = new ArrayList<String>();
			
			
			double average = getAverageByID(r.getMyID(), minimalRaters);
			if (average == 0) {
				continue;
			}
			else {
				ratings.add(new Rating(r.getMyID(), average));
			}
		}
		return ratings;
		
	}
	
	//helper method which returns the average movie rating ID if there are enough ratings
	private double getAverageByID(String nameID, int minimalRaters) {
		double totalScore = 0;
		FirstRatings firstR = new FirstRatings();
		int numOfRatings = firstR.numOfRatingsByMovie(myRaters, Integer.parseInt(nameID));
		if (numOfRatings < minimalRaters) {
			return 0.0;
		} else {
			for (Rater r : myRaters) {
				ArrayList<String> ratingsByCurrentRater = r.getItemsRated();
				double ratingOfCurrentMovie = r.getRating(nameID);
				if (ratingOfCurrentMovie < 0) {
					continue;
				} else {
					totalScore += ratingOfCurrentMovie;
				}
				System.out.println(ratingOfCurrentMovie);
			}
			System.out.println("totalScore " + totalScore + " | " + " num of ratings " + numOfRatings);
			double averageScore = totalScore / numOfRatings;
			return averageScore;
		}

	}
	

	@Override
	public String toString() {
		return "SecondRatings [myMovies=" + myMovies + ", myRaters=" + myRaters + "]";
	}
	
	
}
