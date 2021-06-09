package com.centennial.deanpinlac_comp304sec002_lab2.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.centennial.deanpinlac_comp304sec002_lab2.R;
import com.centennial.deanpinlac_comp304sec002_lab2.models.Pizza;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>{

    protected List<Pizza> localDataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        protected final TextView rowName;
        protected final TextView rowSize;
        protected final TextView rowToppings;
        protected final TextView labelToppings;

        public ViewHolder(@NonNull View view){
            super(view);
            //Define click listener for the ViewHolder's View

            rowName = (TextView) view.findViewById(R.id.rowItemName);
            rowSize = (TextView) view.findViewById(R.id.rowItemSize);
            rowToppings = (TextView) view.findViewById(R.id.rowItemToppings);
            labelToppings = (TextView) view.findViewById(R.id.labelItemToppings);
        }
    }

    public CustomAdapter(List<Pizza> dataSet){
        localDataSet = dataSet;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.order_row_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position){
        Pizza pizza = localDataSet.get(position);
        viewHolder.rowName.setText(pizza.getName().value);
        viewHolder.rowSize.setText(pizza.getSize().toString());
        viewHolder.rowToppings.setText(pizza.getToppings());
    }

    @Override
    public int getItemCount(){
        return localDataSet.size();
    }
}
