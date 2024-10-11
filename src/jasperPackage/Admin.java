package jasperPackage;

public class Admin extends User{
	
	private String userName;
	private String password;

	public static Admin getDummyAdmin(){
		return new Admin("dummyAdmin", "password");
	}

	public Admin(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}
	
	public String getUserName() {return userName;}
	public String getPassword() {return password;}
	
	@Override
	public String listMovies() throws CustomException{
		CinemaDatabase cinemaDatabase = CinemaDatabase.getInstance();
		StringBuilder result = new StringBuilder("The following movies are found in the database: \n\n");
		result.append(Movie.displayMovieInfoHeaderForAdmin());
		int count = 0;
		for (Movie movie : cinemaDatabase.getMovies()) {
			count++;
			result.append(movie.displayMovieInfoForAdmin(count)).append("\n");
		}
		if (count == 0) {
			throw new CustomException("There are no movies found in the database.");
		}
		return result.toString();
	}
	
	@Override
	public String searchMovieByName(String name) throws CustomException {
		name = name.strip().toLowerCase();
		CinemaDatabase cinemaDatabase = CinemaDatabase.getInstance();
		StringBuilder result = new StringBuilder("The search results are as follows: \n\n");
		result.append(Movie.displayMovieInfoHeaderForAdmin());
		int count = 0;
		for (Movie movie : cinemaDatabase.getMovies()) {
			if (movie.getName().toLowerCase().contains(name)) {
				count++;
				result.append(movie.displayMovieInfoForAdmin(count)).append("\n");
			}
		}
		if (count == 0) {
			throw new CustomException("There are no movies matched with the search keyword.");
		}
		return result.toString();
	}
	
	@Override
	public String searchMovieByGenre(String genre) throws CustomException {
		genre = genre.strip().toLowerCase();
		CinemaDatabase cinemaDatabase = CinemaDatabase.getInstance();
		StringBuilder result = new StringBuilder("The search results are as follows: \n\n");
		result.append(Movie.displayMovieInfoHeaderForAdmin());
		int count = 0;
		for (Movie movie : cinemaDatabase.getMovies()) {
			if (movie.getGenre().toLowerCase().equals(genre)) {
				count++;
				result.append(movie.displayMovieInfoForAdmin(count)).append("\n");
			}
		}
		if (count == 0) {
			throw new CustomException("There are no movies matched with the search keyword.");
		}
		return result.toString();
	}
}
