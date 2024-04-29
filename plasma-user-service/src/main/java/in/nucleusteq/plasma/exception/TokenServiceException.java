/**
 * Custom exception indicating an error related to token services.
 */
package in.nucleusteq.plasma.exception;

public class TokenServiceException extends Exception {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a new TokenServiceException with the specified detail message.
	 *
	 * @param message the detail message.
	 */
	public TokenServiceException(String message) {
		super(message);
	}
}
