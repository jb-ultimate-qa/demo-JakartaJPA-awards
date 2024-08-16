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
        return """
                Try this URL:</br></br>
                <a href="/demoJPA-awards-1.0-SNAPSHOT/awards/jpql-get-all">awards/jpql-get-all</a></br>
                <a href="/demoJPA-awards-1.0-SNAPSHOT/awards/hql-get-all">awards/hql-get-all</a></br>
                <a href="/demoJPA-awards-1.0-SNAPSHOT/awards/sql-get-awards-after-2022">awards/sql-get-awards-after-2022</a>
                """;
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
    @Path("sql-get-awards-after-2022")
    public String getAwardsAfter2022() {
        SqlQueries sqlQueries = new SqlQueries();
        return sqlQueries.getAwardsAfter2022();
    }
}