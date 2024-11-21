package test.movie_test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import release.exception.ExInvalidSeatingPlan;
import release.exception.ExNoMovieToShow;
import release.movie.House;
import release.movie.Movie;
import release.movie.MovieInfoToString;
import release.movie.MovieSession;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.HashMap;

public class MovieInfoToStringTest {

    @Test
    void testShowMovie() throws ExInvalidSeatingPlan, ExNoMovieToShow {
        Movie movie = new Movie("Deadpool & Wolverine", "Comedy", 170, 100, 8.3, "III", "English", "Chinese");
        House house = new House(1, 10, 10);
        MovieSession movieSession = new MovieSession(movie, "10:00", "12:30", house);
        Map<Integer, Movie> scheduledMovies = new LinkedHashMap<>();
        scheduledMovies.put(1, movie);

        String expected = "    Movie                           Duration     Genre           Price      Popularity     Class     Language      Subtitles     \n" +
                "----------------------------------------------------------------------------------------------------------------------------------\n" +
                " 1) Deadpool & Wolverine            170 mins     Comedy          $100.0     8.3 / 10.0     III       English       Chinese       \n";

        assertEquals(expected, MovieInfoToString.showMovie(scheduledMovies));
    }

//    @Test
//    void showMovieInfoHeaderReturnsCorrectFormat() {
//        String expected = "Default Header\n" + "-".repeat(130) + "\n";
//        assertEquals(expected, MovieInfoToString.showMovieInfoHeader());
//    }
//
//    @Test
//    void showMovieInfoReturnsCorrectFormat() {
//        Movie movie = new Movie("Inception", 148, "Sci-Fi", 12.5, 8.8, "PG-13", "English", "Spanish");
//        String expected = " 1) Inception                        148 mins  Sci-Fi         $12.5      8.8/ 10.0  PG-13      English      Spanish     ";
//        assertEquals(expected, MovieInfoToString.showMovieInfo(1, movie));
//    }
//
//    @Test
//    void showMovieInfoHeaderAdminReturnsCorrectFormat() {
//        String expected = "Default Header\n" + "Scheduled?" + "\n" + "-".repeat(140) + "\n";
//        assertEquals(expected, MovieInfoToString.showMovieInfoHeaderAdmin());
//    }
//
//    @Test
//    void showMovieInfoAdminReturnsCorrectFormatWhenScheduled() {
//        Movie movie = new Movie("Inception", 148, "Sci-Fi", 12.5, 8.8, "PG-13", "English", "Spanish");
//        MovieScheduler ms = new MovieScheduler();
//        ms.scheduleMovie(movie);
//        String expected = " 1) Inception                        148 mins  Sci-Fi         $12.5      8.8/ 10.0  PG-13      English      Spanish     Scheduled?   Yes";
//        assertEquals(expected, MovieInfoToString.showMovieInfoAdmin(1, movie));
//    }
//
//    @Test
//    void showMovieInfoAdminReturnsCorrectFormatWhenNotScheduled() {
//        Movie movie = new Movie("Inception", 148, "Sci-Fi", 12.5, 8.8, "PG-13", "English", "Spanish");
//        String expected = " 1) Inception                        148 mins  Sci-Fi         $12.5      8.8/ 10.0  PG-13      English      Spanish     Scheduled?   No";
//        assertEquals(expected, MovieInfoToString.showMovieInfoAdmin(1, movie));
//    }
//
//    @Test
//    void showAllMovieSessionsReturnsCorrectFormat() throws ExNoMovieToShow {
//        Map<Integer, MovieSession> movieSessions = new HashMap<>();
//        Movie movie = new Movie("Inception", 148, "Sci-Fi", 12.5, 8.8, "PG-13", "English", "Spanish");
//        MovieSession session = new MovieSession(movie, 1, "10:00", "12:30", new SeatingPlan(100, 50));
//        movieSessions.put(1, session);
//        String expected = "    Movie                           House    Seats     Start     End       Class     Language     Subtitles   Price  \n" +
//                "-".repeat(117) + "\n" +
//                " 1) Inception                        1       50/100    10:00     12:30     PG-13     English      Spanish     $12.5  \n";
//        assertEquals(expected, MovieInfoToString.showAllMovieSessions(movieSessions));
//    }
//
//    @Test
//    void showAllMovieSessionsThrowsExceptionWhenEmpty() {
//        Map<Integer, MovieSession> movieSessions = new HashMap<>();
//        assertThrows(ExNoMovieToShow.class, () -> MovieInfoToString.showAllMovieSessions(movieSessions));
//    }
}