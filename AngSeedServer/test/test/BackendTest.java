/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import static com.jayway.restassured.RestAssured.basePath;
import static com.jayway.restassured.RestAssured.baseURI;
import static com.jayway.restassured.RestAssured.defaultParser;
import static com.jayway.restassured.RestAssured.given;
import com.jayway.restassured.parsing.Parser;
import static com.jayway.restassured.path.json.JsonPath.from;
import javax.ws.rs.core.MediaType;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import static org.hamcrest.Matchers.equalTo;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import rest.ApplicationConfig;

/**
 *
 * @author sofus
 */
public class BackendTest {

    static Server server;

    public BackendTest() {
        baseURI = "http://localhost:8082";
        defaultParser = Parser.JSON;
        basePath = "/api";
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        server = new Server(8082);
        ServletHolder servletHolder = new ServletHolder(org.glassfish.jersey.servlet.ServletContainer.class);

        servletHolder.setInitParameter("javax.ws.rs.Application", ApplicationConfig.class.getName());
        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.setContextPath("/");
        contextHandler.addServlet(servletHolder, "/api/*");
        server.setHandler(contextHandler);
        server.start();
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        server.stop();
        //waiting for all the server threads to terminate so we can exit gracefully
        server.join();
    }

    @Test
    public void testGetAllUsers() {
        given()
                .contentType(MediaType.APPLICATION_JSON)
                .when()
                .get("/admin/users")
                .then().
                statusCode(200);
    }

    @Test
    public void Login() {
        //Successful login
        given().
                contentType("application/json").
                body("{'username':'user','password':'test'}").
                when().
                post("/login").
                then().
                statusCode(200);

    }

    @Test
    public void testGetAllCurrencys() {
        given()
                .contentType(MediaType.APPLICATION_JSON)
                .when()
                .get("/currency/dailyrates")
                .then().
                statusCode(200);
    }

    @Test
    public void testRegistreUser() {
        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"username\": \"testUser\", \"passwrod\": \"test\"}")
                .when()
                .post("/user/registration")
                .then()
                .statusCode(200).and().body("username", equalTo("testUser"));

    }
}
