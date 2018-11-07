import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import org.apache.commons.csv.*;


public class MovieDatabase {



	//Key: movieId, Value: Movie Object
	private static HashMap<String, Movie> ourMovies;

	public static void initialize(String movieFile) {
		if (ourMovies ==null) {
			ourMovies = new HashMap<String, Movie>();
			loadMovies(movieFile);
		}
	}

	public static void initialize() {
		if (ourMovies ==null) {
			ourMovies = new HashMap<String, Movie>();
			loadMovies("data/ratedmoviesfull.csv");
		}
	}

	private static void loadMovies(String fileName) {
		ArrayList<Movie> movieList = new ArrayList<Movie>();
		try {
			FileReader csvData = new FileReader(fileName);
			CSVParser parser = new CSVParser(csvData, CSVFormat.DEFAULT.withHeader());
			for (CSVRecord r : parser) {
				Movie currMovie = new Movie(
						r.get("id"),
						r.get("title"),
						Integer.parseInt(r.get("year")),
						r.get("genre").toLowerCase(),
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

		for (Movie m : movieList) {
			ourMovies.put(m.getId(), m);
		}
	}

	public static String getID (String title) {
		initialize();
		for (String s : ourMovies.keySet()) {
			String currentTitle = ourMovies.get(s).getTitle();
			if (title.equals(currentTitle)) {
				return ourMovies.get(s).getId();
			}
		}
		return "Movie not found";
	}

	public static boolean containsID(String id) {
		initialize();
		return ourMovies.containsKey(id);
	}

	public static int getYear(String id) {
		initialize();
		return ourMovies.get(id).getYear();
	}

	public static String getGenres(String id) {
		initialize();
		return ourMovies.get(id).getGenre();
	}

	public static String getTitle(String id) {
		initialize();
		return ourMovies.get(id).getTitle();
	}

	public static Movie getMovie(String id) {
		initialize();
		return ourMovies.get(id);
	}

	public static String getPoster(String id) {
		initialize();
		return ourMovies.get(id).getPoster();
	}

	public static int getMinutes(String id) {
		initialize();
		return ourMovies.get(id).getMinutes();
	}

	public static String getCountry(String id) {
		initialize();
		return ourMovies.get(id).getCountry();
	}

	public static String getDirector(String id) {
		initialize();
		return ourMovies.get(id).getDirector();
	}

	public static int size() {
		return ourMovies.size();
	}

	public static ArrayList<String> filterBy(Filter f) {
		initialize();
		ArrayList<String> list = new ArrayList<String>();
		for(String id : ourMovies.keySet()) {
			if (f.satisfies(id)) {
				list.add(id);
			}
		}
		return list;
	}

	public static ArrayList<String> getMovies(){
		initialize();
		ArrayList<String> list = new ArrayList<String>();
		for(String id : ourMovies.keySet()) {
			list.add(id);
		}
		return list;
	}

	public static ArrayList<String> getGenres(){
		initialize();

		//returns all of the possible genres from the MovieDatabase
		ArrayList<String> allGenres = new ArrayList<String>();
		for (String s : ourMovies.keySet()) {
			String genres = ourMovies.get(s).getGenre();
			String[] genresSplit = genres.split(", ");
			for (String t : genresSplit) {

				if (!allGenres.contains(t) && !t.equals("n/a")) {
					allGenres.add(t);
				}
			}
		}
		Collections.sort(allGenres);
		return allGenres;
	}


	//returns the average movie rating ID if there are enough ratings
	public static double getAverageByID(String nameID, int minimalRaters) {
		double totalScore = 0;
		int numOfRatings = numOfRatingsByMovie(RaterDatabase.getRaters(), Integer.parseInt(nameID));
		if (numOfRatings < minimalRaters) {
			return 0.0;
		} else {
			for (Rater r : RaterDatabase.getRaters()) {
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

	//returns an integer representing how many users have rated a particular movie
	public static  int numOfRatingsByMovie(ArrayList<Rater> ratings, int movieID) {
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

	//returns an ArrayList of all Ratings
	//contains the HashSet rating for every movie with at least n raters
	public static ArrayList<Rating> getAverageRatings(int minimalRaters){
		ArrayList<Rating> ratings = new ArrayList<Rating>();
		HashSet<Rating> ratingsHS = new HashSet<Rating>();
		for (Rater r : RaterDatabase.getRaters()) {
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
	
	public static ArrayList<Rating>  getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria){
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
