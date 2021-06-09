package com.centennial.deanpinlac_comp304sec002_lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class CustomerInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_info);

        Button buttonCompleteOrder = findViewById(R.id.buttonCompleteOrder);
        buttonCompleteOrder.setOnClickListener(v -> {
            Intent intent = new Intent(
                    CustomerInfoActivity.this, SummaryActivity.class);
            startActivity(intent);
        });
    }
}