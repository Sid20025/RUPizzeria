package p5;

import java.util.Arrays;

/**
 * Represents a Meatzza pizza, which is a specialty pizza with predefined toppings.
 * The crust type and size are specified during creation, and the price is determined
 * based on the size of the pizza.
 * @author Siddharth, Ibtesaam
 */
public class Meatzza extends Pizza {

    /**
     * Constructs a Meatzza pizza with the specified crust and size.
     * This pizza has the following fixed toppings:
     * Sausage, Pepperoni, Beef, and Ham.
     *
     * @param crust The crust type for the pizza (e.g., Stuffed for Chicago, Hand-tossed for New York).
     * @param size  The size of the pizza (Small, Medium, Large).
     */
    public Meatzza(Crust crust, Size size) {
        this.crust = crust;
        this.size = size;


        this.toppings = Arrays.asList(
                Topping.SAUSAGE,
                Topping.PEPPERONI,
                Topping.BEEF,
                Topping.HAM
        );
    }

    /**
     * Calculates the price of the Meatzza pizza based on its size.
     *
     * @return The price of the pizza:
     *         - Small: $17.99
     *         - Medium: $19.99
     *         - Large: $21.99
     *         If the size is not set, returns 0.0.
     */
    @Override
    public double price() {
        switch (this.size) {
            case SMALL:
                return 17.99;
            case MEDIUM:
                return 19.99;
            case LARGE:
                return 21.99;
            default:
                return 0.0;
        }
    }

    /**
     * Provides a string representation of the Meatzza pizza, including its crust type,
     * size, toppings, and price.
     *
     * @return A string representation of the Meatzza pizza.
     */
    @Override
    public String toString() {
        String style = this.getStyle();
        return "Meatzza{" +
                "style=" + style +
                "crust=" + crust +
                ", size=" + size +
                ", toppings=" + toppings +
                ", price=" + price() +
                '}';
    }
}
