import java.util.ArrayList;
import java.util.Scanner;
public class MovieRecommendationRunner {

	public void launchMovieRecommender() {
		MovieDatabase.initialize();
		Scanner reader = new Scanner(System.in);
		String userGenrePreference = getGenrePreference(reader);
		ArrayList<String> itemsToRate = getItemsToRate(userGenrePreference);
		getRatingsFromUser(itemsToRate, reader);
		
		
		//print recommendations for new user
		printRecommendations("5555");
		reader.close();
	}

	public void getRatingsFromUser (ArrayList<String> itemsToRate, Scanner reader) {
	//ask user to rate movies
			System.out.println("Please rate the following movies from 1-10. \nIf you have never seen the movie, enter 0 and we will ask you to rate a different movie." );
			int count = 0;
			int numToAsk = Math.min(itemsToRate.size(), 10);
			for (int i = 0; i < numToAsk; i++) {
				if (count == 10) {
					break;
				} 
				
				if (i > itemsToRate.size()-1) {
					System.out.println("Sorry, we don't have enough films in that genre for you to review. Please re-run.");
					return;
				}

				String current = itemsToRate.get(i);
				System.out.println(MovieDatabase.getTitle(current) + " | " + MovieDatabase.getYear(current) + " | Genre(s): " + MovieDatabase.getGenres(current));	
				String userRatingString = reader.nextLine();

				while (!inputSatisfiesRequirements(userRatingString)) {
					System.out.println("Please enter an integer 1 - 10");
					userRatingString = reader.nextLine();				
				}

				int userRating = Integer.parseInt(userRatingString);
				if (userRating == 0) {
					System.out.println("Skipping " + MovieDatabase.getTitle(itemsToRate.get(i)) + "...\n");
					continue;
				} 

				//5555 is an arbitrary new userID
				RaterDatabase.addRaterRating("5555", itemsToRate.get(i), userRating);
				count ++;

			}
	}

	//ask the user what kind of genre they want to watch
	public String getGenrePreference(Scanner reader) {
		ArrayList<String> allGenres = MovieDatabase.getGenres();
		String genres = allGenres.toString();
		genres = genres.replaceAll("\\[", "").replaceAll("\\]","");
		System.out.println("What kind of movie are you in the mood for? Our genres are: \n" + genres);
		String preference = reader.nextLine();

		preference = preference.toLowerCase();  
		preference = preference.replaceAll("/[^-a-zA-Z0-9]+/", "");
		while (!genres.contains(preference)) {
			System.out.println("Please enter one of the genres from our list.");
			preference = reader.nextLine();
		}

		//reader.close();
		//Can't close this reader because it closes System.in as well and causes my later Scanners to fail. Leaving comment here my own education.
		//https://stackoverflow.com/questions/13042008/java-util-nosuchelementexception-scanner-reading-user-input
		return preference;
	}

	//returns an ArrayList of Strings which represent movie Titles which will be presented to the user to rate
	public ArrayList<String> getItemsToRate(String genre) { 

		//ask the user what genre they feel like watching
		Filter genreFilter = new FilterByGenre(genre);


		//THIS LINE IS TAKING FOREVER
		ArrayList<Rating> highestRatedWithFilter = MovieDatabase.getAverageRatingsByFilter(0, genreFilter);


		ArrayList<String> movieIDs = new ArrayList<String>();
		int countTo = highestRatedWithFilter.size();
		for (int i = 0; i < countTo; i++) {
			movieIDs.add(highestRatedWithFilter.get(i).getItem());
		}

		return movieIDs;
	}

	//print recommendations for the new user
	public void printRecommendations (String raterID) {
		System.out.println();
		System.out.println("---------------------------------");
		System.out.println("Thanks for creating a profile. We will now suggest movies for you.");
		System.out.println("These movies are suggested based on other users' ratings, giving those who review movies most similarly to you a greater weight.");
		System.out.println();

		SimilarityScores fr = new SimilarityScores();
		ArrayList<Rating> similarRatings = fr.getSimilarRatings(raterID, 1, 1);

		if (similarRatings.get(0).getValue() == -1) {
			System.out.println("Sorry, we could not recommend anything for you.");
			return;
		}
		for (int i = 0; i <20; i++) {
			if ((i) >= similarRatings.size()){
				break;
			}
			System.out.println(MovieDatabase.getTitle(similarRatings.get(i).getItem()) + " | " + MovieDatabase.getGenres(similarRatings.get(i).getItem()));
		}
	}

	private boolean inputSatisfiesRequirements(String m) {
		if (!isInteger(m)) {
			return false;
		} else {
			int n = Integer.parseInt(m);
			if (n < 0 || n > 10) {
				return false;
			}
		}
		return true;
	}

	public boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (Exception e) {
			return false;

		}
	}
}
