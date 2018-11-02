import java.util.ArrayList;

public interface Rater {

    //creates a new rating and adds to myRatings
    public void addRating(String item, double rating);
    
    	public String getMyID();
    	
    //returns the rating of a movie if it is in myRatings
    public double getRating(String item);
    
    public int numRatings();

    public ArrayList<String> getItemsRated();
    
    @Override
   	public String toString();
}
