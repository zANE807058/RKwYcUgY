// 代码生成时间: 2025-09-12 05:04:56
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.cfg.Environment;
import java.util.Properties;
import java.io.Serializable;

// 定义实体类
@Entity
@Table(name = "users")
public class User implements Serializable {
    private Long id;
    private String name;
    private String email;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

// 实现缓存策略的DAO类
public class UserDao {
    private SessionFactory sessionFactory;

    public UserDao() {
        // 配置Hibernate
        Configuration configuration = new Configuration().configure();
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    public User findById(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            transaction.commit();
            return user;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    public void save(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}

// 主程序，演示缓存策略的使用
public class CacheStrategyExample {
    public static void main(String[] args) {
        UserDao userDao = new UserDao();

        // 创建用户
        User user = new User();
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        userDao.save(user);

        // 从缓存中获取用户
        User userFromCache = userDao.findById(user.getId());
        System.out.println("User from cache: " + userFromCache.getName());
    }
}