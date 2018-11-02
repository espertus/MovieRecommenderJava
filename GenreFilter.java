
public class GenreFilter implements Filter{
	private String myFilter;
	
	public GenreFilter(String filter) {
		myFilter = filter;
	}
	
	@Override
	public boolean satisfies(String id) {
		return MovieDatabase.getGenres(id).contains(myFilter);
	}
}

	
