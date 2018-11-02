import java.util.ArrayList;

public class MovieRecommender {
    public static void main(String args[]){
        //FirstRatings tester = new FirstRatings();
        //tester.testLoadMovies();
        //tester.testLoadRaters();
    	String movieFile = "data/ratedmovies_short.csv";
		String raterFile = "data/ratings_short.csv";
		MovieRunnerAverage mra = new MovieRunnerAverage();
		mra.printAverageRatings();
    	//MovieRunnerAverage tester2 = new MovieRunnerAverage();
    	//tester2.printAverageRatings();
		mra.getAverageRatingOneMovie();
    }
    
    
}