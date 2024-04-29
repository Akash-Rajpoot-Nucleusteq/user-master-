/**
 * Data Transfer Object (DTO) representing a token request containing an employee email.
 */
package in.nucleusteq.plasma.dto.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class TokenRequest {
    /**
     * The email address of the employee.
     */
    private String email;
}
