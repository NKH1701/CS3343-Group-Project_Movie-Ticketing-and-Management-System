package jasperPackage;

public class Adult implements CustomerState{
	private Adult() {}
	
	private static final Adult instance = new Adult();
	
	public static Adult getInstance() {
		return instance;
	}

	@Override
	public Boolean canViewClassIIIMovies() {
		return true;
	}

	@Override
	public double getDiscount() {
		return 1.0;
	}
	
	@Override
	public String toString() {
		return "adult";
	}
}
