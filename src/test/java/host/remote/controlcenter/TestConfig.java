package host.remote.controlcenter;

import host.remote.controlcenter.repository.AvailabilityStateRepository;
import host.remote.controlcenter.repository.OperatingSystemRepository;
import host.remote.controlcenter.repository.RemoteHostRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

    @Bean
    public TestDataPreloader testDataPreloader(RemoteHostRepository remoteHostRepository,
                                               AvailabilityStateRepository availabilityStateRepository,
                                               OperatingSystemRepository operatingSystemRepository) {
        return new TestDataPreloader(remoteHostRepository,
                availabilityStateRepository,
                operatingSystemRepository);
    }
}