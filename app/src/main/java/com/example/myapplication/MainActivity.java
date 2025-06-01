package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
/**
 * MainActivity serves as the entry point of the application.
 * It provides navigation to various pizza-related screens such as New York Style, Chicago Style,
 * Current Orders, and All Orders.
 *
 * @author Siddharth, Ibtesaam
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    /**
     * Opens the New York Pizza menu.
     *
     * @param view The clicked ImageButton.
     */
    public void NewYorkMenu(View view) {
        Intent intent = new Intent(this, NewYorkPizzaActivity.class); // Change to your New York menu activity
        startActivity(intent);
    }

    public void chicagoMenu(View view) {
        Intent intent = new Intent(this, ChicagoPizzaActivity.class); // Change to your New York menu activity
        startActivity(intent);
    }

    /**
     * Opens the All Orders screen.
     *
     * @param view The clicked ImageButton.
     */
    public void AllOrders(View view) {
        Intent intent = new Intent(this, StoreOrdersActivity.class); // Change to your All Orders activity
        startActivity(intent);
    }

    /**
     * Opens the Current Orders screen.
     *
     * @param view The clicked ImageButton.
     */
    public void CurrentOrders(View view) {
        Intent intent = new Intent(this, CurrentOrdersActivity.class); // Change to your Current Orders activity
        startActivity(intent);
    }
}
