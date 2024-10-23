package Scheduling;

import java.util.HashMap;
import java.util.ArrayList;

public abstract class Schedule {
	private String schedulingName;
	private ArrayList<Movie> movies;
	private ArrayList<ScheduleSlot> scheduleSlots;
	protected HashMap<Theater, Integer> availableTheaters;
	protected final int BUFFER_TIME = 15;
	
    public Schedule(String schedulingName, ArrayList<Movie> movies) {
    	this.schedulingName = schedulingName;
        this.movies = movies;
        this.scheduleSlots = new ArrayList<>(); 
        this.availableTheaters = new HashMap<>();
    }
    
	public abstract void scheduling();
	
	public ArrayList<Movie> getMovies() {
		return movies;
	}
	
	public void addScheduleSlots(ScheduleSlot scheduleSlot) {
		scheduleSlots.add(scheduleSlot);
	}
	
	public ArrayList<ScheduleSlot> getScheduleSlots() {
		return scheduleSlots;
	}

	public String getSchedulingName() {
        return schedulingName;
    }
	
	
	public int getBufferTime() {
		return BUFFER_TIME;
	}
	
	public void scheduleMovie(Movie movie, Theater theater) {
        int startTime = availableTheaters.get(theater);  
        int endTime = startTime + movie.getDuration();

        if (endTime <= 24 * 60) {
            String formattedStartTime = convertMinutesToTimeString(startTime);
            String formattedEndTime = convertMinutesToTimeString(endTime);

            ScheduleSlot slot = new ScheduleSlot(movie, formattedStartTime, formattedEndTime, theater);
            addScheduleSlots(slot);
           
            availableTheaters.put(theater, endTime + getBufferTime());
        }
    }
	
	public String convertMinutesToTimeString(int minutesSinceMidnight) {
        int hours = minutesSinceMidnight / 60;
        int minutes = minutesSinceMidnight % 60;
        return String.format("%02d:%02d", hours, minutes);
    }
	
	
	public Theater findNextAvailableTheater() {
        return availableTheaters.entrySet().stream()
                .min((entry1, entry2) -> entry1.getValue().compareTo(entry2.getValue()))  // Compare based on available time
                .get().getKey();
    }
	
	
	public boolean checkAvailableSlots(ArrayList<Movie> movies) {
	    // Check if any movie can still fit in the available time of any theater
	    for (Theater theater : availableTheaters.keySet()) {
	        int availableTime = availableTheaters.get(theater);
	        
	        // Check if any movie can fit in this theater
	        for (int i = 1; i < movies.size(); i++) {
	        	Movie movie = movies.get(i);
	        	
	            if (movie.getDuration() + availableTime <= 24 * 60) {
	                return true;  // There's still room for at least one movie
	            }
	        }
	    }
	    return false;  // No more room for any movie in any theater
	}	
}
