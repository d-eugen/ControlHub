package host.remote.controlcenter;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public abstract class BaseTestConfig {
    // Common configuration for all tests
}
