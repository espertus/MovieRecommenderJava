import java.util.ArrayList;

public class MovieRunnerWithFilters {
	public void printAverageRatings() {
		System.out.println("running printAverageRatings");

		int minimalRaters = 35;
		String movieFile = "data/ratedmoviesfull.csv";
		String raterFile = "data/ratings.csv";
		ThirdRatings tr = new ThirdRatings(raterFile);
		MovieDatabase.initialize(movieFile);

		System.out.println("number of movies in database: " + MovieDatabase.size());
		System.out.println("number of raters used: " + tr.getRaterSize());
		System.out.println("number of movies found " + tr.getAverageRatings(minimalRaters).size());

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
		GenreFilter genreFilter = new GenreFilter(genre);

		String raterFile = "data/ratings.csv";
		ThirdRatings tr = new ThirdRatings(raterFile);

		ArrayList<Rating> averagesWithFilter = tr.getAverageRatingsByFilter(minimalRaters, genreFilter);
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

		String raterFile = "data/ratings.csv";
		ThirdRatings tr = new ThirdRatings(raterFile);
		YearsAfterFilter yaf = new YearsAfterFilter(2000);
		ArrayList<Rating> averagesWithFilter = tr.getAverageRatingsByFilter(minimalRaters, yaf);


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
		String raterFile = "data/ratings.csv";
		ThirdRatings tr = new ThirdRatings(raterFile);
		MinutesFilter mf = new MinutesFilter(05, 135);
		
		int minimalRaters = 5;

		ArrayList<Rating> averagesWithFilter = tr.getAverageRatingsByFilter(minimalRaters, mf);
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
		String raterFile = "data/ratings.csv";
		ThirdRatings tr = new ThirdRatings(raterFile);
		String directors = "Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack";
		DirectorsFilter df = new DirectorsFilter(directors);
		
		int minimalRaters = 4;

		ArrayList<Rating> averagesWithFilter = tr.getAverageRatingsByFilter(minimalRaters, df);
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
		AllFilters allFilters = new AllFilters();
		int year = 1990;
		String genre = "Drama";
		int minimalRaters = 8;
		
		allFilters.addFilter(new YearsAfterFilter(year));
		allFilters.addFilter(new GenreFilter(genre));
		
		String raterFile = "data/ratings.csv";
		ThirdRatings tr = new ThirdRatings(raterFile);
		ArrayList<Rating> averagesWithFilter = tr.getAverageRatingsByFilter(minimalRaters, allFilters);
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
		AllFilters allFilters = new AllFilters();

		String directors = "Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack";
		int minMinutes = 90;
		int maxMinutes = 180;

		allFilters.addFilter(new DirectorsFilter(directors));

		allFilters.addFilter(new MinutesFilter(minMinutes, maxMinutes));

		String raterFile = "data/ratings.csv";
		ThirdRatings tr = new ThirdRatings(raterFile);

		ArrayList<Rating> averagesWithFilter = tr.getAverageRatingsByFilter(minimalRaters, allFilters);
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

}
