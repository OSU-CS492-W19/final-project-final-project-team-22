package com.example.munch;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.munch.data.Food;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView mFoodCardsRV;

    private FoodAdapter mAdapter;
    private FoodViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set member variables to elements in the activity_main.xml file.
        mFoodCardsRV = findViewById(R.id.rv_food_cards);

        mAdapter = new FoodAdapter();
        mFoodCardsRV.setAdapter(mAdapter);

        //mFoodCardsRV.setHasFixedSize(true);
        mFoodCardsRV.setLayoutManager(new LinearLayoutManager((this)));

        mViewModel = ViewModelProviders.of(this).get(FoodViewModel.class);

        mViewModel.getFoods().observe(this, new Observer<List<Food>>(){
            @Override
            public void onChanged(@Nullable List<Food> foods){
                mAdapter.updateFood(foods);
            }
        });

        mViewModel.loadFoods();
    }
}
