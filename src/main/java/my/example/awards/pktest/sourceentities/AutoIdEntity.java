package my.example.awards.pktest.sourceentities;

import jakarta.persistence.*;

//@Entity
@Table(name = "auto_id_entity")
public class AutoIdEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}