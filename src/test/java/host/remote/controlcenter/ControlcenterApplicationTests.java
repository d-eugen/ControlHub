package host.remote.controlcenter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ControlcenterApplicationTests extends AbstractBaseTest {

	@Autowired
	private TestDataPreloader testDataPreloader;

	@BeforeEach
	void setUp() {
		testDataPreloader.clearAndPreloadData();
	}

	@Test
	void contextLoads() {
		// This test will fail if the application context cannot start
	}
}