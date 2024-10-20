package selectMovieModule;

public class User{
	
	public String listMovies() throws CustomException{
		CinemaDatabase cinemaDatabase = CinemaDatabase.getInstance();
		StringBuilder result = new StringBuilder("The following movies are currently ticketing: \n\n");
		result.append(Movie.displayMovieInfoHeaderForCustomer());
		int count = 0;
		for (Movie movie : cinemaDatabase.getMovies()) {
			if (movie.getMovieSession() != null) {
				count++;
				result.append(movie.displayMovieInfoForCustomer(count)).append("\n");
			}
		}
		if (count == 0) {
			throw new CustomException("There are currently no movies scheduled in this cinema.");
		}
		return result.toString();
	}
	public String searchMovieByName(String name) throws CustomException{
		name = name.strip().toLowerCase();
		CinemaDatabase cinemaDatabase = CinemaDatabase.getInstance();
		StringBuilder result = new StringBuilder("The search results are as follows: \n\n");
		result.append(Movie.displayMovieInfoHeaderForCustomer());
		int count = 0;
		for (Movie movie : cinemaDatabase.getMovies()) {
			if (movie.getMovieSession() != null && movie.getName().toLowerCase().contains(name)) {
				count++;
				result.append(movie.displayMovieInfoForCustomer(count)).append("\n");
			}
		}
		if (count == 0) {
			throw new CustomException("There are no movies matched with the search keyword.");
		}
		return result.toString();
	}
	public String searchMovieByGenre(String genre) throws CustomException{
		genre = genre.strip().toLowerCase();
		CinemaDatabase cinemaDatabase = CinemaDatabase.getInstance();
		StringBuilder result = new StringBuilder("The search results are as follows: \n\n");
		result.append(Movie.displayMovieInfoHeaderForCustomer());
		int count = 0;
		for (Movie movie : cinemaDatabase.getMovies()) {
			if (movie.getMovieSession() != null && movie.getGenre().toLowerCase().equals(genre)) {
				count++;
				result.append(movie.displayMovieInfoForCustomer(count)).append("\n");
			}
		}
		if (count == 0) {
			throw new CustomException("There are no movies matched with the search keyword.");
		}
		return result.toString();
	}

}
