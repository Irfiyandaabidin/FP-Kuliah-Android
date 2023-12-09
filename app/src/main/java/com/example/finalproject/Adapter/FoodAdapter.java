package com.example.finalproject.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.DetailFoodActivity;
import com.example.finalproject.FoodModel;
import com.example.finalproject.MainActivity;
import com.example.finalproject.R;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {
    private final LayoutInflater mInflater;
    private List<FoodModel> foodList;
    private Context context;
    final OnItemClickListener listener;

    public FoodAdapter(List<FoodModel> foodList, Context context, OnItemClickListener listener) {
        this.mInflater = LayoutInflater.from(context);
        this.foodList = foodList;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.food_item, parent, false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        FoodModel item = foodList.get(position);
        holder.bindData(foodList.get(position));
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public void setItem(List<FoodModel> item) {
        foodList = item;
    }

    public class FoodViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvRating, tvPrice;
        ImageView ivImage;

        Button btnAdd, btnDelete;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvRating = itemView.findViewById(R.id.tvRating);
            ivImage = itemView.findViewById(R.id.ivImage);
            btnAdd = itemView.findViewById(R.id.btnAdd);
            btnDelete = itemView.findViewById(R.id.btnDelete);

            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onAddButtonClick(position);
                        }
                    }
                }
            });

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onDeleteButtonClick(position);
                        }
                    }
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    listener.onItemClick(foodList.get(position));
                    Intent intent = new Intent(context, DetailFoodActivity.class);
                    intent.putExtra("food_name", foodList.get(position).getName());
                    intent.putExtra("food_price", foodList.get(position).getPrice());
                    intent.putExtra("food_ratting", foodList.get(position).getRating());
                    intent.putExtra("food_description", foodList.get(position).getDescription());
                    intent.putExtra("image", foodList.get(position).getImageResourceId());
                    context.startActivity(intent);
                }
            });
        }

        public void bindData(final FoodModel item) {
            ivImage.setImageResource(item.getImageResourceId());
            tvName.setText(item.getName());
            tvPrice.setText(item.getPrice());
            tvRating.setText(item.getRating());
        }
    }

    public interface OnItemClickListener {
        void onItemClick(FoodModel item);

        void onAddButtonClick(int position);

        void onDeleteButtonClick(int position);
    }

    public void filterList(List<FoodModel> filteredList) {
        this.foodList = filteredList;
        notifyDataSetChanged();
    }
}
