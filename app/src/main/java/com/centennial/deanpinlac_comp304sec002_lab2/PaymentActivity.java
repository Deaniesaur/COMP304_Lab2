package com.centennial.deanpinlac_comp304sec002_lab2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import com.centennial.deanpinlac_comp304sec002_lab2.utils.CustomAdapter;

public class PaymentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        RadioGroup groupPaymentMethods = findViewById(R.id.groupPaymentMethods);
        CardView cardViewCardDetails = findViewById(R.id.cardViewCardDetails);

        groupPaymentMethods.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(groupPaymentMethods.getCheckedRadioButtonId() == R.id.radioCash){
                    cardViewCardDetails.setVisibility(View.GONE);
                }else{
                    cardViewCardDetails.setVisibility(View.VISIBLE);
                }
            }
        });

        Button buttonCustomerInfo = findViewById(R.id.buttonCustomerInfo);
        buttonCustomerInfo.setOnClickListener(v -> {
            Intent intent = new Intent(PaymentActivity.this, CustomerInfoActivity.class);
            startActivity(intent);
        });
    }
}