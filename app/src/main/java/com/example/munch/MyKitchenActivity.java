package com.example.munch;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;

import com.example.munch.data.FavoriteFood;
import com.example.munch.utils.FoodUtils;
import com.example.munch.utils.SpoonacularUtils;

import java.util.List;

public class MyKitchenActivity extends AppCompatActivity implements FavoriteFoodAdapter.OnFavoriteFoodItemClickListener {

    private BottomNavigationView mMenu;
    private RecyclerView mFavoriteFoodsRV;

    private FavoriteFoodAdapter mFavoriteFoodAdapter;
    private FavoriteFoodViewModel mFavoriteFoodModel;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_kitchen);

        mMenu = findViewById(R.id.navigation_view);
        mMenu.setOnNavigationItemSelectedListener(
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.navigation_my_profile:
                            Intent intent2 = new Intent(getApplicationContext(), MyProfileActivity.class);
                            startActivity(intent2);
                            return true;
                        case R.id.navigation_my_home:
                            Log.d(MainActivity.class.getSimpleName(), "in navigation_menu my home logic");
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            return true;
                        case R.id.navigation_my_kitchen:
                            //Nothing happens.
                            return true;
                    }

                    return true;
                }
            }
        );

        mFavoriteFoodAdapter = new FavoriteFoodAdapter(this);
        mFavoriteFoodsRV = findViewById(R.id.rv_favorite_foods);
        mFavoriteFoodsRV.setAdapter(mFavoriteFoodAdapter);
        mFavoriteFoodsRV.setLayoutManager(new LinearLayoutManager(this));
        mFavoriteFoodsRV.setHasFixedSize(true);

        mFavoriteFoodModel = ViewModelProviders.of(this).get(FavoriteFoodViewModel.class);
        mFavoriteFoodModel.getAllFavoriteFoods().observe(this, new Observer<List<FavoriteFood>>() {
            @Override
            public void onChanged(@Nullable List<FavoriteFood> favoriteFoods) {
                mFavoriteFoodAdapter.updateFavoriteFoods(favoriteFoods);
            }
        });
    }

    @Override
    public void onFavoriteFoodItemClick(FavoriteFood favoriteFood) {
        System.out.println("FAVORITE FOOD ITEM CLICK: " + favoriteFood.title);

        Intent intent = new Intent(this, FoodDetailActivity.class);
        intent.putExtra(SpoonacularUtils.EXTRA_FOOD, FoodUtils.toFood(favoriteFood));
        startActivity(intent);
    }
}
