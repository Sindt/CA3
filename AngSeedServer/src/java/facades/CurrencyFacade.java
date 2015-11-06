/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entity.Currency;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

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

    public Currency getCurrency(String code) {
        EntityManager em = getEntityManager();
        Currency c = new Currency();
        try {
            Query q = em.createNamedQuery("Currency.findByCode");
            c = (Currency) q.setParameter("code", code).getSingleResult();
        } catch (Exception e) {
        }
        return c;

    }

    public List<Currency> getAllCurrencys() {
        EntityManager em = getEntityManager();
        List<Currency> curList;
        try {
            curList = new ArrayList();
            Query q = em.createNamedQuery("Currency.findAll");
            curList = q.getResultList();
        } finally {
            em.close();
        }
        return curList;
    }

    public void moveRate() {
        EntityManager em = getEntityManager();
        List<Currency> curList;
        try {
            curList = new ArrayList();
            Query q = em.createNamedQuery("Currency.findAll");
            curList = q.getResultList();
            for (Currency c : curList) {
                c.setRateOld(c.getRateNew());
            }
        } catch (Exception e) {
        }
    }

    public void updateCurrency(String code, String rate) {
        EntityManager em = getEntityManager();
        Currency c = getCurrency(code);

        try {
            em.getTransaction().begin();
            if (rate.equalsIgnoreCase("-")) {
                c.setRateNew(0);
            } else {
                c.setRateNew(Double.parseDouble(rate));
            }
            em.getTransaction().commit();

        } catch (Exception e) {
        } finally {
            em.close();
        }

    }
}
