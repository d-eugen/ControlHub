package host.remote.controlcenter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import host.remote.controlcenter.model.RemoteHost;
import host.remote.controlcenter.model.OperatingSystem;
import host.remote.controlcenter.model.AvailabilityState;
import host.remote.controlcenter.model.OperatingSystemType;
import host.remote.controlcenter.model.AvailabilityStateType;
import host.remote.controlcenter.repository.RemoteHostRepository;
import host.remote.controlcenter.repository.OperatingSystemRepository;
import host.remote.controlcenter.repository.AvailabilityStateRepository;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
@ContextConfiguration(initializers = DatasourceInitializer.class)
class ControlcenterApplicationTests {

	@Autowired
	private TestDataPreloader testDataPreloader;

	@Autowired
	private RemoteHostRepository remoteHostRepository;

	@Autowired
	private OperatingSystemRepository operatingSystemRepository;

	@Autowired
	private AvailabilityStateRepository availabilityStateRepository;

	@BeforeEach
	void setUp() {
		testDataPreloader.clearAndPreloadData();
	}

	@Test
	void contextLoads() {
		// This test will fail if the application context cannot start
	}

	@Test
	void testAvailabilityStatePreloaded() {
		List<AvailabilityState> states = availabilityStateRepository.findAll();
		assertEquals(2, states.size(), "Should have 2 availability states");
		assertTrue(states.stream().anyMatch(state -> state.getName() == AvailabilityStateType.ONLINE), "ONLINE state should be present");
		assertTrue(states.stream().anyMatch(state -> state.getName() == AvailabilityStateType.OFFLINE), "OFFLINE state should be present");
	}

	@Test
	void testOperatingSystemPreloaded() {
		List<OperatingSystem> osList = operatingSystemRepository.findAll();
		assertEquals(4, osList.size(), "Should have 4 operating systems");
		assertTrue(osList.stream().anyMatch(os -> os.getName() == OperatingSystemType.LINUX), "LINUX OS should be present");
		assertTrue(osList.stream().anyMatch(os -> os.getName() == OperatingSystemType.WINDOWS), "WINDOWS OS should be present");
		assertTrue(osList.stream().anyMatch(os -> os.getName() == OperatingSystemType.MACOS), "MACOS should be present");
		assertTrue(osList.stream().anyMatch(os -> os.getName() == OperatingSystemType.OTHER), "OTHER OS should be present");
	}

	@Test
	void testRemoteHostsPreloaded() {
		List<RemoteHost> hosts = remoteHostRepository.findAll();
		assertEquals(5, hosts.size(), "Should have 5 remote hosts");

		// Test specific host data
		RemoteHost host1 = remoteHostRepository.findByHostname("host1").orElse(null);
		assertNotNull(host1, "host1 should exist");
		assertEquals("192.168.0.1", host1.getIpAddress(), "IP address should match for host1");
		assertEquals("Ubuntu 20.04", host1.getOperatingSystemDetails(), "OS details should match for host1");
		assertEquals(OperatingSystemType.LINUX, host1.getOperatingSystem().getName(), "OS should be LINUX for host1");
		assertEquals(AvailabilityStateType.ONLINE, host1.getAvailabilityState().getName(), "State should be ONLINE for host1");

		RemoteHost host5 = remoteHostRepository.findByHostname("host5").orElse(null);
		assertNotNull(host5, "host5 should exist");
		assertEquals("192.168.0.5", host5.getIpAddress(), "IP address should match for host5");
		assertEquals("Debian 10", host5.getOperatingSystemDetails(), "OS details should match for host5");
		assertEquals(OperatingSystemType.LINUX, host5.getOperatingSystem().getName(), "OS should be LINUX for host5");
		assertEquals(AvailabilityStateType.ONLINE, host5.getAvailabilityState().getName(), "State should be ONLINE for host5");
	}

	@Test
	void testRemoteHostDistribution() {
		long linuxHosts = remoteHostRepository.findAll().stream()
				.filter(host -> host.getOperatingSystem().getName() == OperatingSystemType.LINUX)
				.count();
		assertEquals(3, linuxHosts, "Should have 3 Linux hosts");

		long windowsHosts = remoteHostRepository.findAll().stream()
				.filter(host -> host.getOperatingSystem().getName() == OperatingSystemType.WINDOWS)
				.count();
		assertEquals(2, windowsHosts, "Should have 2 Windows hosts");

		long onlineHosts = remoteHostRepository.findAll().stream()
				.filter(host -> host.getAvailabilityState().getName() == AvailabilityStateType.ONLINE)
				.count();
		assertEquals(3, onlineHosts, "Should have 3 online hosts");

		long offlineHosts = remoteHostRepository.findAll().stream()
				.filter(host -> host.getAvailabilityState().getName() == AvailabilityStateType.OFFLINE)
				.count();
		assertEquals(2, offlineHosts, "Should have 2 offline hosts");
	}
}