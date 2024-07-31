package host.remote.controlcenter.service;

import host.remote.controlcenter.model.RemoteHost;
import host.remote.controlcenter.repository.RemoteHostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RemoteHostService {

    private final RemoteHostRepository remoteHostRepository;

    public RemoteHostService(RemoteHostRepository remoteHostRepository) {
        this.remoteHostRepository = remoteHostRepository;
    }

    public List<RemoteHost> getAllRemoteHosts() {
        return remoteHostRepository.findAll();
    }

    public RemoteHost getRemoteHostById(Long id) {
        return remoteHostRepository.findById(id).orElse(null);
    }

    public RemoteHost saveRemoteHost(RemoteHost remoteHost) {
        return remoteHostRepository.save(remoteHost);
    }

    public void deleteRemoteHost(Long id) {
        remoteHostRepository.deleteById(id);
    }
}