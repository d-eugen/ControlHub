package host.remote.controlcenter.repository;

import host.remote.controlcenter.model.OperatingSystemInfo;
import host.remote.controlcenter.model.OperatingSystemType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OperatingSystemInfoRepository extends JpaRepository<OperatingSystemInfo, Long> {
    Optional<OperatingSystemInfo> findByName(OperatingSystemType name);
}