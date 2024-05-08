package in.nucleusteq.plasma.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) representing output data for login.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginOutDTO {
	/**
	 * The user's email address.
	 */
	private String email;

	/**
	 * The role of the user.
	 */
	private String role;
	/**
	 * The employee id of the user.
	 */
	private String employeeId;
}
