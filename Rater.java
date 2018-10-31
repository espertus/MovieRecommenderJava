import java.util.ArrayList;

public class Rater {
    private String myID;
    private ArrayList<Rating> myRatings;

    Rater(String myID){
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
        if (myRatings.contains(item)){
            int index = myRatings.indexOf(item);
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
}