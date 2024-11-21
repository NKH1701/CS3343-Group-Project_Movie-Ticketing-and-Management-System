package release.exception;

/**
 * InvalidMovieTicketException class
 * It is used to represent the exception when the movie ticket is invalid
 */
public class ExInvalidMovieTicket extends Exception {
  public ExInvalidMovieTicket() {
        super("Invalid movie ticket");
    }
}
