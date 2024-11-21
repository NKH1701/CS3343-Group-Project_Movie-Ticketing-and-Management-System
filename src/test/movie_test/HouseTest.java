package test.movie_test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import release.movie.House;
import release.movie.Movie;
import release.movie.MovieSession;
import release.movie.SeatingPlan;
import release.exception.ExInvalidSeatingPlan;

public class HouseTest {

    @Test
    public void testDefaultConstructor() {
        try {
            House house = new House();
            assertEquals(0, house.getHouseNumber());
            assertNotNull(house.getNewSeatingPlanForNewMovieSession());
        } catch (ExInvalidSeatingPlan e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    public void testParameterizedConstructor() {
        try {
            House house = new House(1, 10, 10);
            assertEquals(1, house.getHouseNumber());
            SeatingPlan seatingPlan = house.getNewSeatingPlanForNewMovieSession();
            assertNotNull(seatingPlan);
            assertEquals(10, seatingPlan.getRows());
            assertEquals(10, seatingPlan.getColumns());
        } catch (ExInvalidSeatingPlan e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    public void testAddMovieSession() {
        try {
            House house = new House(1, 10, 10);
            Movie movie = new Movie("Inception", "Sci-Fi", 148, 10.0, 9.5, "IIA", "English", "English");
            MovieSession session = new MovieSession(movie, "00:00", "03:00", house);
            house.addMovieSession(session);
            assertNotNull(house.getMovieSessions());
            assertEquals(1, house.getMovieSessions().size());
            assertTrue(house.getMovieSessions().contains(session));
        } catch (ExInvalidSeatingPlan e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    public void testCompareTo() {
        try {
            House house1 = new House(1, 10, 10);
            House house2 = new House(2, 10, 10);
            assertTrue(house1.compareTo(house2) < 0);
            assertTrue(house2.compareTo(house1) > 0);
            assertEquals(0, house1.compareTo(house1));
        } catch (ExInvalidSeatingPlan e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    public void testToString() {
        try {
            House house = new House(1, 10, 10);
            String expected = "House number: 1";
            assertEquals(expected, house.toString());
        } catch (ExInvalidSeatingPlan e) {
            fail("Exception should not be thrown");
        }
    }
}
