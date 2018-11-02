import java.util.ArrayList;

public class AllFilters implements Filter{
	private ArrayList<Filter> filters;
	
	public void addFilter(Filter f) {
		filters.add(f);
	}
	
	public boolean satisfies(String id) {
		for (Filter f : filters) {
			if (!f.satisfies(id)) {
				return false;
			}
		}
		return true;
	}
}
