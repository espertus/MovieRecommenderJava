import java.util.ArrayList;
import java.io.FileReader;
import java.io.IOException;
import org.apache.commons.csv.*;
import java.util.HashMap;
import java.util.HashSet;

// This method should process every record from the CSV file whose name is filename
// and return an ArrayListof type Rater with all the rater data from the file.

public class FirstRatings {

	//read Movies from a CSV file
	public ArrayList<Movie> loadMovies( String fileName ){

		ArrayList<Movie> movieList = new ArrayList<Movie>();
		//File csvData = new File(fileName);


		// open file input stream
		try {
			FileReader csvData = new FileReader(fileName);

			CSVParser parser = new CSVParser(csvData, CSVFormat.DEFAULT.withHeader());
			for (CSVRecord r : parser) {
				Movie currMovie = new Movie(
						r.get("id"),
						r.get("title"),
						Integer.parseInt(r.get("year")),
						r.get("genre"),
						r.get("director"),
						r.get("country"),
						Integer.parseInt(r.get("minutes")),
						r.get("poster")
						);
				movieList.add(currMovie);
			}
			parser.close();
		}

		catch (java.io.FileNotFoundException e){
			System.out.println("ERROR, MOVIE FILE NOT FOUND");
		}
		catch (IOException e){
			System.out.println("IOEXCEPTION");
		}

		return movieList;
	}

	//returns the number of movies which are classified as a certain genre
	public int moviesInGenre(ArrayList<Movie> movies, String genre) {
		int count = 0;
		for (Movie m : movies) {	
			String currGenre = m.getGenre();
			if (currGenre.contains(genre)){
				count++;
			}
		}
		return count;
	}

	//returns the number of movies longer than a given number of movies
	public int moviesLongerThan(ArrayList<Movie> movies, int givenLength) {
		int count = 0;
		for (Movie m : movies) {	
			int currLength = m.getMinutes();
			if (currLength > givenLength) {
				count++;
			}
		}
		return count;
	}

	//prints the highest number of movies directed by any director and the names of directors who have directed that many movies
	public void getDirectors(ArrayList<Movie> movies) {
		HashMap <String, Integer> directorsHM = new HashMap <String, Integer>();

		for (Movie m : movies) {
			String currDirectors = m.getDirector();
			if (currDirectors.indexOf(", ") != -1) {
				String[] currDirectorsArray = currDirectors.split(", ");
				for (int i = 0; i < currDirectorsArray.length; i++) {
					addDirectorsToHashMap(directorsHM, currDirectorsArray[i]);
				}
			} else {
				addDirectorsToHashMap(directorsHM, currDirectors);
			}
		}
		//print all the directors
		
		//print the most movies directed by any one or more directors
		int mostMovies = biggestMax(directorsHM);
		System.out.println("The highest number of movies directed is: " + mostMovies);

		//print the names of the directors which have directed the most movies
		ArrayList<String>directorsWithMost = directorsWithMost(directorsHM, mostMovies);
		System.out.println("The director()s who directed this many movies is:");
		for (String s : directorsWithMost) {
			System.out.println(s);
		}
	}

	//integer of the most movies directed by any person
	public int biggestMax(HashMap <String, Integer> directorsHM) {
		int biggest = -1;
		for (String key : directorsHM.keySet()) {
			int currCount = directorsHM.get(key);
			if (currCount > biggest) {
				biggest = currCount;
			}
		}
		return biggest;	
	}

	//return an ArrayList with the directors that directed the most movies
	public ArrayList<String> directorsWithMost(HashMap <String, Integer> directorsHM, int biggestMax){
		ArrayList<String> directors = new ArrayList<String>();
		for (String key : directorsHM.keySet()) {
			if (directorsHM.get(key) == biggestMax) {
				directors.add(key);
			}
		}
		return directors;
	}

	//helper method for directorsWithMost
	public void addDirectorsToHashMap(HashMap <String, Integer> directorsHM, String currDirectors) {
		if (!directorsHM.containsKey(currDirectors)) {
			directorsHM.put(currDirectors, 1);
		} else {
			int currValue = directorsHM.get(currDirectors);
			directorsHM.put(currDirectors, currValue + 1);
		}
	}

