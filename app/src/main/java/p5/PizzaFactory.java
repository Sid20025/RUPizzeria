package p5;

/**
 * Represents a factory for creating different types of pizzas.
 * This interface defines methods to create specific pizza varieties.
 * Each implementation of this interface corresponds to a specific pizza style
 * (e.g., Chicago-style or New York-style).
 * @author Siddharth, Ibtesaam
 */
public interface PizzaFactory {

    /**
     * Creates a Deluxe pizza with predefined toppings and crust.
     *
     * @return A {@link Pizza} object representing a Deluxe pizza.
     */
    Pizza createDeluxe();

    /**
     * Creates a BBQ Chicken pizza with predefined toppings and crust.
     *
     * @return A {@link Pizza} object representing a BBQ Chicken pizza.
     */
    Pizza createBBQChicken();

    /**
     * Creates a Meatzza pizza with predefined toppings and crust.
     *
     * @return A {@link Pizza} object representing a Meatzza pizza.
     */
    Pizza createMeatzza();

    /**
     * Creates a Build Your Own pizza with a customizable list of toppings.
     * This pizza starts with no toppings, allowing the user to add up to a maximum of 7.
     *
     * @return A {@link Pizza} object representing a Build Your Own pizza.
     */
    Pizza createBuildYourOwn();
}
