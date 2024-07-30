package host.remote.controlcenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ControlcenterApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(ControlcenterApplication.class);

		// Add custom initializer class to run before application context is initialized
		application.addInitializers(new DatabaseInitializer());

		application.run(args);
	}

}
