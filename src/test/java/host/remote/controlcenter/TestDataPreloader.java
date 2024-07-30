package host.remote.controlcenter;

import host.remote.controlcenter.model.AvailabilityState;
import host.remote.controlcenter.model.AvailabilityStateType;
import host.remote.controlcenter.model.OperatingSystem;
import host.remote.controlcenter.model.OperatingSystemType;
import host.remote.controlcenter.model.RemoteHost;
import host.remote.controlcenter.repository.AvailabilityStateRepository;
import host.remote.controlcenter.repository.OperatingSystemRepository;
import host.remote.controlcenter.repository.RemoteHostRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Component
public class TestDataPreloader {
    private final RemoteHostRepository remoteHostRepository;
    private final AvailabilityStateRepository availabilityStateRepository;
    private final OperatingSystemRepository operatingSystemRepository;

    public TestDataPreloader(RemoteHostRepository remoteHostRepository,
                             AvailabilityStateRepository availabilityStateRepository,
                             OperatingSystemRepository operatingSystemRepository) {
        this.remoteHostRepository = remoteHostRepository;
        this.availabilityStateRepository = availabilityStateRepository;
        this.operatingSystemRepository = operatingSystemRepository;
    }

    public void preloadData() {
        preloadAvailabilityStateTypeEnums();
        preloadOperatingSystemTypeEnums();
        preloadRemoteHostData();
    }

    private void preloadAvailabilityStateTypeEnums() {
        // Preload AvailabilityStateType enums
        Arrays.stream(AvailabilityStateType.values()).forEach(stateType ->
                availabilityStateRepository.save(new AvailabilityState(null, stateType))
        );
    }

    private void preloadOperatingSystemTypeEnums() {
        // Preload OperatingSystemType enums
        Arrays.stream(OperatingSystemType.values()).forEach(osType ->
                operatingSystemRepository.save(new OperatingSystem(null, osType))
        );
    }

    private void  preloadRemoteHostData()
    {
        // Preload 5 rows into RemoteHost
        OperatingSystem linux = operatingSystemRepository.findByName(OperatingSystemType.LINUX).orElse(null);
        OperatingSystem windows = operatingSystemRepository.findByName(OperatingSystemType.WINDOWS).orElse(null);
        AvailabilityState online = availabilityStateRepository.findByName(AvailabilityStateType.ONLINE).orElse(null);
        AvailabilityState offline = availabilityStateRepository.findByName(AvailabilityStateType.OFFLINE).orElse(null);

        remoteHostRepository.save(new RemoteHost(null, "host1", "192.168.0.1", linux, "Ubuntu 20.04", online, LocalDateTime.now()));
        remoteHostRepository.save(new RemoteHost(null, "host2", "192.168.0.2", windows, "Windows 10", offline, LocalDateTime.now()));
        remoteHostRepository.save(new RemoteHost(null, "host3", "192.168.0.3", linux, "CentOS 8", online, LocalDateTime.now()));
        remoteHostRepository.save(new RemoteHost(null, "host4", "192.168.0.4", windows, "Windows Server 2019", offline, LocalDateTime.now()));
        remoteHostRepository.save(new RemoteHost(null, "host5", "192.168.0.5", linux, "Debian 10", online, LocalDateTime.now()));
    }
}
