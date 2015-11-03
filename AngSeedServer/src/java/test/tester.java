/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entity.User;
import facades.UserFacade;
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
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AngSeedServerPU");
        //Persistence.generateSchema("AngSeedServerPU", null);

        UserFacade uf = new UserFacade(emf);
        User u = new User();
        u.setUserName("user");
        u.setPassword("test");
        u.AddRole("User");
        uf.addUser(u);

        User u1 = uf.getUser(u.getId());
        System.out.println(u1.getUserName());

    }

}
