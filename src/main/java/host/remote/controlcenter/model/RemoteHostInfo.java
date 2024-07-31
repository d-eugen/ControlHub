package host.remote.controlcenter.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RemoteHostInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "remote_host_id", unique = true)
    private RemoteHost remoteHost;

    private String hostname;
    private String ipAddressLocal;
    private String ipAddressExternal;
    private LocalDateTime lastUpdated;
    private double clockSpeedGHz;
    private double memoryUsedGb;
}
