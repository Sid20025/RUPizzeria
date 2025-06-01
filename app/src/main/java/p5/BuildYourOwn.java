package p5;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a "Build Your Own" pizza, allowing customization of toppings
 * up to a maximum of 7. The price varies based on size and number of toppings.
 * @author Siddharth, Ibtesaam
 */
public class BuildYourOwn extends Pizza {

    /** Base price for a small Build Your Own pizza. */
    private static final double SMALL_BASE_PRICE = 8.99;

    /** Base price for a medium Build Your Own pizza. */
    private static final double MEDIUM_BASE_PRICE = 10.99;

    /** Base price for a large Build Your Own pizza. */
    private static final double LARGE_BASE_PRICE = 12.99;

    /** Price for each additional topping added to the pizza. */
    private static final double TOPPING_PRICE = 1.69;

    /**
     * Constructs a "Build Your Own" pizza with the specified crust, size, and initial toppings.
     *
     * @param crust    The crust type for the pizza (e.g., Pan, Hand-tossed).
     * @param size     The size of the pizza (Small, Medium, Large).
     * @param toppings A list of initial toppings added to the pizza.
     */
    public BuildYourOwn(Crust crust, Size size, List<Topping> toppings) {
        this.crust = crust;
        this.size = size;
        this.toppings = new ArrayList<>(toppings);
    }

    /**
     * Adds a topping to the pizza. The maximum number of toppings allowed is 7.
     * If the topping is already present or the maximum number of toppings is reached,
     * the topping is not added.
     *
     * @param topping The topping to add to the pizza.
     */
    public void addTopping(Topping topping) {
        if (toppings.size() < 7 && !toppings.contains(topping)) {
            toppings.add(topping);
        }
    }

    public void clearToppings(){
         // Check if the toppings list exists
            toppings.clear();    // Clear all toppings from the list

    }

    /**
     * Removes a topping from the pizza. If the topping is not present, no action is taken.
     *
     * @param topping The topping to remove from the pizza.
     */
    public void removeTopping(Topping topping) {
        toppings.remove(topping);
    }

    /**
     * Calculates the price of the Build Your Own pizza based on its size and number of toppings.
     * The price is the base price for the selected size plus the cost of all additional toppings.
     *
     * @return The total price of the pizza. If the size is not set, returns 0.0.
     */
    @Override
    public double price() {
        double basePrice;
        switch (this.size) {
            case SMALL:
                basePrice = SMALL_BASE_PRICE;
                break;
            case MEDIUM:
                basePrice = MEDIUM_BASE_PRICE;
                break;
            case LARGE:
                basePrice = LARGE_BASE_PRICE;
                break;
            default:
                return 0.0; // Default case if size is not set
        }
        return basePrice + (toppings.size() * TOPPING_PRICE);
    }

    /**
     * Provides a string representation of the Build Your Own pizza, including its crust type,
     * size, toppings, and price.
     *
     * @return A string representation of the Build Your Own pizza.
     */
    @Override
    public String toString() {
        String style = this.getStyle();
        return "BuildYourOwn{" +
                "style=" + style +
                "crust=" + crust +
                ", size=" + size +
                ", toppings=" + toppings +
                ", price=" + price() +
                '}';
    }
}
