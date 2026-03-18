package my.example.awards.queries;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import my.example.awards.model.Award;

import java.util.List;

import static my.example.awards.utils.QueryResultPrettier.concatenateObjects;
import static my.example.awards.utils.QueryResultPrettier.printObjects;

public class SqlQueries {

    private static final EntityManagerFactory entityManagerFactory;

    static {
        try {  // use unit from persistence.xml
            entityManagerFactory = Persistence.createEntityManagerFactory("jpa-default");
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static String getAwardsAfter2022(){
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            List<?> results = entityManager
                    .createNativeQuery("select * from award where award.year_ > 2023;", Award.class)
                    .getResultList();

            printObjects(results);
            return concatenateObjects(results);
        }
    }

    public static void testSqlQueries(){
        EntityManager em = entityManagerFactory.createEntityManager();
        Query valid = em.createNativeQuery("select * from award where award.year_ > 2023", Award.class);
//        Query invalidSchema = em.createNativeQuery("select * from award where award.wrong_column > 2023", Award.class);
//        Query invalidSyntax = em.createNativeQuery("select * from award as a wrong_keyword award.year_ > 2023", Award.class);
//        Query checkSchemaCompletion = em.createNativeQuery("select * from award where award", Award.class);
//        Query checkSyntaxCompletion = em.createNativeQuery("select * from award wh", Award.class);
    }

}
