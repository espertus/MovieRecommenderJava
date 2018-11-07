import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

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
			loadRaters("data/ratings.csv");
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
		initialize();
		ArrayList<Rater> raters = new ArrayList<Rater>(ourRaters.values());
		return raters;
	}

	public static int size() {
		return ourRaters.size();
	}
	
	public static void loadRaters( String fileName){
		ArrayList<Rater> allRatersAL = new ArrayList<Rater>();

		try {

			FileReader csvData = new FileReader(fileName);
			CSVParser parser = new CSVParser(csvData, CSVFormat.DEFAULT.withHeader());
			String firstRater = null;
			int count = 0;


			for (CSVRecord r :parser) {
				String currRaterID = r.get("rater_id");
				String movieID = r.get("movie_id");
				double rating = Double.parseDouble(r.get("rating"));
				if (firstRater == null) {
					firstRater = currRaterID;
					Rater currRater = addRater(currRaterID, movieID, rating);
					allRatersAL.add(currRater);
					continue;
				} 
				if (currRaterID.equals(firstRater)) {	
					allRatersAL.get(count).addRating(r.get("movie_id"),Double.parseDouble(r.get("rating")));
					continue;
				} 

				else {
					Rater currRater = addRater(currRaterID, movieID, rating);
					allRatersAL.add(currRater);
				}
				firstRater = currRaterID;
				count ++;
			}
			parser.close();
		}

		catch (java.io.FileNotFoundException e){
			System.out.println("ERROR, RATINGS FILE NOT FOUND");
		}

		catch (IOException e){
			System.out.println("IOEXCEPTION AT RATINGS FILE");
		}

		for (Rater r : allRatersAL) {
			ourRaters.put(r.getMyID(), r);
		}
		}
	
	//helper method for loadRaters
	public static Rater addRater(String raterID, String movieID, double rating) {
		EfficientRater currRater = new EfficientRater(raterID);
		currRater.addRating(movieID, rating);
		return currRater;
	}

}
