package my.example.awards.pktest.sourceentities;

import jakarta.persistence.*;

import java.util.UUID;

//@Entity
@Table(name = "uuid_entity")
public class UuidEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

}