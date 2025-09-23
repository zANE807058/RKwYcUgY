// 代码生成时间: 2025-09-23 13:26:56
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import java.util.List;
import java.util.ArrayList;

/**
 * This class demonstrates how to prevent SQL injection using Hibernate framework.
 */
public class PreventSqlInjection {

    private static SessionFactory sessionFactory;

    // Initialize the session factory (Singleton pattern)
    static {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + "Exception: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Method to demonstrate how to prevent SQL injection when querying the database.
     * @param input The user input that should be sanitized.
     * @return A list of results or an empty list if no results or an error occurs.
     */
    public List<String> queryDatabase(String input) {
        List<String> results = new ArrayList<>();
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            // Use named query or query API to prevent SQL injection
            Query<String> query = session.createQuery("FROM Entity WHERE field LIKE :input", String.class);
            query.setParameter("input", "%" + input + "%");
            results = query.getResultList();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
        return results;
    }

    /**
     * Close the session factory.
     */
    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    public static void main(String[] args) {
        PreventSqlInjection demo = new PreventSqlInjection();
        List<String> results = demo.queryDatabase("exampleInput");
        for (String result : results) {
            System.out.println(result);
        }
        PreventSqlInjection.shutdown();
    }
}
