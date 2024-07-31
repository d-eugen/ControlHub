package host.remote.controlcenter;

import host.remote.controlcenter.model.*;
import host.remote.controlcenter.repository.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Random;

@Component
public class TestDataPreloader {

    private final RemoteHostRepository remoteHostRepository;
    private final OperatingSystemInfoRepository operatingSystemRepository;
    private final CpuInfoRepository cpuInfoRepository;
    private final MemoryInfoRepository memoryInfoRepository;
    private final RemoteHostInfoRepository remoteHostInfoRepository;

    public TestDataPreloader(RemoteHostRepository remoteHostRepository,
                             OperatingSystemInfoRepository operatingSystemRepository,
                             CpuInfoRepository cpuInfoRepository,
                             MemoryInfoRepository memoryInfoRepository,
                             RemoteHostInfoRepository remoteHostInfoRepository) {
        this.remoteHostRepository = remoteHostRepository;
        this.operatingSystemRepository = operatingSystemRepository;
        this.cpuInfoRepository = cpuInfoRepository;
        this.memoryInfoRepository = memoryInfoRepository;
        this.remoteHostInfoRepository = remoteHostInfoRepository;
    }

    public void clearAndPreloadData() {
        clearData();
        preloadData();
    }

    public void clearData() {
        remoteHostRepository.deleteAll();
        operatingSystemRepository.deleteAll();
        cpuInfoRepository.deleteAll();
        memoryInfoRepository.deleteAll();
        remoteHostInfoRepository.deleteAll();
    }

    public void preloadData() {
        preloadOperatingSystemTypeEnums();
        preloadRemoteHostData();
    }

    private void preloadOperatingSystemTypeEnums() {
        Arrays.stream(OperatingSystemType.values()).forEach(osType ->
                operatingSystemRepository.save(new OperatingSystemInfo(null, osType, ""))
        );
    }

    private void preloadRemoteHostData() {
        Random random = new Random();
        String[] vendors = {"Intel", "AMD", "Samsung", "Crucial"};

        for (int i = 0; i < 20; i++) {
            CpuInfo cpuInfo = new CpuInfo(null, vendors[random.nextInt(vendors.length)],
                    random.nextInt(4) + 1, random.nextInt(8) + 1, 2.0 + random.nextDouble() * 3.0);
            cpuInfoRepository.save(cpuInfo);

            MemoryInfo memoryInfo = new MemoryInfo(null, vendors[random.nextInt(vendors.length)],
                    8 * (random.nextInt(4) + 1), 2.0 + random.nextDouble() * 2.0);
            memoryInfoRepository.save(memoryInfo);

            OperatingSystemInfo osInfo = operatingSystemRepository.findAll().get(random.nextInt(operatingSystemRepository.findAll().size()));

            RemoteHost remoteHost = new RemoteHost(null, osInfo, cpuInfo, memoryInfo, null, null);
            remoteHostRepository.save(remoteHost);

            RemoteHostInfo remoteHostInfo = new RemoteHostInfo(null, remoteHost, "host-" + i,
                    "192.168.1." + (i + 1), "203.0.113." + (i + 1), LocalDateTime.now(),
                    cpuInfo.getMaxClockSpeedGHz() * (0.5 + random.nextDouble() * 0.5),
                    memoryInfo.getTotalMemoryGb() * (0.2 + random.nextDouble() * 0.6));
            remoteHostInfoRepository.save(remoteHostInfo);

            remoteHost.setRemoteHostInfo(remoteHostInfo);
            remoteHostRepository.save(remoteHost);
        }
    }
}