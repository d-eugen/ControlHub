package host.remote.controlcenter;

import host.remote.controlcenter.model.OperatingSystemInfo;
import host.remote.controlcenter.model.OperatingSystemType;
import host.remote.controlcenter.repository.OperatingSystemInfoRepository;
import host.remote.controlcenter.repository.RemoteHostRepository;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class TestDataPreloader {
    private final RemoteHostRepository remoteHostRepository;
    private final OperatingSystemInfoRepository operatingSystemRepository;

    public TestDataPreloader(RemoteHostRepository remoteHostRepository,
                             OperatingSystemInfoRepository operatingSystemRepository) {
        this.remoteHostRepository = remoteHostRepository;
        this.operatingSystemRepository = operatingSystemRepository;
    }

    public void clearAndPreloadData() {
        clearData();
        preloadData();
    }

    public void clearData() {
        remoteHostRepository.deleteAll();
        operatingSystemRepository.deleteAll();
    }
    public void preloadData() {
        preloadOperatingSystemTypeEnums();
        preloadRemoteHostData();
    }


    private void preloadOperatingSystemTypeEnums() {
        // Preload OperatingSystemType enums
        Arrays.stream(OperatingSystemType.values()).forEach(osType ->
                operatingSystemRepository.save(new OperatingSystemInfo(null, osType, ""))
        );
    }

    private void  preloadRemoteHostData()
    {

    }
}
