package com.example.munch.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface FavoriteFoodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(FavoriteFood food);

    @Delete
    void delete(FavoriteFood food);

    @Query("SELECT * FROM favorite_foods")
    LiveData<List<FavoriteFood>> getAllFavoriteFoods();
}
