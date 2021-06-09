package com.centennial.deanpinlac_comp304sec002_lab2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;

import com.centennial.deanpinlac_comp304sec002_lab2.models.Pizza;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Menu extends AppCompatActivity {

    private SharedPreferences sharedPref;
    private final List<Pizza> pizzaList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        sharedPref = getSharedPreferences(
                "pizza_pref", Context.MODE_PRIVATE);

    }

    @Override
    protected void onResume() {
        super.onResume();

        String jsonPizza = sharedPref.getString("add_pizza", null);
        if(jsonPizza != null){
            Gson gson = new Gson();
            Pizza pizza = gson.fromJson(jsonPizza, Pizza.class);

            //Add pizza
            pizzaList.add(pizza);

            //Update buttonCheckout
            Button buttonCheckout = findViewById(R.id.buttonCheckout);
            int cartSize = pizzaList.size();
            if(cartSize > 0){
                buttonCheckout.setText("Checkout (" + cartSize + " items)");
                buttonCheckout.setEnabled(true);
            }else{
                buttonCheckout.setText("Checkout");
                buttonCheckout.setEnabled(false);
            }

            //Remove pizza from SharedPref
            sharedPref.edit().remove("add_pizza").apply();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.pizza_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        sharedPref.edit().putInt("pizza_choice", item.getItemId()).apply();

        Intent intent = new Intent(Menu.this, PizzaDetails.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }
}