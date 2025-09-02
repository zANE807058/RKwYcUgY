// 代码生成时间: 2025-09-02 23:14:34
 * It uses Hibernate to interact with the database.
 * 
 * @author Your Name
 * @version 1.0
 */
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.List;
import java.util.ArrayList;

// Entity class for a Notification
class Notification {
    private int id;
    private String message;
    private String recipient;
    
    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public String getRecipient() { return recipient; }
    public void setRecipient(String recipient) { this.recipient = recipient; }
}

// DAO class for Notification
class NotificationDAO {
    private SessionFactory sessionFactory;
    
    public NotificationDAO() {
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
    }
    
    public void addNotification(Notification notification) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(notification);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    public List<Notification> getAllNotifications() {
        List<Notification> notifications = new ArrayList<>();
        Session session = sessionFactory.openSession();
        try {
            notifications = session.createQuery("FROM Notification").list();
        } finally {
            session.close();
        }
        return notifications;
    }
}

// Service class for Notification
class NotificationService {
    private NotificationDAO notificationDAO;
    
    public NotificationService() {
        this.notificationDAO = new NotificationDAO();
    }
    
    public void sendNotification(String message, String recipient) {
        Notification notification = new Notification();
        notification.setMessage(message);
        notification.setRecipient(recipient);
        notificationDAO.addNotification(notification);
    }
    
    public List<Notification> viewAllNotifications() {
        return notificationDAO.getAllNotifications();
    }
}

// Main class to run the program
public class NotificationSystemMain {
    public static void main(String[] args) {
        NotificationService notificationService = new NotificationService();
        
        // Send a notification
        notificationService.sendNotification("Hello, this is a test notification!", "John Doe");
        
        // View all notifications
        List<Notification> notifications = notificationService.viewAllNotifications();
        for (Notification notification : notifications) {
            System.out.println("Notification ID: " + notification.getId() + ", Message: " + notification.getMessage() + ", Recipient: " + notification.getRecipient());
        }
    }
}
