package in.nucleusteq.plasma.dto.out;

import lombok.Builder;
import lombok.Data;

/**
 * Data Transfer Object (DTO) representing output data for logout.
 */
@Builder
@Data
public class LogoutOutDTO {
	/**
	 * A message indicating the result of the logout operation.
	 */
	String message;
}
