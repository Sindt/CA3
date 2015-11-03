/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

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
        
        Persistence.generateSchema("AngSeedServerPU", null);
        
    }
    
}
