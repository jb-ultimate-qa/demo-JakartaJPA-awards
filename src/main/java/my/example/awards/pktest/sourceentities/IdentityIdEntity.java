package my.example.awards.pktest.sourceentities;

import jakarta.persistence.*;

//@Entity
@Table(name = "identity_id_entity")
public class IdentityIdEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}