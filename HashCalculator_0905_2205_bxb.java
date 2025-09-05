// 代码生成时间: 2025-09-05 22:05:43
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * A utility class to calculate hash values using Hibernate framework.
 * It demonstrates the usage of Java's MessageDigest API to compute hash values.
 */
public class HashCalculator {

    /**
     * Calculates the hash value of a given input using SHA-256 algorithm.
     * 
     * @param input The input string to calculate the hash for
     * @return A Base64 encoded string representing the hash value
     */
    public String calculateHash(String input) {
        try {
            // Get an instance of SHA-256 MessageDigest
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Update digest using input bytes
            digest.update(input.getBytes(StandardCharsets.UTF_8));

            // Perform the hashing
            byte[] hashBytes = digest.digest();

            // Convert the byte array to a Base64 string
            return Base64.getEncoder().encodeToString(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            // Log and handle the exception if SHA-256 algorithm is not available
            throw new RuntimeException("SHA-256 algorithm is not available", e);
        }
    }

    /**
     * Main method to demonstrate the usage of the HashCalculator class.
     * 
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        HashCalculator calculator = new HashCalculator();

        // Example input string
        String input = "Hello, Hibernate!";

        // Calculate the hash value and print it
        String hashValue = calculator.calculateHash(input);
        System.out.println("Hash of 'Hello, Hibernate!' is: " + hashValue);
    }
}
