package my.example.awards.pktest.sourceentities;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

//@Entity
@Table(name = "seq_gen_gen_id_entity")
public class SeqGenGenIdEntity {
    @Id
    @GeneratedValue(generator = "sequence-generator")
    @SequenceGenerator(
            name = "sequence-generator",
            sequenceName = "seq_gen_gen_id_entity_seq",
            initialValue = 5,
            allocationSize = 2
    )
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}