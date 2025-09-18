// 代码生成时间: 2025-09-18 14:06:58
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import java.util.List;

/**
 * MathUtils - A utility class providing mathematical operations using Hibernate framework.
 */
public class MathUtils {

    /**
     * Calculate the sum of two numbers.
     * @param num1 The first number.
     * @param num2 The second number.
     * @return The sum of the two numbers.
     */
    public static double sum(double num1, double num2) {
        return num1 + num2;
    }

    /**
     * Calculate the difference between two numbers.
     * @param num1 The first number.
     * @param num2 The second number.
     * @return The difference between the two numbers.
     */
    public static double difference(double num1, double num2) {
        return num1 - num2;
    }

    /**
     * Calculate the product of two numbers.
     * @param num1 The first number.
     * @param num2 The second number.
     * @return The product of the two numbers.
     */
    public static double product(double num1, double num2) {
        return num1 * num2;
    }

    /**
     * Calculate the division of two numbers.
     * @param num1 The first number (dividend).
     * @param num2 The second number (divisor).
     * @return The division result or null if divisor is zero.
     */
    public static Double division(double num1, double num2) {
        if (num2 == 0) {
            throw new IllegalArgumentException("Divisor cannot be zero.");
        }
        return num1 / num2;
    }

    /*
     * Hibernate Session Factory Initialization.
     */
    private static SessionFactory sessionFactory;
    static {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + "" + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static void main(String[] args) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            try {
                // Perform mathematical operations
                double sumResult = sum(10.0, 20.0);
                System.out.println("Sum: " + sumResult);

                double differenceResult = difference(10.0, 5.0);
                System.out.println("Difference: " + differenceResult);

                double productResult = product(10.0, 5.0);
                System.out.println("Product: " + productResult);

                try {
                    double divisionResult = division(10.0, 2.0);
                    System.out.println("Division: " + divisionResult);
                } catch (IllegalArgumentException e) {
                    System.err.println("Error: " + e.getMessage());
                }

                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
