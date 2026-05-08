package my.example.awards.hib_any.simple;

import jakarta.persistence.*;
import org.hibernate.annotations.Any;
import org.hibernate.annotations.AnyDiscriminatorValue;
import org.hibernate.annotations.AnyKeyJavaClass;

@Entity
@Table(name = "s_child")
public class Child {
    @Id
    @GeneratedValue
    private Long id;

    @Any
    @AnyKeyJavaClass(Long.class)
    @JoinColumn(name = "OWNER_ID")
    @Column(name="p_type")
    @AnyDiscriminatorValue(discriminator="P", entity= Parent.class)
    private Object owner;
}