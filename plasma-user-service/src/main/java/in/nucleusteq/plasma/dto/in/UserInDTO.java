package in.nucleusteq.plasma.dto.in;

import java.util.Date;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode
public class UserInDTO {
    private String employeeId;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Date startDate;
    private String phoneNumber;
    private String city;
    private String state;
    private int peresonalDetailId;
    private Set<RoleInDTO> roles;
	
}
