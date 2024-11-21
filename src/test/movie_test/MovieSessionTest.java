package test.movie_test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import release.exception.ExInvalidSeatingPlan;
import release.movie.Movie;
import release.movie.MovieSession;
import release.movie.House;

public class MovieSessionTest {

    @Test
    public void testConstructor() throws ExInvalidSeatingPlan {
        Movie movie = new Movie("Inception", "Sci-Fi", 148, 10.0, 9.5, "IIA", "English", "English");
        House house = new House(1, 10, 10);
        MovieSession session = new MovieSession(movie, "00:00", "03:00", house);

        assertEquals(movie, session.getMovie());
        assertEquals("00:00", session.getStartTime());
        assertEquals("03:00", session.getEndTime());
        assertEquals(house, session.getHouse());
        assertNotNull(session.getSeats());
    }

    @Test
    public void testCompareTo() throws ExInvalidSeatingPlan {
        Movie movie = new Movie("Inception", "Sci-Fi", 148, 10.0, 9.5, "IIA", "English", "English");
        House house = new House(1, 10, 10);
        House house2 = new House(2, 10, 10);
        MovieSession session1 = new MovieSession(movie, "00:00", "03:00", house);
        MovieSession session2 = new MovieSession(movie, "03:15", "06:15", house);
        MovieSession session3 = new MovieSession(movie, "00:00", "02:30", house2);

        assertTrue(session1.compareTo(session2) < 0);
        assertTrue(session2.compareTo(session1) > 0);
        assertEquals(0, session1.compareTo(session3));
    }

    @Test
    public void testToString() throws ExInvalidSeatingPlan {
        Movie movie = new Movie("Inception", "Sci-Fi", 148, 10.0, 9.5, "IIA", "English", "English");
        House house = new House(1, 10, 10);
        MovieSession session = new MovieSession(movie, "00:00", "03:00", house);

        String expected = "Movie: Inception, House: 1, Start Time: 00:00, End Time: 03:00";
        assertEquals(expected, session.toString());
    }
}
