package my.example.awards.hib_any.simple;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "s_parent")
public class Parent {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "owner")
    private Set<Child> children = new HashSet<>();
}
