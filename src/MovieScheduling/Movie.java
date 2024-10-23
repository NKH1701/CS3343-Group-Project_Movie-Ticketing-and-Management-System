package Scheduling;

public class Movie implements Comparable<Movie>{
    private String title;
    private String genre;
    private int duration;
    private int ticketPrice;
    private int popularityScore;

    public Movie(String title, String genre, int duration, int ticketPrice, int popularityScore) {
        this.title = title;
        this.genre = genre;
        this.duration = duration;
        this.ticketPrice = ticketPrice;
        this.popularityScore = popularityScore;
    }

    // Getter for title
    public String getTitle() {
        return title;
    }

    // Getter for genre
    public String getGenre() {
        return genre;
    }

    // Getter for duration
    public int getDuration() {
        return duration;
    }

    // Getter for ticketPrice
    public int getTicketPrice() {
        return ticketPrice;
    }

    // Getter for popularityScore
    public int getPopularityScore() {
        return popularityScore;
    }
    
    public int compareTo(Movie other) {
        double thisScore = this.popularityScore * this.ticketPrice;
        double otherScore = other.popularityScore * other.ticketPrice;

        return Double.compare(otherScore, thisScore);
    }
}

