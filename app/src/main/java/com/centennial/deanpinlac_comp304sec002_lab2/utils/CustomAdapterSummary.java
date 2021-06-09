package com.centennial.deanpinlac_comp304sec002_lab2.utils;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

import com.centennial.deanpinlac_comp304sec002_lab2.models.Pizza;

import java.util.ConcurrentModificationException;
import java.util.List;

public class CustomAdapterSummary extends CustomAdapter{
    public CustomAdapterSummary(List<Pizza> dataSet, Context context) {
        super(dataSet, context);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position){
        Pizza pizza = localDataSet.get(position);
        viewHolder.rowName.setText(pizza.getName().value);
        viewHolder.rowSize.setText(pizza.getSize().toString());
        viewHolder.rowCrust.setText(pizza.getCrust().toString());
        viewHolder.rowPrice.setText(Common.formatDecPlace2(pizza.getSubTotal()));
        viewHolder.rowToppings.setVisibility(View.GONE);
        viewHolder.labelToppings.setVisibility(View.INVISIBLE);
        viewHolder.buttonDelete.setVisibility(View.GONE);
    }
}
