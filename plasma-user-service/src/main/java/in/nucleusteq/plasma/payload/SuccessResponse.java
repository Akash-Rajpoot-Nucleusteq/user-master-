package in.nucleusteq.plasma.payload;

import lombok.Getter;
import lombok.Setter;

/**
 * SuccessResponse represents a successful response message with a status code.
 */
@Getter
@Setter
public class SuccessResponse {
  /**
   * The success message.
   */
  private String message;

  /**
   * The HTTP status code.
   */
  private int status;

  /**
   * Constructs a SuccessResponse with the given message and status.
   *
   * @param message The success message.
   * @param status  The HTTP status code.
   */
  public SuccessResponse(final String message, final int status) {
    this.message = message;
    this.status = status;
  }
}
