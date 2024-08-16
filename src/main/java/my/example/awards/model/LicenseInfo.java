package my.example.awards.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public record LicenseInfo(
        @Column(name = "sn") Integer serialNumber,
        @Column(name = "descr") String description) {
}
