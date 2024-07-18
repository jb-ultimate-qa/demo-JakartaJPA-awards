package my.example.awards.queries;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import my.example.awards.model.Award;

import static my.example.awards.utils.QueryResultPrettier.concatenateObjects;
import static my.example.awards.utils.QueryResultPrettier.printObjects;

public class SqlQueries {

    private static final EntityManager entityManager;

    static {
        try { // use unit from persistence.xml
            entityManager = Persistence.createEntityManagerFactory("jpa-default")
                    .createEntityManager();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public String getAwardsAfter2022(){ //works
        var results = entityManager
                .createNativeQuery("select * from award where award.year > 2022;", Award.class)
                .getResultList();

        printObjects(results);
        return concatenateObjects(results);
    }
}
