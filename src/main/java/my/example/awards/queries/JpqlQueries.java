package my.example.awards.queries;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

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
                    .createQuery("FROM Award a")
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
                            """)
                    .setParameter(1, sportSchoolName)
                    .getResultList();

            printObjects(results);
            return concatenateObjects(results);
        }
    }
}
