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
            return c = (Currency) q.setParameter("code", code).getSingleResult();
        } catch (Exception e) {
            return null;
        }

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
                em.getTransaction().begin();
                c.setRateOld(c.getRateNew());
                em.merge(c);
                em.getTransaction().commit();
            }
        } catch (Exception e) {
        } finally {
            em.close();
        }
    }

    public void updateCurrency(Currency c, String rate) {
        EntityManager em = getEntityManager();
        Currency cur;
        try {
            em.getTransaction().begin();
            cur = em.find(Currency.class, c.getId());
            if (rate.equalsIgnoreCase("-")) {
                cur.setRateNew(0);
            } else {
                cur.setRateNew(Double.parseDouble(rate));
            }
            em.merge(cur);
            em.getTransaction().commit();

        } catch (Exception e) {
        } finally {
            em.close();
        }

    }

    public void moveRate(int id) {
        EntityManager em = getEntityManager();
        Currency c = new Currency();
        try {
            em.getTransaction().begin();
            c = em.find(Currency.class, id);
            c.setRateOld(c.getRateNew());
            System.out.println(c.getRateOld());
            em.merge(c);
            em.getTransaction().commit();
        } catch (Exception e) {
        } finally {
            em.close();
        }
    }
}
