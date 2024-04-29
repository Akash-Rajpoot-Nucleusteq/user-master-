package in.nucleusteq.plasma.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception indicating unauthorized access.
 */
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedAccessException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new UnauthorizedAccessException with the specified error
     * message.
     *
     * @param message The error message.
     */
    public UnauthorizedAccessException(final String message) {
        super(message);
    }
}
