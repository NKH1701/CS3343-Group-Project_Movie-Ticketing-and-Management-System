package Scheduling;

import java.util.ArrayList;

public class ScheduleManagment {
	private ArrayList<Movie> movies;
	private Schedule scheduleStrategy;
	private ArrayList<ScheduleSlot> scheduleSlots;
	
	
	public ScheduleManagment(ArrayList<Movie> movies) {
		this.movies = movies;
		this.scheduleStrategy = new ProfitMaximizationSchedule(this.movies);
		this.scheduleSlots = new ArrayList<>(); 
		
	}
	
	public void changeSchedulingStrategies(String command) {
		if(command == "1") {
			scheduleStrategy =  new ProfitMaximizationSchedule(movies);
		}
		else {
			scheduleStrategy =  new BalancedSchedule(movies);
		}
	}
	
	public String showSchedulingStrategies() {
		return scheduleStrategy.getSchedulingName();
	}
	
	public void setScheduleSlot() {
		scheduleStrategy.scheduling();
		this.scheduleSlots = scheduleStrategy.getScheduleSlots();
	}
	
	public ArrayList<ScheduleSlot> getScheduleSlot() {
		return scheduleSlots;
	}
}


