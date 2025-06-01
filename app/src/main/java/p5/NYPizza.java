package p5;

import java.util.ArrayList;

/**
 * Represents a factory for creating New York-style pizzas.
 * Implements the {@link PizzaFactory} interface and provides methods
 * for creating specific types of New York-style pizzas.
 * @author Siddharth, Ibtesaam
 */
public class NYPizza implements PizzaFactory {

    /**
     * Creates a New York-style Deluxe pizza with a Brooklyn crust.
     * The size of the pizza is set to Medium by default but can be adjusted later.
     *
     * @return A {@link Deluxe} pizza object with New York-style specifications.
     */
    @Override
    public Pizza createDeluxe() {

        return new Deluxe(Crust.BROOKLYN, Size.MEDIUM);
    }

    /**
     * Creates a New York-style BBQ Chicken pizza with a Thin crust.
     * The size of the pizza is set to Medium by default but can be adjusted later.
     *
     * @return A {@link BBQChicken} pizza object with New York-style specifications.
     */
    @Override
    public Pizza createBBQChicken() {

        return new BBQChicken(Crust.THIN, Size.MEDIUM);
    }

    /**
     * Creates a New York-style Meatzza pizza with a Hand-tossed crust.
     * The size of the pizza is set to Medium by default but can be adjusted later.
     *
     * @return A {@link Meatzza} pizza object with New York-style specifications.
     */
    @Override
    public Pizza createMeatzza() {

        return new Meatzza(Crust.HAND_TOSSED, Size.MEDIUM);
    }

    /**
     * Creates a New York-style Build Your Own pizza with a Hand-tossed crust.
     * The size of the pizza is set to Medium by default but can be adjusted later.
     * The toppings list is initialized as empty.
     *
     * @return A {@link BuildYourOwn} pizza object with New York-style specifications.
     */
    @Override
    public Pizza createBuildYourOwn() {

        return new BuildYourOwn(Crust.HAND_TOSSED, Size.MEDIUM, new ArrayList<>());
    }
}
