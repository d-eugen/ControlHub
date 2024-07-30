package host.remote.controlcenter.model;

import host.remote.controlcenter.BaseTestConfig;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OperatingSystemTest extends BaseTestConfig {

    @Test
    void testOperatingSystemConstructorAndGetters() {
        OperatingSystem os = new OperatingSystem(1L, OperatingSystemType.LINUX);
        assertEquals(1L, os.getId());
        assertEquals(OperatingSystemType.LINUX, os.getName());
    }

    @Test
    void testOperatingSystemSetters() {
        OperatingSystem os = new OperatingSystem();
        os.setId(2L);
        os.setName(OperatingSystemType.WINDOWS);
        assertEquals(2L, os.getId());
        assertEquals(OperatingSystemType.WINDOWS, os.getName());
    }

    @Test
    void testOperatingSystemEqualsAndHashCode() {
        OperatingSystem os1 = new OperatingSystem(1L, OperatingSystemType.LINUX);
        OperatingSystem os2 = new OperatingSystem(1L, OperatingSystemType.LINUX);
        OperatingSystem os3 = new OperatingSystem(2L, OperatingSystemType.WINDOWS);

        assertEquals(os1, os2);
        assertNotEquals(os1, os3);
        assertEquals(os1.hashCode(), os2.hashCode());
        assertNotEquals(os1.hashCode(), os3.hashCode());
    }
}