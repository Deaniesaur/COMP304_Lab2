package com.centennial.deanpinlac_comp304sec002_lab2.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.centennial.deanpinlac_comp304sec002_lab2.CheckoutActivity;
import com.centennial.deanpinlac_comp304sec002_lab2.R;
import com.centennial.deanpinlac_comp304sec002_lab2.models.Pizza;

import java.text.DecimalFormat;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>{

    protected List<Pizza> localDataSet;
    protected Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        protected final TextView rowName;
        protected final TextView rowSize;
        protected final TextView rowToppings;
        protected final TextView rowCrust;
        protected final TextView rowPrice;
        protected final TextView labelToppings;
        protected final ImageButton buttonDelete;

        public ViewHolder(@NonNull View view){
            super(view);
            //Define click listener for the ViewHolder's View

            rowName = view.findViewById(R.id.rowItemName);
            rowSize = view.findViewById(R.id.rowItemSize);
            rowToppings = view.findViewById(R.id.rowItemToppings);
            rowCrust = view.findViewById(R.id.rowItemCrust);
            rowPrice = view.findViewById(R.id.rowItemPrice);
            labelToppings = view.findViewById(R.id.labelItemToppings);
            buttonDelete = view.findViewById(R.id.rowItemDelete);
        }
    }

    public CustomAdapter(List<Pizza> dataSet, Context context){
        localDataSet = dataSet;
        this.context = context;
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
        viewHolder.rowCrust.setText(pizza.getCrust().toString());

        viewHolder.buttonDelete.setOnClickListener(v -> {
            removeAt(position);
            ((CheckoutActivity) context).itemRemoved();
        });

        DecimalFormat df = new DecimalFormat("$ #.00");
        viewHolder.rowPrice.setText(df.format(pizza.getSubTotal()));
    }

    public void removeAt(int position){
        localDataSet.remove(position);
        notifyDataSetChanged();
        notifyItemChanged(position);
    }

    @Override
    public int getItemCount(){
        return localDataSet.size();
    }
}
