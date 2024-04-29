package in.nucleusteq.plasma.exception;

public class InvalidCredentialsException extends Exception {
    /**
     * serial id.
     */
    private static final long serialVersionUID = -7556398392266114494L;

    /**
     * Constructs a new InvalidCredentialsException with the specified detail
     * message.
     *
     * @param message the detail message.
     */
    public InvalidCredentialsException(String message) {
        super(message);
    }
}
