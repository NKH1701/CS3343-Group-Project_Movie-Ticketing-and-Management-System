package jasperPackage;
import java.util.List;

public class House implements Cloneable{
	private String houseNumber;
	private final SeatingPlan defaultSeatingPlan;
	private List<MovieSession> movieSessions;

	
//	private Map<Movie, SeatingPlan> movieSeatingPlan;
	
	public House(String roomNumber, int rows, int columns) {
		this.houseNumber = roomNumber;
		this.defaultSeatingPlan = new SeatingPlan(rows, columns);
	}
	
	public SeatingPlan getNewSeatingPlanForNewMovieSession() {
		return new SeatingPlan(defaultSeatingPlan.getRows(), defaultSeatingPlan.getColumns());
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
