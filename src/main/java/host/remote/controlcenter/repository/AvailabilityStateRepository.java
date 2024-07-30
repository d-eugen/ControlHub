package host.remote.controlcenter.repository;


import host.remote.controlcenter.model.AvailabilityState;
import host.remote.controlcenter.model.AvailabilityStateType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AvailabilityStateRepository extends JpaRepository<AvailabilityState, Long> {
    Optional<AvailabilityState> findByName(AvailabilityStateType name);
}