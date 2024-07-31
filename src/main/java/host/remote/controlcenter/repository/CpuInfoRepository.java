package host.remote.controlcenter.repository;

import host.remote.controlcenter.model.CpuInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CpuInfoRepository extends JpaRepository<CpuInfo, Long> {
}
