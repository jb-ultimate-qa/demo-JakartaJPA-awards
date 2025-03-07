package my.example.awards.pktest.sourceentities;

import jakarta.persistence.*;

//@Entity
@Table(name = "seq_by_orm_id_entity")
public class SeqByOrmIdEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}