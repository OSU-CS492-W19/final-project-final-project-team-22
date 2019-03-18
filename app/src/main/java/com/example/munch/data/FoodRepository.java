package com.example.munch.data;

import android.arch.lifecycle.MutableLiveData;
import android.os.Parcelable;
import android.util.Log;

import com.example.munch.utils.SpoonacularUtils;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FoodRepository implements FoodAsyncTask.Callback {

    private static final String TAG = FoodRepository.class.getSimpleName();

    private static FoodRepository INSTANCE;
    private MutableLiveData<List<Food>> mFoods;
    private boolean mIsFoodLoaded = false;
    private Parcelable mFoodState;

    public static FoodRepository getInstance() {
        if(INSTANCE == null) {
            synchronized (FoodRepository.class) {
                if(INSTANCE == null)
                    INSTANCE = new FoodRepository();
            }
        }

        return INSTANCE;
    }

    private FoodRepository() {
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
            mIsFoodLoaded = true;
        } else {
            Log.d(TAG, "using cached foods");
        }
    }

    private boolean shouldLoadFoods() {
        return !mIsFoodLoaded;
    }

    @Override
    public void onLoadFinished(List<Food> foods) {
        mFoods.setValue(foods);
    }

    public void setFoodState(Parcelable newState) {
        mFoodState = newState;
    }

    public Parcelable getFoodState() {
        return mFoodState;
    }
}
