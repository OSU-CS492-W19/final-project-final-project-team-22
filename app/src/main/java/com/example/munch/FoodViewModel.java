package com.example.munch;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.munch.data.Food;
import com.example.munch.data.FoodRepository;

import java.util.List;

public class FoodViewModel extends ViewModel {
    private LiveData<List<Food>> mFoods;

    private FoodRepository mRepository;

    public FoodViewModel() {
        mRepository = new FoodRepository();
        mFoods = mRepository.getFoods();
    }

    public LiveData<List<Food>> getFoods() {
        return mFoods;
    }

    public void loadFoods() {
        mRepository.loadFoods();
    }
}
