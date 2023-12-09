package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.widget.SearchView;
import android.widget.TextView;

import com.example.finalproject.Adapter.FoodAdapter;
import com.example.finalproject.Adapter.PromoPagerAdapter;
import com.example.finalproject.database.AppDatabase;
import com.example.finalproject.database.entitas.Food;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private ViewPager promoViewPager;
    private int currentPage = 0;

    Button btnCheckout, btnAdd;
    AppDatabase database;
    TextView tvName, tvPrice;

    private static final short DELAY_MS = 3000;
    private static final short PERIOD_MS = 3000;
    List<FoodModel> elements;

    FoodAdapter adapter;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        promoViewPager = findViewById(R.id.promoViewPager);
        btnCheckout = findViewById(R.id.btnCheckout);
        database = AppDatabase.getInstance(getApplicationContext());

        // Gantilah dengan sumber daya gambar yang sesuai
        int[] promoImages = {R.drawable.banner_discount_food, R.drawable.banner_discount_food, R.drawable.banner_discount_food};

        PromoPagerAdapter promoPagerAdapter = new PromoPagerAdapter(this, promoImages);
        promoViewPager.setAdapter(promoPagerAdapter);

        // Otomatis ganti halaman setiap beberapa detik
        final Handler handler = new Handler();
        final Runnable update = new Runnable() {
            public void run() {
                if (currentPage == promoImages.length) {
                    currentPage = 0;
                }
                promoViewPager.setCurrentItem(currentPage++, true);
            }
        };

        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        }, DELAY_MS, PERIOD_MS);
        addData();

        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, CheckoutActivity.class);
                startActivity(i);
            }
        });

        SearchView searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(this);
    }
//        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.item_spacing);


    private void addData() {
        elements = new ArrayList<>();
        elements.add(new FoodModel("Rendang1", "20000", "4", R.drawable.rendang, "Description rendang"));
        elements.add(new FoodModel("Mie Ayam", "10000", "5", R.drawable.mieayam, "Description Mie ayam"));
        elements.add(new FoodModel("Baso", "120000", "4", R.drawable.baso, "Description baso"));
        elements.add(new FoodModel("Rendang", "20000", "4", R.drawable.rendang, "Description rendang"));
        elements.add(new FoodModel("Mie Ayam", "10000", "5", R.drawable.mieayam, "Description Mie ayam"));
        elements.add(new FoodModel("Baso", "120000", "4", R.drawable.baso, "Description baso"));
        elements.add(new FoodModel("Rendang", "20000", "4", R.drawable.rendang, "Description rendang"));
        elements.add(new FoodModel("Mie Ayam", "10000", "5", R.drawable.mieayam, "Description Mie ayam"));
        elements.add(new FoodModel("Baso", "120000", "4", R.drawable.baso, "Description baso"));

        adapter = new FoodAdapter(elements, this, new FoodAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(FoodModel item) {
//                detail(item);
            }

            @Override
            public void onAddButtonClick(int position) {
                Food food = new Food();
                food.name = elements.get(position).name;
                food.price = elements.get(position).price;
                database.foodDao().insert(food);
            }

            @Override
            public void onDeleteButtonClick(int position) {

            }
        });

        recyclerView = findViewById(R.id.rvFoods);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.addItemDecoration(new RecyclerViewItemDecoration(this));
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        filterData(s);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        filterData(s);
        return true;
    }


    //    private void detail(FoodModel item) {
//        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
//        intent
//    }

    private void filterData(String query) {
        List<FoodModel> filteredList = new ArrayList<>();

        for(FoodModel food : elements) {
            if(food.getName().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(food);
            }

            FoodAdapter adapter = (FoodAdapter) recyclerView.getAdapter();
            if(adapter != null) {
                adapter.filterList(filteredList);
            }
        }
    }


}