import java.util.ArrayList;

public class FilterByMultipleCriteria implements Filter{
	private ArrayList<Filter> filters;
	
	public void addFilter(Filter f) {
		if (filters == null) {
			filters = new ArrayList<Filter>();
		}
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
