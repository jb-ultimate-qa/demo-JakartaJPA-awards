package my.example.awards.model;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "player")
public class Player extends Person {
    @Column(name = "rating")
    private Integer rating;

    @OneToMany(mappedBy = "winner", orphanRemoval = true)
    private Set<Award> awards = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "player_sport_school",
            joinColumns = @JoinColumn(name = "player_id"),
            inverseJoinColumns = @JoinColumn(name = "sport_school_id"))
    private Set<SportSchool> sportSchools = new LinkedHashSet<>();

    public Set<SportSchool> getSportSchools() {
        return sportSchools;
    }

    public void setSportSchools(Set<SportSchool> sportSchools) {
        this.sportSchools = sportSchools;
    }

    public Set<Award> getAwards() {
        return awards;
    }

    public void setAwards(Set<Award> awards) {
        this.awards = awards;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}