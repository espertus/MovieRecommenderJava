import java.util.ArrayList;

public class MovieRunnerWithFilters {
	public void printAverageRatings() {
		String movieFile = "data/ratedmovies_short.csv";
		String raterFile = "data/ratings_short.csv";
		ThirdRatings tr = new ThirdRatings(raterFile);
		MovieDatabase.initialize(movieFile);

		System.out.println("number of movies in database: " + MovieDatabase.size());
		System.out.println("number of raters used: " + tr.getRaterSize());
		System.out.println("number of movies found " + tr.getAverageRatings(1).size());
		
		for (Rating r : tr.getAverageRatings(1)) {

			System.out.println(r.getValue() 
					+ " | " +
					MovieDatabase.getTitle(r.getItem()));

		}

	}
	public void printAverageRatingsByGenre() {
		System.out.println("running printAverageRatingsByGenre");

		String genre = "Crime";
		GenreFilter genreFilter = new GenreFilter(genre);
		
		String raterFile = "data/ratings_short.csv";
		ThirdRatings tr = new ThirdRatings(raterFile);
		
		ArrayList<Rating> averagesWithFilter = tr.getAverageRatingsByFilter(1, genreFilter);
		for (Rating r : averagesWithFilter) {
			System.out.println(r.getValue()
					+ " | " +
					MovieDatabase.getTitle(r.getItem()));
		}
	}
	
	public void printAverageRatingsByYear() {
		System.out.println("running printAverageRatingsByYear");
		String raterFile = "data/ratings_short.csv";
		ThirdRatings tr = new ThirdRatings(raterFile);
		YearsAfterFilter yaf = new YearsAfterFilter(2000);
		ArrayList<Rating> averagesWithFilter = tr.getAverageRatingsByFilter(1, yaf);
		
		for (Rating r : averagesWithFilter) {
			System.out.println(r.getValue()
					+ " | " +
					MovieDatabase.getTitle(r.getItem()));
		}
		
	}

}
