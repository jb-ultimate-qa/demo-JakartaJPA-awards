package my.example.awards.model;

import jakarta.persistence.*;

@Entity
@Table(name = "license")
@NamedQuery(name = "License.findByLicenseNumber103",                // by Embedded attribute, JPQL
        query = "select licenseNumber, licenseInfo.serialNumber, licenseInfo.description " +
        "from License l where l.licenseInfo.serialNumber = 103")
@NamedNativeQuery(name = "SQL.License.findByLicenseNumber102",      // by Embedded attribute, SQL
        query = "select license_number, sn, descr from license where sn = 102")
public class License {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "license_number")
    private String licenseNumber;

    @Embedded
    private LicenseInfo licenseInfo;

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LicenseInfo getLicenseInfo() {
        return licenseInfo;
    }

    public void setLicenseInfo(LicenseInfo licenseInfo) {
        this.licenseInfo = licenseInfo;
    }
}