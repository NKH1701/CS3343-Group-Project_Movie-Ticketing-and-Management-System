package test.movie_test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import release.movie.BookSeatService;
import release.movie.SeatingPlan;
import release.exception.ExInvalidSeat;

public class BookSeatServiceTest {

    private BookSeatService bookSeatService;
    private SeatingPlan seatingPlan;

    @BeforeEach
    public void setUp() throws Exception {
        bookSeatService = BookSeatService.getInstance();
        seatingPlan = new SeatingPlan(10, 10);
    }

    @Test
    public void testBookSeatSuccess_V1() throws ExInvalidSeat {
        assertTrue(bookSeatService.bookSeat(seatingPlan, "A1"));
        assertEquals("X", seatingPlan.getSeatingPlan().get(0).get(0));
        assertEquals(99, seatingPlan.getRemainingSeats());
    }

    @Test
    public void testBookSeatSuccess_V2() throws ExInvalidSeat {
        assertTrue(bookSeatService.bookSeat(seatingPlan, "A10"));
    }

    @Test
    public void testBookSeatSuccess_V3() throws ExInvalidSeat {
        assertTrue(bookSeatService.bookSeat(seatingPlan, "J10"));
    }

    @Test
    public void testBookSeatFailure() throws ExInvalidSeat {
        bookSeatService.bookSeat(seatingPlan, "A1");
        assertFalse(bookSeatService.bookSeat(seatingPlan, "A1"));
        // won't be 98 due to above booking failure
        assertEquals(99, seatingPlan.getRemainingSeats());
    }

    @Test
    public void testReleaseSeatSuccess() throws ExInvalidSeat {
        bookSeatService.bookSeat(seatingPlan, "A1");
        // before release, seat is X
        assertEquals("X", seatingPlan.getSeatingPlan().get(0).get(0));
        assertTrue(bookSeatService.releaseSeat(seatingPlan, "A1"));
        // after release, seat changes back from X to O
        assertEquals("O", seatingPlan.getSeatingPlan().get(0).get(0));
        assertEquals(100, seatingPlan.getRemainingSeats());
    }

    @Test
    public void testReleaseSeatFailure() {
        // if seat was not previously booked, cannot be released, so return false
        assertFalse(bookSeatService.releaseSeat(seatingPlan, "A1"));
        assertEquals(100, seatingPlan.getRemainingSeats());
    }

    @Test
    public void testInvalidSeatBooking_V1() {
        // fails option.length() < 2
        assertThrows(ExInvalidSeat.class, () -> {
            bookSeatService.bookSeat(seatingPlan, "A");
        });
    }

    @Test
    public void testInvalidSeatBooking_V2() {
        // fails option.length() > 3
        assertThrows(ExInvalidSeat.class, () -> {
            bookSeatService.bookSeat(seatingPlan, "A100");
        });
    }

    @Test
    public void testInvalidSeatBooking_V3() {
        // fails !Character.isAlphabetic(option.charAt(0))
        assertThrows(ExInvalidSeat.class, () -> {
            bookSeatService.bookSeat(seatingPlan, "11");
        });
    }

    @Test
    public void testInvalidSeatBooking_V4() {
        // fails Character.isAlphabetic(option.charAt(1))
        assertThrows(ExInvalidSeat.class, () -> {
            bookSeatService.bookSeat(seatingPlan, "AA");
        });
    }

    @Test
    public void testInvalidSeatBooking_V5() {
        // fails row < 0
        assertThrows(ExInvalidSeat.class, () -> {
            bookSeatService.bookSeat(seatingPlan, "A0");
        });
    }

    @Test
    public void testInvalidSeatBooking_V6() {
        // fails row >= seatingPlan.getRows()
        assertThrows(ExInvalidSeat.class, () -> {
            bookSeatService.bookSeat(seatingPlan, "A11");
        });
    }

    @Test
    public void testInvalidSeatBooking_V7() {
        // fails col >= seatingPlan.getColumns()
        assertThrows(ExInvalidSeat.class, () -> {
            bookSeatService.bookSeat(seatingPlan, "K1");
        });
    }

}
