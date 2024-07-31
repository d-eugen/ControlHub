package host.remote.controlcenter.repository;

import host.remote.controlcenter.model.MemoryInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoryInfoRepository extends JpaRepository<MemoryInfo, Long> {
}
