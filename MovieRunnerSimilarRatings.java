import java.util.ArrayList;

public class MovieRunnerSimilarRatings {
	

	public void printSimilarRatings() {
		SimilarityScores fr = new SimilarityScores();
		ArrayList<Rating> similar = fr.getSimilarRatings("71", 20, 5);
		System.out.println(MovieDatabase.getTitle(similar.get(0).getItem()));
	}

	public void printSimilarRatingsByGenre() {
		SimilarityScores fr = new SimilarityScores();
		Filter f = new FilterByGenre("Mystery");
		ArrayList<Rating> similar = fr.getSimilarRatingsByFilter("964", 20, 5, f);
		System.out.println(MovieDatabase.getTitle(similar.get(0).getItem()));

	}

	public void printSimilarRatingsByDirector() {
		SimilarityScores fr = new SimilarityScores();
		Filter f = new FilterByDirector("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh");
		ArrayList<Rating> similar = fr.getSimilarRatingsByFilter("120", 10, 2, f);
		System.out.println(MovieDatabase.getTitle(similar.get(0).getItem()));

	}

	public void printSimilarRatingsByGenreAndMinutes() {
		SimilarityScores fr = new SimilarityScores();
		Filter minutes = new FilterByMinutes(80,160);
		Filter genre = new FilterByGenre("Drama");
		FilterByMultipleCriteria af = new FilterByMultipleCriteria();
		af.addFilter(minutes);
		af.addFilter(genre);
		ArrayList<Rating> similar = fr.getSimilarRatingsByFilter("168", 10, 3, af);
		System.out.println(MovieDatabase.getTitle(similar.get(0).getItem()));	
	}
	
	public void printSimilarRatingsByYearAndMinutes() {
		SimilarityScores fr = new SimilarityScores();
		Filter year = new FilterByYearSince(1975);
		Filter minutes = new FilterByMinutes(70,200);
		FilterByMultipleCriteria af = new FilterByMultipleCriteria();
		af.addFilter(minutes);
		af.addFilter(year);
		ArrayList<Rating> similar = fr.getSimilarRatingsByFilter("314", 10, 5, af);
		System.out.println(MovieDatabase.getTitle(similar.get(0).getItem()));
	}
}






