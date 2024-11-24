package test.movie_test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import release.exception.ExInvalidSeatingPlan;
import release.movie.House;
import release.movie.Movie;
import release.movie.MovieService;
import release.movie.MovieSession;
import release.database.Database;
import release.exception.ExInvalidOption;
import release.exception.ExInvalidSearch;
import release.exception.ExNoMovieToShow;

import java.util.LinkedHashMap;
import java.util.Map;

public class MovieServiceTest {

    private MovieService movieService;
    private Database db;

    @BeforeEach
    public void setUp() {
        movieService = MovieService.getInstance();
        db = Database.getInstance();
        db.resetDB();
    }

    @Test
    public void testGetAllScheduledMovies() throws ExNoMovieToShow, ExInvalidSeatingPlan {
        Movie movie = new Movie("Inception", "Sci-Fi", 148, 10.0, 9.5, "IIA", "English", "English");
        MovieSession session = new MovieSession(movie, "00:00", "03:00", new House(1, 10, 10));
        movie.addMovieSession(session);
        db.addMovie(movie);

        Map<Integer, Movie> scheduledMovies = movieService.getAllScheduledMovies();
        assertEquals(1, scheduledMovies.size());
        assertTrue(scheduledMovies.containsValue(movie));
    }

    @Test
    public void testGetAllScheduledMovies_NoMovies() {
        assertThrows(ExNoMovieToShow.class, () -> {
            movieService.getAllScheduledMovies();
        });
    }

    @Test
    public void testSearchScheduledMovies() throws ExInvalidSearch, ExInvalidSeatingPlan {
        Movie movie = new Movie("Inception", "Sci-Fi", 148, 10.0, 9.5, "IIA", "English", "English");
        Movie movie2 = new Movie("Inception 2", "Sci-Fi", 148, 10.0, 9.5, "IIA", "English", "English");
        House house = new House(1, 10, 10);
        MovieSession session = new MovieSession(movie, "00:00", "02:00", house);
        MovieSession session2 = new MovieSession(movie2, "02:15", "04:15", house);
        movie.addMovieSession(session);
        movie2.addMovieSession(session2);
        db.addMovie(movie);
        db.addMovie(movie2);

        Map<Integer, Movie> searchResults = movieService.searchScheduledMovies("Inception 2");
        assertEquals(1, searchResults.size());
        assertTrue(searchResults.containsValue(movie2));

        Map<Integer, Movie> searchResults2 = movieService.searchScheduledMovies("Inception");
        assertEquals(2, searchResults2.size());
        assertTrue(searchResults2.containsValue(movie) && searchResults2.containsValue(movie2));
    }

    @Test
    public void testSearchScheduledMovies_NoMatch() throws ExInvalidSeatingPlan {
        Movie movie = new Movie("Inception", "Sci-Fi", 148, 10.0, 9.5, "IIA", "English", "English");
        Movie movie2 = new Movie("Inception 2", "Sci-Fi", 148, 10.0, 9.5, "IIA", "English", "English");
        House house = new House(1, 10, 10);
        MovieSession session = new MovieSession(movie, "00:00", "02:00", house);
        MovieSession session2 = new MovieSession(movie2, "02:15", "04:15", house);
        movie.addMovieSession(session);
        movie2.addMovieSession(session2);
        db.addMovie(movie);
        db.addMovie(movie2);

        assertThrows(ExInvalidSearch.class, () -> {
            movieService.searchScheduledMovies("Hello World");
        });
    }
    
    @Test
    public void testSearchMoviesAdmin() throws ExInvalidSearch {
    	Movie movie = new Movie("Inception", "Sci-Fi", 148, 10.0, 9.5, "IIA", "English", "English");
        Movie movie2 = new Movie("Inception 2", "Sci-Fi", 148, 10.0, 9.5, "IIA", "English", "English");
        db.addMovie(movie);
        db.addMovie(movie2);
        
        Map<Integer, Movie> searchResults = movieService.searchMoviesAdmin("Inception 2");
        assertEquals(1, searchResults.size());
        assertTrue(searchResults.containsValue(movie2));

        Map<Integer, Movie> searchResults2 = movieService.searchMoviesAdmin("Inception");
        assertEquals(2, searchResults2.size());
        assertTrue(searchResults2.containsValue(movie) && searchResults2.containsValue(movie2));
    }
    
    @Test
    public void testSearchMoviesAdmin_NoMatch() throws ExInvalidSeatingPlan {
    	Movie movie = new Movie("Inception", "Sci-Fi", 148, 10.0, 9.5, "IIA", "English", "English");
        Movie movie2 = new Movie("Inception 2", "Sci-Fi", 148, 10.0, 9.5, "IIA", "English", "English");
        db.addMovie(movie);
        db.addMovie(movie2);
      
        assertThrows(ExInvalidSearch.class, () -> {
            movieService.searchMoviesAdmin("Hello World");
        });
    }

