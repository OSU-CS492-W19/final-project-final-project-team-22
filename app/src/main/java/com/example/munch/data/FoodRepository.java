package com.example.munch.data;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.munch.utils.SpoonacularUtils;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FoodRepository implements FoodAsyncTask.Callback {

    private static final String TAG = FoodRepository.class.getSimpleName();

    private MutableLiveData<List<Food>> mFoods;

    public FoodRepository() {
        mFoods = new MutableLiveData<>();
        mFoods.setValue(null);
    }

    public MutableLiveData<List<Food>> getFoods() {
        return mFoods;
    }

    public void loadFoods() {
        if (shouldLoadFoods()) {
            mFoods.setValue(null);
            String url = SpoonacularUtils.buildSpoonacularURL();
            Log.d(TAG, "fetching foods with url: " + url);
            new FoodAsyncTask(url, this).execute();
        } else {
            Log.d(TAG, "using cached foods");
        }
    }

    private boolean shouldLoadFoods() {
        return true;
    }

    @Override
    public void onLoadFinished(List<Food> foods) {
        mFoods.setValue(foods);
    }
}
