
public class FilterByYearSince implements Filter {
	private int myYear;
	
	public FilterByYearSince(int year) {
		myYear = year;
	}
	
	public boolean satisfies (String id) {
		return MovieDatabase.getYear(id) >= myYear;
	}
}
