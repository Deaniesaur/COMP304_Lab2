package com.centennial.deanpinlac_comp304sec002_lab2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.pizza_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Context context = getApplicationContext();
        SharedPreferences sharedPref = getSharedPreferences(
                "pizza_choice", Context.MODE_PRIVATE);

        sharedPref.edit().putInt("pizza_choice", item.getItemId()).apply();

//        switch(item.getItemId()){
//            case R.id.optionCanadian:
//                return true;
//            case R.id.optionChicken:
//                return true;
//            case R.id.optionHawaiian:
//                return true;
//            case R.id.optionSmokey:
//                return true;
//            case R.id.optionVeggie:
//                return true;
//            default:
//                break;
//        }

        Intent intent = new Intent(Menu.this, PizzaDetails.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }
}