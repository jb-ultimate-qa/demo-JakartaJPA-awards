package my.example.awards.hib_any.props;

import jakarta.persistence.*;
import org.hibernate.annotations.AnyDiscriminatorValue;
import org.hibernate.annotations.AnyKeyJavaClass;
import org.hibernate.annotations.ManyToAny;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "prop_obj")
public class MyObject {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToAny
    @Column(name = "property_type", length = 2)
    @AnyKeyJavaClass(Long.class)
    @AnyDiscriminatorValue(discriminator = "S", entity = StringProperty.class)
    @AnyDiscriminatorValue(discriminator = "I", entity = IntegerProperty.class)
    @JoinTable(name = "prop_obj_props",
            joinColumns = @JoinColumn(name = "object_id"),
            inverseJoinColumns = @JoinColumn(name = "property_id"))
    private List<Property> properties = new ArrayList<>();

//    @ManyToMany
//    @JoinTable(name = "prop_obj_strprops",
//            joinColumns = @JoinColumn(name = "object_id"),
//            inverseJoinColumns = @JoinColumn(name = "property_id"))
//    private List<StringProperty> strProperties = new ArrayList<>();

}