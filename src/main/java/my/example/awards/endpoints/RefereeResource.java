package my.example.awards.endpoints;

import jakarta.ws.rs.*;
import my.example.awards.queries.HqlQueries;

@Path("/referee")
public class RefereeResource {
    @GET
    @Produces("text/html")
    @Path("/")
    public String getLinks() {
        return "Try this URL:</br></br><a href=\"" +
                "referee/hql-get-with-licence-number\">referee/hql-get-with-licence-number</a></br>";
    }

    @GET
    @Path("hql-get-with-licence-number")
    public String getRefereeByLicenceNumber() {
        return HqlQueries.getRefereeByLicenceNumber("LR001");
    }
}