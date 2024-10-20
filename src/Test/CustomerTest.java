package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.*;

import selectMovieModule.*;


public class CustomerTest {
	private Customer customer = Customer.getDummyAdultCustomer();
    private CinemaDatabase cinemaDatabase = CinemaDatabase.getInstance();
	
	@AfterEach
    public void setUp() throws CustomException {
        cinemaDatabase.resetDB();
    }
	
	@Test
	public void testCustomerStateForAgeReturnChildrenState() throws CustomException {
        CustomerState childrenState = Customer.customerStateForAge(10);
        CustomerState expected = Children.getInstance();
        assertEquals(expected, childrenState);
    }
	
	@Test
	public void testCustomerStateForAge_v2() throws CustomException {
        CustomerState adultState = Customer.customerStateForAge(20);
        CustomerState expected = Adult.getInstance();
        assertEquals(expected, adultState);
    }
	
	@Test
	public void testCustomerStateForAge_v3() throws CustomException {
        CustomerState elderlyState = Customer.customerStateForAge(70);
        CustomerState expected = Elderly.getInstance();
        assertEquals(expected, elderlyState);
    }
	
	@Test
	public void testCustomerStateForAge_v4() throws CustomException {
		Exception exception = assertThrows(CustomException.class, () -> Customer.customerStateForAge(-1));
		assertEquals("Invalid age.", exception.getMessage());
    }
	
	@Test
    public void testGetAvailableMoviesReturnsNonEmptyMap() throws CustomException {

        Movie movie = new Movie();
        cinemaDatabase.addMovie(movie);
        movie.addMovieSession(new MovieSession(movie));

        Map<String, Movie> availableMovies = customer.getAvailableMovies();

        assertFalse(availableMovies.isEmpty());
        assertEquals(1, availableMovies.size());
        assertTrue(availableMovies.containsValue(movie));
        
    }

    @Test
    public void testGetAvailableMoviesThrowsExceptionWhenNoMoviesAvailable() {

    	Movie movie = new Movie();
        cinemaDatabase.addMovie(movie);

        CustomException exception = assertThrows(CustomException.class, () -> {
            customer.getAvailableMovies();
        });

        assertEquals("Sorry! There are currently no movies scheduled for selection.", exception.getMessage());
    }

    @Test
    public void testGetMovieSessionsFromMovieReturnsCorrectSessions() throws CustomException {
    	Customer customer = Customer.getDummyAdultCustomer();
		CinemaDatabase cinemaDatabase = CinemaDatabase.getInstance();
		
    	Movie movie = new Movie();
    	cinemaDatabase.addMovie(movie);
    	MovieSession session = new MovieSession(movie);
    	movie.addMovieSession(session);
        
        Map<String, Movie> availableMovies = customer.getAvailableMovies();

        List<MovieSession> sessions = customer.getMovieSessionsFromMovie(availableMovies, "1");

        assertEquals(1, sessions.size());
        assertTrue(sessions.contains(session));
    }

    @Test
    public void testGetMovieSessionsFromMovieThrowsExceptionForInvalidInput() {
    	Customer customer = Customer.getDummyAdultCustomer();
		CinemaDatabase cinemaDatabase = CinemaDatabase.getInstance();
        Map<String, Movie> availableMovies = new HashMap<>();

        CustomException exception = assertThrows(CustomException.class, () -> {
            customer.getMovieSessionsFromMovie(availableMovies, "1");
        });

        assertEquals("Invalid input. Please select a valid movie option.", exception.getMessage());
    }

    @Test
    public void testBookSeatBooksSuccessfully() throws CustomException {
        MovieSession session = new MovieSession(new Movie());
        customer.addCachedSelectedMovieSession(session);
        String result = customer.bookSeat("A1");
        assertEquals("Seat \"A1\" has been booked.", result);
    }

    @Test
    public void testBookSeatThrowsExceptionForUnavailableSeat() throws CustomException {
    	MovieSession session = new MovieSession(new Movie());
        SeatingPlan seatingPlan = session.getSeats();
        customer.addCachedSelectedMovieSession(session);
        seatingPlan.bookSeat("A1");

        CustomException exception = assertThrows(CustomException.class, () -> {
            customer.bookSeat("A1");
        });

        assertEquals("Seat \"A1\" is not available.", exception.getMessage());
    }

    @Test
    public void testUndoBookSeatsReleasesBookedSeats() throws CustomException {
    	MovieSession session = new MovieSession(new Movie());
        SeatingPlan seatingPlan = session.getSeats();
        customer.addCachedSelectedMovieSession(session);
        customer.addCachedSelectedMovieSession(session);
        customer.addCachedSelectedSeats("A1");
        seatingPlan.bookSeat("A1");

        String result = customer.undoBookSeats();

        assertTrue(result.contains("Seat \"A1\" has been released."));

    }

    @Test
    public void testAddCachedSelectedSeatsAddsSeatsCorrectly() throws CustomException {
        boolean result = customer.addCachedSelectedSeats("A1");

        assertTrue(result);
        assertTrue(customer.getCachedSelectedSeats().contains("A1"));
    }

    @Test
    public void testAddCachedSelectedSeatsPreventsDuplicateSeats() {
        customer.addCachedSelectedSeats("A1");
        boolean result = customer.addCachedSelectedSeats("A1");

        assertFalse(result);
    }

    @Test
    public void testAddCachedSelectedMovieSessionCachesSessionCorrectly() throws CustomException {
        MovieSession session = new MovieSession(new Movie());
        boolean result = customer.addCachedSelectedMovieSession(session);

        assertTrue(result);
        assertEquals(session, customer.getCachedSelectedMovieSession());
    }

    @Test
    public void testAddCachedSelectedMovieSessionPreventsMultipleSessions() throws CustomException {
        MovieSession session1 = new MovieSession(new Movie());
        customer.addCachedSelectedMovieSession(session1);
        boolean result = customer.addCachedSelectedMovieSession(session1);

        assertFalse(result);
    }

    @Test
    public void testClearCachedSelectedSeatsClearsSeats() {
        customer.addCachedSelectedSeats("A1");
        boolean result = customer.clearCachedSelectedSeats();

        assertTrue(result);
        assertThrows(CustomException.class, () -> {
            customer.getCachedSelectedSeats();
        });
    }

    @Test
    public void testClearCachedSelectedMovieSessionClearsSession() throws CustomException {
        MovieSession session = new MovieSession(new Movie());
        customer.addCachedSelectedMovieSession(session);
        boolean result = customer.clearCachedSelectedMovieSession();

        assertTrue(result);
        assertThrows(CustomException.class, () -> {
            customer.getCachedSelectedMovieSession();
        });
    }
	
	

}
