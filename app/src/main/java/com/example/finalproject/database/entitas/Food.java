package com.example.finalproject.database.entitas;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Food {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String name;

    public String price;
}
