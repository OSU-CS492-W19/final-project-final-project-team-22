package com.example.munch;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.munch.data.FavoriteFood;
import com.example.munch.data.FavoriteFoodRepository;

import java.util.List;

public class FavoriteFoodViewModel extends AndroidViewModel {
    private FavoriteFoodRepository mFavoriteFoodRepository;

    public FavoriteFoodViewModel(Application application) {
        super(application);
        mFavoriteFoodRepository = new FavoriteFoodRepository(application);
    }

    public void insertFavoriteFood(FavoriteFood favoriteFood) {
        mFavoriteFoodRepository.insertFavoriteFood(favoriteFood);
    }

    public void deleteFavoriteFood(FavoriteFood favoriteFood) {
        mFavoriteFoodRepository.deleteFavoriteFood(favoriteFood);
    }

    public LiveData<List<FavoriteFood>> getAllFavoriteFoods() {
        return mFavoriteFoodRepository.getAllFavoriteFoods();
    }
}
