package facades;

import entity.User;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import security.PasswordHash;

public class UserFacade {

    private EntityManagerFactory emf;

    public UserFacade(EntityManagerFactory e) {
        emf = e;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    /*
     Return the Roles if users could be authenticated, otherwise null
     */
    public List<String> authenticateUser(String userName, String password) {
        User user = getUserByName(userName);
        return user != null && user.getPassword().equals(password) ? user.getRoles() : null;
    }

    public User addUser(String username, String password) {
        EntityManager em = getEntityManager();
        User u = new User();

        try {
            u.setUsername(username);
            u.setPassword(PasswordHash.createHash(password));
            u.AddRole("User");
            em.getTransaction().begin();
            em.persist(u);
            em.getTransaction().commit();
            return u;
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UserFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(UserFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            em.close();
        }
    }

    public User getUser(int id) {
        EntityManager em = getEntityManager();
        try {
            User u = em.find(User.class, id);
            if (u == null) {
            }
            return u;
        } finally {
            em.close();
        }
    }

    public void deleteUser(int id) {
        EntityManager em = getEntityManager();
        try {
            User u = em.find(User.class, id);

            em.getTransaction().begin();
            em.remove(u);
            em.getTransaction().commit();

        } finally {
            em.close();
        }

    }

    public User getUserByName(String username) {
        EntityManager em = getEntityManager();
        try {
            User u = new User();
            Query q = em.createNamedQuery("User.findByName");
            u = (User) q.setParameter("username", username).getSingleResult();
            if (u == null) {
            }
            return u;
        } finally {
            em.close();
        }
    }

    public List<User> getAllUsers() {
        EntityManager em = getEntityManager();
        List<User> userList;
        try {
            userList = new ArrayList();
            Query q = em.createNamedQuery("User.findAll");
            userList = q.getResultList();
        } finally {
            em.close();
        }
        return userList;
    }
}
