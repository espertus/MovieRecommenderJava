import java.util.ArrayList;

public class PlainRater implements Rater {
	
   

	private String myID;
    private ArrayList<Rating> myRatings;

    PlainRater(String myID){
        this.myID = myID;
        myRatings = new ArrayList<Rating>();
    }

    //creates a new rating and adds to myRatings
    public void addRating(String item, double rating){
        Rating newRating = new Rating(item, rating);
        myRatings.add(newRating);
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
        for (Rating r : myRatings){
            String title = r.getItem();
            myItems.add(title);
        }
        return myItems;
    }
    
    @Override
   	public String toString() {
   		return "PlainRater [myID=" + myID + ", myRatings=" + myRatings + "]";
   	}

}