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
import com.centennial.deanpinlac_comp304sec002_lab2.utils.Common;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    private SharedPreferences sharedPref;
    private List<Pizza> pizzaList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        sharedPref = getSharedPreferences(
                "pizza_pref", Context.MODE_PRIVATE);

        Button buttonCheckout = findViewById(R.id.buttonCheckout);
        buttonCheckout.setOnClickListener(v -> {
            //Saved List of Pizza Orders
            String jsonPizzaList = Common.convertToJson(pizzaList);
            sharedPref.edit().putString("pizza_cart", jsonPizzaList).apply();

            Intent intent = new Intent(MenuActivity.this, CheckoutActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        String jsonPizza = sharedPref.getString("add_pizza", null);
        if(jsonPizza != null){
            Pizza pizza = Common.convertJsonToObject(jsonPizza, Pizza.class);

            //Add pizza
            pizzaList.add(pizza);

            //Remove pizza from SharedPref
            sharedPref.edit().remove("add_pizza").apply();
        }

        //Retrieved Updated Cart
        String jsonPizzaList = sharedPref.getString("pizza_cart", null);
        if(jsonPizzaList != null){
            Type pizzaListType = new TypeToken<ArrayList<Pizza>>(){}.getType();
            pizzaList = Common.convertJsonToObject(jsonPizzaList, pizzaListType);

            //Update buttonCheckout

            //Remove pizza from SharedPref
            sharedPref.edit().remove("pizza_cart").apply();
        }

        Button buttonCheckout = findViewById(R.id.buttonCheckout);
        int cartSize = pizzaList.size();
        if(cartSize > 0){
            buttonCheckout.setText("Checkout (" + cartSize + " items)");
            buttonCheckout.setEnabled(true);
        }else{
            buttonCheckout.setText("Checkout");
            buttonCheckout.setEnabled(false);
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

        Intent intent = new Intent(MenuActivity.this, PizzaDetailsActivity.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }
}