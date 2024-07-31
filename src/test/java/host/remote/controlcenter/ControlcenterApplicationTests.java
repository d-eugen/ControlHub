package host.remote.controlcenter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ControlcenterApplicationTests extends AbstractBaseTest {
	@Autowired
	private TestDataPreloader testDataPreloader;

}