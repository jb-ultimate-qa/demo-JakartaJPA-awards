package my.example.awards.model;

import jakarta.persistence.*;
import my.example.awards.model.supplemental.Person;

@Entity
@Table(name = "referee")
public class Referee extends Person {
    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "license_id")
    private License license;

    public License getLicense() {
        return license;
    }

    public void setLicense(License license) {
        this.license = license;
    }

    @Override
    public String toString() {
        return "Referee " + getFullName() + " with license number " + license.getLicenseNumber();
    }
}