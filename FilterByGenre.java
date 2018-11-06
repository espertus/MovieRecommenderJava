
public class FilterByGenre implements Filter{
	private String myFilter;
	
	public FilterByGenre(String filter) {
		myFilter = filter;
	}
	
	@Override
	public boolean satisfies(String id) {
		return MovieDatabase.getGenres(id).contains(myFilter);
	}
}

	
