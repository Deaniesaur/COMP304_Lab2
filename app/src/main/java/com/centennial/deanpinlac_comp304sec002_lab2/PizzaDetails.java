package com.centennial.deanpinlac_comp304sec002_lab2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

public class PizzaDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_details);

        setPizzaDetails();
    }

    private void setPizzaDetails(){
        Context context = getApplicationContext();
        SharedPreferences sharedPref = context.getSharedPreferences(
                "pizza_choice", Context.MODE_PRIVATE);

        int choice = sharedPref.getInt("pizza_choice", 0);
        ImageView pizzaPreview = findViewById(R.id.imgPizzaPreview);

        switch(choice){
            case R.id.optionCanadian:
                pizzaPreview.setImageResource(R.drawable.canadian_pizza);
                break;
            case R.id.optionChicken:
                pizzaPreview.setImageResource(R.drawable.chicken_caesar);
                break;
            case R.id.optionHawaiian:
                pizzaPreview.setImageResource(R.drawable.hawaiian_pizza);
                break;
            case R.id.optionSmokey:
                pizzaPreview.setImageResource(R.drawable.smokey_maple_bacon);
                break;
            case R.id.optionVeggie:
                pizzaPreview.setImageResource(R.drawable.veggie_lovers);
                break;
            default:
                pizzaPreview.setImageResource(R.drawable.veggie_lovers);
                return;
        }
    }
}