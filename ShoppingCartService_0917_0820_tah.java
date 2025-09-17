// 代码生成时间: 2025-09-17 08:20:34
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// ShoppingItem entity class
class ShoppingItem {
    private UUID id;
    private String name;
    private double price;

    public ShoppingItem() {}

    public ShoppingItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    // Getters and setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}

// ShoppingCart entity class
class ShoppingCart {
    private UUID id;
    private List<ShoppingItem> items = new ArrayList<>();

    public ShoppingCart() {}

    // Getters and setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public List<ShoppingItem> getItems() { return items; }
    public void setItems(List<ShoppingItem> items) { this.items = items; }

    // Add item to the cart
    public void addItem(ShoppingItem item) {
        items.add(item);
    }
}

// ShoppingCartService class
public class ShoppingCartService {
    SessionFactory sessionFactory;

    public ShoppingCartService() {
        // Create sessionFactory from hibernate.cfg.xml
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    // Method to create a new shopping cart
    public ShoppingCart createCart() {
        ShoppingCart cart = new ShoppingCart();
        cart.setId(UUID.randomUUID());

        // Persist the new cart
        persist(cart);
        return cart;
    }

    // Method to add an item to a cart
    public void addItemToCart(ShoppingCart cart, ShoppingItem item) {
        cart.addItem(item);

        // Persist the changes
        persist(cart);
    }

    // Helper method to persist entities using Hibernate
    private void persist(Object obj) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(obj);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException("Error persisting entity", e);
        } finally {
            if (session != null) session.close();
        }
    }

    // Example usage
    public static void main(String[] args) {
        ShoppingCartService service = new ShoppingCartService();
        ShoppingCart cart = service.createCart();
        
        ShoppingItem item1 = new ShoppingItem("Apple", 0.50);
        ShoppingItem item2 = new ShoppingItem("Banana", 0.30);
        
        service.addItemToCart(cart, item1);
        service.addItemToCart(cart, item2);
    }
}