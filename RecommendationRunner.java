import java.util.ArrayList;

public class RecommendationRunner implements Recommender {

    public ArrayList<String> getItemsToRate() {
        // TODO Auto-generated method stub
        
        Filter yearFilter = new YearsAfterFilter(2000);
        Filter genreFilter = new GenreFilter("Comedy");
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(yearFilter);
        allFilters.addFilter(genreFilter);
        FourthRatings fr = new FourthRatings();
        ArrayList<Rating> highestRatedWithFilter = fr.getAverageRatingsByFilter(0, allFilters);
        
        ArrayList<String> movieIDs = new ArrayList<String>();

        for (int i = 0; i < 20; i++) {
            movieIDs.add(highestRatedWithFilter.get(i).getItem());
        }
        
        return movieIDs;
    }

    @Override
    public void printRecommendationsFor (String webRaterID) {
        FourthRatings fr = new FourthRatings();
        ArrayList<Rating> similarRatings = fr.getSimilarRatings(webRaterID, 1, 1);
        System.out.println("<table> <th>Poster</th><th>Movie</th> <th>Genre</th> <th>Year</th><br>");
        System.out.println("<style>img { height: 80px; }</style>");
        for (int i = 0; i <20; i++) {
            if ((i) >= similarRatings.size()){
                return;
            }
            String imageURL = MovieDatabase.getPoster(similarRatings.get(i).getItem());
            System.out.println("<tr>");
            System.out.println("<td> <img src = " + imageURL + " height = \"40\"> </td>");
            System.out.println("<td>" + MovieDatabase.getTitle(similarRatings.get(i).getItem()) + "</td>");
            System.out.println("<td>" + MovieDatabase.getGenres(similarRatings.get(i).getItem()) + "</td>");
            System.out.println("<td>" + MovieDatabase.getYear(similarRatings.get(i).getItem()) + "</td>");
            System.out.println("</tr>");
        }
        System.out.println("</table>");

    }

}
