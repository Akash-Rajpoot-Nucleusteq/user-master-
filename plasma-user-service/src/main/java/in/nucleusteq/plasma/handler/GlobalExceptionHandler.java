package in.nucleusteq.plasma.handler;

import org.springframework.expression.spel.SpelEvaluationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import in.nucleusteq.plasma.exception.DuplicateException;
import in.nucleusteq.plasma.exception.ResourceNotFoundException;
import in.nucleusteq.plasma.exception.UnauthorizedAccessException;
import in.nucleusteq.plasma.exception.responce.APIErrorResponse;

/**
 * Global exception handler for handling various exceptions.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
	/**
	 * Handles DuplicateException by returning a response with a "Conflict" status.
	 *
	 * @param ex The DuplicateException to handle.
	 * @return A ResponseEntity with a "Conflict" status and an error message.
	 */
	@ExceptionHandler(DuplicateException.class)
	public ResponseEntity<APIErrorResponse> handleDuplicateException(DuplicateException ex) {
		APIErrorResponse apiError = new APIErrorResponse(HttpStatus.CONFLICT, ex.getMessage(), ex);
		return new ResponseEntity<>(apiError, HttpStatus.CONFLICT);
	}

	/**
	 * Handles UnauthorizedAccessException by returning a response with an
	 * "Unauthorized" status.
	 *
	 * @param exception The UnauthorizedAccessException to handle.
	 * @return A ResponseEntity with an "Unauthorized" status and an error message.
	 */
	@ExceptionHandler(UnauthorizedAccessException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public final ResponseEntity<APIErrorResponse> handleUnauthorizedAccessException(
			final UnauthorizedAccessException exception) {
		APIErrorResponse errorResponse = new APIErrorResponse(exception.getMessage(),
				exception.getStackTrace().toString());
		return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
	}

	/**
	 * Handles ResourceNotFoundException by returning a response with a "Not Found"
	 * status.
	 * 
	 * @param exception The ResourceNotFoundException to handle.
	 * @return A ResponseEntity with a "Not Found" status and an error message.
	 */
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public final ResponseEntity<APIErrorResponse> handleResourceNotFound(final ResourceNotFoundException exception) {
		APIErrorResponse errorResponse = new APIErrorResponse("Not Found", exception.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	/**
	 * Handles SpelEvaluationException by returning a response with an
	 * "Unauthorized" status.
	 *
	 * @param exception The SpelEvaluationException to handle.
	 * @return A ResponseEntity with an "Unauthorized" status and an error message.
	 */
	@ExceptionHandler(SpelEvaluationException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public final ResponseEntity<APIErrorResponse> spelEvaluationException(final SpelEvaluationException exception) {
		APIErrorResponse errorResponse = new APIErrorResponse("Access denied",
				"Insufficient privilage to access this resource");
		return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
	}

	/**
	 * Handles AccessDeniedException by returning a response with a "Forbidden"
	 * status.
	 *
	 * @param exception The AccessDeniedException to handle.
	 * @return A ResponseEntity with a "Forbidden" status and an error message.
	 */
	@ExceptionHandler(AccessDeniedException.class)
	public final ResponseEntity<Object> handleAccessDeniedException(final AccessDeniedException exception) {
		APIErrorResponse errorResponse = new APIErrorResponse("Access denied",
				"Insufficient privilage to access this resource");
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
	}
}
