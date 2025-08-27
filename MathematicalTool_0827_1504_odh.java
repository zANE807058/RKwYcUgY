// 代码生成时间: 2025-08-27 15:04:50
package com.example.mathtool;

import java.util.Scanner;

/**
 * A mathematical tool class that provides basic mathematical operations.
 */
public class MathematicalTool {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Mathematical Tool - Choose an operation: ");
            System.out.println("1: Add");
            System.out.println("2: Subtract");
            System.out.println("3: Multiply");
            System.out.println("4: Divide");
            System.out.println("5: Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter two numbers to add: ");
                    double num1 = scanner.nextDouble();
                    double num2 = scanner.nextDouble();
                    System.out.printf("The sum of %.2f and %.2f is: %.2f
", num1, num2, add(num1, num2));
                    break;
                case 2:
                    System.out.print("Enter two numbers to subtract: ");
                    num1 = scanner.nextDouble();
                    num2 = scanner.nextDouble();
                    System.out.printf("The difference of %.2f and %.2f is: %.2f
", num1, num2, subtract(num1, num2));
                    break;
                case 3:
                    System.out.print("Enter two numbers to multiply: ");
                    num1 = scanner.nextDouble();
                    num2 = scanner.nextDouble();
                    System.out.printf("The product of %.2f and %.2f is: %.2f
", num1, num2, multiply(num1, num2));
                    break;
                case 4:
                    System.out.print("Enter two numbers to divide: ");
                    num1 = scanner.nextDouble();
                    num2 = scanner.nextDouble();
                    System.out.printf("The quotient of %.2f and %.2f is: %.2f
", num1, num2, divide(num1, num2));
                    break;
                case 5:
                    System.out.println("Exiting the program...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /**
     * Adds two numbers.
     * @param a The first number.
     * @param b The second number.
     * @return The sum of a and b.
     */
    public static double add(double a, double b) {
        return a + b;
    }

    /**
     * Subtracts the second number from the first.
     * @param a The first number.
     * @param b The second number.
     * @return The difference of a and b.
     */
    public static double subtract(double a, double b) {
        return a - b;
    }

    /**
     * Multiplies two numbers.
     * @param a The first number.
     * @param b The second number.
     * @return The product of a and b.
     */
    public static double multiply(double a, double b) {
        return a * b;
    }

    /**
     * Divides the first number by the second.
     * @param a The dividend.
     * @param b The divisor.
     * @return The quotient of a divided by b.
     */
    public static double divide(double a, double b) {
        if (b == 0) {
            throw new IllegalArgumentException("Divisor cannot be zero.");
        }
        return a / b;
    }
}