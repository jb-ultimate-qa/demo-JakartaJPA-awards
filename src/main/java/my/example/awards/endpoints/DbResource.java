package my.example.awards.endpoints;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import my.example.awards.queries.SqlQueries;

@Path("/db")
@Produces("text/plain")
public class DbResource {
    @GET
    @Path("get-users")
    public String getAwardsAfter2022() {
        SqlQueries sqlQueries = new SqlQueries();
        return sqlQueries.getUsers();
    }

}
