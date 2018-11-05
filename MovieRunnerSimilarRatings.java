import java.util.ArrayList;

public class MovieRunnerSimilarRatings {
	

	public void printSimilarRatings() {
		FourthRatings fr = new FourthRatings();
		ArrayList<Rating> similar = fr.getSimilarRatings("71", 20, 5);
		System.out.println(MovieDatabase.getTitle(similar.get(0).getItem()));
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






