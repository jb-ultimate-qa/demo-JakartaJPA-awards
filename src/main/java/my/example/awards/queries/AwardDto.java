package my.example.awards.queries;

import my.example.awards.model.Award;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link Award}
 */
public class AwardDto implements Serializable {
    private final Short year;
    private final String title;

    public AwardDto(Short year, String title) {
        this.year = year;
        this.title = title;
    }

    public AwardDto(String title) {
        this.year = 2022;
        this.title = title;
    }

    public Short getYear() {
        return year;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AwardDto entity = (AwardDto) o;
        return Objects.equals(this.year, entity.year) &&
                Objects.equals(this.title, entity.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, title);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "year = " + year + ", " +
                "title = " + title + ")";
    }
}