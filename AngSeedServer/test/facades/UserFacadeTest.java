/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import deploy.DeploymentConfiguration;
import entity.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Sindt
 */
public class UserFacadeTest {

    UserFacade facade = new UserFacade(Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME));
    private int id;
    private int id1;
    private int id2;

    public UserFacadeTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        EntityManager em = facade.getEntityManager();
        try {
            em.getTransaction().begin();
            em.createQuery("delete from User").executeUpdate();
            User u = new User("user", "test");
            u.AddRole("User");
            User u1 = new User("admin", "test");
            u1.AddRole("User");
            User u2 = new User("user_admin", "test");
            u2.AddRole("User");
            em.persist(u);
            em.persist(u1);
            em.persist(u2);
            em.getTransaction().commit();
            id = u.getId();
            id1 = u1.getId();
            id2 = u2.getId();
        } catch (Exception e) {

        }
    }

    @After
    public void tearDown() {
        EntityManager em = facade.getEntityManager();
        try {
            User u1 = em.find(User.class, id1);
            User u2 = em.find(User.class, id2);
            em.getTransaction().begin();
            em.remove(u1);
            em.remove(u2);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    /**
     * Test of getEntityManager method, of class UserFacade.
     */
    @Ignore
    public void testGetEntityManager() {
        System.out.println("getEntityManager");
    }

    /**
     * Test of authenticateUser method, of class UserFacade.
     */
    @Test
    public void testAuthenticateUser() {
        System.out.println("authenticateUser");
        String username = "user";
        String password = "test";
        List<String> userList = facade.authenticateUser(username, password);
        assertNotNull(userList);
    }

    /**
     * Test of addUser method, of class UserFacade.
     */
    @Test
    public void testAddUser() {
        System.out.println("addUser");
        User user = facade.addUser("testUser", "test");
        user = facade.getUser(user.getId());
        assertEquals("testUser", user.getUsername());
    }

    /**
     * Test of getUser method, of class UserFacade.
     */
    @Test
    public void testGetUser() {
        System.out.println("getUser");
        User u = facade.getUser(id1);
        assertEquals(u.getUsername(), "admin");
    }

    /**
     * Test of deleteUser method, of class UserFacade.
     */
    @Test
    public void testDeleteUser() {
        System.out.println("deleteUser");
        facade.deleteUser(id);
        assertEquals(facade.getAllUsers().size(), 2);
    }

    /**
     * Test of getUserByName method, of class UserFacade.
     */
    @Test
    public void testGetUserByName() {
        System.out.println("getUserByName");
        String username = "user";
        User result = facade.getUserByName(username);
        assertEquals(username, result.getUsername());
    }

    /**
     * Test of getAllUsers method, of class UserFacade.
     */
    @Test
    public void testGetAllUsers() {
        System.out.println("getAllUsers");
        List<User> userList = facade.getAllUsers();
        assertNotNull(userList);
    }

}
