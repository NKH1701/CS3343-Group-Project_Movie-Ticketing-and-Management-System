package jasperPackage;

public class Children implements CustomerState{
	
	private Children() {}
	
	private static final Children instance = new Children();
	
	public static Children getInstance() {
		return instance;
	}

	@Override
	public Boolean canViewClassIIIMovies() {
		return false;
	}

	@Override
	public double getDiscount() {
		return 0.8;
	}
	
	@Override
	public String toString() {
		return "children";
	}

}
