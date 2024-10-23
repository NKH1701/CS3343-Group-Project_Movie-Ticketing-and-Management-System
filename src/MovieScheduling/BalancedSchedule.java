package Scheduling;

import java.util.ArrayList;
import java.util.Collections;

public class BalancedSchedule extends Schedule {
	
	public BalancedSchedule(ArrayList<Movie> movies) {
		super("Balanced Scheduling", movies);
	}
	
	public void scheduling() {
		ArrayList<Movie> movies = getMovies();
		int theaterIDcount = 1;
		
		for(int i=0; i<4; i++) {
			String theater_id = Integer.toString(theaterIDcount);
			availableTheaters.put(new Theater(theater_id), 11 * 60);
			theaterIDcount++;
		}
			
		
	    int i = 0;
	    
        while(checkAvailableSlots(movies)) {
            Movie movie = movies.get(i);
            
            Theater nextAvailableTheater = findNextAvailableTheater();
            scheduleMovie(movie, nextAvailableTheater);
            
            i++;
            if(i == movies.size()) {
            	i = 0;
            	break;
            }       	           
        }
        
        Collections.sort(movies);
        
        while(checkAvailableSlots(movies)) {
            Movie movie = movies.get(i);
            
            Theater nextAvailableTheater = findNextAvailableTheater();
            scheduleMovie(movie, nextAvailableTheater);
            
            i++;
            if(i == movies.size()) {
            	i = 0;
            }       	           
        }   
	}
	
}
