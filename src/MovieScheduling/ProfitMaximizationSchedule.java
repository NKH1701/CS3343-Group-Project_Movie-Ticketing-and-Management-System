package Scheduling;

import java.util.Collections;
import java.util.ArrayList;
import java.util.HashMap;

public class ProfitMaximizationSchedule extends Schedule {
	
	public ProfitMaximizationSchedule(ArrayList<Movie> movies) {
		super("Profit Maximization Scheduling", movies);
	}
	
	
	public void scheduling() {
		ArrayList<Movie> movies = getMovies();
		Collections.sort(movies);
		int theaterIDcount = 1;
		
		
		for(int i=0; i<4; i++) {
			String theater_id = Integer.toString(theaterIDcount);
			if(i < 2) {
				Movie topMovie = movies.get(i);
				scheduleTopMovie(topMovie, new Theater(theater_id));
			}
			
			else {
				availableTheaters.put(new Theater(theater_id), 11 * 60);
			}
			
			theaterIDcount++;
		}
		
	    
	    int i = 2;
	    
        while(checkAvailableSlots(movies)) {
            Movie movie = movies.get(i);
            
            Theater nextAvailableTheater = findNextAvailableTheater();
            scheduleMovie(movie, nextAvailableTheater);
            
            i++;
            if(i == movies.size()) {
            	i = 2;
            }       	           
        }	
	}
	
	
	private void scheduleTopMovie(Movie movie, Theater theater) {
		int availableTime = (24 - 11) * 60;  // Total minutes available in a day
	    int startTime = 11 * 60;  // Start time in minutes from midnight
	    
	    while (availableTime  >= movie.getDuration()) {
	    	int endTime = startTime + movie.getDuration();
	    	
	    	 String formattedStartTime = convertMinutesToTimeString(startTime);
	         String formattedEndTime = convertMinutesToTimeString(endTime);
	         
	         ScheduleSlot slot = new ScheduleSlot(movie, formattedStartTime, formattedEndTime, theater);
	         addScheduleSlots(slot);
	         
	         startTime = endTime + getBufferTime(); 
	         availableTime -= (movie.getDuration() + getBufferTime());
	    }
	}
}
