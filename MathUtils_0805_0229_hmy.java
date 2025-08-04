// 代码生成时间: 2025-08-05 02:29:15
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.SessionFactoryBuilder;
import org.hibernate.query.Query;
import org.hibernate.HibernateException;
import java.util.Properties;
import java.util.List;
import java.util.ArrayList;

/**
 * MathUtils class provides a set of mathematical operations.
 */
public class MathUtils {

    /**
     * Adds two numbers.
     * @param num1 First number to add.
     * @param num2 Second number to add.
     * @return The sum of num1 and num2.
     */
    public double add(double num1, double num2) {
        return num1 + num2;
    }

    /**
     * Subtracts the second number from the first.
     * @param num1 First number to subtract.
     * @param num2 Number to subtract from num1.
     * @return The difference of num1 and num2.
     */
    public double subtract(double num1, double num2) {
        return num1 - num2;
    }

    /**
     * Multiplies two numbers.
     * @param num1 First number to multiply.
     * @param num2 Second number to multiply.
     * @return The product of num1 and num2.
     */
    public double multiply(double num1, double num2) {
        return num1 * num2;
    }

    /**
     * Divides the first number by the second.
     * @param num1 First number to divide.
     * @param num2 Number to divide num1 by.
     * @return The quotient of num1 and num2.
     * @throws IllegalArgumentException if num2 is zero.
     */
    public double divide(double num1, double num2) {
        if (num2 == 0) {
            throw new IllegalArgumentException("Cannot divide by zero.");
        }
        return num1 / num2;
    }

    /**
     * Calculates the modulus of the two numbers.
     * @param num1 First number.
     * @param num2 Second number.
     * @return The modulus of num1 and num2.
     */
    public double modulus(double num1, double num2) {
        return num1 % num2;
    }

    /**
     * Main method to test the MathUtils class.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        MathUtils mathUtils = new MathUtils();

        try {
            System.out.println("Addition: " + mathUtils.add(10, 5));
            System.out.println("Subtraction: " + mathUtils.subtract(10, 5));
            System.out.println("Multiplication: " + mathUtils.multiply(10, 5));
            System.out.println("Division: " + mathUtils.divide(10, 5));
            System.out.println("Modulus: " + mathUtils.modulus(10, 5));

            // Test division by zero
            System.out.println("Division by zero: " + mathUtils.divide(10, 0));
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
