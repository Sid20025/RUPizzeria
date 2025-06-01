package p5;

import java.util.Arrays;

/**
 * Represents a Deluxe pizza, which is a specialty pizza with predefined toppings.
 * The crust type and size are specified during creation, and the price is determined
 * based on the size of the pizza.
 * @author Siddharth, Ibtesaam
 */
public class Deluxe extends Pizza {

    /**
     * Constructs a Deluxe pizza with the specified crust and size.
     * This pizza has the following fixed toppings:
     * Sausage, Pepperoni, Green Pepper, Onion, and Mushroom.
     *
     * @param crust The crust type for the pizza (e.g., Deep Dish, Brooklyn).
     * @param size  The size of the pizza (Small, Medium, Large).
     */
    public Deluxe(Crust crust, Size size) {
        this.crust = crust;
        this.size = size;


        this.toppings = Arrays.asList(
                Topping.SAUSAGE,
                Topping.PEPPERONI,
                Topping.GREEN_PEPPER,
                Topping.ONION,
                Topping.MUSHROOM
        );
    }

    /**
     * Calculates the price of the Deluxe pizza based on its size.
     *
     * @return The price of the pizza:
     *         - Small: $16.99
     *         - Medium: $18.99
     *         - Large: $20.99
     *         If the size is not set, returns 0.0.
     */
    @Override
    public double price() {
        switch (this.size) {
            case SMALL:
                return 16.99;
            case MEDIUM:
                return 18.99;
            case LARGE:
                return 20.99;
            default:
                return 0.0;
        }
    }

    /**
     * Provides a string representation of the Deluxe pizza, including its crust type,
     * size, toppings, and price.
     *
     * @return A string representation of the Deluxe pizza.
     */
    @Override
    public String toString() {
        String style = this.getStyle();
        return "Deluxe{" +
                "style=" + style +
                "crust=" + crust +
                ", size=" + size +
                ", toppings=" + toppings +
                ", price=" + price() +
                '}';
    }
}
