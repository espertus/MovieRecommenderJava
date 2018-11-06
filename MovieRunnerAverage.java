import java.util.ArrayList;

public class MovieRunnerAverage {
	public void printAverageRatings() {
		
		OldMethods sr = new OldMethods();
		//System.out.println(sr.getMovieSize());
		//System.out.println(sr.getRaterSize());
		//System.out.println(sr.getAverageRatings(0).toString());
		ArrayList<Rating> moreThanX = sr.getAverageRatings(12);
		//System.out.println(moreThanX.toString());
		String idOfLowest = sr.getIdOfLowestRated(moreThanX);
		String titleofLowest = MovieDatabase.getTitle(idOfLowest);
		System.out.println(titleofLowest);
		//System.out.println("More than 50 " + morethan50.size());

//		ArrayList<Rating> ratings = sr.getAverageRatings(3);
//		for (Rating r : ratings) {
//			String title = sr.getTitle(r.getItem());
//			System.out.println(title);
//			System.out.println(r.getValue());
//			System.out.println();
//		}
	}

	public void getAverageRatingOneMovie() {
	
		OldMethods sr = new OldMethods();
		String id = MovieDatabase.getID("Vacation");
		//System.out.println(id);
		if (!id.equals("Movie not found")){
			System.out.println(sr.getAverageByID(id, 0));
		}
	}
	
	

}
