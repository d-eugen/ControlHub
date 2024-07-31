package host.remote.controlcenter;

import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

/**
 * Class for managing common test context configuration
 */
@ActiveProfiles("test")
@ContextConfiguration(initializers = DatasourceInitializer.class) // Add custom context initializer
@Import({TestDataPreloader.class})
public abstract class AbstractBaseTest {
    // Common configuration for all tests
}
