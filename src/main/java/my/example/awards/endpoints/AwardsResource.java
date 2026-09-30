package my.example.awards.endpoints;

import jakarta.ws.rs.*;
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
        return JpqlQueries.getAllAwards();
    }

    @GET
    @Path("hql-get-all")
    public String getAllAwardsHibernate() {
        return HqlQueries.getAllAwards();
    }

    @GET
    @Path("hql-get-awards-of-high-rated-players")
    public String getAwardsOfHighRatedPlayers() {
        return HqlQueries.getAwardsOfHighRatedPlayers();
    }

    @GET
    @Path("sql-get-awards-after-2022")
    public String getAwardsAfter2022() {
        return SqlQueries.getAwardsAfter2022();
    }

    @GET
    @Path("test-hql")
    public String testHql() {
        return HqlQueries.testHqlQueries();
    }

    @GET
    @Path("test-nav")
    public String testNavigation() {
        return HqlQueries.testNavigation();
    }
}