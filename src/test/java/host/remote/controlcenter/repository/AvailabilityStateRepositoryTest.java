package host.remote.controlcenter.repository;

import host.remote.controlcenter.BaseTestConfig;
import host.remote.controlcenter.TestDataPreloader;
import host.remote.controlcenter.model.AvailabilityState;
import host.remote.controlcenter.model.AvailabilityStateType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class AvailabilityStateRepositoryTest extends BaseTestConfig {
    private final AvailabilityStateRepository availabilityStateRepository;
    private final TestDataPreloader testDataPreloader;

    @Autowired
    public AvailabilityStateRepositoryTest(AvailabilityStateRepository availabilityStateRepository,
                                           TestDataPreloader testDataPreloader) {
        this.availabilityStateRepository = availabilityStateRepository;
        this.testDataPreloader = testDataPreloader;
    }

    @BeforeEach
    void setUp() {
        testDataPreloader.clearData();
    }

    @Test
    public void testSaveAvailabilityState() {
        AvailabilityState availabilityState = new AvailabilityState(null, AvailabilityStateType.ONLINE);
        AvailabilityState savedAvailabilityState = availabilityStateRepository.save(availabilityState);

        assertThat(savedAvailabilityState.getId()).isNotNull();
        assertThat(savedAvailabilityState.getName()).isEqualTo(AvailabilityStateType.ONLINE);
    }

    @Test
    public void testFindAvailabilityStateById() {
        AvailabilityState availabilityState = new AvailabilityState(null, AvailabilityStateType.ONLINE);
        AvailabilityState savedAvailabilityState = availabilityStateRepository.save(availabilityState);

        Optional<AvailabilityState> foundAvailabilityState = availabilityStateRepository.findById(savedAvailabilityState.getId());
        assertThat(foundAvailabilityState).isPresent();
        assertThat(foundAvailabilityState.get().getName()).isEqualTo(AvailabilityStateType.ONLINE);
    }

    @Test
    public void testUpdateAvailabilityState() {
        AvailabilityState availabilityState = new AvailabilityState(null, AvailabilityStateType.ONLINE);
        AvailabilityState savedAvailabilityState = availabilityStateRepository.save(availabilityState);

        savedAvailabilityState.setName(AvailabilityStateType.OFFLINE);
        AvailabilityState updatedAvailabilityState = availabilityStateRepository.save(savedAvailabilityState);

        assertThat(updatedAvailabilityState.getName()).isEqualTo(AvailabilityStateType.OFFLINE);
    }

    @Test
    public void testDeleteAvailabilityState() {
        AvailabilityState availabilityState = new AvailabilityState(null, AvailabilityStateType.ONLINE);
        AvailabilityState savedAvailabilityState = availabilityStateRepository.save(availabilityState);

        availabilityStateRepository.deleteById(savedAvailabilityState.getId());
        Optional<AvailabilityState> foundAvailabilityState = availabilityStateRepository.findById(savedAvailabilityState.getId());

        assertThat(foundAvailabilityState).isNotPresent();
    }
}
