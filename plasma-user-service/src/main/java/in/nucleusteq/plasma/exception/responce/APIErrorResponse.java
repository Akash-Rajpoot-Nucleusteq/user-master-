package in.nucleusteq.plasma.exception.responce;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.Data;

/**
 * Response object for API errors.
 */
@Data
public class APIErrorResponse {
    /**
     * The HTTP status of the error.
     */
    private HttpStatus status;

    /**
     * The timestamp when the error occurred.
     */
    private String timestamp;

    /**
     * The error message.
     */
    private String message;

    /**
     * Additional debug information about the error.
     */
    private String debugMessage;

    /**
     * The numeric value of the HTTP status.
     */
    private int statusNumber;

    /**
     * Constructs an APIErrorResponse object with the given message and details.
     *
     * @param message The error message.
     * @param details Additional details about the error.
     */
    public APIErrorResponse(String message, String details) {
        this.timestamp = LocalDateTime.now().toString();
        this.message = message;
    }

    /**
     * Constructs an APIErrorResponse object with the given HTTP status.
     *
     * @param status The HTTP status of the error.
     */
    public APIErrorResponse(HttpStatus status) {
        this.status = status;
    }

    /**
     * Constructs an APIErrorResponse object with the given HTTP status and
     * exception.
     *
     * @param status The HTTP status of the error.
     * @param ex     The exception that caused the error.
     */
    public APIErrorResponse(HttpStatus status, Throwable ex) {
        this.status = status;
        this.message = "Unexpected error";
        this.debugMessage = ex.getLocalizedMessage();
    }

    /**
     * Constructs an APIErrorResponse object with the given HTTP status, message,
     * and exception.
     *
     * @param status  The HTTP status of the error.
     * @param message The error message.
     * @param ex      The exception that caused the error.
     */
    public APIErrorResponse(HttpStatus status, String message, Throwable ex) {
        this.status = status;
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }
}
