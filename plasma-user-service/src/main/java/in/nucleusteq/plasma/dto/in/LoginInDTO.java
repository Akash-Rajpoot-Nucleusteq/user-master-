package in.nucleusteq.plasma.dto.in;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) representing login input data.
 */
@Getter
@Setter
public class LoginInDTO {
   /**
    * The user's email address.
    */
   @NotBlank(message = "Email is required.")
   private String email;
   /**
    * The user's password.
    */
   @NotBlank(message = "Password is required.")
   private String password;

}
