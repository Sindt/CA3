package facades;

import entity.User;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class UserFacade {

    private EntityManagerFactory emf;

    public UserFacade(EntityManagerFactory e) {
        emf = e;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    private final Map<String, User> users = new HashMap<>();

    public UserFacade() {
        //Test Users
        User user = new User("user", "test");
        user.AddRole("User");
        users.put(user.getUserName(), user);
        User admin = new User("admin", "test");
        admin.AddRole("Admin");
        users.put(admin.getUserName(), admin);

        User both = new User("user_admin", "test");
        both.AddRole("User");
        both.AddRole("Admin");
        users.put(both.getUserName(), both);
    }

    public User getUserByUserId(String id) {
        return users.get(id);
    }
    /*
     Return the Roles if users could be authenticated, otherwise null
     */

    public List<String> authenticateUser(String userName, String password) {
        User user = users.get(userName);
        return user != null && user.getPassword().equals(password) ? user.getRoles() : null;
    }

    public User addUser(User u) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(u);
            em.getTransaction().commit();
            return u;
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
}
