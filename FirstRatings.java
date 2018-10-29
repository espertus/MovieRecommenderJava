import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.apache.commons.*;

public class FirstRatings {
    public ArrayList<Movie> loadMovies(){

        ArrayList<Movie> movieList = new ArrayList<Movie>();
        String file = "src/data/ratedmovies_short.csv";
        BufferedReader br = null;
        String line = "";
        String delimiter = ",";

        // open file input stream
        try {

            br = new BufferedReader(new FileReader(file));
            int i = 0;
            while ((line = br.readLine()) != null) {

                String[] data = line.split(delimiter);
                for (int j = 0; j < 6; j++) {
                    System.out.println(j + " " + data[j] + "  ");
                }

            }
        }

        catch (java.io.FileNotFoundException e){
            System.out.println("ERROR, FILE NOT FOUND");
        }
        catch (IOException e){
            System.out.println("IOEXCEPTION");
        }

        return movieList;
    }
}