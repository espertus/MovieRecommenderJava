
public class YearsAfterFilter implements Filter {
	private int myYear;
	
	public YearsAfterFilter(int year) {
		myYear = year;
	}
	
	public boolean satisfies (String id) {
		return MovieDatabase.getYear(id) >= myYear;
	}
}
