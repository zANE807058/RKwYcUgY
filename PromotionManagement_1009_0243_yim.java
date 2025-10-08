// 代码生成时间: 2025-10-09 02:43:23
// PromotionManagement.java
// This class is responsible for managing marketing activities using Hibernate framework.
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.List;
import java.util.ArrayList;

// The Promotion class represents a marketing activity.
class Promotion {
    private Long id;
    private String name;
    private String description;
    // Constructors, getters and setters are omitted for brevity.
}

// The PromotionManagement class encapsulates the business logic for managing promotions.
public class PromotionManagement {

    private SessionFactory sessionFactory;

    public PromotionManagement() {
        // Initialize the Hibernate session factory.
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    // Method to add a new promotion.
    public void addPromotion(Promotion promotion) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(promotion);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to retrieve all promotions.
    public List<Promotion> getAllPromotions() {
        List<Promotion> promotions = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            promotions = session.createQuery("FROM Promotion", Promotion.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return promotions;
    }

    // Method to update an existing promotion.
    public void updatePromotion(Promotion promotion) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(promotion);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to delete a promotion by its ID.
    public void deletePromotion(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Promotion promotion = session.get(Promotion.class, id);
            if (promotion != null) {
                session.delete(promotion);
                transaction.commit();
            } else {
                transaction.rollback();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Close the session factory when the application is done.
    public void close() {
        sessionFactory.close();
    }
}
