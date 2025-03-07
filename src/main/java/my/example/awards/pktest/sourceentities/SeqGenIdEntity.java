package my.example.awards.pktest.sourceentities;

import jakarta.persistence.*;

//@Entity
@Table(name = "seq_gen_id_entity")
public class SeqGenIdEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SeqGenIdEntity_SEQ")
    @SequenceGenerator(name = "SeqGenIdEntity_SEQ")
    @Column(name = "id", nullable = false)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}