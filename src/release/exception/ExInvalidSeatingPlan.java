package release.exception;

public class ExInvalidSeatingPlan extends Exception{
	public ExInvalidSeatingPlan() {
		super("[Exception] Invalid seating plan size. The seating plan size should be between 5 x 5 and 20 x 20.");
	}
	public ExInvalidSeatingPlan(String message) {
		super("[Exception] " + message);
	}
}
