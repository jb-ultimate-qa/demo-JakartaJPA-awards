package my.example.awards.endpoints;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import my.example.awards.queries.HqlQueries;

@Path("/hello")
public class HelloResource {
    @GET
    @Produces("text/html")
    public String hello() {
        return "Hello, User!" +
                "Try this URL:</br></br><a href=\"awards\">awards</a></br>" +
                "<a href=\"players\">players</a></br>" +
                "<a href=\"referee\">referee</a></br>";
    }

    @GET
    @Path("test")
    public int printTest() {
        HqlQueries hqlQueries = new HqlQueries();
        hqlQueries.testNegative();
        return 0;
    }
}