package Scheduling;

public class ScheduleSlot implements Comparable<ScheduleSlot> {
	private Movie movie;
    private String startTime;
    private String endTime;
    private Theater theater;
    
    public ScheduleSlot(Movie movie, String startTime, String endTime, Theater theater) {
        this.movie = movie;
        this.startTime = startTime;
        this.endTime = endTime;
        this.theater = theater;
    }
    
    public String getStartTime() {
        return startTime;
    }
    
    public int compareTo(ScheduleSlot other) {
        int thisStartTime = convertTimeToMinutes(this.startTime);
        int otherStartTime = convertTimeToMinutes(other.getStartTime());
        return Integer.compare(thisStartTime, otherStartTime);
    }

    // Helper method to convert HH:mm time to minutes from midnight
    private static int convertTimeToMinutes(String time) {
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        return hours * 60 + minutes;
    }
    
    
    public String toString() {
        return "Movie: " + movie.getTitle() + ", Start: " + startTime + ", End: " + endTime + ", Theater: " + theater.getID();
    }  
}
