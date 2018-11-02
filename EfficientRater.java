import java.util.ArrayList;
import java.util.HashMap;

public class EfficientRater {
	private String myID;
	//String: Key: movieID, Value: Rating
    private HashMap<String, Rating> myRatings;

    EfficientRater(String myID){
        this.myID = myID;
        myRatings = new HashMap<String, Rating>();
    }

    //creates a new rating and adds to myRatings
    public void addRating(String item, double rating){
        Rating newRating = new Rating(item, rating);
        myRatings.put(item, newRating);
    }

    public String getMyID() {
        return myID;
    }

    //returns the rating of a movie if it is in myRatings
    public double getRating(String item) {
    	ArrayList allContained = getItemsRated();
        if (allContained.contains(item)){
            int index = allContained.indexOf(item);
            return myRatings.get(index).getValue();
        }
        return -1;
    }

    public int numRatings(){
        return myRatings.size();
    }

    public ArrayList<String> getItemsRated(){
        ArrayList<String> myItems = new ArrayList<String>();
        for (String key : myRatings.keySet()){
            myItems.add(key);
        }
        return myItems;
    }
    
    @Override
   	public String toString() {
   		return "Rater [myID=" + myID + ", myRatings=" + myRatings + "]";
   	}
}
