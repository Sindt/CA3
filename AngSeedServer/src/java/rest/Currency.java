/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import deploy.DeploymentConfiguration;
import facades.CurrencyFacade;
import facades.JSONConvert;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Sindt
 */
@Path("currency")
public class Currency {

    CurrencyFacade facade = new CurrencyFacade(Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME));

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Currency
     */
    public Currency() {
    }

    @GET
    @Path("dailyrates")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRates() {
        return Response.ok(JSONConvert.getJSONFromCurrencys(facade.getAllCurrencys())).build();
    }

}
