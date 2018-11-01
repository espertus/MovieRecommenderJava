
public class MovieRunnerAverage {
	public void printAverageRatings() {
		String movieFile = "data/ratedmovies_short.csv";
		String raterFile = "data/ratings_short.csv";
		SecondRatings sr = new SecondRatings(movieFile, raterFile);
	System.out.println(sr.getMovieSize());
	System.out.println(sr.getRaterSize());
	}

}
