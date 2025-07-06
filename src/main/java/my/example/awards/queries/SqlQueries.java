package my.example.awards.queries;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
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

}
