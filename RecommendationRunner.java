import java.util.ArrayList;

public class RecommendationRunner implements Recommender {

    public ArrayList<String> getItemsToRate() {
        // TODO Auto-generated method stub
        
        Filter yearFilter = new FilterByYearSince(2000);
        Filter genreFilter = new FilterByGenre("Comedy");
        FilterByMultipleCriteria allFilters = new FilterByMultipleCriteria();
        allFilters.addFilter(yearFilter);
        allFilters.addFilter(genreFilter);
        AverageRatings fr = new AverageRatings();
        ArrayList<Rating> highestRatedWithFilter = fr.getAverageRatingsByFilter(0, allFilters);
        
        ArrayList<String> movieIDs = new ArrayList<String>();

        for (int i = 0; i < 20; i++) {
            movieIDs.add(highestRatedWithFilter.get(i).getItem());
        }
        
        return movieIDs;
    }

    @Override
    public void printRecommendationsFor (String webRaterID) {
    	SimilarityScores fr = new SimilarityScores();
        ArrayList<Rating> similarRatings = fr.getSimilarRatings(webRaterID, 1, 1);
        System.out.println("<style>.poster { height: 80px; } table {text-align: center; border-style: groove padding: 40px;} td {padding: 30px}</style>");

        System.out.println("<table> <th>Poster</th><th>Movie</th> <th>Genre</th> <th>Year</th><br>");
        for (int i = 0; i <20; i++) {
            if ((i) >= similarRatings.size()){
                break;
            }
            String imageURL = MovieDatabase.getPoster(similarRatings.get(i).getItem());
            System.out.println("<tr>");
            System.out.println("<td> <img class = \"poster\" src = " + imageURL + "> </td>");
            System.out.println("<td>" + MovieDatabase.getTitle(similarRatings.get(i).getItem()) + "</td>");
            System.out.println("<td>" + MovieDatabase.getGenres(similarRatings.get(i).getItem()) + "</td>");
            System.out.println("<td>" + MovieDatabase.getYear(similarRatings.get(i).getItem()) + "</td>");
            System.out.println("</tr>");
        }
        System.out.println("</table>");

    }

}
