
public class FilterByMinutes implements Filter{
	private int minMinutes;
	private int maxMinutes;
	
	public FilterByMinutes(int min, int max) {
		minMinutes = min;
		maxMinutes = max;
	}
	
	public boolean satisfies (String id) {
		return ((MovieDatabase.getMinutes(id) >= minMinutes) 
				&& (MovieDatabase.getMinutes(id) <=maxMinutes));
	}
}
