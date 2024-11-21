package test.movie_test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import release.movie.SeatingPlan;
import release.exception.ExInvalidSeatingPlan;

public class SeatingPlanTest {

    @Test
    public void testDefaultConstructor() {
        try {
            SeatingPlan seatingPlan = new SeatingPlan();
            assertEquals(10, seatingPlan.getRows());
            assertEquals(10, seatingPlan.getColumns());
            assertEquals(100, seatingPlan.getTotalSeats());
            assertEquals(100, seatingPlan.getRemainingSeats());
            assertNotNull(seatingPlan.getSeatingPlan());
        } catch (ExInvalidSeatingPlan e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    public void testParameterizedConstructor() {
        try {
            SeatingPlan seatingPlan = new SeatingPlan(15, 15);
            assertEquals(15, seatingPlan.getRows());
            assertEquals(15, seatingPlan.getColumns());
            assertEquals(225, seatingPlan.getTotalSeats());
            assertEquals(225, seatingPlan.getRemainingSeats());
            assertNotNull(seatingPlan.getSeatingPlan());
        } catch (ExInvalidSeatingPlan e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    public void testInvalidConstructorParameters() {
        assertThrows(ExInvalidSeatingPlan.class, () -> {
            new SeatingPlan(4, 4);
        });
        assertThrows(ExInvalidSeatingPlan.class, () -> {
            new SeatingPlan(21, 21);
        });
    }

    @Test
    public void testMinusOneSeat() {
        try {
            SeatingPlan seatingPlan = new SeatingPlan(10, 10);
            seatingPlan.minusOneSeat();
            assertEquals(99, seatingPlan.getRemainingSeats());

            // Should not be less than 0
            for (int i = 0; i < 101; i++) {
                seatingPlan.minusOneSeat();
            }
            assertEquals(0, seatingPlan.getRemainingSeats());

        } catch (ExInvalidSeatingPlan e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    public void testAddBackOneSeat() {
        try {
            SeatingPlan seatingPlan = new SeatingPlan(10, 10);
            seatingPlan.minusOneSeat();
            seatingPlan.addbackOneSeat();
            assertEquals(100, seatingPlan.getRemainingSeats());

            // should not be 101, capped at 100
            seatingPlan.addbackOneSeat();
            assertEquals(100, seatingPlan.getRemainingSeats());

        } catch (ExInvalidSeatingPlan e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    public void testToString() {
        try {
            SeatingPlan seatingPlan = new SeatingPlan(10, 10);
            String expected = "Seating plan with 10 rows and 10 columns.";
            assertEquals(expected, seatingPlan.toString());
        } catch (ExInvalidSeatingPlan e) {
            fail("Exception should not be thrown");
        }
    }
}
