package my.example.awards.endpoints;

import jakarta.ws.rs.*;
import my.example.awards.queries.HqlAnnotated;
import my.example.awards.queries.HqlQueries;
import my.example.awards.queries.JpqlQueries;
import my.example.awards.queries.SqlQueries;

import static my.example.awards.utils.HtmlHelper.generateHtmlLinks;

@Path("/awards")
@Produces("text/plain")
public class AwardsResource {

    @GET
    @Produces("text/html")
    @Path("/")
    public String getLinks() {
        return generateHtmlLinks("awards",
                "jpql-get-all",
                "hql-get-all",
                "hql-get-awards-of-high-rated-players",
                "sql-get-awards-after-2022");
    }

    @GET
    @Path("jpql-get-all")
    public String getAllAwards() {
        JpqlQueries jpqlQueries = new JpqlQueries();
        return jpqlQueries.getAllAwards();
    }

    @GET
    @Path("hql-get-all")
    public String getAllAwardsHibernate() {
        HqlQueries hqlQueries = new HqlQueries();
        return hqlQueries.getAllAwards();
    }

    @GET
    @Path("hql-get-awards-of-high-rated-players")
    public String getAwardsOfHighRatedPlayers() {
        HqlQueries hqlQueries = new HqlQueries();
        return hqlQueries.getAwardsOfHighRatedPlayers();
    }

    @GET
    @Path("sql-get-awards-after-2022")
    public String getAwardsAfter2022() {
        SqlQueries sqlQueries = new SqlQueries();
        return sqlQueries.getAwardsAfter2022();
    }

    @GET
    @Path("hql-dto")
    public String getAwardsDto() {
        HqlQueries hqlQueries = new HqlQueries();
        hqlQueries.testDto();
        return "null";
    }

//    @GET
//    @Path("hql-dto")
//    public String getAwardsDto() {
//        return HqlAnnotated.getAwardInDto();
//    }
//
//    @GET
//    @Path("hql-dto-fqn")
//    public String getAwardsFqnDto() {
//        SqlQueries sqlQueries = new SqlQueries();
//        return sqlQueries.getAwardsAfter2022();
//    }
}