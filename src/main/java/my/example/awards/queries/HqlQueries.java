package my.example.awards.queries;

import my.example.awards.model.Award;
import my.example.awards.model.Referee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import static my.example.awards.utils.QueryResultPrettier.concatenateObjects;
import static my.example.awards.utils.QueryResultPrettier.printObjects;

public class HqlQueries {

    private static final SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static String getAllAwards() {
        try (Session session = sessionFactory.openSession()) {
            var results = session
                    .createQuery("FROM Award a", Award.class)
                    .getResultList();

            printObjects(results);
            return concatenateObjects(results);
        }
    }

    public static String getAwardsOfHighRatedPlayers(){
        try (Session session = sessionFactory.openSession()) {
            var results = session
                    .createQuery("FROM Award a where a.winner.rating >= 1200", Award.class)
                    .getResultList();

            printObjects(results);
            return concatenateObjects(results);
        }
    }

    public static String getRefereeByLicenceNumber(String licenceNumber){
        try (Session session = sessionFactory.openSession()) {
            var results = session
                    .createQuery("FROM Referee r where r.license.licenseNumber = :ln", Referee.class)
                    .setParameter("ln", licenceNumber)
                    .getResultList();

            printObjects(results);
            return concatenateObjects(results);
        }
    }
}
