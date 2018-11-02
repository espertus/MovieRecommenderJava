import java.util.ArrayList;

public interface RaterI {


	//creates a new rating and adds to myRatings
	private void addRating(String item, double rating){
	}

	String getMyID();

	//returns the rating of a movie if it is in myRatings
	public double getRating(String item);

	public int numRatings();

	public ArrayList<String> getItemsRated();

	@Override
	public String toString();
}
