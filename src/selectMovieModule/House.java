package selectMovieModule;
import java.util.List;

public class House implements Cloneable{
	private String houseNumber;
	private final SeatingPlan defaultSeatingPlan;
	private List<MovieSession> movieSessions;

	
	public House() throws CustomException {
		this.defaultSeatingPlan = new SeatingPlan();
		this.houseNumber = "houseNum";
	}
	
	public House(String roomNumber, int rows, int columns) throws CustomException {
		this.defaultSeatingPlan = new SeatingPlan(rows, columns);
		this.houseNumber = roomNumber;
	}
	
	public SeatingPlan getNewSeatingPlanForNewMovieSession() {
		try {
			return new SeatingPlan(defaultSeatingPlan.getRows(), defaultSeatingPlan.getColumns());
		} catch (CustomException e) {
			return null;
		}
	}

	public String getHouseNumber() {
		return this.houseNumber;
	}
	
//	@Override
//	public String toString() {
//		StringBuilder result = new StringBuilder();
//		result.append("Room number: " + this.houseNumber);
//		List<Movie> scheduledMovies = getScheduledMovies();
//		if (scheduledMovies == null) {
//			return result.toString();
//		} else {
//			result.append("\n");
//		}
//		for (Movie movie : scheduledMovies) {
//			result.append("Movie: " + movie.getName() + "\n");
//			result.append(movieSeatingPlan.get(movie).displaySeatingPlan());
//		}
//		return result.toString();
//	}
}
