package host.remote.controlcenter.repository;

import host.remote.controlcenter.model.OperatingSystem;
import host.remote.controlcenter.model.OperatingSystemType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OperatingSystemRepository extends JpaRepository<OperatingSystem, Long> {
    Optional<OperatingSystem> findByName(OperatingSystemType name);
}