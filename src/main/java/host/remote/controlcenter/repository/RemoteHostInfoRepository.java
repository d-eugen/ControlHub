package host.remote.controlcenter.repository;

import host.remote.controlcenter.model.RemoteHostInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RemoteHostInfoRepository extends JpaRepository<RemoteHostInfo, Long> {
}
