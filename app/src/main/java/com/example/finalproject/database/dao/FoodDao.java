package com.example.finalproject.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.finalproject.database.entitas.Food;

import java.util.List;

@Dao
public interface FoodDao {
    @Query("SELECT * FROM food")
    List<Food> getAll();

    @Insert
    void insert(Food... foods);

    @Delete
    void delete(Food food);
}
