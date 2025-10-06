// 代码生成时间: 2025-10-07 01:57:32
package health.monitoring;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

/**
 * HealthMonitoringService class provides functionality to manage health monitoring data.
 * It uses Hibernate to interact with the database.
 */
public class HealthMonitoringService {

    private SessionFactory sessionFactory;

    /**
     * Constructor to initialize the Hibernate SessionFactory.
     */
    public HealthMonitoringService() {
        // Initialize Hibernate SessionFactory
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    /**
     * Method to save health data into the database.
     * @param healthData The health data object to be saved.
     * @return The saved health data object with the generated ID.
     */
    public HealthData saveHealthData(HealthData healthData) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(healthData);
            transaction.commit();
            return healthData;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return null;
        } finally {
            if (session != null) session.close();
        }
    }

    /**
     * Method to retrieve all health data from the database.
     * @return A list of all health data objects.
     */
    public List<HealthData> getAllHealthData() {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Query<HealthData> query = session.createQuery("FROM HealthData", HealthData.class);
            return query.getResultList();
        } finally {
            if (session != null) session.close();
        }
    }

    /**
     * Method to retrieve a single health data object by its ID.
     * @param id The ID of the health data object to retrieve.
     * @return The health data object with the given ID.
     */
    public HealthData getHealthDataById(int id) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            return session.get(HealthData.class, id);
        } finally {
            if (session != null) session.close();
        }
    }

    /**
     * Method to update a health data object.
     * @param healthData The health data object with updated information.
     * @return The updated health data object.
     */
    public HealthData updateHealthData(HealthData healthData) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(healthData);
            transaction.commit();
            return healthData;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return null;
        } finally {
            if (session != null) session.close();
        }
    }

    /**
     * Method to delete a health data object by its ID.
     * @param id The ID of the health data object to delete.
     */
    public void deleteHealthData(int id) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            HealthData healthData = session.get(HealthData.class, id);
            if (healthData != null) {
                session.delete(healthData);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
    }

    /**
     * Main method for testing the HealthMonitoringService.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        HealthMonitoringService service = new HealthMonitoringService();
        // Example usage of the service
        HealthData data = new HealthData();
        data.setBloodPressure("120/80");
        data.setHeartRate(72);
        service.saveHealthData(data);
        System.out.println("Health data saved: " + data);
    }
}

/**
 * HealthData class represents health monitoring data.
 */
class HealthData {
    private int id;
    private String bloodPressure;
    private int heartRate;

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getBloodPressure() { return bloodPressure; }
    public void setBloodPressure(String bloodPressure) { this.bloodPressure = bloodPressure; }
    public int getHeartRate() { return heartRate; }
    public void setHeartRate(int heartRate) { this.heartRate = heartRate; }

    @Override
    public String toString() {
        return "HealthData{id=" + id + ", bloodPressure='" + bloodPressure + ', heartRate=' + heartRate + '}';
    }
}