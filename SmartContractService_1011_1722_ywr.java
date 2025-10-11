// 代码生成时间: 2025-10-11 17:22:04
package com.example.smartcontract;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import com.example.model.SmartContract;
import java.util.List;

/**
 * Service class to handle operations related to Smart Contracts using Hibernate.
 */
public class SmartContractService {

    private static final SessionFactory sessionFactory;

    static {
        // Initialize the Session Factory.
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    /**
     * Adds a new smart contract to the database.
     * 
     * @param contract The smart contract object to be added.
     * @return The saved smart contract object.
     */
    public SmartContract addSmartContract(SmartContract contract) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                session.save(contract);
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) transaction.rollback();
                throw new RuntimeException("Error adding smart contract", e);
            }
            return contract;
        }
    }

    /**
     * Retrieves all smart contracts from the database.
     * 
     * @return A list of all smart contract objects.
     */
    public List<SmartContract> getAllSmartContracts() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM SmartContract", SmartContract.class).list();
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving smart contracts", e);
        }
    }

    /**
     * Retrieves a specific smart contract by its ID.
     * 
     * @param id The ID of the smart contract to retrieve.
     * @return The smart contract object if found, otherwise null.
     */
    public SmartContract getSmartContractById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(SmartContract.class, id);
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving smart contract by ID", e);
        }
    }

    /**
     * Updates a smart contract in the database.
     * 
     * @param contract The smart contract object with updated information.
     * @return The updated smart contract object.
     */
    public SmartContract updateSmartContract(SmartContract contract) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                session.update(contract);
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) transaction.rollback();
                throw new RuntimeException("Error updating smart contract", e);
            }
            return contract;
        }
    }

    /**
     * Deletes a smart contract from the database by its ID.
     * 
     * @param id The ID of the smart contract to delete.
     */
    public void deleteSmartContract(int id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                SmartContract contract = session.get(SmartContract.class, id);
                if (contract != null) {
                    session.delete(contract);
                }
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) transaction.rollback();
                throw new RuntimeException("Error deleting smart contract", e);
            }
        }
    }

    public static void main(String[] args) {
        // Example usage of the service.
        SmartContractService service = new SmartContractService();
        SmartContract contract = new SmartContract();
        // Set properties of the contract.
        service.addSmartContract(contract);
        // Perform other operations as needed.
    }
}
