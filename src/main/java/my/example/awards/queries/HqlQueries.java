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
                    .createQuery("from Award a", Award.class)
                    .getResultList();

            printObjects(results);
            return concatenateObjects(results);
        }
    }

    public static String getAwardsOfHighRatedPlayers(){
        try (Session session = sessionFactory.openSession()) {
            var results = session
                    .createQuery("from Award a where a.winner.rating >= 1200", Award.class)
                    .getResultList();

            printObjects(results);
            return concatenateObjects(results);
        }
    }

    public static String getRefereeByLicenceNumber(String licenceNumber){
        try (Session session = sessionFactory.openSession()) {
            var results = session
                    .createQuery("from Referee r where r.license.licenseNumber = :ln", Referee.class)
                    .setParameter("ln", licenceNumber)
                    .getResultList();

            printObjects(results);
            return concatenateObjects(results);
        }
    }

    public static void testHqlQueries() {
        Session session = sessionFactory.openSession();
        var valid = session.createQuery("from Award a where a.title like '%a'", Award.class);
//        var invalidSchema = session.createQuery("from Award a where a.wrongAttr = 1", Award.class);
//        var invalidSyntax = session.createQuery("from Award a wrong_keyword", Award.class);
//        var checkSchemaCompletion = session.createQuery("from Award a where a.", Award.class);
//        var checkSyntaxCompletion = session.createQuery("from Award a wh", Award.class);
    }
}
