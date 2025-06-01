package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import p5.*;

/**
 * Activity for managing the Chicago-style pizza ordering interface.
 * Handles user interactions, pizza selection, and customization logic.
 * Supports adding pizzas to the current order.
 *
 * @author Siddharth, Ibtesaam
 */

public class ChicagoPizzaActivity extends AppCompatActivity {
    // UI components
    private Spinner pizzaTypeSpinner;
    private TextView crustTypeTextView;
    private TextView pizzaCostTextView;
    private RadioGroup sizeRadioGroup;
    private RadioButton smallRadioButton, mediumRadioButton, largeRadioButton;
    private Button addToOrderButton, backButton;
    private RecyclerView toppingsRecyclerView;

    private ToppingAdapter toppingsAdapter;
    private List<String> availableToppings;

    private PizzaFactory pizzaFactory;
    private Pizza currentPizza;

    private ImageView imageView;

    private Order currentOrder;

    /**
     * Called when the activity is first created.
     * Sets up the UI components, listeners, and initializes the toppings list.
     *
     * @param savedInstanceState If the activity is being re-initialized, contains the previous state.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chicago_style);
       pizzaFactory = new ChicagoPizza();

        // Initialize UI components
        pizzaTypeSpinner = findViewById(R.id.spinner1);
        crustTypeTextView = findViewById(R.id.editText9);
        pizzaCostTextView = findViewById(R.id.editText7);
        sizeRadioGroup = findViewById(R.id.radioGroup);
        smallRadioButton = findViewById(R.id.smallRB);
        mediumRadioButton = findViewById(R.id.mediumRB);
        largeRadioButton = findViewById(R.id.largeRB);
        addToOrderButton = findViewById(R.id.button11);
        backButton = findViewById(R.id.button);
        toppingsRecyclerView = findViewById(R.id.recyclerView);
        imageView = findViewById(R.id.imageView2);

        // Initialize available toppings

        RecyclerView toppingsRecyclerView = findViewById(R.id.recyclerView);

        // Setup RecyclerView for toppings
        List<String> availableToppings = new ArrayList<>();
        setRecyclerView(toppingsRecyclerView, availableToppings);

        // Setup Spinner for Pizza Types
        setupPizzaTypeSpinner();

        resetUI();

        // Handle Add to Order Button Click
        addToOrderButton.setOnClickListener(v -> addToOrder());

        // Handle Back Button Click
        backButton.setOnClickListener(v -> finish());

        // Handle Size Selection
        sizeRadioGroup.setOnCheckedChangeListener((group, checkedId) -> updatePizzaPrice());
    }


    /**
     * Sets up the RecyclerView for managing toppings.
     *
     * @param recyclerView The RecyclerView for displaying toppings.
     * @param availableToppings The list of available toppings.
     */
    private void setRecyclerView(RecyclerView recyclerView, List<String> availableToppings) {
        // Initialize the ToppingAdapter with the list of available toppings
        toppingsAdapter = new ToppingAdapter(availableToppings, selectedToppings -> {
            updateToppingsInPizza(selectedToppings);
            updatePizzaPrice();
        });


        // Set the RecyclerView's layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Set the adapter for the RecyclerView
        recyclerView.setAdapter(toppingsAdapter);

        // Optional: Add an observer to monitor changes in selected toppings

    }
    /**
     * Configures the Spinner for selecting pizza types.
     * Sets up a listener to handle pizza type selection changes.
     */
    private void setupPizzaTypeSpinner() {
        List<String> pizzaTypes = List.of("Select Pizza Type", "Deluxe", "BBQ Chicken", "Meatzza", "Build Your Own");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, pizzaTypes);
        pizzaTypeSpinner.setAdapter(adapter);

        pizzaTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedType = pizzaTypes.get(position);

