package p5;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an order in the pizzeria system.
 * Each order is uniquely identified by an order number and contains a list of pizzas.
 * The order includes functionality to calculate the subtotal, tax, and total cost.
 * @author Siddharth, Ibtesaam
 */
public class Order {

    /**
     * Static counter to generate unique order numbers.
     * Incremented each time a new order is created.
     */
    private static int orderCounter = 0;

    /** The unique order number for this order. */
    private final int number;

    /** The list of pizzas in this order. */
    private List<Pizza> pizzas;

    /** New Jersey sales tax rate (6.625%). */
    private static final double TAX_RATE = 0.06625;

    /**
     * Constructs a new Order with a unique order number.
     * Initializes an empty list of pizzas.
     */
    public Order() {
        this.number = orderCounter++;
        this.pizzas = new ArrayList<>();
    }

    /**
     * Adds a pizza to the order.
     *
     * @param pizza The pizza to add to the order.
     */
    public void addPizza(Pizza pizza) {
        pizzas.add(pizza);
    }

    /**
     * Removes a specific pizza from the order.
     * If the pizza is not in the order, no action is taken.
     *
     * @param pizza The pizza to remove from the order.
     */
    public void removePizza(Pizza pizza) {
        pizzas.remove(pizza);
    }

    /**
     * Clears all pizzas from the order.
     */
    public void clearOrder() {
        pizzas.clear();
    }

    /**
     * Calculates the subtotal of the order, which is the sum of the prices of all pizzas.
     *
     * @return The subtotal amount for the order.
     */
    public double getSubtotal() {
        double subtotal = 0.0;
        for (Pizza pizza : pizzas) {
            subtotal += pizza.price();
        }
        return subtotal;
    }

    /**
     * Calculates the sales tax for the order based on the subtotal.
     *
     * @return The sales tax amount for the order.
     */
    public double getTax() {
        return getSubtotal() * TAX_RATE;
    }

    /**
     * Calculates the total cost of the order, which includes the subtotal and sales tax.
     *
     * @return The total cost of the order.
     */
    public double getTotal() {
        return getSubtotal() + getTax();
    }

    /**
     * Gets the unique order number for this order.
     *
     * @return The order number.
     */
    public int getNumber() {
        return number;
    }

    /**
     * Gets the list of pizzas in this order.
     *
     * @return A list of pizzas in the order.
     */
    public List<Pizza> getPizzas() {
        return pizzas;
    }

    /**
     * Provides a string representation of the order, including the order number,
     * details of all pizzas in the order, and the calculated subtotal, tax, and total.
     *
     * @return A string representation of the order.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order Number: ").append(number).append("\n");
        sb.append("Pizzas in Order:\n");
        for (Pizza pizza : pizzas) {
            sb.append(pizza.toString()).append("\n");
        }
        sb.append(String.format("Subtotal: $%.2f\n", getSubtotal()));
        sb.append(String.format("Tax: $%.2f\n", getTax()));
        sb.append(String.format("Total: $%.2f\n", getTotal()));
        return sb.toString();
    }
}
