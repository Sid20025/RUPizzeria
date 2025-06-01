package com.example.myapplication;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import p5.*;

import p5.Deluxe;
import p5.Pizza;
import p5.Topping;

/**
 * Activity class for managing New York-style pizza orders.
 * Handles pizza type selection, size, toppings, and adding pizzas to the order.
 * @author Siddharth, Ibtesaam
 */

public class NewYorkPizzaActivity extends AppCompatActivity {

        private Spinner pizzaTypeSpinner;
        private TextView crustTypeTextView;
        private TextView pizzaCostTextView;
        private RadioGroup sizeRadioGroup;
        private RadioButton smallRadioButton, mediumRadioButton, largeRadioButton;
        private Button addToOrderButton, backButton;

        private RecyclerView toppingsRecyclerView;

        private ToppingAdapter toppingsAdapter;

        private PizzaFactory pizzaFactory;
        private List<String> availableToppings;
        private Pizza currentPizza;

        private ImageView imageView;

        private Order currentOrder;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.ny_style);
            pizzaFactory = new NYPizza();

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
            List<String> availableToppings = new ArrayList<>();
            RecyclerView toppingsRecyclerView = findViewById(R.id.recyclerView);


            // Setup RecyclerView for toppings
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
         * @param recyclerView      The RecyclerView to set up.
         * @param availableToppings List of available toppings.
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
         * Sets up the Spinner for selecting pizza types.
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
         * Handles changes to the selected pizza type.
         *
         * @param pizzaType The selected pizza type.
         */
        private void handlePizzaTypeChange(String pizzaType) {
            switch (pizzaType) {
                case "Deluxe":

                    imageView.setImageResource(R.drawable.deluxe_ny);
                    currentPizza = pizzaFactory.createDeluxe();
                    updateUIForPizzaType();
                    updateRecyclerViewForPizzaType("Deluxe");
                    break;
                case "BBQ Chicken":

                    imageView.setImageResource(R.drawable.bbq_chicken_ny);
                    currentPizza = pizzaFactory.createBBQChicken();
                    updateUIForPizzaType();
                    updateRecyclerViewForPizzaType("BBQ Chicken");
                    break;
                case "Meatzza":

                    imageView.setImageResource(R.drawable.meatzza_ny);
                    currentPizza = pizzaFactory.createMeatzza();
                    updateUIForPizzaType();
                    updateRecyclerViewForPizzaType("Meatzza");

                    break;
                case "Build Your Own":

                    imageView.setImageResource(R.drawable.byo_ny);
                    currentPizza = pizzaFactory.createBuildYourOwn();
                    updateUIForPizzaType();
                    updateRecyclerViewForPizzaType("Build Your Own");
                    break;
            }

        }
        /**
         * Updates the UI with details of the selected pizza.
         */
        private void updateUIForPizzaType() {
            crustTypeTextView.setText(currentPizza.getCrust().toString());
            pizzaCostTextView.setText(String.format("%.2f", currentPizza.price()));
        }

        /**
         * Updates the RecyclerView for the selected pizza type.
         *
         * @param pizzaType The selected pizza type.
         */
        private void updateRecyclerViewForPizzaType(String pizzaType) {
            if (pizzaType.equals("Build Your Own")) {
                List<String> availableToppings = new ArrayList<>(List.of(
                        "Sausage", "Pepperoni", "Green Pepper", "Onion", "Mushroom",
                        "BBQ Chicken", "Beef", "Ham", "Provolone", "Cheddar",
                        "Pineapple", "Olive", "Spinach", "Tomato"
                ));
                // Enable customization for Build Your Own
                toppingsAdapter.setCustomizable(true);
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
        public void smallNYPizza(View view) {
            if (currentPizza == null) return;

            currentPizza.setSize(Size.SMALL);
            updatePizzaPrice();
        }

        /**
         * Handles the medium pizza size selection.
         *
         * @param view The clicked RadioButton.
         */
        public void mediumNYPizza(View view) {
            if (currentPizza == null) return;

            currentPizza.setSize(Size.MEDIUM);
            updatePizzaPrice();
        }

        /**
         * Handles the large pizza size selection.
         *
         * @param view The clicked RadioButton.
         */
        public void largeNYPizza(View view) {
            if (currentPizza == null) return;

            currentPizza.setSize(Size.LARGE);
            updatePizzaPrice();
        }

        public void addToppings(View view) {

        }
        /**
         * Updates the pizza price
         */
        private void updatePizzaPrice() {
            if (currentPizza != null) {
                pizzaCostTextView.setText(String.format("%.2f", currentPizza.price()));
            }
        }




        /**
         * Resets the UI to its default state with no selections.
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
            imageView.setImageResource(R.drawable.ny_pizza);
            // Clear current pizza object

        }

        // Other methods to handle pizza selection and size changes...
        /**
         * Updates the toppings in the pizza for "Build Your Own".
         *
         * @param selectedToppings List of selected toppings.
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
     * Navigates back to the main menu.
     * Ends the current activity and returns to the previous screen.
     *
     * @param view The clicked view triggering this method.
     */
        public void back_main_menu(View view) {
            // End the current activity and go back to the main menu
            finish();
        }

        /**
         * Adds the configured pizza to the current order.
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


