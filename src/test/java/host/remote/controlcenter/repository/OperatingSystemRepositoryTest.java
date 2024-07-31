package host.remote.controlcenter.repository;

import host.remote.controlcenter.AbstractBaseTest;
import host.remote.controlcenter.TestDataPreloader;
import host.remote.controlcenter.model.OperatingSystem;
import host.remote.controlcenter.model.OperatingSystemType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class OperatingSystemRepositoryTest extends AbstractBaseTest {
    private final OperatingSystemRepository operatingSystemRepository;
    private final TestDataPreloader testDataPreloader;

    @Autowired
    public OperatingSystemRepositoryTest(OperatingSystemRepository operatingSystemRepository,
                                         TestDataPreloader testDataPreloader) {
        this.operatingSystemRepository = operatingSystemRepository;
        this.testDataPreloader = testDataPreloader;
    }

    @BeforeEach
    void setUp() {
        testDataPreloader.clearData();
    }

    @Test
    public void testSaveOperatingSystem() {
        OperatingSystem operatingSystem = new OperatingSystem(null, OperatingSystemType.LINUX);
        OperatingSystem savedOperatingSystem = operatingSystemRepository.save(operatingSystem);

        assertThat(savedOperatingSystem.getId()).isNotNull();
        assertThat(savedOperatingSystem.getName()).isEqualTo(OperatingSystemType.LINUX);
    }

    @Test
    public void testFindOperatingSystemById() {
        OperatingSystem operatingSystem = new OperatingSystem(null, OperatingSystemType.LINUX);
        OperatingSystem savedOperatingSystem = operatingSystemRepository.save(operatingSystem);

        Optional<OperatingSystem> foundOperatingSystem = operatingSystemRepository.findById(savedOperatingSystem.getId());
        assertThat(foundOperatingSystem).isPresent();
        assertThat(foundOperatingSystem.get().getName()).isEqualTo(OperatingSystemType.LINUX);
    }

    @Test
    public void testUpdateOperatingSystem() {
        OperatingSystem operatingSystem = new OperatingSystem(null, OperatingSystemType.LINUX);
        OperatingSystem savedOperatingSystem = operatingSystemRepository.save(operatingSystem);

        savedOperatingSystem.setName(OperatingSystemType.WINDOWS);
        OperatingSystem updatedOperatingSystem = operatingSystemRepository.save(savedOperatingSystem);

        assertThat(updatedOperatingSystem.getName()).isEqualTo(OperatingSystemType.WINDOWS);
    }

    @Test
    public void testDeleteOperatingSystem() {
        OperatingSystem operatingSystem = new OperatingSystem(null, OperatingSystemType.LINUX);
        OperatingSystem savedOperatingSystem = operatingSystemRepository.save(operatingSystem);

        operatingSystemRepository.deleteById(savedOperatingSystem.getId());
        Optional<OperatingSystem> foundOperatingSystem = operatingSystemRepository.findById(savedOperatingSystem.getId());

        assertThat(foundOperatingSystem).isNotPresent();
    }
}
