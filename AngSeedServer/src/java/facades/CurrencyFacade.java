/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entity.Currency;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Sindt
 */
public class CurrencyFacade {

    private EntityManagerFactory emf;

    public CurrencyFacade(EntityManagerFactory e) {
        emf = e;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Currency addCurruncy(Currency cur) {
        EntityManager em = getEntityManager();
        Currency c = new Currency();
        try {
            em.getTransaction().begin();
            em.persist(cur);
            em.getTransaction().commit();
            return cur;
        } catch (Exception e) {
        } finally {
            em.close();
        }
        return c;
    }
}
