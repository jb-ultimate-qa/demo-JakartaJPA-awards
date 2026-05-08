package my.example.awards.hib_any.props;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "prop_str")
public class StringProperty {
    @Id
    private Long id;

    private String name;
    private String value;

//    @ManyToMany(mappedBy = "strProperties")
//    private List<MyObject> myStrObjects = new ArrayList<>();

    // is not allowed!
//    @ManyToMany(mappedBy = "properties")
//    private List<MyObject> myObjects = new ArrayList<>();
}