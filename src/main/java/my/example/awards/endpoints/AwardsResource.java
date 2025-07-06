package my.example.awards.endpoints;

import jakarta.ws.rs.*;
import my.example.awards.queries.HqlQueries;
import my.example.awards.queries.JpqlQueries;
import my.example.awards.queries.SqlQueries;

@Path("/awards")
@Produces("text/plain")
public class AwardsResource {

    @GET
    @Produces("text/html")
    @Path("/")
    public String getLinks() {
        return "Try this URL:</br></br><a href=\"" +
                "awards/jpql-get-all\">awards/jpql-get-all</a></br><a href=\"" +
                "awards/hql-get-all\">awards/hql-get-all</a></br><a href=\"" +
                "awards/hql-get-awards-of-high-rated-players\">awards/hql-get-awards-of-high-rated-players</a></br><a href=\"" +
                "awards/sql-get-awards-after-2022\">awards/sql-get-awards-after-2022</a>";
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
}