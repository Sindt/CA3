package rest;

import deploy.DeploymentConfiguration;
import facades.JSONConvert;
import facades.UserFacade;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("admin")
//@RolesAllowed("Admin")
public class Admin {

    @Context
    private UriInfo context;

    EntityManagerFactory emf = Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME);
    UserFacade facade = new UserFacade(emf);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("users")
    public Response getAllUsers() {
        return Response.ok(JSONConvert.getJSONFromUsers(facade.getAllUsers())).build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("user/{id}")
    public Response deleteUser(@PathParam("id") int id) {
        facade.deleteUser(id);
        return Response.ok().build();
    }

}
