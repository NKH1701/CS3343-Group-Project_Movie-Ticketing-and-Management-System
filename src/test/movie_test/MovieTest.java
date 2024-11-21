package test.movie_test;

import org.junit.jupiter.api.Test;
import release.exception.ExInvalidSeatingPlan;
import release.movie.House;
import release.movie.Movie;
import release.movie.MovieSession;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class MovieTest {

    @Test
    public void testDefaultConstructor() {
        Movie movie = new Movie();
        assertEquals("name", movie.getName());
        assertEquals("genre", movie.getGenre());
        assertEquals(0, movie.getDuration());
        assertEquals(0, movie.getPrice());
        assertEquals(0, movie.getPopularityScore());
        assertEquals("class", movie.getClassification());
        assertEquals("language", movie.getLanguage());
        assertEquals("subtitles", movie.getSubtitles());
    }

    @Test
    public void testParameterizedConstructor() {
        Movie movie = new Movie("Inception", "Sci-Fi", 148, 10.0, 9.5, "IIA", "English", "English");
        assertEquals("Inception", movie.getName());
        assertEquals("Sci-Fi", movie.getGenre());
        assertEquals(148, movie.getDuration());
        assertEquals(10.0, movie.getPrice());
        assertEquals(9.5, movie.getPopularityScore());
        assertEquals("IIA", movie.getClassification());
        assertEquals("English", movie.getLanguage());
        assertEquals("English", movie.getSubtitles());
    }

    @Test
    public void testAddMovieSession() throws ExInvalidSeatingPlan {
        Movie movie = new Movie();
        House house = new House(1, 10, 10);
        MovieSession session = new MovieSession(movie, "00:00", "03:00", house);
        List<MovieSession> sessions = movie.addMovieSession(session);
        assertEquals(1, sessions.size());
        assertTrue(sessions.contains(session));
    }

    @Test
    public void testGetMovieSessionList_Empty() {
        Movie movie = new Movie();
        List<MovieSession> sessions = movie.getMovieSessionList();
        assertNotNull(sessions);
        assertTrue(sessions.isEmpty());
    }

    @Test
    public void testGetMovieSessionList_WithSessions() throws ExInvalidSeatingPlan {
        Movie movie = new Movie();
        House house = new House(1, 10, 10);
        MovieSession session1 = new MovieSession(movie, "00:00", "03:00", house);
        MovieSession session2 = new MovieSession(movie, "04:00", "07:00", house);
        movie.addMovieSession(session1);
        movie.addMovieSession(session2);
        List<MovieSession> sessions = movie.getMovieSessionList();
        assertNotNull(sessions);
        assertEquals(2, sessions.size());
        assertTrue(sessions.contains(session1) && sessions.contains(session2));
    }

    @Test
    public void testEquals() {
        Movie movie1 = new Movie("Inception", "Sci-Fi", 148, 10.0, 9.5, "IIA", "English", "English");
        Movie movie2 = new Movie("Inception", "Sci-Fi", 148, 10.0, 9.5, "IIA", "English", "English");
        assertTrue(movie1.equals(movie2));
    }

    @Test
    public void testNotEquals() {
        Movie movie1 = new Movie("Inception", "Sci-Fi", 148, 10.0, 9.5, "IIA", "English", "English");
        Movie movie2 = new Movie("Interstellar", "Sci-Fi", 169, 12.0, 9.0, "IIA", "English", "English");
        assertFalse(movie1.equals(movie2));
    }

    @Test
    public void testCompareTo_V1() {
        // movie 2 is more popular than movie 1
        Movie movie1 = new Movie("Inception", "Sci-Fi", 148, 10.0, 9.0, "IIA", "English", "English");
        Movie movie2 = new Movie("Interstellar", "Sci-Fi", 169, 12.0, 9.5, "IIA", "English", "English");
        assertTrue(movie1.compareTo(movie2) > 0);
    }

    @Test
    public void testCompareTo_V2() {
        // movie 2 is less popular than movie 2
        Movie movie1 = new Movie("Inception", "Sci-Fi", 148, 20.0, 10.0, "IIA", "English", "English");
        Movie movie2 = new Movie("Interstellar", "Sci-Fi", 169, 12.0, 9.5, "IIA", "English", "English");
        assertTrue(movie1.compareTo(movie2) < 0);
    }

    @Test
    public void testCompareTo_V3() {
        // movie 2 is equally popular as movie 2
        Movie movie1 = new Movie("Inception", "Sci-Fi", 148, 9.0, 10.0, "IIA", "English", "English");
        Movie movie2 = new Movie("Interstellar", "Sci-Fi", 169, 10.0, 9.0, "IIA", "English", "English");
        assertEquals(0, movie1.compareTo(movie2));
    }
}
