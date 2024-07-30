package host.remote.controlcenter;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * This class implements CommandLineRunner, which means its run method
 * will be executed on application startup, but only after the Spring
 * context initialization is complete.
 */
@Component
public class ControlcenterStartupRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {

        System.out.println("CommandLineRunner execution");
    }
}
