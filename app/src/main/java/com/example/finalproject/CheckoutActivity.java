package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.finalproject.Adapter.CheckoutAdapter;
import com.example.finalproject.database.AppDatabase;
import com.example.finalproject.database.entitas.Food;

import java.util.ArrayList;
import java.util.List;

public class CheckoutActivity extends AppCompatActivity {

    AppDatabase database;
    List<Food> list;
    CheckoutAdapter checkoutAdapter;

    RecyclerView rvCheckout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        rvCheckout = findViewById(R.id.rvCheckout);
        database = AppDatabase.getInstance(getApplicationContext());
        list = new ArrayList<>();
        list.clear();
        list.addAll(database.foodDao().getAll());
        checkoutAdapter = new CheckoutAdapter(list, getApplicationContext());

        rvCheckout.setHasFixedSize(true);
        rvCheckout.setLayoutManager(new LinearLayoutManager(this));
        rvCheckout.setAdapter(checkoutAdapter);
    }

    protected void onStart() {
        super.onStart();
        list.clear();
        list.addAll(database.foodDao().getAll());
    }
}