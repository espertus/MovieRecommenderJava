import java.util.ArrayList;

public class MovieRecommender {
    public static void main(String args[]){
//        FirstRatings tester = new FirstRatings();
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
    	
    	
//    	FourthRatings fr = new FourthRatings();
//    	System.out.println(fr.getAverageByID("1798709", 7));
//    	
//    	ArrayList<Rating> averageRatings = fr.getAverageRatings(0);
//    	for (Rating r : averageRatings) {
//    		System.out.println(r.getItem());
//    	}
////    	
//    	MovieRunnerSimilarRatings mrws = new MovieRunnerSimilarRatings();
//    	mrws.printAverageRatings();
//    	System.out.println();
//    	mrws.printAverageRatingsByYearAndGenre();
//    	
    	FourthRatings fr = new FourthRatings();
    	fr.getSimilarRatings("65", 20, 5);
    }
    
    public static void testMovieDatabase() {
    	ThirdRatings tr = new ThirdRatings();
    	ArrayList<Rating> ratings = tr.getAverageRatings(0);

    	for (Rating r : ratings) {
    		Double value = r.getValue();
    		if (value > 9) {
        		System.out.println(r.getItem());
    		}
    	}
    }
    
    
}