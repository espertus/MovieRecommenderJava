import java.util.ArrayList;

public class MovieRunnerSimilarRatings {
	public void printAverageRatings() {
		System.out.println("running printAverageRatings");

		int minimalRaters = 0;
		String movieFile = "data/ratedmoviesfull.csv";
		String raterFile = "data/ratings.csv";
		FourthRatings fr = new FourthRatings(movieFile, raterFile);
		System.out.println("number of movies in database: " + MovieDatabase.size());
		System.out.println("number of raters used: " + RaterDatabase.size());
		System.out.println("number of movies found " + fr.getAverageRatings(minimalRaters).size());

		//		for (Rating r : tr.getAverageRatings(35)) {
		//
		//			System.out.println(r.getValue() 
		//					+ " | " +
		//					MovieDatabase.getTitle(r.getItem()));
		//
		//		}
	}
	public void printAverageRatingsByYearAndGenre(){
		System.out.println("running printAverageRatingsByYearAndGenre");
		AllFilters allFilters = new AllFilters();
		int year = 1990;
		String genre = "Drama";
		int minimalRaters = 8;

		allFilters.addFilter(new YearsAfterFilter(year));
		allFilters.addFilter(new GenreFilter(genre));

		//String raterFile = "data/ratings.csv";
		FourthRatings fr = new FourthRatings();
		ArrayList<Rating> averagesWithFilter = fr.getAverageRatingsByFilter(minimalRaters, allFilters);
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

	public void printSimilarRatings() {
		FourthRatings fr = new FourthRatings();
		ArrayList<Rating> similar = fr.getSimilarRatings("71", 20, 5);

		System.out.println(MovieDatabase.getTitle(similar.get(0).getItem()));
		//		for (Rating r : similar) {
		//			System.out.println(MovieDatabase.getTitle(r.getItem()));
		//		}
	}

	public void printSimilarRatingsByGenre() {
		FourthRatings fr = new FourthRatings();
		Filter f = new GenreFilter("Mystery");
		ArrayList<Rating> similar = fr.getSimilarRatingsByFilter("964", 20, 5, f);
		System.out.println(MovieDatabase.getTitle(similar.get(0).getItem()));

	}

	public void printSimilarRatingsByDirector() {
		FourthRatings fr = new FourthRatings();
		Filter f = new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh");
		ArrayList<Rating> similar = fr.getSimilarRatingsByFilter("120", 10, 2, f);
		System.out.println(MovieDatabase.getTitle(similar.get(0).getItem()));

	}

	public void printSimilarRatingsByGenreAndMinutes() {
		FourthRatings fr = new FourthRatings();
		Filter minutes = new MinutesFilter(80,160);
		Filter genre = new GenreFilter("Drama");
		AllFilters af = new AllFilters();
		af.addFilter(minutes);
		af.addFilter(genre);
		ArrayList<Rating> similar = fr.getSimilarRatingsByFilter("168", 10, 3, af);
		System.out.println(MovieDatabase.getTitle(similar.get(0).getItem()));	
	}
	
	public void printSimilarRatingsByYearAndMinutes() {
		FourthRatings fr = new FourthRatings();
		Filter year = new YearsAfterFilter(1975);
		Filter minutes = new MinutesFilter(70,200);
		AllFilters af = new AllFilters();
		af.addFilter(minutes);
		af.addFilter(year);
		ArrayList<Rating> similar = fr.getSimilarRatingsByFilter("314", 10, 5, af);
		System.out.println(MovieDatabase.getTitle(similar.get(0).getItem()));
	}
}






