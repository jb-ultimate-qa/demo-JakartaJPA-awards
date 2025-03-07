package my.example.awards.pktest.sourceentities;

import jakarta.persistence.*;

import java.util.UUID;

//@Entity
@Table(name = "auto_uuid_entity")
public class AutoUuidEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

}