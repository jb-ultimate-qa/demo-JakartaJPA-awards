package my.example.awards.queries;

import jakarta.persistence.EntityAgent;
import jakarta.persistence.EntityHandler;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Statement;
import jakarta.persistence.StatementOrTypedQuery;
import my.example.awards.model.Award;
import my.example.awards.model.Competition;
import my.example.awards.model.License;
import my.example.awards.model.Player;

import java.util.List;

/**
 * Jakarta Persistence 4 / Hibernate 8 - EntityAgent / EntityHandler samples.
 * <p>
 * Language injection responsibilities (same dialect rules as on EntityManager):
 * <ul>
 *   <li>{@code createQuery(String...)} / {@code createStatement(String)} - JPQL</li>
 *   <li>{@code createNativeQuery(String...)} / {@code createNativeStatement(String)} - SQL</li>
 *   <li>{@code createNamedQuery(String)} / {@code createNamedStatement(String)} - navigation to a {@code @NamedQuery} / {@code @NamedStatement} / {@code @NamedNativeQuery} / {@code @NamedNativeStatement}</li>
 * </ul>
 */
public class EntityAgentQueries {

    private static final EntityManagerFactory entityManagerFactory;

    static {
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("jpa-default");
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    // ---- createQuery (JPQL / HQL) --------------------------------------------

    public static List<Award> getAllAwards(EntityAgent agent) {
        return agent.createQuery("from Award a", Award.class).getResultList();
    }

    public static List<Award> getAwardsByYear(EntityAgent agent, short year) {
        return agent
                .createQuery("select a from Award a where a.year = :year", Award.class)
                .setParameter("year", year)
                .getResultList();
    }

    public static List<Competition> nationalCompetitions(EntityAgent agent) {
        return agent.createQuery(
                "from Competition c where c.competitionLevel = my.example.awards.model.CompetitionLevel.NATIONAL",
                Competition.class).getResultList();
    }

    public static List<Player> playersBySchool(EntityHandler handler, String school) {
        // EntityHandler (super-interface) also defines createQuery
        return handler.createQuery(
                        "select p from Player p join p.sportSchools s where s.title = :school",
                        Player.class)
                .setParameter("school", school)
                .getResultList();
    }

    public static void testJpqlQueriesOnAgent(EntityAgent agent) {
        StatementOrTypedQuery valid =
                agent.createQuery("from Award a where a.title like '%er'");
//        StatementOrTypedQuery invalidSchema = agent.createQuery("from Award a where a.wrongAttr = 1");
//        StatementOrTypedQuery invalidSyntax = agent.createQuery("from Award a wrong_keyword");
//        StatementOrTypedQuery checkSchemaCompletion = agent.createQuery("from Award a where a.");
//        StatementOrTypedQuery checkSyntaxCompletion = agent.createQuery("from Award a wh");
    }

    // ---- createStatement (JPQL DML) ------------------------------------------

    public static int bumpAllRatings(EntityAgent agent, int delta) {
        Statement stmt = agent.createStatement(
                "update Player p set p.rating = p.rating + :delta where p.rating is not null");
        stmt.setParameter("delta", delta);
        return stmt.execute();
    }

    public static int deleteAwardsBefore(EntityAgent agent, short year) {
        return agent.createStatement("delete from Award a where a.year < :year")
                .setParameter("year", year)
                .execute();
    }

    public static void testJpqlStatements(EntityAgent agent) {
        Statement valid =
                agent.createStatement("update Award a set a.title = 'X' where a.year = 2023");
//        Statement invalidSchema = agent.createStatement("update Award a set a.wrongAttr = 1");
//        Statement invalidSyntax = agent.createStatement("update Award a wrong_keyword");
//        Statement checkSchemaCompletion = agent.createStatement("update Award a set a.");
//        Statement checkSyntaxCompletion = agent.createStatement("update Award a se");
    }

    // ---- createNativeQuery (SQL) ---------------------------------------------

    public static List<Award> rawAwardsAfter(EntityAgent agent, short since) {
        return agent
                .createNativeQuery("select * from award where year_ > :since", Award.class)
                .setParameter("since", since)
                .getResultList();
    }

    public static List<Object[]> ratingsRaw(EntityAgent agent, int minRating) {
        return agent.createNativeQuery(
                        "select p.first_name, p.rating from player p where p.rating >= :minRating order by p.rating desc",  Object[].class)
                .setParameter("minRating", minRating).getResultList();
    }

    public static void testNativeQueries(EntityAgent agent) {
        StatementOrTypedQuery valid =
                agent.createNativeQuery("select * from award where award.year_ > 2023");
//        StatementOrTypedQuery invalidSchema   = agent.createNativeQuery("select * from award where award.wrong_column > 2023");
//        StatementOrTypedQuery invalidSyntax   = agent.createNativeQuery("select * from award as a wrong_keyword award.year_ > 2023");
//        StatementOrTypedQuery schemaCompletion= agent.createNativeQuery("select * from award where award.");
//        StatementOrTypedQuery syntaxCompletion= agent.createNativeQuery("select * from award wh");
    }

    // ---- createNativeStatement (SQL DML) -------------------------------------

    public static int retitleNative(EntityAgent agent, int id, String newTitle) {
        return agent
                .createNativeStatement("update award set title = :newTitle where id = :id")
                .setParameter("newTitle", newTitle)
                .setParameter("id", id)
                .execute();
    }

    public static int detachPlayerFromAllSchoolsNative(EntityAgent agent, long playerId) {
        return agent
                .createNativeStatement("delete from player_sport_school where player_id = :pid")
                .setParameter("pid", playerId)
                .execute();
    }

    public static void testNativeStatements(EntityAgent agent) {
        Statement valid =
                agent.createNativeStatement("update award set title = 'X' where year_ = 2023");
//        Statement invalidSchema = agent.createNativeStatement("update award set wrong_column = 'X' where year_ = 2023");
//        Statement invalidSyntax = agent.createNativeStatement("update award wrong_keyword title = 'X'");
//        Statement schemaCompletion = agent.createNativeStatement("update award set award.");
//        Statement syntaxCompletion = agent.createNativeStatement("update award se");
    }

    // ---- createNamedQuery / createNamedStatement -----------------------------
    // Names below must navigate to @NamedQuery / @NamedNativeQuery / @NamedStatement / @NamedNativeStatement

    public static void testNamedNavigation(EntityAgent agent) {
        // existing @NamedQuery (JPQL)
        var nq1 = agent.createNamedQuery("Award.findByYear", Award.class);
        var nq2 = agent.createNamedQuery("Competition.findByCity", Competition.class);
        var nq3 = agent.createNamedQuery("Competition.findTitleBySecondPlayerFullName", Competition.class);
        var nq4 = agent.createNamedQuery("Competition.findRegional", Competition.class);
        var nq5 = agent.createNamedQuery("License.findByLicenseNumber103", License.class);
        var nq6 = agent.createNamedQuery("Player.countDistinctByFullNameAndRating", Player.class);

        // existing @NamedNativeQuery (SQL)
        var nnq1 = agent.createNamedQuery("SQL.License.findByLicenseNumber102", License.class);
        var nnq2 = agent.createNamedQuery("SQL.Player.findNameByWinningAfter2023", Player.class);

        // JPA 4: @NamedStatement (JPQL DML) - navigated via createNamedStatement
        Statement ns1 = agent.createNamedStatement("Award.retitle");
        Statement ns2 = agent.createNamedStatement("Award.deleteByYear");
        Statement ns3 = agent.createNamedStatement("Competition.retitleByCity");
        Statement ns4 = agent.createNamedStatement("Competition.deleteBySecondPlayerFullName");
        Statement ns5 = agent.createNamedStatement("Competition.promoteRegional");
        Statement ns6 = agent.createNamedStatement("License.updateDescriptionBySerialNumber");
        Statement ns7 = agent.createNamedStatement("Player.bumpRating");
        Statement ns8 = agent.createNamedStatement("Player.deleteUnrated");

        // JPA 4: @NamedNativeStatement (SQL DML)
        Statement nns1 = agent.createNamedStatement("SQL.Award.bumpYears");
        Statement nns2 = agent.createNamedStatement("SQL.License.deleteBySerialNumber");
        Statement nns3 = agent.createNamedStatement("SQL.Competition.deleteByCity");
        Statement nns4 = agent.createNamedStatement("SQL.Player.detachFromSchool");
    }

    // ---- Mixed: same EntityManager exposing the new methods directly --------
    // EntityManager extends EntityHandler in JPA 4, so the new create*Statement
    // methods are also available on EntityManager.

    public static void testOnEntityManager() {
        try (EntityManager em = entityManagerFactory.createEntityManager()) {
            Statement s1 = em.createStatement("delete from Award a where a.year < :year");
            Statement s2 = em.createNativeStatement("delete from award where year_ < :year");
            StatementOrTypedQuery q1 = em.createQuery("from Award a where a.year > :year");
            StatementOrTypedQuery q2 = em.createNativeQuery("select * from award where year_ > :year");
            Statement ns = em.createNamedStatement("Award.retitle");
        }
    }
}
