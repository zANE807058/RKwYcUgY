// 代码生成时间: 2025-08-29 01:13:32
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.dialect.H2Dialect;
import javax.sql.DataSource;
import org.h2.jdbcx.JdbcDataSource;
import java.util.Properties;

/**
 * DatabaseConnectionPoolManager is a class responsible for managing the database connection pool.
 * It uses Hibernate to create a session factory with a connection pool.
 */
public class DatabaseConnectionPoolManager {

    private static SessionFactory sessionFactory;

    /**
     * Initializes the database connection pool and creates a session factory.
     * @param configPath The path to the Hibernate configuration file.
     */
    public void init(String configPath) {
        try {
            // Create a configuration object and configure it from the given path
            Configuration configuration = new Configuration().configure(configPath);
            
            // Create a service registry with the dialect and connection pool properties
            Properties properties = new Properties();
            properties.put("hibernate.dialect", H2Dialect.class.getName());
            properties.put("hibernate.hbm2ddl.auto", "update");
            properties.put("hibernate.show_sql", "true");
            properties.put("hibernate.format_sql", "true");
            
            // Set up the connection pool properties
            JdbcDataSource dataSource = new JdbcDataSource();
            dataSource.setURL("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
            properties.put("hibernate.connection.datasource", dataSource);
            
            // Build the service registry with the provided properties
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(properties).build();
            
            // Create the session factory using the service registry
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            // Handle configuration error
            System.err.println("Initial SessionFactory creation failed." + "
" + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Returns the current session factory.
     * @return The session factory instance.
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * Closes the session factory and releases any resources.
     */
    public void close() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
