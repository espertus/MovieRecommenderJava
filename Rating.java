public class Rating {
    private String item; //description of the item being rated, will be IMDB ID in this case
    private double value; //the rating

    public Rating(String item, double value) {
        this.item = item;
        this.value = value;
    }

    public String getItem() {
        return item;
    }

    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "item:'" + item + '\'' +
                ", value:" + value;
    }

    public int compareTo(Rating other){
        if (value < other.value) return -1;
        if (value > other.value) return 1;
        return 0;
    }
}