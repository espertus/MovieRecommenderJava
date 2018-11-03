import java.util.ArrayList;
import java.util.HashMap;

public class RaterDatabase {
	//Key: raterID Value: Ratings by user
	private static HashMap<String, Rater> ourRaters;

	public static void initialize(String fileName) {
		if (ourRaters ==null) {
			ourRaters = new HashMap<String, Rater>();
			loadRaters(fileName);
		}
	}

	public static void initialize() {
		if (ourRaters ==null) {
			ourRaters = new HashMap<String, Rater>();
			loadRaters("data/ratings_short.csv");
		}
	}
	private static void loadRaters(String fileName) {
		FirstRatings fr = new FirstRatings();
		ArrayList<Rater> list = fr.loadRaters(fileName);
		for (Rater r : list) {
			ourRaters.put(r.getMyID(), r);
		}
	}

	public static void addRatings(String fileName) {
		loadRaters(fileName);
	}

	public static void addRaterRating(String raterID, String movieID, double rating) {
		Rater rater =  null;
        if (ourRaters.containsKey(raterID)) {
            rater = ourRaters.get(raterID); 
        } 
        else { 
            rater = new EfficientRater(raterID);
            ourRaters.put(raterID,rater);
         }
         rater.addRating(movieID,rating);
	}

	public static Rater getRater(String id) {
		return ourRaters.get(id);
	}

	public static ArrayList<Rater> getRaters(){
		ArrayList<Rater> raters = new ArrayList<Rater>(ourRaters.values());
		return raters;
	}

	public static int size() {
		return ourRaters.size();
	}
}
