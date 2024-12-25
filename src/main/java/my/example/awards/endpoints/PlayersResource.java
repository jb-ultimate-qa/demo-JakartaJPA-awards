package my.example.awards.endpoints;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import my.example.awards.queries.HqlQueries;
import my.example.awards.queries.JpqlQueries;

@Path("/players")
@Produces("text/plain")
public class PlayersResource {

    @GET
    @Produces("text/html")
    @Path("/")
    public String getLinks() {
        return "Try this URL:</br></br><a href=\"" +
                "players/jpql-get-from-sport-club\">players/jpql-get-from-sport-club</a></br>";
    }

    @GET
    @Path("jpql-get-from-sport-club")
    public String getPlayersFormCitySportClub() {
        JpqlQueries jpqlQueries = new JpqlQueries();
        return jpqlQueries.getPlayersBySchool("City Sports Club");
    }

    @GET
    @Path("rank")
    public String getRank() {
        HqlQueries queries = new HqlQueries();
        return queries.selectRank();
    }
}