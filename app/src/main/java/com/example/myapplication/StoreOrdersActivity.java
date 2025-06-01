package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;
import p5.*;

/**
 * StoreOrdersActivity manages and displays all placed orders in the store.
 * Users can view the list of pizzas in a selected order, remove an order, or navigate back to the main menu.
 *
 * @author Siddharth, Ibtesaam
 */
public class StoreOrdersActivity extends AppCompatActivity {

    private ListView listView;
    private TextView totalTextView;
    private Button backButton, removeButton;
    private Spinner orderNumberSpinner;

    private StoreOrdersManager storeOrdersManager;
    private List<Order> storeOrders;
    private int selectedOrderIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_list);

        // Initialize the UI elements
        listView = findViewById(R.id.listview);
        totalTextView = findViewById(R.id.textView13);
        backButton = findViewById(R.id.button7);
        removeButton = findViewById(R.id.button4);
        orderNumberSpinner = findViewById(R.id.spinner);

        // Initialize the singleton manager
        storeOrdersManager = StoreOrdersManager.getInstance();

        // Retrieve the list of orders
        storeOrders = storeOrdersManager.getStoreOrders();

        // Populate Spinner and ListView
        setupOrderNumberSpinner();

        // Set up event listeners
        setupEventListeners();
    }

    /**
     * Configures the Spinner to display order numbers.
     * When a number is selected, the corresponding order details are shown in the ListView.
     */
    private void setupOrderNumberSpinner() {
        // Retrieve all orders from the manager
        List<Order> allOrders = storeOrdersManager.getStoreOrders();
        List<Integer> orderNumbers = new ArrayList<>();

        // Populate Spinner with order numbers
        for (Order order : allOrders) {
            orderNumbers.add(order.getNumber());
        }

        ArrayAdapter<Integer> spinnerAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, orderNumbers);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        orderNumberSpinner.setAdapter(spinnerAdapter);

        // Initially update the ListView to be empty
        updateListViewWithOrder(null);

        // Set a listener to update the ListView when an order is selected
        orderNumberSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected order and update the ListView
                Order selectedOrder = allOrders.get(position);
                updateListViewWithOrder(selectedOrder);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Optional: Handle cases where no selection is made
            }
        });
    }

    /**
     * Updates the ListView to display the pizzas in the selected order.
     *
     * @param selectedOrder The order whose pizzas are to be displayed, or null to clear the ListView.
     */
    private void updateListViewWithOrder(Order selectedOrder) {
        if (selectedOrder != null) {
            List<Pizza> pizzas = selectedOrder.getPizzas();

            // Use the custom adapter to display pizzas
            CurrentOrderAdapter adapter = new CurrentOrderAdapter(this, pizzas);
            listView.setAdapter(adapter);
        } else {
            resetUI();
        }

        // Update the total cost
        updateTotal();
    }

    /**
     * Resets the ListView to an empty state.
     */
    private void resetUI() {
        ArrayAdapter<String> emptyAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<>());
        listView.setAdapter(emptyAdapter);
    }

    /**
     * Configures event listeners for the buttons.
     * Includes logic for the Back button and the Remove Order button.
     */
    private void setupEventListeners() {
        // Back Button
        backButton.setOnClickListener(v -> finish());

        // Remove Button
        removeButton.setOnClickListener(v -> {
            if (!storeOrdersManager.getStoreOrders().isEmpty()) {
                // Get the selected order index from the Spinner
                selectedOrderIndex = orderNumberSpinner.getSelectedItemPosition();

                if (selectedOrderIndex != -1) {
                    // Remove the selected order
                    storeOrdersManager.removeOrder(storeOrders.get(selectedOrderIndex));
                    storeOrders.remove(selectedOrderIndex);

                    // Reset selection and update UI
                    selectedOrderIndex = -1;
                    setupOrderNumberSpinner();
                    resetUI();

                    Toast.makeText(this, "Order removed.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Please select an order to remove.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "No orders available to remove.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Updates the total cost of all orders displayed in the TextView.
     */
    private void updateTotal() {
        double total = 0.0;

        // Calculate the total cost of all orders
        for (Order order : storeOrders) {
            total += order.getTotal();
        }

        // Display the total cost
        totalTextView.setText(String.format("$%.2f", total));
    }
}
