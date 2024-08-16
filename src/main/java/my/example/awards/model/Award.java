package my.example.awards.model;

import jakarta.persistence.*;

@Entity
@Table(name = "award")
public class Award {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "year_", nullable = false)
    private Short year;

    @Column(name = "title", nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "winner_id")
    private Player winner;

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public Short getYear() {
        return year;
    }

    public void setYear(Short year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " (" + year + " - " + title + ")";
    }
}