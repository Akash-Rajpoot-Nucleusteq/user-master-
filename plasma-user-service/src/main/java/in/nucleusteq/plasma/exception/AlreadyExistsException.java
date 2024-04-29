/**
 * Custom exception indicating that an entity already exists.
 */
package in.nucleusteq.plasma.exception;

public class AlreadyExistsException extends RuntimeException {

	/**
	 * Constructs a new AlreadyExistsException with the specified detail message.
	 *
	 * @param message the detail message.
	 */
	public AlreadyExistsException(String message) {
		super(message);
	}
}
