/**
 * Custom exception indicating that a requested resource was not found.
 */
package in.nucleusteq.plasma.exception;

public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new ResourceNotFoundException with the specified error message.
     *
     * @param message The error message.
     */
    public ResourceNotFoundException(final String message) {
        super(message);
    }
}
