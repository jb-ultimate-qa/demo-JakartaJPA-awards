package my.example.awards.queries;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import my.example.awards.model.*;

import java.util.List;

import static my.example.awards.utils.QueryResultPrettier.concatenateObjects;
import static my.example.awards.utils.QueryResultPrettier.printObjects;

public class JpqlQueries {

    private static final EntityManagerFactory entityManagerFactory;

    static {
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("jpa-default");
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static String getAllAwards(){
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            List<?> results = entityManager
                    .createQuery("FROM Award a", Award.class)
                    .getResultList();

            printObjects(results);
            return concatenateObjects(results);
        }
    }

    public static String getPlayersBySchool(String sportSchoolName) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            List<?> results = entityManager
                    .createQuery("""
                            select pl.fullName from Player pl
                                join pl.sportSchools ss
                                where ss.title = ?1
                            """, String.class)
                    .setParameter(1, sportSchoolName)     // Use 'National Training Center'
                    .getResultList();

            printObjects(results);
            return concatenateObjects(results);
        }
    }

    public static void testJpqlQueries(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query valid = entityManager.createQuery("from Award a where a.title like '%er'");
//        Query invalidSchema = entityManager.createQuery("from Award a where a.wrongAttr = 1");
//        Query invalidSyntax = entityManager.createQuery("from Award a wrong_keyword");
//        Query checkSchemaCompletion = entityManager.createQuery("from Award a where a.");
//        Query checkSyntaxCompletion = entityManager.createQuery("from Award a wh");
    }

    public void testNavigation(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        var results1 = entityManager.createNamedQuery("Competition.findByCity", Competition.class);
        var results2 = entityManager.createNamedQuery("Competition.findTitleBySecondPlayerFullName", Competition.class);
        var results3 = entityManager.createNamedQuery("Competition.findRegional", Competition.class);
        var results4 = entityManager.createNamedQuery("License.findByLicenseNumber103", License.class);
        var results5 = entityManager.createNamedQuery("SQL.License.findByLicenseNumber102", License.class);
        var results6 = entityManager.createNamedQuery("Player.countDistinctByFullNameAndRating", Player.class);
        var results7 = entityManager.createNamedQuery("SQL.Player.findNameByWinningAfter2023", Player.class);
    }
}
