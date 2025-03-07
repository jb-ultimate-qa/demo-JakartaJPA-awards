package my.example.awards.pktest.sourceentities;

import jakarta.persistence.*;

//@Entity
@Table(name = "table_id_entity")
public class TableIdEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "table-generator")
    @TableGenerator(name = "table-generator", table = "table_ids",
            pkColumnName = "seq_id", valueColumnName = "seq_value")
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}