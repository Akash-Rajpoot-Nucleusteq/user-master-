
/**
 * Data Transfer Object (DTO) representing input data for a role.
 */
package in.nucleusteq.plasma.dto.in;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RoleInDTO {
    /**
     * The ID of the role.
     */
    private Long id;
    /**
     * The name of the role.
     */
    private String name;
}
