package com.centennial.deanpinlac_comp304sec002_lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.centennial.deanpinlac_comp304sec002_lab2.enums.Crust;
import com.centennial.deanpinlac_comp304sec002_lab2.enums.PizzaName;
import com.centennial.deanpinlac_comp304sec002_lab2.enums.Price;
import com.centennial.deanpinlac_comp304sec002_lab2.enums.Size;
import com.centennial.deanpinlac_comp304sec002_lab2.models.Pizza;
import com.google.gson.Gson;

import static android.widget.AdapterView.*;

public class PizzaDetails extends AppCompatActivity {

    private Pizza pizza;
    private SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_details);

        Context context = getApplicationContext();
        sharedPref = context.getSharedPreferences(
                "pizza_pref", Context.MODE_PRIVATE);

        sharedPref.edit().putString("add_pizza", null).apply();

        setPizzaDetails();
        Spinner spinnerSize = findViewById(R.id.spinnerSize);
        ArrayAdapter adapter = new ArrayAdapter<Size>(
                this, android.R.layout.simple_list_item_1, Size.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSize.setAdapter(adapter);
        spinnerSize.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pizza.setSize((Size) spinnerSize.getSelectedItem());
                updatePrice();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        RadioButton radioThin = findViewById(R.id.radioThin);

        CheckBox checkAdd = findViewById(R.id.checkAddPizza);
        checkAdd.setOnClickListener(v -> {
            if(checkAdd.isChecked()){

                //Set Pizza Crust
                if(radioThin.isChecked()) pizza.setCrust(Crust.Thin);
                else pizza.setCrust(Crust.Thick);

                Gson gson = new Gson();
                String jsonPizza = gson.toJson(pizza);
                sharedPref.edit().putString("add_pizza", jsonPizza).apply();
                finish();
            }
        });
    }

    private void setPizzaDetails(){

        int choice = sharedPref.getInt("pizza_choice", 0);
        ImageView pizzaPreview = findViewById(R.id.img_pizza_prev);
        TextView textIngredients = findViewById(R.id.text_pizza_ing);
        String[] ingredients;

        switch(choice){
            case R.id.optionChicken:
                pizza = new Pizza(PizzaName.Chicken, Price.Chicken);
                pizzaPreview.setImageResource(R.drawable.chicken_caesar);
                ingredients = getResources().getStringArray(R.array.chicken_caesar_pizza_ing);
                break;
            case R.id.optionHawaiian:
                pizza = new Pizza(PizzaName.Hawaiian, Price.Hawaiian);
                pizzaPreview.setImageResource(R.drawable.hawaiian_pizza);
                ingredients = getResources().getStringArray(R.array.hawaiian_pizza_ing);
                break;
            case R.id.optionSmokey:
                pizza = new Pizza(PizzaName.Smokey, Price.Smokey);
                pizzaPreview.setImageResource(R.drawable.smokey_maple_bacon);
                ingredients = getResources().getStringArray(R.array.smokey_maple_bacon_pizza_ing);
                break;
            case R.id.optionVeggie:
                pizza = new Pizza(PizzaName.Veggie, Price.Veggie);
                pizzaPreview.setImageResource(R.drawable.veggie_lovers);
                ingredients = getResources().getStringArray(R.array.veggie_lover_pizza_ing);
                break;
            case R.id.optionCanadian:
            default:
                pizza = new Pizza(PizzaName.Canadian, Price.Canadian);
                pizzaPreview.setImageResource(R.drawable.canadian_pizza);
                ingredients = getResources().getStringArray(R.array.canadian_pizza_ing);
        }

        //Set Value ot Ingredients TextView
        textIngredients.setText(formatIngredients(ingredients));
        //Update Price
        updatePrice();
    }

    private String formatIngredients(String[] strArray){
        String output = "";
        for(String str: strArray){
            output += "\u2022 " + str + "\n";
        }
        return output;
    }
    
    private void updatePrice(){
        TextView textPrice = findViewById(R.id.textPrice);
        textPrice.setText("$ " + pizza.getSubTotal());
    }
}