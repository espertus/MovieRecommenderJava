
public class FilterByDirector implements Filter{
	private String[] directors;

	FilterByDirector(String givenDirectors){
		directors = givenDirectors.split(",");
	}
	
	@Override
	public boolean satisfies(String id) {
		String movieDirectors = MovieDatabase.getDirector(id);
		for (String s : directors) {
				if (movieDirectors.indexOf(s) != -1) {
				return true;
			}
		}
		return false;
	}
}
