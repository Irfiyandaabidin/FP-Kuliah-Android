package com.example.finalproject.Adapter;

import static androidx.core.content.ContextCompat.startActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.CheckoutActivity;
import com.example.finalproject.MainActivity;
import com.example.finalproject.R;
import com.example.finalproject.database.AppDatabase;
import com.example.finalproject.database.entitas.Food;

import java.util.List;

public class CheckoutAdapter extends RecyclerView.Adapter<CheckoutAdapter.CheckoutViewHolder> {
    private List<Food> foodList;
    private Context context;

    private Dialog dialog;

    public CheckoutAdapter(List<Food> foodList, Context context) {
        this.foodList = foodList;
        this.context = context;
    }

    @NonNull
    @Override
    public CheckoutAdapter.CheckoutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_checkout_adapter, parent, false);
        return new CheckoutAdapter.CheckoutViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CheckoutAdapter.CheckoutViewHolder holder, int position) {
        holder.tvCheckoutName.setText(foodList.get(position).name);
        holder.tvCheckoutPrice.setText(foodList.get(position).price);
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Food food = foodList.get(position);
                holder.database.foodDao().delete(food);
                foodList.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public class CheckoutViewHolder extends RecyclerView.ViewHolder {
        TextView tvCheckoutName, tvCheckoutPrice;
        Button btnDelete;

        AppDatabase database;
        public CheckoutViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCheckoutName = itemView.findViewById(R.id.tvCheckoutName);
            tvCheckoutPrice = itemView.findViewById(R.id.tvCheckoutPrice);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            database = AppDatabase.getInstance(context.getApplicationContext());
        }
    }
}
