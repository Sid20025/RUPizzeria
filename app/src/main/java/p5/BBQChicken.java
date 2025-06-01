package p5;

import java.util.Arrays;

/**
 * Represents a BBQChicken pizza, which is a specific type of pizza with pre-defined toppings
 * and a price that varies based on its size.
 * The crust type is determined by the style (e.g., Pan for Chicago, Thin for New York).
 * @author Siddharth, Ibtesaam
 */
public class BBQChicken extends Pizza {

    /**
     * Constructs a BBQChicken pizza with the specified crust and size.
     * This pizza has the following fixed toppings: BBQ Chicken, Green Pepper, Provolone, and Cheddar.
     *
     * @param crust The crust type for the pizza (e.g., Pan, Thin).
     * @param size  The size of the pizza (Small, Medium, Large).
     */
    public BBQChicken(Crust crust, Size size) {
        this.crust = crust;
        this.size = size;

        // Set the specific toppings for BBQChicken pizza
        this.toppings = Arrays.asList(Topping.BBQ_CHICKEN, Topping.GREEN_PEPPER, Topping.PROVOLONE, Topping.CHEDDAR);
    }

    /**
     * Calculates the price of the BBQChicken pizza based on its size.
     *
     * @return The price of the pizza:
     *         - Small: $14.99
     *         - Medium: $16.99
     *         - Large: $19.99
     *         If the size is not set, returns 0.0.
     */
    @Override
    public double price() {
        switch (this.size) {
            case SMALL:
                return 14.99;
            case MEDIUM:
                return 16.99;
            case LARGE:
                return 19.99;
            default:
                return 0.0;
        }
    }

    /**
     * Provides a string representation of the BBQChicken pizza, including its crust type,
     * size, toppings, and price.
     *
     * @return A string representation of the BBQChicken pizza.
     */
    @Override
    public String toString() {
        String style = this.getStyle();
        return "BBQChicken{" +
                "style=" + style +
                "crust=" + crust +
                ", size=" + size +
                ", toppings=" + toppings +
                ", price=" + price() +
                '}';
    }
}
