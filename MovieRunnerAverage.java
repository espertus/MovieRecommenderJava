import java.util.ArrayList;

public class MovieRunnerAverage {
	public void printAverageRatings() {
		String movieFile = "data/ratedmovies_short.csv";
		String raterFile = "data/ratings_short.csv";
		SecondRatings sr = new SecondRatings(movieFile, raterFile);
	//System.out.println(sr.getMovieSize());
	//System.out.println(sr.getRaterSize());
	//System.out.println(sr.getAverageRatings(0).toString());
	
	
	ArrayList<Rating> ratings = sr.getAverageRatings(3);
	for (Rating r : ratings) {
		String title = sr.getTitle(r.getItem());
		System.out.println(title);
		System.out.println(r.getValue());
		System.out.println();
	}
	
	}

}
