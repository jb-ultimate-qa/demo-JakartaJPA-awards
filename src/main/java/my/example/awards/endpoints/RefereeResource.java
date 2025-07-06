package my.example.awards.endpoints;

import jakarta.ws.rs.*;
import my.example.awards.queries.HqlQueries;

import static my.example.awards.utils.HtmlHelper.generateHtmlLinks;

@Path("/referee")
public class RefereeResource {
    @GET
    @Produces("text/html")
    @Path("/")
    public String getLinks() {
        return generateHtmlLinks("referee", "hql-get-with-licence-number");
    }

    @GET
    @Path("hql-get-with-licence-number")
    public String getRefereeByLicenceNumber() {
        return HqlQueries.getRefereeByLicenceNumber("LR001");
    }
}