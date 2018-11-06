//import java.util.ArrayList;

public class MovieRecommender {
	public static void main(String args[]){
		//        LoadMovies tester = new LoadMovies();
		//        tester.testLoadMovies();
		//        tester.testLoadRaters();

		//String movieFile = "data/ratedmovies_short.csv";
		//String raterFile = "data/ratings_short.csv";
		//MovieRunnerAverage mra = new MovieRunnerAverage();
		//mra.getAverageRatingOneMovie();

		//mra.printAverageRatings();
		//MovieRunnerAverage tester2 = new MovieRunnerAverage();
		//tester2.printAverageRatings();
		//testMovieDatabase();
		//    	MovieRunnerWithFilters mrwf = new MovieRunnerWithFilters();
		//    	mrwf.printAverageRatings();
		//    	System.out.println();
		//
		//    	mrwf.printAverageRatingsByGenre();
		//    	System.out.println();
		//    	mrwf.printAverageRatingsByYear();
		//    	
		//    	System.out.println();
		//    	mrwf.printAverageRatingsByGenre();
		//    	
		//    	System.out.println();
		//    	mrwf.printAverageRatingsByMinutes();
		//    	
		//    	System.out.println();
		//    	mrwf.printAverageRatingsByDirectors();
		//    	
		//    	System.out.println();
		//    	mrwf.printAverageRatingsByYearAndGenre();
		//    	
		//    	System.out.println();
		//    	mrwf.printAverageRatingsByDirectorsAndMinutes();


		//    	AverageRatings fr = new AverageRatings();
		//    	System.out.println(fr.getAverageByID("1798709", 7));
		//    	
		//    	ArrayList<Rating> averageRatings = fr.getAverageRatings(0);
		//    	for (Rating r : averageRatings) {
		//    		System.out.println(r.getItem());
		//    	}
		////    	
		//    	SimilarityScoresRunner mrws = new SimilarityScoresRunner();
		//    	mrws.printAverageRatings();
		//    	System.out.println();
		//    	mrws.printAverageRatingsByYearAndGenre();
		//    	
		//AverageRatings fr = new AverageRatings();
		//fr.getSimilarRatings("65", 20, 5);
//
SimilarityScoresRunner mrws = new SimilarityScoresRunner();
		System.out.println("running printSimilarRatings");
		mrws.printSimilarRatings();
		System.out.println();

		System.out.println("running printSimilarRatingsByGenre");
		mrws.printSimilarRatingsByGenre();
		System.out.println();

		System.out.println("running printSimilarRatingsByDirector");
		mrws.printSimilarRatingsByDirector();
		System.out.println();

		System.out.println("running printSimilarRatingsByGenreAndMinutes");
		mrws.printSimilarRatingsByGenreAndMinutes();
		System.out.println();


		System.out.println("running printSimilarRatingsByYearAndMinutes");
		mrws.printSimilarRatingsByYearAndMinutes();
		
//		RecommendationRunner rr = new RecommendationRunner();
//		rr.printRecommendationsFor("1");
	}
}