	//read Raters and Ratings from a CSV file
	public ArrayList<Rater> loadRaters( String fileName){
		ArrayList<Rater> allRatersAL = new ArrayList<Rater>();

		try {

			FileReader csvData = new FileReader(fileName);
			CSVParser parser = new CSVParser(csvData, CSVFormat.DEFAULT.withHeader());
			String firstRater = null;
			int count = 0;


			for (CSVRecord r :parser) {
				String currRaterID = r.get("rater_id");
				String movieID = r.get("movie_id");
				double rating = Double.parseDouble(r.get("rating"));
				if (firstRater == null) {
					firstRater = currRaterID;
					Rater currRater = addRater(currRaterID, movieID, rating);
					allRatersAL.add(currRater);
					continue;
				} 
				if (currRaterID.equals(firstRater)) {	
					allRatersAL.get(count).addRating(r.get("movie_id"),Double.parseDouble(r.get("rating")));
					continue;
				} 

				else {
					Rater currRater = addRater(currRaterID, movieID, rating);
					allRatersAL.add(currRater);
				}
				firstRater = currRaterID;
				count ++;
			}
			parser.close();
		}

		catch (java.io.FileNotFoundException e){
			System.out.println("ERROR, RATINGS FILE NOT FOUND");
		}

		catch (IOException e){
			System.out.println("IOEXCEPTION AT RATINGS FILE");
		}

		return allRatersAL;
	}

	//helper method for loadRaters
	public Rater addRater(String raterID, String movieID, double rating) {
		EfficientRater currRater = new EfficientRater(raterID);
		currRater.addRating(movieID, rating);
		return currRater;
	}

	//returns an integer representing how many movies a given user has rated
	public int numOfRatingsByUser(ArrayList<Rater> raters, int userID) {
		int count = 0;
		for (Rater r : raters) {
			int currentID = Integer.parseInt(r.getMyID());
			if (currentID == userID) {
				count += r.numRatings();
			}
		}
		return count;
	}
	
	//returns an integer representing the UserID of the user who has rated the most movies
	//this did not get correct answer per coursera - look into
	public int mostRatingUser(ArrayList<Rater> raters) {
		int biggestSoFar = -1;
		for (Rater r : raters) {
			int currID = Integer.parseInt(r.getMyID());
			int numOfRatings = numOfRatingsByUser(raters, currID);
//			System.out.println("user " + currID + " has left "+ numOfRatings);
			if (numOfRatings > biggestSoFar) {
				biggestSoFar = currID;
			}
		}
		return biggestSoFar;
	}
	
	//returns an integer representing the maximum number of ratings by any one user
	public int mostMoviesRated(ArrayList<Rater> raters) {
			int biggestSoFar = -1;
			for (Rater r : raters) {
				int currID = Integer.parseInt(r.getMyID());
				int numOfRatings = numOfRatingsByUser(raters, currID);
				if (numOfRatings > biggestSoFar) {
					biggestSoFar = numOfRatings;
				}
			}
			return biggestSoFar;
		}
	
	//returns an integer representing how many users have rated a particular movie
	public int numOfRatingsByMovie(ArrayList<Rater> ratings, int movieID) {
		int count = 0;

		for (Rater r : ratings) {
			ArrayList<String> itemsRated = r.getItemsRated();
			for (String s : itemsRated) {
				int currMovie = Integer.parseInt(s);
				if (currMovie == movieID) {
					count++;
				}
			}
		}
		return count;
	}

	//returns the number of unique movies rated in the file
	public int numOfUniqueMoviesRated(ArrayList<Rater> ratings) {

		HashSet<String> map = new HashSet<String>();

		for (Rater r : ratings) {
			ArrayList<String> itemsRated = r.getItemsRated();
			for (String currentMovie : itemsRated) {
				map.add(currentMovie);
			}
		}
		return map.size();
	}

	
	//tester method for loadRaters
	public void testLoadRaters() {
		String fileName = "data/ratings.csv";
		ArrayList<Rater> raters = loadRaters(fileName);
		System.out.println(raters.size());

		int userID = 193;
		System.out.println("The user " + userID + " has rated this many movies: " + numOfRatingsByUser(raters, userID));
		
		int movieID = 1798709;
		System.out.println("The number of ratings for movie " + movieID + " is " + numOfRatingsByMovie(raters, movieID));
		
		
		
		System.out.println("The number of unique movies rated was " + numOfUniqueMoviesRated(raters));
		
		System.out.println("The UserID who does the most rating is " + mostRatingUser(raters));
		
		System.out.println("The number of ratings this user left was: " + mostMoviesRated(raters));
		
	}

	//tester method for loading and analyzing movies
	public void testLoadMovies(){
		String fileName = "data/ratedmoviesfull.csv";
		ArrayList<Movie> movies = loadMovies(fileName);
		System.out.println("Movies in this file: " + movies.size());

		String genre = "Comedy";
		int moviesInComedy = moviesInGenre(movies, genre);
		System.out.println("Movies in the genre " + genre + ": " + moviesInComedy);

		int minutesToTest = 150;
		int longerthan150 = moviesLongerThan(movies, minutesToTest);
		System.out.println("Movies longer than " + minutesToTest + " minutes: " +longerthan150);

		getDirectors(movies);
	}

}



