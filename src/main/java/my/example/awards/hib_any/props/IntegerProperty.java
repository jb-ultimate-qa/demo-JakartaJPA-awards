package my.example.awards.hib_any.props;

import jakarta.persistence.*;

@Entity
@Table(name = "prop_int")
public class IntegerProperty {
    @Id
    private Long id;

    private String name;
    private Integer value;
}