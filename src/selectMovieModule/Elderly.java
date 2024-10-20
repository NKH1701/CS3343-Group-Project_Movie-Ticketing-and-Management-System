package selectMovieModule;

public class Elderly implements CustomerState {
	private Elderly() {}

	private static final Elderly instance = new Elderly();

	public static Elderly getInstance() {
		return instance;
	}

	@Override
	public Boolean canViewClassIIIMovies() {
		return true;
	}

	@Override
	public double getDiscount() {
		return 0.5;
	}

	@Override
	public String toString() {
		return "elderly";
	}
}
