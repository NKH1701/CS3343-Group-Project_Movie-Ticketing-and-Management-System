package selectMovieModule;

import java.util.List;

public class MovieSession {
	Movie movie;
	Time showTime;
	House house;
	SeatingPlan seats;
	
	public MovieSession(Movie movie) throws CustomException {
		this.movie = movie;
		this.showTime = new Time(0, 0);
		this.house = new House();
		this.seats = house.getNewSeatingPlanForNewMovieSession();
	}
		
	public MovieSession(Movie movie, Time showTime, House house) {
		this.movie = movie;
		this.showTime = showTime;
		this.house = house;
		this.seats = house.getNewSeatingPlanForNewMovieSession();
	}
	
	public Movie getMovie() {return movie;}
	
	public Time getShowTime() {return showTime;}
	
	public House getHouse() {return house;}
	
	public SeatingPlan getSeats() {return seats;}
	
	public String displayBasicInfo() {
		return "house: " + house.getHouseNumber() + ", show time: " + showTime;
	}

	public static String displayMovieSessions(List<MovieSession> movieSessions) {
		StringBuilder result = new StringBuilder("The following sessions of \"")
				.append(movieSessions.get(0).getMovie().getName())
				.append("\" are available: \n");
		int count = 0;
		for (MovieSession movieSession : movieSessions) {
			count++;
			result.append(String.format("%3d)%-1s", count, " ") + movieSession.displayBasicInfo() + '\n');
		}
		return result.toString();
	}
}
