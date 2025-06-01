package p5;



/**
 * Singleton class to manage the current order in the application.
 * Ensures that only one current order exists at any time.
 * @author Siddharth, Ibtesaam
 */
public class CurrentOrdersManager {

    /** Singleton instance of the CurrentOrdersManager. */
    private static final CurrentOrdersManager instance = new CurrentOrdersManager();

    /** The current order being managed. */
    private Order currentOrder;

    /**
     * Private constructor to enforce the singleton pattern.
     * Initializes the manager with a new {@link Order}.
     */
    private CurrentOrdersManager() {
        currentOrder = new Order();
    }

    /**
     * Provides access to the singleton instance of CurrentOrdersManager.
     *
     * @return The singleton instance of CurrentOrdersManager.
     */
    public static CurrentOrdersManager getInstance() {
        return instance;
    }

    /**
     * Retrieves the current order being managed.
     *
     * @return The current {@link Order}.
     */
    public Order getCurrentOrder() {
        return currentOrder;
    }

    /**
     * Resets the current order by creating a new {@link Order} instance.
     * Ensures that a fresh order is available for new transactions.
     */
    public void resetCurrentOrder() {
        currentOrder = new Order();
    }
}
