package my.example.awards.queries;

import my.example.awards.model.*;
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
                    .setParameter("ln", licenceNumber)         // Use 'LR003'
                    .getResultList();

            printObjects(results);
            return concatenateObjects(results);
        }
    }

    public static String testHqlQueries() {
        Session session = sessionFactory.openSession();
        var valid = session.createQuery("from Award a where a.title like '%r'", Award.class).getResultList();
//        var invalidSchema = session.createQuery("from Award a where a.wrongAttr = 1", Award.class);
//        var invalidSyntax = session.createQuery("from Award a wrong_keyword", Award.class);
//        var checkSchemaCompletion = session.createQuery("from Award a where a.", Award.class);
//        var checkSyntaxCompletion = session.createQuery("from Award a wh", Award.class);
        return "ok";
    }

    public static String testNavigation(){
        Session session = sessionFactory.openSession();
//        var results1 = session.createNamedQuery("Competition.findByCity", Competition.class)
//                .setParameter("1", "London");
//        var results2 = session.createNamedQuery("Competition.findTitleBySecondPlayerFullName", Competition.class)
//                .setParameter("fullName", "Carol").getResultList();
        var results3 = session.createNamedQuery("Competition.findRegional", Competition.class).getResultList();
//        var results4 = session.createNamedQuery("License.findByLicenseNumber103", License.class);
//        var results5 = session.createNamedQuery("SQL.License.findByLicenseNumber102", License.class);
//        var results6 = session.createNamedQuery("Player.countDistinctByFullNameAndRating", Player.class);
//        var results7 = session.createNamedQuery("SQL.Player.findNameByWinningAfter2023", Player.class);
//        System.out.println(results1);
//        System.out.println(results2);
        return "ok";
    }
}
