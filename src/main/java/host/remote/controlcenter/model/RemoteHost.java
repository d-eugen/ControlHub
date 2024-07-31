package host.remote.controlcenter.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RemoteHost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "operating_system_info_id")
    private OperatingSystemInfo operatingSystemInfo;

    @ManyToOne
    @JoinColumn(name = "cpu_info_id")
    private CpuInfo cpuInfo;

    @ManyToOne
    @JoinColumn(name = "memory_info_id")
    private MemoryInfo memoryInfo;

    @OneToOne(mappedBy = "remoteHost", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private RemoteHostInfo remoteHostInfo;

    @Transient
    @JsonInclude
    @JsonProperty("availabilityState")
    private AvailabilityStateType availabilityState;

    public AvailabilityStateType getAvailabilityState() {
        availabilityState =  AvailabilityStateCalculator.calculateAvailabilityState(remoteHostInfo.getLastUpdated());
        return availabilityState;
    }

    // Not a part of the JPA persistent state
    static class AvailabilityStateCalculator {
        private final static int AVAILABILITY_THRESHOLD_MS = 60000;
        static AvailabilityStateType calculateAvailabilityState(LocalDateTime lastUpdatedInUTC) {
            if (lastUpdatedInUTC == null) {
                return AvailabilityStateType.OFFLINE;
            }
            LocalDateTime nowInUTC = LocalDateTime.now(ZoneId.of("UTC"));
            Duration duration = Duration.between(lastUpdatedInUTC, nowInUTC);
            if (duration.toSeconds() < AVAILABILITY_THRESHOLD_MS) {
                return AvailabilityStateType.ONLINE;
            } else {
                return AvailabilityStateType.OFFLINE;
            }
        }
    }
}
