package Scheduling;

import java.util.ArrayList;
import java.util.Collections;


public class Main {
	 public static void main(String[] args) {
	        ArrayList<Movie> movies = new ArrayList<>();
	        movies.add(new Movie("Movie A", "x", 120, 100, 15));
	        movies.add(new Movie("Movie B", "x", 150, 90, 14));
	        movies.add(new Movie("Movie C", "x", 130, 60, 3));
	        movies.add(new Movie("Movie D", "x", 120, 60, 15));
	        movies.add(new Movie("Movie E", "x", 125, 75, 12));
	        movies.add(new Movie("Movie F", "x", 90, 65, 10));
	        movies.add(new Movie("Movie G", "x", 90, 66, 10));
	        movies.add(new Movie("Movie H", "x", 90, 65, 11));
	        movies.add(new Movie("Movie I", "x", 90, 65, 12));
	        movies.add(new Movie("Movie J", "x", 90, 65, 13));

	        ScheduleManagment scheduleManagment = new ScheduleManagment(movies);
	        
	        scheduleManagment.setScheduleSlot();
	        ArrayList<ScheduleSlot> scheduleSlot = scheduleManagment.getScheduleSlot();
	        Collections.sort(scheduleSlot); 
	        
	        for(ScheduleSlot slot : scheduleSlot) {
	        	System.out.println(slot);
	        }
	    }
}
