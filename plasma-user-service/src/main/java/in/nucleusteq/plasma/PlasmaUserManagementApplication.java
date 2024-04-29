package in.nucleusteq.plasma;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * PlasmaUserManagementApplication is the entry point for the Plasma User
 * Management application.
 */
@SpringBootApplication
@EnableFeignClients
public class PlasmaUserManagementApplication {

	/**
	 * Main method to start the application.
	 *
	 * @param args Command-line arguments.
	 */
	public static void main(String[] args) {
		SpringApplication.run(PlasmaUserManagementApplication.class, args);
	}

}