    @Test
    public void testGetSelectedMovieSession() throws ExInvalidOption, ExInvalidSeatingPlan {
        Movie movie = new Movie("Inception", "Sci-Fi", 148, 10.0, 9.5, "IIA", "English", "English");
        Movie movie2 = new Movie("Inception 2", "Sci-Fi", 148, 10.0, 9.5, "IIA", "English", "English");
        House house = new House(1, 10, 10);
        MovieSession session = new MovieSession(movie, "00:00", "02:00", house);
        MovieSession session2 = new MovieSession(movie2, "02:15", "04:15", house);
        movie.addMovieSession(session);
        movie2.addMovieSession(session2);
        Map<Integer, MovieSession> movieSessions = new LinkedHashMap<>();
        movieSessions.put(1, session);
        movieSessions.put(2, session2);

        MovieSession selectedSession = movieService.getSelectedMovieSession(movieSessions, 1);
        assertEquals(session, selectedSession);

        MovieSession selectedSession2 = movieService.getSelectedMovieSession(movieSessions, 2);
        assertEquals(session2, selectedSession2);
    }

    @Test
    public void testGetSelectedMovieSession_InvalidOption() throws ExInvalidSeatingPlan {
        Movie movie = new Movie("Inception", "Sci-Fi", 148, 10.0, 9.5, "IIA", "English", "English");
        Movie movie2 = new Movie("Inception 2", "Sci-Fi", 148, 10.0, 9.5, "IIA", "English", "English");
        House house = new House(1, 10, 10);
        MovieSession session = new MovieSession(movie, "00:00", "02:00", house);
        MovieSession session2 = new MovieSession(movie2, "02:15", "04:15", house);
        movie.addMovieSession(session);
        movie2.addMovieSession(session2);
        Map<Integer, MovieSession> movieSessions = new LinkedHashMap<>();
        movieSessions.put(1, session);
        movieSessions.put(2, session2);

        assertThrows(ExInvalidOption.class, () -> {
            movieService.getSelectedMovieSession(movieSessions, 3);
        });
    }

    @Test
    public void testIsScheduled() throws ExInvalidSeatingPlan {
        Movie movie = new Movie("Inception", "Sci-Fi", 148, 10.0, 9.5, "IIA", "English", "English");
        assertFalse(movieService.isScheduled(movie));

        MovieSession session = new MovieSession(movie, "00:00", "03:00", new House(1, 10, 10));
        movie.addMovieSession(session);
        assertTrue(movieService.isScheduled(movie));
    }

    @Test
    public void testGetAllMovieSessions() throws ExNoMovieToShow, ExInvalidSeatingPlan {
        Movie movie = new Movie("Inception", "Sci-Fi", 148, 10.0, 9.5, "IIA", "English", "English");
        Movie movie2 = new Movie("Inception 2", "Sci-Fi", 148, 10.0, 9.5, "IIA", "English", "English");
        House house = new House(1, 10, 10);
        MovieSession session = new MovieSession(movie, "00:00", "02:00", house);
        MovieSession session2 = new MovieSession(movie2, "02:15", "04:15", house);
        movie.addMovieSession(session);
        movie2.addMovieSession(session2);
        db.addMovie(movie);
        db.addMovie(movie2);

        Map<Integer, MovieSession> movieSessions = movieService.getAllMovieSessions();
        assertEquals(2, movieSessions.size());
        assertTrue(movieSessions.containsValue(session) && movieSessions.containsValue(session2));
    }

    @Test
    public void testGetAllMovieSessions_NoSessions() throws ExInvalidSeatingPlan {
        Movie movie = new Movie("Inception", "Sci-Fi", 148, 10.0, 9.5, "IIA", "English", "English");
        Movie movie2 = new Movie("Inception 2", "Sci-Fi", 148, 10.0, 9.5, "IIA", "English", "English");
        House house = new House(1, 10, 10);
        // By simply removing the sessions, we can test that there will be no movie session to show
//        MovieSession session = new MovieSession(movie, "00:00", "02:00", house);
//        MovieSession session2 = new MovieSession(movie2, "02:15", "04:15", house);
//        movie.addMovieSession(session);
//        movie2.addMovieSession(session2);
        db.addMovie(movie);
        db.addMovie(movie2);
        assertThrows(ExNoMovieToShow.class, () -> {
            movieService.getAllMovieSessions();
        });
    }
}
