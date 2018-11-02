import java.util.ArrayList;

public class MovieRunnerAverage {
	public void printAverageRatings() {
		String movieFile = "data/ratedmoviesfull.csv";
		String raterFile = "data/ratings.csv";
		SecondRatings sr = new SecondRatings(movieFile, raterFile);
		//System.out.println(sr.getMovieSize());
		//System.out.println(sr.getRaterSize());
		//System.out.println(sr.getAverageRatings(0).toString());
		ArrayList<Rating> moreThanX = sr.getAverageRatings(12);
		//System.out.println(moreThanX.toString());
		String idOfLowest = sr.getIdOfLowestRated(moreThanX);
		String titleofLowest = sr.getTitle(idOfLowest);
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
		String movieFile = "data/ratedmoviesfull.csv";
		String raterFile = "data/ratings.csv";
		SecondRatings sr = new SecondRatings(movieFile, raterFile);
		String id = sr.getID("Vacation");
		//System.out.println(id);
		if (!id.equals("ID not found")){
			System.out.println(sr.getAverageByID(id, 0));
		}
	}
	
	

}
