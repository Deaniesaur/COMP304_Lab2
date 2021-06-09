package com.centennial.deanpinlac_comp304sec002_lab2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.centennial.deanpinlac_comp304sec002_lab2.models.Pizza;
import com.centennial.deanpinlac_comp304sec002_lab2.utils.Common;
import com.centennial.deanpinlac_comp304sec002_lab2.utils.CustomAdapterSummary;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SummaryActivity extends AppCompatActivity {

    SharedPreferences sharedPref;
    List<Pizza> pizzaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        sharedPref = getSharedPreferences("pizza_pref", Context.MODE_PRIVATE);
        String jsonPizzaList = sharedPref.getString("pizza_cart", null);
        if(jsonPizzaList != null){
            Type pizzaListType = new TypeToken<ArrayList<Pizza>>(){}.getType();
            pizzaList = Common.convertJsonToObject(jsonPizzaList, pizzaListType);
        }else{
            pizzaList = new ArrayList<>();
        }

        RecyclerView recyclerOrderSummary = findViewById(R.id.recyclerOrderSummary);
        CustomAdapterSummary adapterSummary = new CustomAdapterSummary(pizzaList);
        recyclerOrderSummary.setAdapter(adapterSummary);
        recyclerOrderSummary.setLayoutManager(new LinearLayoutManager(this));
    }
}