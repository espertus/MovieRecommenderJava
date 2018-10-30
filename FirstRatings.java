import java.util.ArrayList;
import java.util.Collections;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.io.IOException;
import java.io.File;
import org.apache.commons.csv.*;
import java.util.HashMap;

public class FirstRatings {
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
		}

		catch (java.io.FileNotFoundException e){
			System.out.println("ERROR, MOVIE FILE NOT FOUND");
		}
		catch (IOException e){
			System.out.println("IOEXCEPTION");
		}

		return movieList;
	}
	
	public ArrayList<Rater> loadRaters( String fileName){
		ArrayList<Rater> rater = new ArrayList<Rater>();
		try {
			FileReader csvData = new FileReader(fileName);
		}
		catch (java.io.FileNotFoundException e){
			System.out.println("ERROR, MOVIE FILE NOT FOUND");
		}
		return rater;
	}
	
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
			if (currLength >= givenLength) {
				count++;
			}
		}
		return count;
	}
	
	

	public void getDirectors(ArrayList<Movie> movies) {
		HashMap <String, Integer> directorsHM = new HashMap <String, Integer>();

		for (Movie m : movies) {
			String currDirectors = m.getDirector();
			if (currDirectors.indexOf(", ") != -1) {
				String[] currDirectorsArray = currDirectors.split(", ");
				for (int i = 0; i < currDirectorsArray.length; i++) {
					addToHashMap(directorsHM, currDirectorsArray[i]);
				}
			} else {
				addToHashMap(directorsHM, currDirectors);
			}
		}
		//print all the directors
		for (String key : directorsHM.keySet()) {
			System.out.println(key);
		}
		//print the most movies directed by any one or more directors
		int mostCommon = biggestMax(directorsHM);
		System.out.println(mostCommon);

		//print the names of the directors which have directed the most movies
		ArrayList<String>directorsWithMost = directorsWithMost(directorsHM, mostCommon);
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


	//check if a director is in the hashmap
	//either add it or increment value by 1
	public void addToHashMap(HashMap <String, Integer> directorsHM, String currDirectors) {
		if (!directorsHM.containsKey(currDirectors)) {
			directorsHM.put(currDirectors, 1);
		} else {
			int currValue = directorsHM.get(currDirectors);
			directorsHM.put(currDirectors, currValue + 1);
		}
	}

	
	
	public void testLoadMovies(){
		String fileName = "data/ratedmovies_short.csv";
		ArrayList<Movie> movies = loadMovies(fileName);
		System.out.println(movies.size());

		int moviesInComedy = moviesInGenre(movies, "Comedy");
		System.out.println(moviesInComedy);

		int longerthan150 = moviesLongerThan(movies, 150);
		System.out.println(longerthan150);

		getDirectors(movies);
	}
	
	
}

