package host.remote.controlcenter.repository;

import host.remote.controlcenter.AbstractBaseTest;
import host.remote.controlcenter.TestDataPreloader;
import host.remote.controlcenter.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class RemoteHostRepositoryTest extends AbstractBaseTest {
    private final RemoteHostRepository remoteHostRepository;
    private final AvailabilityStateRepository availabilityStateRepository;
    private final OperatingSystemRepository operatingSystemRepository;
    private final TestDataPreloader testDataPreloader;

    private OperatingSystem linuxOs;
    private AvailabilityState onlineState;

    @Autowired
    public RemoteHostRepositoryTest(RemoteHostRepository remoteHostRepository,
                                    AvailabilityStateRepository availabilityStateRepository,
                                    OperatingSystemRepository operatingSystemRepository,
                                    TestDataPreloader testDataPreloader) {
        this.remoteHostRepository = remoteHostRepository;
        this.availabilityStateRepository = availabilityStateRepository;
        this.operatingSystemRepository = operatingSystemRepository;
        this.testDataPreloader = testDataPreloader;
    }

    @BeforeEach
    public void setup() {
        testDataPreloader.clearData();

        linuxOs = new OperatingSystem(null, OperatingSystemType.LINUX);
        onlineState = new AvailabilityState(null, AvailabilityStateType.ONLINE);
        operatingSystemRepository.save(linuxOs);
        availabilityStateRepository.save(onlineState);
    }

    @Test
    public void testSaveRemoteHost() {
        RemoteHost remoteHost = new RemoteHost(null, "TestHost", "192.168.1.1", linuxOs, "Ubuntu 20.04", onlineState, LocalDateTime.now());
        RemoteHost savedRemoteHost = remoteHostRepository.save(remoteHost);

        assertThat(savedRemoteHost.getId()).isNotNull();
        assertThat(savedRemoteHost.getHostname()).isEqualTo("TestHost");
    }

    @Test
    public void testFindRemoteHostById() {
        RemoteHost remoteHost = new RemoteHost(null, "TestHost", "192.168.1.1", linuxOs, "Ubuntu 20.04", onlineState, LocalDateTime.now());
        RemoteHost savedRemoteHost = remoteHostRepository.save(remoteHost);

        Optional<RemoteHost> foundRemoteHost = remoteHostRepository.findById(savedRemoteHost.getId());
        assertThat(foundRemoteHost).isPresent();
        assertThat(foundRemoteHost.get().getHostname()).isEqualTo("TestHost");
    }

    @Test
    public void testUpdateRemoteHost() {
        RemoteHost remoteHost = new RemoteHost(null, "TestHost", "192.168.1.1", linuxOs, "Ubuntu 20.04", onlineState, LocalDateTime.now());
        RemoteHost savedRemoteHost = remoteHostRepository.save(remoteHost);

        savedRemoteHost.setHostname("UpdatedHost");
        RemoteHost updatedRemoteHost = remoteHostRepository.save(savedRemoteHost);

        assertThat(updatedRemoteHost.getHostname()).isEqualTo("UpdatedHost");
    }

    @Test
    public void testDeleteRemoteHost() {
        RemoteHost remoteHost = new RemoteHost(null, "TestHost", "192.168.1.1", linuxOs, "Ubuntu 20.04", onlineState, LocalDateTime.now());
        RemoteHost savedRemoteHost = remoteHostRepository.save(remoteHost);

        remoteHostRepository.deleteById(savedRemoteHost.getId());
        Optional<RemoteHost> foundRemoteHost = remoteHostRepository.findById(savedRemoteHost.getId());

        assertThat(foundRemoteHost).isNotPresent();
    }
}
