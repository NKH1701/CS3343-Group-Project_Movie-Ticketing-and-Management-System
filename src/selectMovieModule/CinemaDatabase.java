package selectMovieModule;

import java.util.ArrayList;
import java.util.List;

public class CinemaDatabase{
	private CinemaDatabase() {}
	private static final CinemaDatabase instance = new CinemaDatabase();
	private Time openHours;
	private Time closeHours;
	private List<House> houses;
	private List<Movie> movies;
	private List<User> registeredUsers;
	
	public static CinemaDatabase getInstance() {return instance;}
	public Time getOpenHours() {return openHours;}
	public Time getCloseHours() {return closeHours;}
	public List<Movie> getMovies() {return movies;}
	public List<House> getHouses() {return houses;}
	public List<User> getRegisteredUsers() {return registeredUsers;}
	
	public void setOpeningHours(Time openingHours) {this.openHours = openingHours;}
	public void setClosingHours(Time closingHours) {this.closeHours = closingHours;}
	
	public List<Movie> addMovie(Movie movie) {
		if (movies == null) {movies = new ArrayList<Movie>();}
		movies.add(movie);
		return movies;
	}

	public void addHouse(House house) {
		if (houses == null) {houses = new ArrayList<House>();}
		houses.add(house);
	}
	
	public void addRegisteredUser(User user) {
		if (registeredUsers == null) {registeredUsers = new ArrayList<User>();}
		registeredUsers.add(user);
	}
	
	public static void setUpDummyDB() throws CustomException {
		CinemaDatabase db = CinemaDatabase.getInstance();
		db.setOpeningHours(new Time(9, 0));
		db.setClosingHours(new Time(24, 0));
		
		House house1 = new House("1", 10, 5);
		db.addHouse(house1);
		House house2 = new House("2", 8, 6);
		db.addHouse(house2);
		House house3 = new House("3", 8, 10);
		db.addHouse(house3);

		
		Movie movie1 = new Movie("The Matrix", "Action", 120, 50, "IIB", "English", "Chinese");
		Movie movie2 = new Movie("The Matrix Reloaded", "Action", 130, 50, "IIB", "English", "Chinese");
		Movie movie3 = new Movie("The Matrix Revolutions", "Action", 140, 50, "IIB", "English", "Chinese");
		Movie movie4 = new Movie("The Matrix Resurrections", "Action", 150, 40, "IIB", "English", "Chinese");
		Movie movie5 = new Movie("The Avengers", "Action", 180, 100, "IIA", "English", "Chinese");
		Movie movie6 = new Movie("Avengers: Age of Ultron", "Action", 190, 55, "IIA", "English", "Chinese");
		Movie movie7 = new Movie("Avengers: Infinity War", "Action", 200, 55, "IIA", "English", "Chinese");
		Movie movie8 = new Movie("Avengers: Endgame", "Action", 210, 60, "IIA", "English", "Chinese");
		Movie movie9 = new Movie("Deadpool", "Comedy", 150, 50, "III", "English", "Chinese");
		Movie movie10 = new Movie("Deadpool 2", "Comedy", 160, 55, "III", "English", "Chinese");
		Movie movie11 = new Movie("Deadpool & Wolverine", "Comedy", 170, 100, "III", "English", "Chinese");
		Movie movie12 =  new Movie("Batman Begins", "Thriller", 150, 45, "IIA", "English", "Chinese");
		Movie movie13 =  new Movie("The Dark Knight", "Thriller", 180, 45, "IIA", "English", "Chinese");
		Movie movie14 =  new Movie("The Dark Knight Rises", "Thriller", 180, 45, "IIA", "English", "Chinese");
		db.addMovie(movie1);
		db.addMovie(movie2);
		db.addMovie(movie3);
		db.addMovie(movie4);
		db.addMovie(movie5);
		db.addMovie(movie6);
		db.addMovie(movie7);
		db.addMovie(movie8);
		db.addMovie(movie9);
		db.addMovie(movie10);
		db.addMovie(movie11);
		db.addMovie(movie12);
		db.addMovie(movie13);
		db.addMovie(movie14);
		
		MovieSession session1 = new MovieSession(movie1, new Time(9, 0), house1);
		movie1.addMovieSession(session1);
		MovieSession session2 = new MovieSession(movie2, new Time(12, 0), house1);
		movie2.addMovieSession(session2);
		MovieSession session3 = new MovieSession(movie3, new Time(15, 0), house1);
		movie3.addMovieSession(session3);
		MovieSession session4 = new MovieSession(movie4, new Time(18, 0), house1);
		movie4.addMovieSession(session4);
		MovieSession session5 = new MovieSession(movie5, new Time(21, 0), house1);
		movie5.addMovieSession(session5);
		MovieSession session6 = new MovieSession(movie5, new Time(9, 0), house2);
		movie5.addMovieSession(session6);
		MovieSession session7 = new MovieSession(movie6, new Time(12, 0), house2);
		movie6.addMovieSession(session7);
		MovieSession session8 = new MovieSession(movie7, new Time(15, 0), house2);
		movie7.addMovieSession(session8);
		MovieSession session9 = new MovieSession(movie8, new Time(18, 0), house2);
		movie8.addMovieSession(session9);
		MovieSession session10 = new MovieSession(movie9, new Time(21, 0), house2);
		movie9.addMovieSession(session10);
		MovieSession session11 = new MovieSession(movie10, new Time(9, 0), house3);
		movie10.addMovieSession(session11);
		MovieSession session12 = new MovieSession(movie7, new Time(12, 0), house3);
		movie7.addMovieSession(session12);
		MovieSession session13 = new MovieSession(movie8, new Time(15, 0), house3);
		movie8.addMovieSession(session13);
		MovieSession session14 = new MovieSession(movie10, new Time(18, 0), house3);
		movie10.addMovieSession(session14);
		MovieSession session15 = new MovieSession(movie11, new Time(21, 0), house3);
		movie11.addMovieSession(session15);

	}
	
	public void resetDB() {
		openHours = null;
		closeHours = null;
		houses = null;
		movies = null;
		registeredUsers = null;
	}
}
