package host.remote.controlcenter;

import host.remote.controlcenter.config.DatasourceConfig;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@ActiveProfiles("test")
@ContextConfiguration(
        initializers = DatasourceInitializer.class,
        classes = {ControlcenterApplication.class, DatasourceConfig.class, TestConfig.class} // Import the test configuration
)
public abstract class BaseTestConfig {
    // Common configuration for all tests
}
