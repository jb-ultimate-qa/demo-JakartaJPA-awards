package my.example.awards.endpoints;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import my.example.awards.queries.HqlQueries;

import static my.example.awards.utils.HtmlHelper.generateHtmlLinksForHello;

@Path("/hello")
public class HelloResource {
    @GET
    @Produces("text/html")
    public String hello() {
        return "Hello, User!" + generateHtmlLinksForHello("awards", "players", "referee");
    }

    @GET
    @Path("test")
    public int printTest() {
        HqlQueries hqlQueries = new HqlQueries();
        hqlQueries.testNegative();
        return 0;
    }
}