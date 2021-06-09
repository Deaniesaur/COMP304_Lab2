package com.centennial.deanpinlac_comp304sec002_lab2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.centennial.deanpinlac_comp304sec002_lab2.enums.PaymentMethod;
import com.centennial.deanpinlac_comp304sec002_lab2.models.Card;
import com.centennial.deanpinlac_comp304sec002_lab2.models.Payment;
import com.centennial.deanpinlac_comp304sec002_lab2.utils.Common;
import com.centennial.deanpinlac_comp304sec002_lab2.utils.CustomAdapter;

public class PaymentActivity extends AppCompatActivity {

    SharedPreferences sharedPref;
    EditText editCardName;
    EditText editCardNumber;
    EditText editCardExpiry;
    EditText editCardSecurity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        sharedPref = getSharedPreferences("pizza_pref", Context.MODE_PRIVATE);

        editCardName = findViewById(R.id.editCardName);
        editCardNumber = findViewById(R.id.editCardNumber);
        editCardExpiry = findViewById(R.id.editCardExpiry);
        editCardSecurity = findViewById(R.id.editCardSecurity);

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

            Payment payment = new Payment();
            int radioChecked = groupPaymentMethods.getCheckedRadioButtonId();
            if(radioChecked == R.id.radioCash){
                payment.setPaymentMethod(PaymentMethod.Cash);
            }else{
                if(radioChecked == R.id.radioCredit)
                    payment.setPaymentMethod(PaymentMethod.Credit);
                else
                    payment.setPaymentMethod(PaymentMethod.Debit);

                if(!validateCard())
                    return;

                Card card = getCardInfo();
                payment.setCard(card);
            }
            //Retrieve Payment Subtotal and Total
            String subTotal = sharedPref.getString("pizza_subtotal", "0.00");
            String total = sharedPref.getString("pizza_total", "0.00");
            payment.setSubTotal(subTotal);
            payment.setTotal(total);

            //Save Payment Info
            String jsonPaymentInfo = Common.convertToJson(payment);
            sharedPref.edit().putString("pizza_payment", jsonPaymentInfo).apply();

            Intent intent = new Intent(PaymentActivity.this, CustomerInfoActivity.class);
            startActivity(intent);
        });
    }

    private Card getCardInfo(){
        Card card = new Card();
        card.setName(editCardName.getText().toString());
        card.setNumber(editCardNumber.getText().toString());
        card.setExpiry(editCardExpiry.getText().toString());
        card.setSecurity(editCardSecurity.getText().toString());

        return card;
    }

    private boolean validateCard(){
        if(TextUtils.isEmpty(editCardName.getText().toString())){
            showError("Name");
            return false;
        }

        if(TextUtils.isEmpty(editCardNumber.getText().toString())){
            showError("Street");
            return false;
        }

        if(TextUtils.isEmpty(editCardExpiry.getText().toString())){
            showError("City");
            return false;
        }

        if(TextUtils.isEmpty(editCardSecurity.getText().toString())){
            showError("State");
            return false;
        }
        return true;
    }

    private void showError(String missing) {
        String message = "Please input " + missing;
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}