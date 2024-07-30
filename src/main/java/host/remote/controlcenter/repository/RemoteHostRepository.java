package host.remote.controlcenter.repository;

import host.remote.controlcenter.model.RemoteHost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RemoteHostRepository extends JpaRepository<RemoteHost, Long> {
    Optional<RemoteHost> findByHostname(String hostname);
}
