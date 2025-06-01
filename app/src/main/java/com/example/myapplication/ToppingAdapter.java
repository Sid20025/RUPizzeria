package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Adapter class for managing the RecyclerView of toppings.
 * Handles displaying topping names, images, and selection states.
 * @author Siddharth, Ibtesaam
 */
public class ToppingAdapter extends RecyclerView.Adapter<ToppingAdapter.ToppingsViewHolder> {

    private List<String> toppingsList; // List of available toppings
    private final List<String> selectedToppings; // List of selected toppings
    private boolean isCustomizable; // Whether the toppings can be customized
    private final Map<String, Integer> toppingImages; // Mapping of topping names to drawable resources
    private final ToppingSelectionListener listener; // Callback listener for topping selection changes

    /**
     * Constructor to initialize the adapter with toppings and a listener.
     *
     * @param toppingsList List of available toppings.
     * @param listener Callback listener for topping selection changes.
     */
    public ToppingAdapter(List<String> toppingsList, ToppingSelectionListener listener) {
        this.toppingsList = toppingsList;
        this.listener = listener;
        this.selectedToppings = new ArrayList<>();
        this.isCustomizable = true;
        this.toppingImages = createToppingImageMap(); // Initialize image mapping
    }

    /**
     * Returns the list of selected toppings.
     *
     * @return List of selected toppings.
     */
    public List<String> getSelectedToppings() {
        return selectedToppings;
    }

    /**
     * Updates the list of available toppings and refreshes the RecyclerView.
     *
     * @param newToppings List of new available toppings.
     */
    public void updateToppingsList(List<String> newToppings) {
        toppingsList.clear();
        toppingsList.addAll(newToppings);
        notifyDataSetChanged(); // Notify RecyclerView to refresh its display
    }

    /**
     * Interface for handling topping selection changes.
     */
    public interface ToppingSelectionListener {
        /**
         * Called when the topping selection changes.
         *
         * @param selectedToppings List of selected toppings.
         */
        void onToppingSelectionChanged(List<String> selectedToppings);
    }

    /**
     * Sets whether the toppings are customizable.
     *
     * @param customizable True if customizable, false otherwise.
     */
    public void setCustomizable(boolean customizable) {
        this.isCustomizable = customizable;
        notifyDataSetChanged(); // Update the RecyclerView
    }

    /**
     * Sets the toppings for predefined pizzas and updates the RecyclerView.
     *
     * @param toppings List of toppings to set.
     */
    public void setToppings(List<String> toppings) {
        this.toppingsList = toppings;
        this.selectedToppings.clear();
        this.selectedToppings.addAll(toppings);
        notifyDataSetChanged(); // Refresh the RecyclerView
    }

    @NonNull
    @Override
    public ToppingsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_topping, parent, false); // Inflate the item_topping layout
        return new ToppingsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ToppingsViewHolder holder, int position) {
        String topping = toppingsList.get(position);
        holder.toppingName.setText(topping);

        // Set the topping image if available
        if (toppingImages.containsKey(topping)) {
            holder.toppingImage.setImageResource(toppingImages.get(topping));
        } else {
            holder.toppingImage.setImageResource(R.drawable.sausage); // Default image
        }

        // Set the CheckBox state and enable/disable based on customization
        holder.toppingCheckBox.setChecked(selectedToppings.contains(topping));
        holder.toppingCheckBox.setEnabled(isCustomizable);

        // Handle CheckBox click events
        holder.toppingCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isCustomizable) {
                if (isChecked) {
                    // Add to selected toppings if the limit is not exceeded
                    if (selectedToppings.size() < 7) {
                        if (!selectedToppings.contains(topping)) {
                            selectedToppings.add(topping);
                            listener.onToppingSelectionChanged(selectedToppings);
                        }
                    } else {
                        // Uncheck the CheckBox and notify user about the limit
                        buttonView.setChecked(false);
                        Toast.makeText(buttonView.getContext(), "You can select up to 7 toppings only!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Remove from selected toppings
                    selectedToppings.remove(topping);
                    listener.onToppingSelectionChanged(selectedToppings);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return toppingsList.size();
    }

    /**
     * Creates a mapping of topping names to their corresponding drawable resources.
     *
     * @return Map of topping names and drawable resources.
     */
    private Map<String, Integer> createToppingImageMap() {
        Map<String, Integer> map = new HashMap<>();
        map.put("Sausage", R.drawable.sausage);
        map.put("Pepperoni", R.drawable.pepperoni);
        map.put("Mushroom", R.drawable.mushroom);
        map.put("Onion", R.drawable.onion);
        map.put("Green Pepper", R.drawable.greenpepper);
        map.put("BBQ Chicken", R.drawable.bbqchicken);
        map.put("Provolone", R.drawable.provolone);
        map.put("Olive", R.drawable.olive);
        map.put("Spinach", R.drawable.spinach);
        map.put("Pineapple", R.drawable.pineapple);
        map.put("Tomato", R.drawable.tomato);
        map.put("Beef", R.drawable.beef);
        map.put("Ham", R.drawable.ham);
        map.put("Cheddar", R.drawable.cheddar);

        // Add more toppings and their corresponding images here
        return map;
    }

    /**
     * ViewHolder class for managing the RecyclerView items.
     */
    static class ToppingsViewHolder extends RecyclerView.ViewHolder {
        private final TextView toppingName;
        private final CheckBox toppingCheckBox;
        private final ImageView toppingImage;

        /**
         * Constructor for initializing the views in a RecyclerView item.
         *
         * @param itemView The root view of the item.
         */
        public ToppingsViewHolder(@NonNull View itemView) {
            super(itemView);
            toppingName = itemView.findViewById(R.id.toppingName);
            toppingCheckBox = itemView.findViewById(R.id.toppingCheckBox);
            toppingImage = itemView.findViewById(R.id.toppingImage);
        }
    }
}
