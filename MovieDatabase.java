import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.commons.csv.*;


public class MovieDatabase {



	//Key: movieId, Value: Movie Object
	private static HashMap<String, Movie> ourMovies;

	public static void initialize(String movieFile) {
		if (ourMovies ==null) {
			ourMovies = new HashMap<String, Movie>();
			loadMovies(movieFile);
		}
	}

	public static void initialize() {
		if (ourMovies ==null) {
			ourMovies = new HashMap<String, Movie>();
			loadMovies("data/ratedmoviesfull.csv");
		}
	}
	
	private static void loadMovies(String fileName) {
		ArrayList<Movie> movieList = new ArrayList<Movie>();
		try {
			FileReader csvData = new FileReader(fileName);
			CSVParser parser = new CSVParser(csvData, CSVFormat.DEFAULT.withHeader());
			for (CSVRecord r : parser) {
				Movie currMovie = new Movie(
						r.get("id"),
						r.get("title"),
						Integer.parseInt(r.get("year")),
						r.get("genre"),
						r.get("director"),
						r.get("country"),
						Integer.parseInt(r.get("minutes")),
						r.get("poster")
						);
				movieList.add(currMovie);
			}
			parser.close();
		}

		catch (java.io.FileNotFoundException e){
			System.out.println("ERROR, MOVIE FILE NOT FOUND");
		}
		catch (IOException e){
			System.out.println("IOEXCEPTION");
		}

		for (Movie m : movieList) {
			ourMovies.put(m.getId(), m);
		}
	}
	
	public static String getID (String title) {
		initialize();
		for (String s : ourMovies.keySet()) {
			String currentTitle = ourMovies.get(s).getTitle();
			if (title.equals(currentTitle)) {
				return ourMovies.get(s).getId();
			}
		}
		return "Movie not found";
	}
	
	public static boolean containsID(String id) {
		initialize();
		return ourMovies.containsKey(id);
	}
	public static int getYear(String id) {
		initialize();
		return ourMovies.get(id).getYear();
	}

	public static String getGenres(String id) {
		initialize();
		return ourMovies.get(id).getGenre();
	}

	public static String getTitle(String id) {
		initialize();
		return ourMovies.get(id).getTitle();
	}

	public static Movie getMovie(String id) {
		initialize();
		return ourMovies.get(id);
	}

	public static String getPoster(String id) {
		initialize();
		return ourMovies.get(id).getPoster();
	}

	public static int getMinutes(String id) {
		initialize();
		return ourMovies.get(id).getMinutes();
	}

	public static String getCountry(String id) {
		initialize();
		return ourMovies.get(id).getCountry();
	}

	public static String getDirector(String id) {
		initialize();
		return ourMovies.get(id).getDirector();
	}

	public static int size() {
		return ourMovies.size();
	}

	public static ArrayList<String> filterBy(Filter f) {
		initialize();
		ArrayList<String> list = new ArrayList<String>();
		for(String id : ourMovies.keySet()) {
			if (f.satisfies(id)) {
				list.add(id);
			}
		}
		return list;
	}

	public static ArrayList<String> getMovies(){
		initialize();
		ArrayList<String> list = new ArrayList<String>();
		for(String id : ourMovies.keySet()) {
			list.add(id);
		}
		return list;
	}
	
	

}
