package my.example.awards.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "award")
public class Award {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "year", nullable = false)
    private Short year;

    @Column(name = "title", nullable = false)
    private String title;

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