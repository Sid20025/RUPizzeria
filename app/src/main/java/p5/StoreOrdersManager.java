package p5;


import java.util.ArrayList;
import java.util.List;

/**
 * Singleton class to manage store orders.
 * Provides shared access to all completed orders in the store and ensures centralized management.
 */
public class StoreOrdersManager {

    /** Singleton instance of the StoreOrdersManager. */
    private static final StoreOrdersManager instance = new StoreOrdersManager();

    /** List to store all completed orders in the store. */
    private final List<Order> storeOrders;

    /**
     * Private constructor to prevent external instantiation.
     * Initializes the list of store orders.
     */
    private StoreOrdersManager() {
        storeOrders = new ArrayList<>();
    }

    /**
     * Gets the singleton instance of the StoreOrdersManager.
     *
     * @return The singleton instance of StoreOrdersManager.
     */
    public static StoreOrdersManager getInstance() {
        return instance;
    }

    /**
     * Adds a completed order to the store orders list.
     *
     * @param order The completed order to add.
     */
    public void addOrder(Order order) {
        storeOrders.add(order);
    }

    /**
     * Retrieves a list of all completed store orders.
     * Returns a copy of the list to prevent direct modification.
     *
     * @return A copy of the list of store orders.
     */
    public List<Order> getStoreOrders() {
        return new ArrayList<>(storeOrders);
    }

    /**
     * Removes a completed order from the store orders list.
     *
     * @param order The order to remove.
     */
    public void removeOrder(Order order) {
        storeOrders.remove(order);
    }
}
