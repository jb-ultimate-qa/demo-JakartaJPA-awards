package my.example.awards.queries;

import my.example.awards.model.Award;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import static my.example.awards.utils.QueryResultPrettier.concatenateObjects;
import static my.example.awards.utils.QueryResultPrettier.printObjects;

public class HqlQueries {

    private final SessionFactory sessionFactory;

    public HqlQueries() {
        try {
            sessionFactory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public String getAllAwards(){ // works
        Session session = sessionFactory.openSession();
        var results = session
                .createQuery("FROM Award a", Award.class)
                .getResultList();

        printObjects(results);
        return concatenateObjects(results);
    }

    public String getAwardsOfHighRatedPlayers(){
        Session session = sessionFactory.openSession();
        var results = session
                .createQuery("FROM Award a where a.winner.rating >= 1200", Award.class)
                .getResultList();

        printObjects(results);
        return concatenateObjects(results);
    }
}
