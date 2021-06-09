package com.centennial.deanpinlac_comp304sec002_lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.centennial.deanpinlac_comp304sec002_lab2.models.Address;
import com.centennial.deanpinlac_comp304sec002_lab2.models.Customer;
import com.centennial.deanpinlac_comp304sec002_lab2.utils.Common;

public class CustomerInfoActivity extends AppCompatActivity {

    SharedPreferences sharedPref;
    EditText editName;
    EditText editStreet;
    EditText editCity;
    EditText editState;
    EditText editPostal;
    EditText editPhone;
    EditText editSauce;
    EditText editDrink;
    EditText editMeat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_info);

        //Initialize SharedPreference
        sharedPref = getSharedPreferences("pizza_pref", Context.MODE_PRIVATE);

        //Initialize EditTexts
        editName = findViewById(R.id.editUserName);
        editStreet = findViewById(R.id.editUserStreet);
        editCity = findViewById(R.id.editUserCity);
        editState = findViewById(R.id.editUserState);
        editPostal = findViewById(R.id.editUserPostal);
        editPhone = findViewById(R.id.editUserPhone);
        editSauce = findViewById(R.id.editUserSauce);
        editDrink = findViewById(R.id.editUserDrink);
        editMeat = findViewById(R.id.editUserMeat);

        Button buttonCompleteOrder = findViewById(R.id.buttonCompleteOrder);
        buttonCompleteOrder.setOnClickListener(v -> {
            if(!validateInput())
                return;

            Customer customer = getCustomerInfo();

            //Save customer to SharedPreferences
            String jsonCustomer = Common.convertToJson(customer);
            sharedPref.edit().putString("pizza_customer", jsonCustomer).apply();

            Intent intent = new Intent(
                    CustomerInfoActivity.this, SummaryActivity.class);
            startActivity(intent);
        });
    }

    private Customer getCustomerInfo(){
        Customer customer = new Customer();
        Address address = new Address();

        address.setStreet(editStreet.getText().toString());
        address.setCity(editCity.getText().toString());
        address.setState(editState.getText().toString());
        address.setPostalCode(editPostal.getText().toString());

        customer.setName(editName.getText().toString());
        customer.setAddress(address);
        customer.setPhoneNumber(editPhone.getText().toString());
        customer.setSauce(editSauce.getText().toString());
        customer.setDrink(editDrink.getText().toString());
        customer.setMeat(editMeat.getText().toString());

        return customer;
    }

    private boolean validateInput(){
        if(TextUtils.isEmpty(editName.getText().toString())){
            showError("Name");
            return false;
        }

        if(TextUtils.isEmpty(editStreet.getText().toString())){
            showError("Street");
            return false;
        }

        if(TextUtils.isEmpty(editCity.getText().toString())){
            showError("City");
            return false;
        }

        if(TextUtils.isEmpty(editState.getText().toString())){
            showError("State");
            return false;
        }

        if(TextUtils.isEmpty(editPostal.getText().toString())){
            showError("Postal Code");
            return false;
        }

        if(TextUtils.isEmpty(editPhone.getText().toString())){
            showError("Phone Number");
            return false;
        }

        if(TextUtils.isEmpty(editSauce.getText().toString())){
            showError("Sauce");
            return false;
        }

        if(TextUtils.isEmpty(editDrink.getText().toString())){
            showError("Drink");
            return false;
        }

        if(TextUtils.isEmpty(editMeat.getText().toString())){
            showError("Meat");
            return false;
        }
        return true;
    }

    private void showError(String missing){
        String message = "Please input " + missing;
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}