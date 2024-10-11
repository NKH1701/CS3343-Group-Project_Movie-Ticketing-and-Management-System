package jasperPackage;

import java.util.ArrayList;
import java.util.List;

public class SeatingPlan{
	private final List<List<String>> seatingPlan = new ArrayList<>();
	private final int rows;
	private final int columns;
	private static final String bookedSymbol = "X";
	private static final String availableSymbol = "O";

	public static boolean validateConstructorParameters(int rows, int columns){
		// limit the seating plan size to be 5 x 5 - 20 x 20
		return rows >= 5 && rows <= 20 && columns >= 5 && columns <= 20;
	}

	public SeatingPlan() {
		// default seating plan
		this(10, 10);
	}

	public SeatingPlan(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		for (int i = 0; i < rows; i++) {
			this.seatingPlan.add(new ArrayList<>(columns));
			for (int j = 0; j < columns; j++) {
				this.seatingPlan.get(i).add(availableSymbol);
			}
		}
	}
	
	public String displaySeatingPlan() {
		final String ANSI_RED = "\u001B[31m";
		final String ANSI_GREEN = "\u001B[32m";
		final String ANSI_RESET = "\u001B[0m";
		StringBuilder result = new StringBuilder();
		result.append("Seating Plan: (").append(ANSI_GREEN).append("\"O\" = available; ")
			  .append(ANSI_RED).append("\"X\" = unavailable").append(ANSI_RESET).append(")\n\n");
		result.append(showScreen());
		result.append(" ".repeat(4));
		char row = 'A';
		for (String seat : this.seatingPlan.get(0)) {
			result.append(String.format("%4s", String.valueOf(row++)));
		}
        result.append("\n");
        for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (j == 0) result.append(String.format("%4d", i + 1));
				String currSeat = this.seatingPlan.get(i).get(j);
				if (currSeat.equals(bookedSymbol)) {
					result.append(ANSI_RED);
					result.append(String.format("%4s", currSeat));
					result.append(ANSI_RESET);
				}else{
					result.append(ANSI_GREEN);
					result.append(String.format("%4s", currSeat));
					result.append(ANSI_RESET);
				}

			}
			result.append("\n");
		}
        return result.toString();
	}
	
	private String showScreen() {
		
		StringBuilder result = new StringBuilder();
		int screenPadding = (4 * seatingPlan.get(0).size() - " SCREEN ".length() - 3 ) / 2;

		return result
		.append(" ".repeat(7))
		.append("|")
		.append("*".repeat(screenPadding))
		.append(" SCREEN ")
		.append("*".repeat(screenPadding - 1))
		.append("|\n\n")
		.toString();
		
	}

	public Boolean isSeatAvailable(String option) throws CustomException {
		
		// e.g. A1, B26
		// TODO: some may be modified to throw exception
		if (option.length() < 2 || option.length() > 3)
			throw new CustomException("Invalid format for \"" + option + "\".");

		if (!Character.isAlphabetic(option.charAt(0)) || Character.isAlphabetic(option.charAt(1))) 
            throw new CustomException("Invalid format for \"" + option + "\".");
		// throw invalid format exception

		int col = option.toLowerCase().charAt(0) - (int)'a';
		int row = Integer.parseInt(option.substring(1)) - 1;
		
		if (row < 0 || row >= rows || col < 0 || col >= columns) 
			throw new CustomException("Invalid format for \"" + option + "\".");
		
		return this.seatingPlan.get(row).get(col).equals(availableSymbol);
		
	}
	
	public String bookSeat(String option) {
		int col = option.toLowerCase().charAt(0) - (int) 'a';
		int row = Integer.parseInt(option.substring(1)) - 1;
		this.seatingPlan.get(row).set(col, bookedSymbol);
		return "Seat \"" + option + "\" has been booked.";
	}

	public String releaseSeat(String option){
		int col = option.toLowerCase().charAt(0) - (int) 'a';
		int row = Integer.parseInt(option.substring(1)) - 1;
		this.seatingPlan.get(row).set(col, availableSymbol);
		return "Seat \"" + option + "\" has been released.";
	}
	
	public int getRows() {
		return rows;
	}
	
	public int getColumns() {
		return columns;
	}
	
	@Override
	public String toString() {
		return "Seating plan with " + rows + " rows and " + columns + " columns.";
	}
}
