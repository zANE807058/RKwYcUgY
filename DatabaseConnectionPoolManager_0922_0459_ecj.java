// 代码生成时间: 2025-09-22 04:59:02
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.registry.selector.DefaultSelector;
import org.hibernate.cfg.Environment;
import java.util.Properties;

/**
 * DatabaseConnectionPoolManager class manages the Hibernate connection pool.
 * It configures the Hibernate session factory and provides methods to create sessions.
 */
public class DatabaseConnectionPoolManager {

    private static SessionFactory sessionFactory;

    /**
     * Initialize the Hibernate session factory.
     */
    public static void initializeSessionFactory() {
        try {
            // Create the Hibernate configuration object
            Configuration configuration = new Configuration();
            configuration.configure(); // Load hibernate.cfg.xml configuration file

            // Set properties - in this example, we are using MySQL
            Properties properties = new Properties();
            properties.setProperty(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
            properties.setProperty(Environment.URL, "jdbc:mysql://localhost:3306/your_database");
            properties.setProperty(Environment.USER, "your_username");
            properties.setProperty(Environment.PASS, "your_password");
            properties.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQL5InnoDBDialect");
            properties.setProperty(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
            properties.setProperty(Environment.CONNECTION_POOL_SIZE, "5"); // Define the size of the connection pool

            // Create a service registry
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(properties)
                    .build();

            // Build the session factory
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + "Exception: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Return the current session factory instance.
     * @return SessionFactory - The Hibernate session factory.
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * Close the session factory and release resources.
     */
    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    /**
     * Create a new Hibernate session.
     * @return Session - A new Hibernate session.
     */
    public static Session openSession() {
        return sessionFactory.openSession();
    }
}
