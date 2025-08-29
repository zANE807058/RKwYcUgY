// 代码生成时间: 2025-08-29 23:40:02
package com.example.urlvalidator;

import java.net.URL;
import javax.persistence.Entity;
import javax.persistence.Id;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate..cfg.Environment;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class UrlValidatorService {

    private SessionFactory sessionFactory;

    // Constructor to create a Session Factory
    public UrlValidatorService() {
        // Create a configuration object and configure it
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Error creating sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    // Method to validate the URL
    public boolean validateUrl(String urlString) {
        try {
            URL url = new URL(urlString);
            // We can add more validations as needed, such as checking the protocol, hostname, etc.
            return true;
        } catch (Exception e) {
            // Log the exception or handle it as required by the application
            System.err.println("Invalid URL: " + urlString);
            return false;
        }
    }

    // Close the session factory when done
    public void close() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    // Main method to test the URL Validator Service
    public static void main(String[] args) {
        try {
            UrlValidatorService service = new UrlValidatorService();
            String testUrl = "http://www.example.com";
            boolean isValid = service.validateUrl(testUrl);
            System.out.println("Is the URL valid? " + isValid);
            service.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
