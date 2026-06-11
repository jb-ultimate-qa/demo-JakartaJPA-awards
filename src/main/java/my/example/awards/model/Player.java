package my.example.awards.model;

import jakarta.persistence.*;
import my.example.awards.model.supplemental.Person;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "player")
@NamedQuery(name = "Player.countDistinctByFullNameAndRating",
        query = "select count(distinct p) from Player p where p.fullName = :fullName and p.rating = :rating") // Use parameter 'Carol' and 1300
@NamedNativeQuery(name = "SQL.Player.findNameByWinningAfter2023",
        query = "select p.first_name, a.title from player p join award a on a.winner_id=p.id where year_ > 2023")
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