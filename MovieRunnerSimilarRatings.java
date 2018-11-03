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
		
		String raterFile = "data/ratings.csv";
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
}
