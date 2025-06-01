package com.example.myapplication;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import p5.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Activity for managing the current order in the cart view.
 * Displays the details of the current order, allows placing orders,
 * clearing the order, removing items, and navigating back to the main menu.
 *
 * @author Siddharth,Ibtesaam
 */
public class CurrentOrdersActivity extends AppCompatActivity {

    // UI Components
    private ListView ordersListView;
    private TextView subtotalTextView, taxTextView, totalTextView, orderNumberTextView;
    private Button clearOrderButton, placeOrderButton, removeOrderbutton, backButton;

    // Singleton Managers
    private CurrentOrdersManager currentOrdersManager;
    private StoreOrdersManager storeOrdersManager;

    // Current Order and Adapter
    private Order currentOrder;
    private CurrentOrderAdapter pizzaArrayAdapter;

    /**
     * Called when the activity is created.
     * Initializes the UI components, sets up listeners, and populates the order details.
     *
     * @param savedInstanceState If the activity is being re-initialized, contains the previous state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart);

        // Initialize Singleton Managers
        currentOrdersManager = CurrentOrdersManager.getInstance();
        storeOrdersManager = StoreOrdersManager.getInstance();

        // Initialize UI Components
        ordersListView = findViewById(R.id.listView);
        subtotalTextView = findViewById(R.id.textView11);
        taxTextView = findViewById(R.id.textView12);
        totalTextView = findViewById(R.id.textView13);
        orderNumberTextView = findViewById(R.id.textView15);
        clearOrderButton = findViewById(R.id.button3);
        placeOrderButton = findViewById(R.id.button2);
        removeOrderbutton = findViewById(R.id.button4);
        backButton = findViewById(R.id.button6);

        // Retrieve the current order
        currentOrder = currentOrdersManager.getCurrentOrder();

        // Populate the order details in the UI
        updateOrderDetails();

        // Set up event listeners for the buttons
        setupEventListeners();
    }

    /**
     * Sets up event listeners for the buttons in the activity.
     * Handles clear order, place order, remove item, and back button clicks.
     */
    private void setupEventListeners() {
        // Clear Order Button
        clearOrderButton.setOnClickListener(v -> {
            currentOrder.clearOrder(); // Clear all pizzas in the current order
            updateOrderDetails(); // Refresh the order details in the UI
            Toast.makeText(this, "Order cleared.", Toast.LENGTH_SHORT).show();
        });

        // Place Order Button
        placeOrderButton.setOnClickListener(v -> {
            if (currentOrdersManager.getCurrentOrder().getPizzas().isEmpty()) {
                Toast.makeText(this, "Cannot place an empty order.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Add the current order to store orders and reset the current order
            storeOrdersManager.addOrder(currentOrdersManager.getCurrentOrder());
            currentOrdersManager.resetCurrentOrder();
            updateOrderDetails(); // Refresh the UI after placing the order

            Toast.makeText(this, "Order placed successfully!", Toast.LENGTH_SHORT).show();
        });

        ordersListView.setOnItemClickListener((parent, view, position, id) -> {
            // Reset background color for all items
            for (int i = 0; i < ordersListView.getChildCount(); i++) {
                ordersListView.getChildAt(i).setBackgroundColor(getResources().getColor(android.R.color.transparent));
            }

            // Highlight the selected item
            view.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));

            // Perform your logic here (e.g., handling the selected pizza)

        });

        // Remove Item Button
        removeOrderbutton.setOnClickListener(v -> {
            // Get the selected item's position in the ListView
            int selectedPosition = ordersListView.getCheckedItemPosition();

            if (selectedPosition == AdapterView.INVALID_POSITION) {
                // No item selected, show an error message
                Toast.makeText(this, "Please select an item to remove.", Toast.LENGTH_SHORT).show();
            } else {
                // Get the selected pizza and remove it from the current order
                Pizza selectedPizza = (Pizza) ordersListView.getItemAtPosition(selectedPosition);
                currentOrder.removePizza(selectedPizza);

                // Update the UI
                updateOrderDetails();

                // Show success message
                Toast.makeText(this, "Item removed from the order.", Toast.LENGTH_SHORT).show();
            }
        });

        // Back Button
        backButton.setOnClickListener(v -> {
            // Navigate back to the main menu
            finish();
        });
    }

    /**
     * Populates the ListView with the pizzas in the current order.
     * Converts the list of pizzas into a descriptive format for display.
     */
    private void populateListView() {
        List<Pizza> pizzas = currentOrder.getPizzas();

        // Create and set up the adapter for the ListView
        pizzaArrayAdapter = new CurrentOrderAdapter(this, pizzas);
        ordersListView.setAdapter(pizzaArrayAdapter);
    }

    /**
     * Updates the order details in the UI, including subtotal, tax, total, and order number.
     * Refreshes the ListView with the current order's pizzas.
     */
    private void updateOrderDetails() {
        // Populate the ListView with current order pizzas
        populateListView();

        // Get the current order details
        int orderNumber = currentOrdersManager.getCurrentOrder().getNumber();
        double subtotal = currentOrder.getSubtotal();
        double tax = currentOrder.getTax(); // Assuming a fixed tax rate
        double total = currentOrder.getTotal();

        // Update the UI components with the order details
        subtotalTextView.setText(String.format("$%.2f", subtotal));
        taxTextView.setText(String.format("$%.2f", tax));
        totalTextView.setText(String.format("$%.2f", total));
        orderNumberTextView.setText(String.format("%d", orderNumber));
    }
}
