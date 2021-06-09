package com.centennial.deanpinlac_comp304sec002_lab2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.centennial.deanpinlac_comp304sec002_lab2.enums.PaymentMethod;
import com.centennial.deanpinlac_comp304sec002_lab2.models.Address;
import com.centennial.deanpinlac_comp304sec002_lab2.models.Customer;
import com.centennial.deanpinlac_comp304sec002_lab2.models.Payment;
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
    Payment payment;
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

        //Retrieve Payment Info
        String jsonPaymentInfo = sharedPref.getString("pizza_payment", null);
        if(jsonPaymentInfo != null){
            payment = Common.convertJsonToObject(jsonPaymentInfo, Payment.class);
            updatePaymentInfo();
        }

        //Retrieve Customer Info
        String jsonCustomerInfo = sharedPref.getString("pizza_customer", null);
        if(jsonCustomerInfo != null){
            customer = Common.convertJsonToObject(jsonCustomerInfo, Customer.class);
            updateCustomerSummary();
        }

        RecyclerView recyclerOrderSummary = findViewById(R.id.recyclerOrderSummary);
        CustomAdapterSummary adapterSummary = new CustomAdapterSummary(pizzaList, this);
        recyclerOrderSummary.setAdapter(adapterSummary);
        recyclerOrderSummary.setLayoutManager(new LinearLayoutManager(this));

        Button buttonSummaryOK = findViewById(R.id.buttonSummaryOK);
        buttonSummaryOK.setOnClickListener(v -> {
            Intent intent = new Intent(SummaryActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });
    }

    private void updatePaymentInfo() {
        TextView summaryPaymentType = findViewById(R.id.summaryPaymentType);
        TextView summaryPaymentEnding = findViewById(R.id.summaryPaymentEnding);
        TextView summaryPaymentSubTotal = findViewById(R.id.summaryPaymentSubtotal);
        TextView summaryPaymentTotal = findViewById(R.id.summaryPaymentTotal);
        TextView labelSummaryCardEnding = findViewById(R.id.labelSummaryCardEnding);

        summaryPaymentType.setText(payment.getPaymentMethod().toString());
        if(payment.getPaymentMethod() == PaymentMethod.Cash){
            summaryPaymentEnding.setVisibility(View.GONE);
            labelSummaryCardEnding.setVisibility(View.GONE);
        }else{
            String cardDesc = "Ends with " + payment.getCard().getNumber().substring(12,16);
            summaryPaymentEnding.setText(cardDesc);
        }
        summaryPaymentSubTotal.setText(payment.getSubTotal());
        summaryPaymentTotal.setText(payment.getTotal());
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