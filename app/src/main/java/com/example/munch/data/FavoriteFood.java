package com.example.munch.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

@Entity(tableName = "favorite_foods")
public class FavoriteFood implements Serializable {
    @PrimaryKey
    @NonNull
    public String title;
    public String instructions;
    public String image;

    public String spoonacularSourceUrl;

    public int spoonacularScore;
    public int preparationMinutes;
    public int cookingMinutes;
    public int readyInMinutes;
}
