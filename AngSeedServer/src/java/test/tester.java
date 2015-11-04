/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import deploy.DeploymentConfiguration;
import entity.User;
import facades.UserFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Sindt
 */
public class tester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME);

        UserFacade uf = new UserFacade(emf);

        User u1 = uf.getUser(1);
        System.out.println(u1.getUsername());
        User u2 = uf.getUserByName("user");
        System.out.println(u2.getPassword());
        //uf.addUser("user_admin", "test");
        List<User> userList = uf.getAllUsers();
        for (User u : userList) {
            System.out.println(u.getUsername() + " " + u.getPassword());
        }

    }

}
