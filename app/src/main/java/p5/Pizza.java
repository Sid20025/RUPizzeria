package p5;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the base class for all types of pizzas.
 * Provides common properties such as toppings, crust, and size, as well as methods
 * for managing toppings and retrieving pizza details.
 * This class is abstract and must be extended by specific pizza types.
 * @author Siddharth, Ibtesaam
 */
public abstract class Pizza {

    /**
     * The list of toppings on the pizza.
     */
    protected List<Topping> toppings;

    /**
     * The crust type of the pizza (e.g., Pan, Thin, Hand-tossed).
     */
    protected Crust crust;

    /**
     * The size of the pizza (Small, Medium, Large).
     */
    protected Size size;

    /**
     * Constructs a new Pizza object.
     * Initializes an empty list of toppings.
     */
    public Pizza() {
        toppings = new ArrayList<>();
    }

    /**
     * Calculates the price of the pizza.
     * This method is abstract and must be implemented by subclasses.
     *
     * @return The price of the pizza.
     */
    public abstract double price();

    /**
     * Determines the style of the pizza.
     * @return The style of the pizza.
     */
    public String getStyle() {
        if (this instanceof Deluxe && this.crust == Crust.DEEP_DISH) {
            return "Chicago Style";
        }
        if (this instanceof BBQChicken && this.crust == Crust.PAN) {
            return "Chicago Style";
        }
        if (this instanceof Meatzza && this.crust == Crust.STUFFED) {
            return "Chicago Style";
        }
        if (this instanceof BuildYourOwn && this.crust == Crust.PAN) {
            return "Chicago Style";
        }
        return "NY Style";
    }




    /**
     * Gets the list of toppings on the pizza.
     *
     * @return A list of {@link Topping} objects representing the toppings on the pizza.
     */
    public List<Topping> getToppings() {
        return toppings;
    }

    /**
     * Gets the crust type of the pizza.
     *
     * @return The {@link Crust} type of the pizza.
     */
    public Crust getCrust() {
        return crust;
    }

    /**
     * Gets the size of the pizza.
     *
     * @return The {@link Size} of the pizza.
     */
    public Size getSize() {
        return size;
    }

    /**
     * Sets the size of the pizza.
     *
     * @param size The {@link Size} to set for the pizza.
     */
    public void setSize(Size size) {
        this.size = size;
    }

    /**
     * Adds a topping to the pizza.
     * This method is primarily used for BuildYourOwn pizzas.
     * A topping will not be added if it is already present or if the maximum
     * of 7 toppings has been reached.
     *
     * @param topping The {@link Topping} to add to the pizza.
     */
    public void addTopping(Topping topping) {
        if (!toppings.contains(topping) && toppings.size() < 7) {
            toppings.add(topping);
        }
    }

    /**
     * Removes a topping from the pizza.
     * This method is primarily used for BuildYourOwn pizzas.
     *
     * @param topping The {@link Topping} to remove from the pizza.
     */
    public void removeTopping(Topping topping) {
        toppings.remove(topping);
    }

    /**
     * Provides a string representation of the pizza, including its crust, size, and toppings.
     *
     * @return A string representation of the pizza.
     */
    @Override
    public String toString() {
        String style = this.getStyle();
        return "Pizza{" +
                "style=" + style +
                "crust=" + crust +
                ", size=" + size +
                ", toppings=" + toppings +
                '}';
    }
}
