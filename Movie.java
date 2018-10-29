public class Movie {
    private String id;
    private String title;
    private int year;
    private String genre; //one or more, separated by commas
    private String director; //one or more, separated by commas
    private String country; //country made in
    private int minutes;

    private String poster; //URL to a poster, or "N/A" if none exists

    public Movie(String id, String title, int year, String genre, String director, String country, int minutes, String poster) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.genre = genre;
        this.director = director;
        this.country = country;
        this.minutes = minutes;
        this.poster = poster;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public String getGenre() {
        return genre;
    }

    public String getDirector() {
        return director;
    }

    public String getCountry() {
        return country;
    }

    public int getMinutes() {
        return minutes;
    }

    public String getPoster() {
        return poster;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", genre='" + genre + '\'' +
                ", director='" + director + '\'' +
                ", country='" + country + '\'' +
                ", minutes=" + minutes +
                ", poster='" + poster + '\'' +
                '}';
    }
}
