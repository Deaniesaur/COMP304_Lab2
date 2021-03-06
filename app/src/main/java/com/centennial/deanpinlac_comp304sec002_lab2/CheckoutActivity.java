package com.centennial.deanpinlac_comp304sec002_lab2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.centennial.deanpinlac_comp304sec002_lab2.models.Pizza;
import com.centennial.deanpinlac_comp304sec002_lab2.utils.Common;
import com.centennial.deanpinlac_comp304sec002_lab2.utils.CustomAdapter;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CheckoutActivity extends AppCompatActivity {

    private static List<Pizza> pizzaList;
    private SharedPreferences sharedPref;
    private String subtotalString;
    private String totalString;
    private Button buttonPayment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        //Retrieve List of Pizza Orders
        sharedPref = getSharedPreferences("pizza_pref", Context.MODE_PRIVATE);

        String jsonPizzaList = sharedPref.getString("pizza_cart", null);
        if(jsonPizzaList!= null){
            Type pizzaListType = new TypeToken<ArrayList<Pizza>>(){}.getType();
            pizzaList = Common.convertJsonToObject(jsonPizzaList, pizzaListType);

//            sharedPref.edit().remove("pizza_cart").apply();
        }

        RecyclerView recyclerCart = findViewById(R.id.recyclerCart);
        recyclerCart.setAdapter(new CustomAdapter(pizzaList, this));
        recyclerCart.setLayoutManager(new LinearLayoutManager(this));

        buttonPayment = findViewById(R.id.buttonPayment);
        buttonPayment.setOnClickListener( v -> {
            //Save Total Payment
            sharedPref.edit().putString("pizza_subtotal", subtotalString).apply();
            sharedPref.edit().putString("pizza_total", totalString).apply();

            Intent intent = new Intent(CheckoutActivity.this, PaymentActivity.class);
            startActivity(intent);
        });

        updatePrices();
    }

    private void updatePrices(){
        TextView textSubTotal = findViewById(R.id.textCheckoutSubTotal);
        TextView textTotal = findViewById(R.id.textCheckoutPrice);
        DecimalFormat df = new DecimalFormat("#.00");

        double subTotal = 0;
        for(Pizza pizza: pizzaList){
            subTotal += pizza.getSubTotal();
        }
        subtotalString = df.format(subTotal);
        textSubTotal.setText(subtotalString);

        double total = subTotal * 1.13;
        totalString = df.format(total);
        textTotal.setText(totalString);

        if(pizzaList.size() > 0)
            buttonPayment.setEnabled(true);
        else
            buttonPayment.setEnabled(false);
    }

    public void itemRemoved(){
        updatePrices();

        String jsonPizzaList = Common.convertToJson(pizzaList);
        sharedPref.edit().putString("pizza_cart", jsonPizzaList).apply();
    }
}