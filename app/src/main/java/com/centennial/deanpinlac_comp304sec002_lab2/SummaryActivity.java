package com.centennial.deanpinlac_comp304sec002_lab2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import com.centennial.deanpinlac_comp304sec002_lab2.models.Address;
import com.centennial.deanpinlac_comp304sec002_lab2.models.Customer;
import com.centennial.deanpinlac_comp304sec002_lab2.models.Pizza;
import com.centennial.deanpinlac_comp304sec002_lab2.utils.Common;
import com.centennial.deanpinlac_comp304sec002_lab2.utils.CustomAdapterSummary;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class SummaryActivity extends AppCompatActivity {

    SharedPreferences sharedPref;
    List<Pizza> pizzaList;
    Customer customer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        sharedPref = getSharedPreferences("pizza_pref", Context.MODE_PRIVATE);

        //Retrieve Orders
        String jsonPizzaList = sharedPref.getString("pizza_cart", null);
        if(jsonPizzaList != null){
            Type pizzaListType = new TypeToken<ArrayList<Pizza>>(){}.getType();
            pizzaList = Common.convertJsonToObject(jsonPizzaList, pizzaListType);
        }else{
            pizzaList = new ArrayList<>();
        }

        //Retrieve Customer Info
        String jsonCustomerInfo = sharedPref.getString("pizza_customer", null);
        if(jsonCustomerInfo != null){
            customer = Common.convertJsonToObject(jsonCustomerInfo, Customer.class);
            updateCustomerSummary();
        }

        RecyclerView recyclerOrderSummary = findViewById(R.id.recyclerOrderSummary);
        CustomAdapterSummary adapterSummary = new CustomAdapterSummary(pizzaList);
        recyclerOrderSummary.setAdapter(adapterSummary);
        recyclerOrderSummary.setLayoutManager(new LinearLayoutManager(this));
    }

    private void updateCustomerSummary(){
        TextView summaryUserName = findViewById(R.id.summaryUserName);
        TextView summaryUserAddress = findViewById(R.id.summaryUserAddress);
        TextView summaryUserPhone = findViewById(R.id.summaryUserPhone);

        Address address = customer.getAddress();
        String[] arrayAddress = {
                address.getStreet(),
                address.getCity(),
                address.getState(),
                address.getPostalCode()
        };

        summaryUserName.setText(customer.getName());
        summaryUserAddress.setText(TextUtils.join(", ", arrayAddress));
        summaryUserPhone.setText(customer.getPhoneNumber());
    }
}