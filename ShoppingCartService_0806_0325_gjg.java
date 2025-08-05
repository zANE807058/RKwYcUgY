// 代码生成时间: 2025-08-06 03:25:39
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

// ShoppingCartService.java
public class ShoppingCartService {

    private Session session;
    private Transaction transaction;

    // Constructor to initialize the Hibernate session
    public ShoppingCartService() {
        this.session = new Configuration().configure().buildSessionFactory().openSession();
    }

    // Adds an item to the shopping cart
    public void addItemToCart(int cartId, int itemId) {
        try {
            transaction = session.beginTransaction();
            // Assuming we have a CartItem entity representing the cart items
            session.save(new CartItem(cartId, itemId));
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    // Removes an item from the shopping cart
    public void removeItemFromCart(int cartItemId) {
        try {
            transaction = session.beginTransaction();
            // Assuming we have a CartItem entity representing the cart items
            CartItem item = session.get(CartItem.class, cartItemId);
            if(item != null) {
                session.delete(item);
                transaction.commit();
            } else {
                System.out.println("Item not found in the cart");
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    // Retrieves a list of items in the shopping cart
    public List<CartItem> getCartItems(int cartId) {
        try {
            List<CartItem> cartItems = new ArrayList<>();
            // Assuming we have a CartItem entity representing the cart items
            Iterator<CartItem> items = session.createQuery("FROM CartItem WHERE cartId = :cartId", CartItem.class)
                    .setParameter("cartId", cartId)
                    .list()
                    .iterator();
            while(items.hasNext()) {
                cartItems.add(items.next());
            }
            return cartItems;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Closes the Hibernate session
    public void closeSession() {
        if (session != null) {
            session.close();
        }
    }

    // Main method for testing the ShoppingCartService
    public static void main(String[] args) {
        ShoppingCartService service = new ShoppingCartService();
        try {
            // Add items to cart
            service.addItemToCart(1, 101);
            service.addItemToCart(1, 102);
            
            // Get items from cart
            List<CartItem> items = service.getCartItems(1);
            for (CartItem item : items) {
                System.out.println("Cart item: " + item);
            }

            // Remove an item from cart
            service.removeItemFromCart(101);
            
            // Get items from cart again to verify removal
            items = service.getCartItems(1);
            for (CartItem item : items) {
                System.out.println("Cart item after removal: " + item);
            }

        } finally {
            service.closeSession();
        }
    }
}

// Assuming we have a CartItem entity representing the cart items
class CartItem {
    private int cartItemId;
    private int cartId;
    private int itemId;

    public CartItem() {
    }

    public CartItem(int cartId, int itemId) {
        this.cartId = cartId;
        this.itemId = itemId;
    }

    // Getters and setters
    public int getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(int cartItemId) {
        this.cartItemId = cartItemId;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    @Override
    public String toString() {
        return "CartItem{cartId=" + cartId + ", itemId=" + itemId + "}";
    }
}