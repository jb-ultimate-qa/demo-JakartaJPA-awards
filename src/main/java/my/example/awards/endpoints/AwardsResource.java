package my.example.awards.endpoints;

import jakarta.ws.rs.*;
import my.example.awards.queries.HqlQueries;
import my.example.awards.queries.JpqlQueries;
import my.example.awards.queries.SqlQueries;

@Path("/awards")
@Produces("text/plain")
public class AwardsResource {
    @GET
    @Path("get-all")
    public String getAllAwards() {
        JpqlQueries jpqlQueries = new JpqlQueries();
        return jpqlQueries.getAllAwards();
    }

    @GET
    @Path("get-all-hib")
    public String getAllAwardsHibernate() {
        HqlQueries hqlQueries = new HqlQueries();
        return hqlQueries.getAllAwards();
    }


    @GET
    @Path("get-after-2022")
    public String getAwardsAfter2022() {
        SqlQueries sqlQueries = new SqlQueries();
        return sqlQueries.getAwardsAfter2022();
    }
}