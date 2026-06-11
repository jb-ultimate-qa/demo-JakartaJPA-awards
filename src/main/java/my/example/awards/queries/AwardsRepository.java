package my.example.awards.queries;

import jakarta.persistence.query.JakartaQuery;
import jakarta.persistence.query.NativeQuery;
import my.example.awards.model.Award;
import my.example.awards.model.Competition;
import my.example.awards.model.License;
import my.example.awards.model.Player;

import java.util.List;

/**
 * Jakarta Persistence 4 / Hibernate 8 repository-style queries.
 * <p>
 * Both annotations live in {@code jakarta.persistence.query}.
 * <ul>
 *   <li>{@link JakartaQuery} - JPQL / HQL / EQL</li>
 *   <li>{@link NativeQuery}  - SQL</li>
 * </ul>
 * The IDE must inject the proper language into the {@code value()} string.
 */
public interface AwardsRepository {

    // ---- @JakartaQuery (JPQL) -------------------------------------------------

    @JakartaQuery("from Award a where a.year = :year")
    List<Award> findByYear(short year);

    @JakartaQuery("select a from Award a where a.title like :pattern")
    List<Award> findByTitlePattern(String pattern);

    // attribute from Mapped Superclass of a ManyToOne attribute
    @JakartaQuery("select a from Award a where a.winner.fullName = :name")
    List<Award> findByWinnerName(String name);

    // Embedded attribute
    @JakartaQuery("select c from Competition c where c.location.city = :city")
    List<Competition> competitionsInCity(String city);

    // enum literal reference
    @JakartaQuery("from Competition c where c.competitionLevel = my.example.awards.model.CompetitionLevel.REGIONAL")
    List<Competition> regionalCompetitions();

    // returns a count
    @JakartaQuery("select count(distinct p) from Player p where p.rating >= :minRating")
    long countByMinRating(int minRating);

    // join
    @JakartaQuery("select p from Player p join p.sportSchools s where s.title = :school")
    List<Player> playersBySchool(String school);

    // DML (Jakarta Query Language also covers update/delete)
    @JakartaQuery("update Award a set a.title = :newTitle where a.id = :id")
    int retitle(int id, String newTitle);

    // --- IDE checks for JakartaQuery ---
//    @JakartaQuery("from Award a where a.wrongAttr = :year")        // invalid schema
//    List<Award> invalidSchema(short year);
//    @JakartaQuery("from Award a wrong_keyword")                    // invalid syntax
//    List<Award> invalidSyntax();
//    @JakartaQuery("from Award a where a.")                         // completion of attribute name
//    List<Award> completionAttr();
//    @JakartaQuery("from Award a wh")                               // completion of keyword
//    List<Award> completionKeyword();

    // ---- @NativeQuery (SQL) --------------------------------------------------

    @NativeQuery("select * from award where year_ > :since")
    List<Award> findRawAfter(short since);

    @NativeQuery("select p.first_name, p.rating from player p where p.rating >= :minRating order by p.rating desc")
    List<Object[]> playerRatingsRaw(int minRating);

    @NativeQuery("select l.license_number, l.sn, l.descr from license l where l.sn = :sn")
    List<Object[]> licenseRowBySerial(int sn);

    // DML in native SQL
    @NativeQuery("update award set title = :newTitle where id = :id")
    int retitleNative(int id, String newTitle);

    @NativeQuery("delete from player_sport_school where player_id = :playerId")
    int detachPlayerFromAllSchools(long playerId);

    // --- IDE checks for NativeQuery ---
//    @NativeQuery("select * from award where award.wrong_column > 2023") // invalid schema
//    List<Award> nativeInvalidSchema();
//    @NativeQuery("select * from award as a wrong_keyword")              // invalid syntax
//    List<Award> nativeInvalidSyntax();
//    @NativeQuery("select * from award where award.")                    // column completion
//    List<Award> nativeCompletionColumn();
//    @NativeQuery("select * from award wh")                              // keyword completion
//    List<Award> nativeCompletionKeyword();

}
