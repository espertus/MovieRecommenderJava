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

	//returns an ArrayList of Ratings
	//contains the average rating for every movie with at least n raters
	public ArrayList<Rating> getAverageRatings(int minimalRaters){
		ArrayList<Rating> ratings = new ArrayList<Rating>();
		for (Rater r : myRaters) {
			ArrayList<String> moviesInList = r.getItemsRated();
			for (String s : moviesInList) {
				double average = getAverageByID(s,minimalRaters);
				if (average == 0) {
					continue;
				}
				else if (!checkIfInList(ratings, s)){
					continue;
					
				}else{
					ratings.add(new Rating(s, average));
				}
			}
		}
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
				ArrayList<String> ratingsByCurrentRater = r.getItemsRated();
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

	//helper method for averageRatingwhich checks if the title is already in an arrayList
	private boolean checkIfInList(ArrayList<Rating> currList, String id) {
		for (Rating r : currList) {
			if (r.getItem().equals(id)) {
				return false;
			}
		}
		return true;
	}
	
	//returns the title of a corresponding id, if it is contained in myMovies
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
