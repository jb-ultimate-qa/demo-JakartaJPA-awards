package my.example.awards.queries;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import my.example.awards.model.Award;

import static my.example.awards.utils.QueryResultPrettier.concatenateObjects;
import static my.example.awards.utils.QueryResultPrettier.printObjects;

public class SqlQueries {

    private final EntityManager entityManager;

    public SqlQueries() {
        try { // use unit from persistence.xml
            entityManager = Persistence.createEntityManagerFactory("jpa-default")
                    .createEntityManager();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public String getAwardsAfter2022(){ //works
        var results = entityManager
                .createNativeQuery("select * from award where award.year_ > 2023;", Award.class)
                .getResultList();

        printObjects(results);
        return concatenateObjects(results);
    }

    public String getUsers(){
        var results = entityManager
                .createNativeQuery("select usename from pg_user;")
                .getResultList();

        printObjects(results);
        return concatenateObjects(results);
    }
}
