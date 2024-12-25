package my.example.awards.queries;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import static my.example.awards.utils.QueryResultPrettier.concatenateObjects;
import static my.example.awards.utils.QueryResultPrettier.printObjects;

public class JpqlQueries {

    private final EntityManager entityManager;

    public JpqlQueries() {
        try { // use unit from persistence.xml
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa-default");
            entityManager = entityManagerFactory.createEntityManager();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public String getAllAwards(){ // works
        var results = entityManager
//                .createQuery("select a from my.example.awards.model.Award a join my.example.awards.model.Player p on p.id=a.winner.id")
                .createQuery("FROM Award a")
                .getResultList();

        printObjects(results);
        return concatenateObjects(results);
    }

    public String getPlayersBySchool(String sportSchoolName){
        var results = entityManager
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
