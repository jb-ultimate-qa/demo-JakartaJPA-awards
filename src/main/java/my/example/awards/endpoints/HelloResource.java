package my.example.awards.endpoints;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

import static my.example.awards.utils.HtmlHelper.generateHtmlLinksForHello;

@Path("/hello")
public class HelloResource {
    @GET
    @Produces("text/html")
    public String hello() {
        return "Hello, User!" + generateHtmlLinksForHello("awards", "players", "referee");
    }
}