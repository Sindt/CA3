package rest;

import deploy.DeploymentConfiguration;
import facades.JSONConvert;
import facades.UserFacade;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("user")
//@RolesAllowed("User")
public class User {

    
    @Context
    private UriInfo context;
    
    EntityManagerFactory emf = Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME);
    UserFacade facade = new UserFacade(emf);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSomething() {
        return "{\"message\" : \"This message was delivered via a REST call accesible by only authenticated USERS\"}";
    }

    @POST
    @Path("/registration")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String registerUser(String user) {

        entity.User newUser = JSONConvert.getUserFromJson(user);
        newUser = facade.addUser(newUser.getUsername(), newUser.getPassword());

        return JSONConvert.getJSONFromUser(newUser);
//
//        if (facade.getUserByName(newUser.getUsername()).toString().isEmpty()) {
//            return Response.ok(facade.addUser(newUser.getUsername(), newUser.getPassword())).build();
//        } else {
//            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
//        }
    }

}