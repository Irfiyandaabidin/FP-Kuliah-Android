package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailFoodActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_food);

        Intent intent = getIntent();

        TextView tvName = findViewById(R.id.tvNameDetail);
        TextView tvDescription = findViewById(R.id.tvDescriptionDetail);
        ImageView ivImage = findViewById(R.id.ivDetailImage);

        String name = intent.getStringExtra("food_name");
        String price = intent.getStringExtra("food_price");
        String description = intent.getStringExtra("food_description");
        int image = intent.getIntExtra("image", R.drawable.ic_launcher_background);

        tvName.setText(name);
        ivImage.setImageResource(image);
        tvDescription.setText(description);
    }
}