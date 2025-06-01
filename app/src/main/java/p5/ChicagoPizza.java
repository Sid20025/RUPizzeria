package p5;

import java.util.ArrayList;

/**
 * Represents a factory for creating Chicago-style pizzas.
 * Implements the {@link PizzaFactory} interface and provides methods
 * for creating specific types of Chicago-style pizzas.
 * @author Siddharth, Ibtesaam
 */
public class ChicagoPizza implements PizzaFactory {

    /**
     * Creates a Chicago-style Deluxe pizza with a Deep Dish crust.
     * The size of the pizza is set to Medium by default but can be adjusted later.
     *
     * @return A {@link Deluxe} pizza object with Chicago-style specifications.
     */
    @Override
    public Pizza createDeluxe() {

        return new Deluxe(Crust.DEEP_DISH, Size.MEDIUM);
    }

    /**
     * Creates a Chicago-style BBQ Chicken pizza with a Pan crust.
     * The size of the pizza is set to Medium by default but can be adjusted later.
     *
     * @return A {@link BBQChicken} pizza object with Chicago-style specifications.
     */
    @Override
    public Pizza createBBQChicken() {

        return new BBQChicken(Crust.PAN, Size.MEDIUM);
    }

    /**
     * Creates a Chicago-style Meatzza pizza with a Stuffed crust.
     * The size of the pizza is set to Medium by default but can be adjusted later.
     *
     * @return A {@link Meatzza} pizza object with Chicago-style specifications.
     */
    @Override
    public Pizza createMeatzza() {

        return new Meatzza(Crust.STUFFED, Size.MEDIUM);
    }

    /**
     * Creates a Chicago-style Build Your Own pizza with a Pan crust.
     * The size of the pizza is set to Medium by default but can be adjusted later.
     * The toppings list is initialized as empty.
     *
     * @return A {@link BuildYourOwn} pizza object with Chicago-style specifications.
     */
    @Override
    public Pizza createBuildYourOwn() {
        return new BuildYourOwn(Crust.PAN, Size.MEDIUM, new ArrayList<>());
    }

}
