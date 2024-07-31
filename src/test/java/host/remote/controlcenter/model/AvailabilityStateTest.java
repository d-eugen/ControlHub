package host.remote.controlcenter.model;

import host.remote.controlcenter.AbstractBaseTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Core domain object test
 */
class AvailabilityStateTest extends AbstractBaseTest {

    @Test
    void testAvailabilityStateConstructorAndGetters() {
        AvailabilityState state = new AvailabilityState(1L, AvailabilityStateType.ONLINE);
        assertEquals(1L, state.getId());
        assertEquals(AvailabilityStateType.ONLINE, state.getName());
    }

    @Test
    void testAvailabilityStateSetters() {
        AvailabilityState state = new AvailabilityState();
        state.setId(2L);
        state.setName(AvailabilityStateType.OFFLINE);
        assertEquals(2L, state.getId());
        assertEquals(AvailabilityStateType.OFFLINE, state.getName());
    }

    @Test
    void testAvailabilityStateEqualsAndHashCode() {
        AvailabilityState state1 = new AvailabilityState(1L, AvailabilityStateType.ONLINE);
        AvailabilityState state2 = new AvailabilityState(1L, AvailabilityStateType.ONLINE);
        AvailabilityState state3 = new AvailabilityState(2L, AvailabilityStateType.OFFLINE);

        assertEquals(state1, state2);
        assertNotEquals(state1, state3);
        assertEquals(state1.hashCode(), state2.hashCode());
        assertNotEquals(state1.hashCode(), state3.hashCode());
    }
}