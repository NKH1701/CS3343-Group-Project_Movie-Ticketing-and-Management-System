package selectMovieModule;

public class Guest extends User{
	
	private Guest() {}
	private static Guest instance = new Guest();
	
	public static Guest getInstance() {return instance;}
	
}
