package my.example.awards.queries;

import my.example.awards.model.Award;
import my.example.awards.model.dto.AwardDto;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

import static java.lang.System.out;
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

    public String getRefereeByLicenceNumber(String licenceNumber){
        Session session = sessionFactory.openSession();
        var results = session
                .createQuery("FROM my.example.awards.model.Referee r where r.license.licenseNumber = :ln",
                        my.example.awards.model.Referee.class)
                .setParameter("ln", licenceNumber)
                .getResultList();

        printObjects(results);
        return concatenateObjects(results);
    }

    public String selectRank(){
        Session session = sessionFactory.openSession();
        var results = session.createQuery(
                "select rank() over (order by 5 rows between current row and unbounded following) from Player",
                Long.class)
                .getResultList();

        printObjects(results);
        return concatenateObjects(results);
    }

    public void testNegative(){
        sessionFactory.inSession(
                s -> out.println(s
                        .createSelectionQuery("select 2 * -3 + 5%2")
                        .getSingleResultOrNull())
        );
    }

    public void testDto(){
        Session session = sessionFactory.openSession();
        List<AwardDto> results = session.createQuery(
                "select new AwardDto(year, title) from Award"
                , AwardDto.class
        ).getResultList();
        results.forEach(System.out::println);
    }

    public void testDto2(){
        Session session = sessionFactory.openSession();
        List<AwardDto> results = session.createQuery("select new my.example.awards.model.dto.AwardDto(title) from Award", AwardDto.class)
                .getResultList();
        results.forEach(System.out::println);
    }
}
