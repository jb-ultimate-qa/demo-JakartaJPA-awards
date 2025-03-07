package my.example.awards.endpoints;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import my.example.awards.queries.JpqlQueries;

@Path("/hello")
public class HelloResource {
    @GET
    @Produces("text/html")
    public String hello() {
        return "Hello, User!" +
                "Try this URL:</br></br><a href=\"awards\">awards</a></br>" +
                "<a href=\"players\">players</a></br>" +
                "<a href=\"referee\">referee</a></br>" +
                "<a href=\"init\">init</a></br>";
    }

    @GET
    @Path("init")
    public String triggerHibernate() {
        JpqlQueries jpqlQueries = new JpqlQueries();
        return jpqlQueries.getAllIdentityIdEntity();
    }
}