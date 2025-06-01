package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.widget.ArrayAdapter;
import java.util.List;
import p5.*;

/**
 * Custom adapter for displaying pizza objects in a ListView.
 * This adapter inflates a custom layout for each item in the current order list.
 *
 * @author Siddharth, Ibtesaam
 */
public class CurrentOrderAdapter extends ArrayAdapter<Pizza> {

    /**
     * Constructs a new CurrentOrderAdapter.
     *
     * @param context The current context (usually the Activity).
     * @param pizzas  The list of pizzas to be displayed in the ListView.
     */
    public CurrentOrderAdapter(Context context, List<Pizza> pizzas) {
        super(context, 0, pizzas);
    }

    /**
     * Provides a view for an adapter item.
     *
     * @param position    The position of the item within the adapter's data set.
     * @param convertView The old view to reuse, if possible.
     * @param parent      The parent view that this view will be attached to.
     * @return A View corresponding to the data at the specified position.
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Get the pizza item for this position
        Pizza pizza = getItem(position);

        // Inflate the custom layout if not already done
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_current_order, parent, false);
        }

        // Find the views in the layout
        TextView textStyle = convertView.findViewById(R.id.textStyle);
        TextView textCrust = convertView.findViewById(R.id.textCrust);
        TextView textSize = convertView.findViewById(R.id.textSize);
        TextView textToppings = convertView.findViewById(R.id.textToppings);

        // Bind the pizza data to the views
        if (pizza != null) {
            textStyle.setText("Style: " + pizza.getStyle());
            textCrust.setText("Crust: " + pizza.getCrust());
            textSize.setText("Size: " + pizza.getSize());
            textToppings.setText("Toppings: " + pizza.getToppings().toString());
        }

        return convertView;
    }
}
