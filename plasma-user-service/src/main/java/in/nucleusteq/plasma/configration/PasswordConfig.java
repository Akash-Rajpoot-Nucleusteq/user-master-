package in.nucleusteq.plasma.configration;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordConfig {
 	/**
     * Provides a BCryptPasswordEncoder bean for password encoding.
     *
     * @return BCryptPasswordEncoder bean for encoding passwords using the BCrypt hashing algorithm.
     */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
