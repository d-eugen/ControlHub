package host.remote.controlcenter.model;

import host.remote.controlcenter.BaseTestConfig;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class RemoteHostTest extends BaseTestConfig {

    @Test
    void testRemoteHostConstructorAndGetters() {
        OperatingSystem os = new OperatingSystem(1L, OperatingSystemType.LINUX);
        AvailabilityState state = new AvailabilityState(1L, AvailabilityStateType.ONLINE);
        LocalDateTime now = LocalDateTime.now();

        RemoteHost host = new RemoteHost(1L, "host1", "192.168.1.1", os, "Ubuntu 20.04", state, now);

        assertEquals(1L, host.getId());
        assertEquals("host1", host.getHostname());
        assertEquals("192.168.1.1", host.getIpAddress());
        assertEquals(os, host.getOperatingSystem());
        assertEquals("Ubuntu 20.04", host.getOperatingSystemDetails());
        assertEquals(state, host.getAvailabilityState());
        assertEquals(now, host.getLastUpdated());
    }

    @Test
    void testRemoteHostSetters() {
        RemoteHost host = new RemoteHost();
        OperatingSystem os = new OperatingSystem(1L, OperatingSystemType.WINDOWS);
        AvailabilityState state = new AvailabilityState(1L, AvailabilityStateType.OFFLINE);
        LocalDateTime now = LocalDateTime.now();

        host.setId(2L);
        host.setHostname("host2");
        host.setIpAddress("10.0.0.1");
        host.setOperatingSystem(os);
        host.setOperatingSystemDetails("Windows 10");
        host.setAvailabilityState(state);
        host.setLastUpdated(now);

        assertEquals(2L, host.getId());
        assertEquals("host2", host.getHostname());
        assertEquals("10.0.0.1", host.getIpAddress());
        assertEquals(os, host.getOperatingSystem());
        assertEquals("Windows 10", host.getOperatingSystemDetails());
        assertEquals(state, host.getAvailabilityState());
        assertEquals(now, host.getLastUpdated());
    }

    @Test
    void testRemoteHostEqualsAndHashCode() {
        OperatingSystem os1 = new OperatingSystem(1L, OperatingSystemType.LINUX);
        AvailabilityState state1 = new AvailabilityState(1L, AvailabilityStateType.ONLINE);
        LocalDateTime now = LocalDateTime.now();

        RemoteHost host1 = new RemoteHost(1L, "host1", "192.168.1.1", os1, "Ubuntu 20.04", state1, now);
        RemoteHost host2 = new RemoteHost(1L, "host1", "192.168.1.1", os1, "Ubuntu 20.04", state1, now);
        RemoteHost host3 = new RemoteHost(2L, "host2", "10.0.0.1", os1, "Ubuntu 20.04", state1, now);

        assertEquals(host1, host2);
        assertNotEquals(host1, host3);
        assertEquals(host1.hashCode(), host2.hashCode());
        assertNotEquals(host1.hashCode(), host3.hashCode());
    }
}