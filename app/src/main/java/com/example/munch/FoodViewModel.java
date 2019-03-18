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
        mRepository = FoodRepository.getInstance();
        mFoods = mRepository.getFoods();
        System.out.println("ASDFASDFASFASDF ASDF ASDFAS DJF JSKADFJASDKFJ ASDKJF ASKDJF SKAJD FKJASD FKJSAD FKJSA DFKJAS FKJAS DFKJSA DKFJAS DJFKASKJDF");
    }

    public LiveData<List<Food>> getFoods() {
        return mFoods;
    }

    public void loadFoods() {
        mRepository.loadFoods();
    }
}
