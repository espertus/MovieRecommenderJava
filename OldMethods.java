//methods written for the Coursera, removed from other Classes during refactoring 
//all have been refactored to work currently, may not be usable in a meaningful way 

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class OldMethods {

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
			//				System.out.println("user " + currID + " has left "+ numOfRatings);
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

	//returns an ArrayList of Ratings
	//contains the average rating for every movie with at least n raters
	public ArrayList<Rating> getAverageRatings(int minimalRaters){
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

	//helper method for getAverageRatings which returns the average movie rating ID if there are enough ratings
	public double getAverageByID(String nameID, int minimalRaters) {
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

	public void printAverageRatings() {
		System.out.println("running printAverageRatings");

		int minimalRaters = 35;


		System.out.println("number of movies in database: " + MovieDatabase.size());
		System.out.println("number of raters used: " + RaterDatabase.size());
		System.out.println("number of movies found " + getAverageRatings(minimalRaters).size());

		//		for (Rating r : tr.getAverageRatings(35)) {
		//
		//			System.out.println(r.getValue() 
		//					+ " | " +
		//					MovieDatabase.getTitle(r.getItem()));
		//
		//		}

	}

	public void printAverageRatingsByGenre() {
		System.out.println("running printAverageRatingsByGenre");
		int minimalRaters = 20;


		String genre = "Comedy";
		FilterByGenre genreFilter = new FilterByGenre(genre);



		ArrayList<Rating> averagesWithFilter = getAverageRatingsByFilter(minimalRaters, genreFilter);
		System.out.println(averagesWithFilter.size());

		//		for (Rating r : averagesWithFilter) {
		//			System.out.println(r.getValue()
		//					+ " | " +
		//					MovieDatabase.getTitle(r.getItem())
		//					+ " | " + 
		//					MovieDatabase.getGenres((r.getItem())));
		//		}
	}

	public void printAverageRatingsByYear() {
		System.out.println("running printAverageRatingsByYear");
		int minimalRaters = 20;

		FilterByYearSince yaf = new FilterByYearSince(2000);
		ArrayList<Rating> averagesWithFilter = getAverageRatingsByFilter(minimalRaters, yaf);


		System.out.println(averagesWithFilter.size());
		//		for (Rating r : averagesWithFilter) {
		//			System.out.println(r.getValue()
		//					+ " | " +
		//					MovieDatabase.getTitle(r.getItem())
		//					+ " | " + 
		//					MovieDatabase.getYear(r.getItem()));
		//		}
	}

	public void printAverageRatingsByMinutes() {
		System.out.println("running printAverageRatingsByMinutes");
		FilterByMinutes mf = new FilterByMinutes(05, 135);

		int minimalRaters = 5;

		ArrayList<Rating> averagesWithFilter = getAverageRatingsByFilter(minimalRaters, mf);
		//		for (Rating r : averagesWithFilter) {
		//			System.out.println(r.getValue()
		//					+ " | " +
		//					MovieDatabase.getTitle(r.getItem())
		//					+ " | " + 
		//					MovieDatabase.getMinutes(r.getItem()));
		//		}
		System.out.println(averagesWithFilter.size());

	}

	public void printAverageRatingsByDirectors () {
		System.out.println("running printAverageRatingsByDirectors");
		String directors = "Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack";
		FilterByDirector df = new FilterByDirector(directors);

		int minimalRaters = 4;

		ArrayList<Rating> averagesWithFilter = getAverageRatingsByFilter(minimalRaters, df);
		//		for (Rating r : averagesWithFilter) {
		//			System.out.println(r.getValue()
		//					+ " | " +
		//					MovieDatabase.getTitle(r.getItem())
		//					+ " | " + 
		//					MovieDatabase.getDirector(r.getItem()));
		//		}	
		System.out.println(averagesWithFilter.size());

	}

	public void printAverageRatingsByYearAndGenre(){
		System.out.println("running printAverageRatingsByYearAndGenre");
		FilterByMultipleCriteria allFilters = new FilterByMultipleCriteria();
		int year = 1990;
		String genre = "Drama";
		int minimalRaters = 8;

		allFilters.addFilter(new FilterByYearSince(year));
		allFilters.addFilter(new FilterByGenre(genre));


		ArrayList<Rating> averagesWithFilter = MovieDatabase.getAverageRatingsByFilter(minimalRaters, allFilters);
		//		for (Rating r : averagesWithFilter) {
		//			System.out.println(r.getValue()
		//					+ " | " +
		//					MovieDatabase.getTitle(r.getItem())
		//					+ " | " +
		//					MovieDatabase.getYear(r.getItem()) 
		//					+ " | " + 
		//					MovieDatabase.getGenres(r.getItem()));
		//		}	
		System.out.println(averagesWithFilter.size());

	}

	public void printAverageRatingsByDirectorsAndMinutes(){
		int minimalRaters = 3;
		System.out.println("running printAverageRatingsByDirectorsAndMinutes");
		FilterByMultipleCriteria allFilters = new FilterByMultipleCriteria();
		String directors = "Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack";
		int minMinutes = 90;
		int maxMinutes = 180;
		allFilters.addFilter(new FilterByDirector(directors));
		allFilters.addFilter(new FilterByMinutes(minMinutes, maxMinutes));
		ArrayList<Rating> averagesWithFilter = MovieDatabase.getAverageRatingsByFilter(minimalRaters, allFilters);
		//		for (Rating r : averagesWithFilter) {
		//			System.out.println(r.getValue()
		//					+ " | " +
		//					MovieDatabase.getTitle(r.getItem())
		//					+ " | " +
		//					MovieDatabase.getMinutes(r.getItem()) 
		//					+ " | " + 
		//					MovieDatabase.getDirector(r.getItem()));
		//		}	
		System.out.println(averagesWithFilter.size());
	}


    public void printSimilarRatings() {
		SimilarityScores fr = new SimilarityScores();
		ArrayList<Rating> similar = fr.getSimilarRatings("71", 20, 5);
		System.out.println(MovieDatabase.getTitle(similar.get(0).getItem()));
	}

	public void printSimilarRatingsByGenre() {
		SimilarityScores fr = new SimilarityScores();
		Filter f = new FilterByGenre("Mystery");
		ArrayList<Rating> similar = fr.getSimilarRatingsByFilter("964", 20, 5, f);
		System.out.println(MovieDatabase.getTitle(similar.get(0).getItem()));
	}

	public void printSimilarRatingsByDirector() {
		SimilarityScores fr = new SimilarityScores();
		Filter f = new FilterByDirector("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh");
		ArrayList<Rating> similar = fr.getSimilarRatingsByFilter("120", 10, 2, f);
		System.out.println(MovieDatabase.getTitle(similar.get(0).getItem()));

	}

	public void printSimilarRatingsByGenreAndMinutes() {
		SimilarityScores fr = new SimilarityScores();
		Filter minutes = new FilterByMinutes(80,160);
		Filter genre = new FilterByGenre("Drama");
		FilterByMultipleCriteria af = new FilterByMultipleCriteria();
		af.addFilter(minutes);
		af.addFilter(genre);
		ArrayList<Rating> similar = fr.getSimilarRatingsByFilter("168", 10, 3, af);
		System.out.println(MovieDatabase.getTitle(similar.get(0).getItem()));	
	}
	
	public void printSimilarRatingsByYearAndMinutes() {
		SimilarityScores fr = new SimilarityScores();
		Filter year = new FilterByYearSince(1975);
		Filter minutes = new FilterByMinutes(70,200);
		FilterByMultipleCriteria af = new FilterByMultipleCriteria();
		af.addFilter(minutes);
		af.addFilter(year);
		ArrayList<Rating> similar = fr.getSimilarRatingsByFilter("314", 10, 5, af);
		System.out.println(MovieDatabase.getTitle(similar.get(0).getItem()));
	}
	
	public void getAverageRatingOneMovie() {
		
		OldMethods sr = new OldMethods();
		String id = MovieDatabase.getID("Vacation");
		//System.out.println(id);
		if (!id.equals("Movie not found")){
			System.out.println(sr.getAverageByID(id, 0));
		}
		else {
			System.out.println("Movie not found");
		}
	}
}
