package selectMovieModule;

import java.util.*;

public class Customer extends User{
	private String userName;
	private String password;
	private CustomerState state;
	private int age;
	private List<String> cachedSelectedSeats;
	private MovieSession cachedSelectedMovieSession;

	public static CustomerState customerStateForAge(int age) throws CustomException {
		if (age >= 0 && age < 18) return Children.getInstance();
		else if (age >= 18 && age < 65) return Adult.getInstance();
		else if (age >= 65) return Elderly.getInstance();
		else throw new CustomException("Invalid age.");
	}
	
	public Customer(String userName, String password, int age) throws CustomException {
		this.state = customerStateForAge(age);
		this.userName = userName;
		this.password = password;
	}
	
	public String getUserName() {return userName;}
    public String getPassword() {return password;}
	public int getAge(){return age;}
    public CustomerState getState() {return state;}
	
	// dummy for children customer
	public static Customer getDummyChildrenCustomer() {
        try {
            return new Customer("testChildren", "password", 10);
        } catch (CustomException e) {
			return null;
        }
    }
	
	// dummy for adult customer
	public static Customer getDummyAdultCustomer() {
        try {
            return new Customer("testAdult", "password", 20);
        } catch (CustomException e) {
            return null;
        }
    }
	
	// dummy for elderly customer
	public static Customer getDummyElderlyCustomer() {
        try {
            return new Customer("testElderly", "password", 70);
        } catch (CustomException e) {
            return null;
        }
    }

	public Map<String, Movie> getAvailableMovies() throws CustomException {
		CinemaDatabase cinemaDatabase = CinemaDatabase.getInstance();
		Map<String, Movie> queriedOptions = new LinkedHashMap<>();
		int count = 0;
		for (Movie movie : cinemaDatabase.getMovies()) {
			if (movie.getMovieSession() != null) {
				count++;
				queriedOptions.put(String.valueOf(count), movie);
			}
		}
		if (count == 0) {
			throw new CustomException("Sorry! There are currently no movies scheduled for selection.");
		}
		return queriedOptions;
	}
	
	public List<MovieSession> getMovieSessionsFromMovie(Map<String, Movie> queriedOptions, String input) throws CustomException {
		if (!queriedOptions.containsKey(input)) {
			throw new CustomException("Invalid input. Please select a valid movie option.");
		}
		return queriedOptions.get(input).getMovieSession();
	}
	
	public MovieSession getSelectedMovieSessionFromOptions(List<MovieSession> movieSessions, String input) {
		int index = Integer.parseInt(input);
		MovieSession selected = movieSessions.get(index-1);
		this.addCachedSelectedMovieSession(selected);
		return selected;
	}
	
	public String bookSeat(String option) throws CustomException {
		SeatingPlan seatingPlan = this.getCachedSelectedMovieSession().getSeats();
		if (!seatingPlan.isSeatAvailable(option)) {
			throw new CustomException("Seat \"" + option + "\" is not available.");
		}
		this.addCachedSelectedSeats(option);
		return seatingPlan.bookSeat(option);
	}

	public String undoBookSeats() throws CustomException {
		List<String> cachedSeats = getCachedSelectedSeats();
		SeatingPlan seatingPlan = getCachedSelectedMovieSession().getSeats();
		StringBuilder returnMsg = new StringBuilder("Due to payment failure:\n");
		for (String seat: cachedSeats){
			returnMsg.append(seatingPlan.releaseSeat(seat)).append("\n");
		}
		return returnMsg.toString();
	}

	public boolean addCachedSelectedSeats(String selectedSeats) {
		if (cachedSelectedSeats == null) cachedSelectedSeats = new ArrayList<>();
		// make sure no duplicate seat will be added
		if (cachedSelectedSeats.contains(selectedSeats)) return false;
		else{
			cachedSelectedSeats.add(selectedSeats);
			return true;
		}
	}

	// Limit only one movie session to be cached
	// if want to cache another session, must first process the first, clear it, before caching another session
	public boolean addCachedSelectedMovieSession(MovieSession cachedSelectedMovieSession) {
		if (this.cachedSelectedMovieSession == null){
			this.cachedSelectedMovieSession = cachedSelectedMovieSession;
			return true;
		}else return false;
	}
	
	public List<String> getCachedSelectedSeats() throws CustomException {
		if (cachedSelectedSeats == null) throw new CustomException("There are no cached selected seats.");
		return cachedSelectedSeats;
	}

	public MovieSession getCachedSelectedMovieSession() throws CustomException {
		if (cachedSelectedMovieSession == null) throw new CustomException("There are no cached movie session.");
		return cachedSelectedMovieSession;
	}

	// avoid repeatedly calling this function when the cache is already null
	public boolean clearCachedSelectedSeats() {
		if (cachedSelectedSeats != null) {
			cachedSelectedSeats = null;
			return true;
		}else return false;
	}

	// avoid repeatedly calling this function when the cache is already null
	public boolean clearCachedSelectedMovieSession() {
		if (cachedSelectedMovieSession != null){
			cachedSelectedMovieSession = null;
			return true;
		}else return false;
	}

	@Override
	public String toString() {
		return "Customer: " + userName + ", state: " + state.toString() + ", age: " + age;
	}

}