                handlePizzaTypeChange(selectedType);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // No action needed
            }
        });
    }
    /**
     * Handles changes in the selected pizza type.
     * Updates the UI and toppings list based on the pizza type.
     *
     * @param pizzaType The selected pizza type.
     */
    private void handlePizzaTypeChange(String pizzaType) {
        switch (pizzaType) {
            case "Deluxe":
                imageView.setImageResource(R.drawable.deluxe_chicago);
                currentPizza = pizzaFactory.createDeluxe();
                updateUIForPizzaType();
                updateRecyclerViewForPizzaType("Deluxe");
                break;
            case "BBQ Chicken":
                imageView.setImageResource(R.drawable.bbq_chicken_chicago);
                currentPizza = pizzaFactory.createBBQChicken();
                updateUIForPizzaType();
                updateRecyclerViewForPizzaType("BBQ Chicken");
                break;
            case "Meatzza":
                imageView.setImageResource(R.drawable.meatzza_chicago);
                currentPizza = pizzaFactory.createMeatzza();
                updateUIForPizzaType();
                updateRecyclerViewForPizzaType("Meatzza");

                break;
            case "Build Your Own":
                imageView.setImageResource(R.drawable.byo_chicago);
                currentPizza = pizzaFactory.createBuildYourOwn();
                updateUIForPizzaType();
                updateRecyclerViewForPizzaType("Build Your Own");
                break;
        }
    }

    /**
     * Updates the UI elements based on the current pizza type.
     */
    private void updateUIForPizzaType() {
        crustTypeTextView.setText(currentPizza.getCrust().toString());
        pizzaCostTextView.setText(String.format("%.2f", currentPizza.price()));
    }

    /**
     * Updates the RecyclerView based on the current pizza type.
     */
    private void updateRecyclerViewForPizzaType(String pizzaType) {
        if (pizzaType.equals("Build Your Own")) {
            // Enable customization for Build Your Own
            toppingsAdapter.setCustomizable(true);
            List<String> availableToppings = new ArrayList<>(List.of(
                    "Sausage", "Pepperoni", "Green Pepper", "Onion", "Mushroom",
                    "BBQ Chicken", "Beef", "Ham", "Provolone", "Cheddar",
                    "Pineapple", "Olive", "Spinach", "Tomato"
            ));
            toppingsAdapter.updateToppingsList(availableToppings);

        } else {
            // Predefined pizzas: Disable customization and show selected toppings
            toppingsAdapter.setCustomizable(false);
            if (pizzaType.equals("Deluxe")) {
                toppingsAdapter.setToppings(new ArrayList<>(List.of("Sausage", "Pepperoni", "Green Pepper", "Onion", "Mushroom")));
            } else if (pizzaType.equals("BBQ Chicken")) {
                toppingsAdapter.setToppings(new ArrayList<>(List.of("BBQ Chicken", "Onion", "Provolone", "Cheddar")));
            } else if (pizzaType.equals("Meatzza")) {
                toppingsAdapter.setToppings(new ArrayList<>(List.of("Sausage", "Pepperoni", "Beef", "Ham")));
            }
        }
    }



    /**
     * Handles the small pizza size selection.
     *
     * @param view The clicked RadioButton.
     */
    public void smallChicagoPizza(View view) {
        if (currentPizza == null) return;

        currentPizza.setSize(Size.SMALL);
        updatePizzaPrice();
    }

    /**
     * Handles the medium pizza size selection.
     *
     * @param view The clicked RadioButton.
     */
    public void mediumChicagoPizza(View view) {
        if (currentPizza == null) return;

        currentPizza.setSize(Size.MEDIUM);
        updatePizzaPrice();
    }

    /**
     * Handles the large pizza size selection.
     *
     * @param view The clicked RadioButton.
     */
    public void largeChicagoPizza(View view) {
        if (currentPizza == null) return;

        currentPizza.setSize(Size.LARGE);
        updatePizzaPrice();
    }


    /**
     * Updates the displayed price based on the current pizza configuration.
     */
    private void updatePizzaPrice() {
        if (currentPizza != null) {
            pizzaCostTextView.setText(String.format("%.2f", currentPizza.price()));
        }
    }


    /**
     * Resets the UI components to their default state.
     */
    private void resetUI() {
        // Reset pizza type spinner
        if (pizzaTypeSpinner != null) {
            pizzaTypeSpinner.setSelection(0); // Assuming the first item is a "Select Pizza Type" prompt
        }

        // Clear radio button selection
        if (sizeRadioGroup != null) {
            sizeRadioGroup.clearCheck();
        }
        crustTypeTextView.setText("");
        pizzaCostTextView.setText("0.00");

        toppingsAdapter.updateToppingsList(new ArrayList<>());
        toppingsAdapter.setCustomizable(false);
        imageView.setImageResource(R.drawable.chicago_pizza);
        // Clear current pizza object

    }




    /**
     * Updates the toppings in the current pizza based on the selected toppings.
     *
     * @param selectedToppings The selected toppings to add.
     */
    private void updateToppingsInPizza(List<String> selectedToppings) {
        if (currentPizza instanceof BuildYourOwn) {
            BuildYourOwn buildYourOwnPizza = (BuildYourOwn) currentPizza;
            buildYourOwnPizza.clearToppings(); // Clear existing toppings

            // Add selected toppings to the pizza
            for (String topping : selectedToppings) {
                buildYourOwnPizza.addTopping(Topping.valueOf(topping.toUpperCase().replace(" ", "_")));
            }
        }
    }

    /**
     * Handles the back button action to return to the main menu.
     *
     * @param view The clicked back button.
     */
    public void back_main_menu(View view) {
        // End the current activity and go back to the main menu
        finish();
    }

    /**
     * Adds the current pizza to the order.
     * Validates that a pizza type and size are selected before adding.
     */
    private void addToOrder() {

        if (pizzaTypeSpinner.getSelectedItem() == null || pizzaTypeSpinner.getSelectedItem().toString().equals("Select Pizza Type")) {
            Toast.makeText(this, "Please select a pizza type!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check if a size is selected
        if (!smallRadioButton.isChecked() && !mediumRadioButton.isChecked() && !largeRadioButton.isChecked()) {
            Toast.makeText(this, "Please select a pizza size!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (currentPizza instanceof BuildYourOwn) {
            BuildYourOwn buildYourOwnPizza = (BuildYourOwn) currentPizza;
            buildYourOwnPizza.clearToppings();

            // Get selected toppings from the adapter
            List<String> selectedToppings = toppingsAdapter.getSelectedToppings();

            if (selectedToppings.isEmpty()) {
                Toast.makeText(this, "Please select at least one topping.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Add selected toppings to the pizza
            for (String topping : selectedToppings) {
                buildYourOwnPizza.addTopping(Topping.valueOf(topping.toUpperCase().replace(" ", "_")));
            }


            // Add selected toppings to the pizza

        }
        // Logic to add pizza to the order
        CurrentOrdersManager.getInstance().getCurrentOrder().addPizza(currentPizza);
        Toast.makeText(this, "Pizza added to order!", Toast.LENGTH_SHORT).show();

        resetUI();
    }
}


