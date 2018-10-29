import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.io.IOException;
import java.io.File;
import org.apache.commons.csv.*;

public class FirstRatings {
    public ArrayList<Movie> loadMovies(){

        ArrayList<Movie> movieList = new ArrayList<Movie>();
        String fileName = "data/ratedmovies_short.csv";
        //File csvData = new File(fileName);
     
       
        // open file input stream
        try {
        	 FileReader csvData = new FileReader(fileName);
        	 
             CSVParser parser = new CSVParser(csvData, CSVFormat.DEFAULT.withHeader());
             for (CSVRecord r : parser) {
            	 System.out.println(r.get("title"));
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