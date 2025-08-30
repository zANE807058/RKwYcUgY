// 代码生成时间: 2025-08-31 06:10:04
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.Random;

/**
 * TestDataGenerator - A utility class to generate test data using Hibernate.
 *
 * @author Your Name
 * @version 1.0
 */
public class TestDataGenerator {

    private static final int RECORDS_TO_GENERATE = 100;
    private static final Random random = new Random();
    private static SessionFactory sessionFactory;

    /**
     * Initializes the Hibernate SessionFactory.
     */
    public static void initializeSessionFactory() {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Exception e) {
            System.err.println("Error creating session factory: " + e.getMessage());
            throw new RuntimeException("Error creating session factory", e);
        }
    }

    /**
     * Shuts down the Hibernate SessionFactory.
     */
    public static void shutdownSessionFactory() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    /**
     * Generates a random number between min and max.
     *
     * @param min The minimum value of the random number.
     * @param max The maximum value of the random number.
     * @return A random number between min and max.
     */
    public static int generateRandomNumber(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }

    /**
     * Generates test data and persists it to the database.
     *
     * @param session The Hibernate session.
     * @return The number of records generated.
     */
    public static int generateTestData(Session session) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            int count = 0;
            for (int i = 0; i < RECORDS_TO_GENERATE; i++) {
                // Your Entity class should be here, for example, User, Product, etc.
                // Entity entity = new Entity();
                // entity.setSomeField(generateRandomNumber(0, 100));
                // session.save(entity);
                count++;
            }
            transaction.commit();
            return count;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Error generating test data", e);
        }
    }

    public static void main(String[] args) {
        initializeSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            int recordsGenerated = generateTestData(session);
            System.out.println("Generated " + recordsGenerated + " records");
        } catch (Exception e) {
            System.err.println("Error generating test data: " + e.getMessage());
        } finally {
            session.close();
            shutdownSessionFactory();
        }
    }
}