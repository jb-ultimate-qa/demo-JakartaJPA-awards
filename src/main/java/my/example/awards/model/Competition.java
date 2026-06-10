package my.example.awards.model;

import jakarta.persistence.*;

@Entity
@Table(name = "competition")
@NamedQueries({  // only select queries
        @NamedQuery(name = "Competition.findByCity",                        // by Embedded attribute, parametrized
                query = "select c from Competition c where c.location.city = ?1"),                  // Use parameter 'London'
        @NamedQuery(name = "Competition.findTitleBySecondPlayerFullName",   // by attribute from Mapped Superclass in ManyToOne relation, parametrized
                query = "select title from Competition where secondPlayer.fullName = :fullName"),   // Use parameter 'Carol'
        @NamedQuery(name = "Competition.findRegional",                      // by enum value
                query = "from Competition where competitionLevel = CompetitionLevel.REGIONAL")
})
@NamedStatements({ // since JPA 4, modifying statements
        @NamedStatement(name = "Competition.retitleByCity",
                statement = "update Competition c set c.title = :newTitle where c.location.city = :city"),
        @NamedStatement(name = "Competition.deleteBySecondPlayerFullName",
                statement = "delete from Competition c where c.secondPlayer.fullName = :fullName"),
        @NamedStatement(name = "Competition.promoteRegional",
                statement = "update Competition c set c.competitionLevel = CompetitionLevel.DOMESTIC where c.competitionLevel = CompetitionLevel.REGIONAL")
})
public class Competition {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title")
    private String title;

    @Enumerated
    @Column(name = "competition_level")
    private CompetitionLevel competitionLevel;

    @Embedded
    private Location location;

    @ManyToOne
    @JoinColumn(name = "referee_id")
    private Referee referee;

    @ManyToOne
    @JoinColumn(name = "first_player_id")
    private Player firstPlayer;

    @ManyToOne
    @JoinColumn(name = "second_player_id")
    private Player secondPlayer;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Player getSecondPlayer() {
        return secondPlayer;
    }

    public void setSecondPlayer(Player secondPlayer) {
        this.secondPlayer = secondPlayer;
    }

    public Player getFirstPlayer() {
        return firstPlayer;
    }

    public void setFirstPlayer(Player firstPlayer) {
        this.firstPlayer = firstPlayer;
    }

    public Referee getReferee() {
        return referee;
    }

    public void setReferee(Referee referee) {
        this.referee = referee;
    }

    public CompetitionLevel getCompetitionLevel() {
        return competitionLevel;
    }

    public void setCompetitionLevel(CompetitionLevel competitionLevel) {
        this.competitionLevel = competitionLevel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}