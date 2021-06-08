package com.centennial.deanpinlac_comp304sec002_lab2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

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
        ImageView pizzaPreview = findViewById(R.id.img_pizza_prev);
        TextView textIngredients = findViewById(R.id.text_pizza_ing);
        String[] ingredients;

        switch(choice){
            case R.id.optionCanadian:
                pizzaPreview.setImageResource(R.drawable.canadian_pizza);
                ingredients = getResources().getStringArray(R.array.canadian_pizza_ing);
                break;
            case R.id.optionChicken:
                pizzaPreview.setImageResource(R.drawable.chicken_caesar);
                ingredients = getResources().getStringArray(R.array.chicken_caesar_pizza_ing);
                break;
            case R.id.optionHawaiian:
                pizzaPreview.setImageResource(R.drawable.hawaiian_pizza);
                ingredients = getResources().getStringArray(R.array.hawaiian_pizza_ing);
                break;
            case R.id.optionSmokey:
                pizzaPreview.setImageResource(R.drawable.smokey_maple_bacon);
                ingredients = getResources().getStringArray(R.array.smokey_maple_bacon_pizza_ing);
                break;
            case R.id.optionVeggie:
                pizzaPreview.setImageResource(R.drawable.veggie_lovers);
                ingredients = getResources().getStringArray(R.array.veggie_lover_pizza_ing);
                break;
            default:
                pizzaPreview.setImageResource(R.drawable.veggie_lovers);
                return;
        }

        //Set Value ot Ingredients TextView
        textIngredients.setText(formatIngredients(ingredients));
    }

    private String formatIngredients(String[] strArray){
        String output = "";
        for(String str: strArray){
            output += "\u2022 " + str + "\n";
        }
        return output;
    }
}