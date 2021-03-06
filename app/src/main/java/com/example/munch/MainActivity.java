package com.example.munch;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import android.support.design.widget.BottomNavigationView;

import com.example.munch.data.Food;
import com.example.munch.utils.FoodUtils;
import com.example.munch.utils.SpoonacularUtils;

import java.util.List;

public class MainActivity extends AppCompatActivity implements FoodAdapter.OnFoodItemClickListener{

    private static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView mFoodCardsRV;

    private FoodAdapter mFoodAdapter;
    private FoodViewModel mFoodViewModel;
    private FavoriteFoodViewModel mFavoriteFoodViewModel;
    private BottomNavigationView mMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                                //Nothing happens, you're already home.
                                return true;
                            case R.id.navigation_my_kitchen:
                                Log.d(MainActivity.class.getSimpleName(), "in navigation_menu my kitchen logic");
                                Intent intent = new Intent(getApplicationContext(), MyKitchenActivity.class);
                                startActivity(intent);
                                return true;
                        }
                        return true;
                    }
                });



        //Set member variables to elements in the activity_main.xml file.
        mFoodCardsRV = findViewById(R.id.rv_food_cards);

        mFoodAdapter = new FoodAdapter(this);
        mFoodCardsRV.setAdapter(mFoodAdapter);

        //mFoodCardsRV.setHasFixedSize(true);
        mFoodCardsRV.setLayoutManager(new LinearLayoutManager((this)));

        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(mFoodCardsRV);


        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback( ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                //awesome code when user grabs recycler card to reorder
                return true;
            }

            @Override
            public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                super.clearView(recyclerView, viewHolder);
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

                if(direction == ItemTouchHelper.RIGHT){
                    Toast.makeText(getApplicationContext(),"It's a match!" ,Toast.LENGTH_SHORT).show();
                    Food food = mFoodAdapter.getFood(viewHolder.getAdapterPosition());
                    System.out.println("FOOD THAT WAS SWIPED: " + food.title);
                    mFavoriteFoodViewModel.insertFavoriteFood(FoodUtils.toFavoriteFood(food));
                }

                else if(direction == ItemTouchHelper.LEFT){

                }

                int position = viewHolder.getAdapterPosition();
                Log.d(MainActivity.class.getSimpleName(), "position is: " + position);
                mFoodCardsRV.getLayoutManager().scrollToPosition(position+1);
            }

        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(mFoodCardsRV);


        mFoodViewModel = ViewModelProviders.of(this).get(FoodViewModel.class);

        mFoodViewModel.getFoods().observe(this, new Observer<List<Food>>(){
            @Override
            public void onChanged(@Nullable List<Food> foods){
                mFoodAdapter.updateFood(foods);
            }
        });

        mFoodViewModel.loadFoods();

        mFavoriteFoodViewModel = ViewModelProviders.of(this).get(FavoriteFoodViewModel.class);
    }


    @Override
    public void onFoodItemClick(Food f) {
        Log.d(MainActivity.class.getSimpleName(), "in onFoodItemClick!");

        Intent intent = new Intent(this, FoodDetailActivity.class);
        intent.putExtra(SpoonacularUtils.EXTRA_FOOD, f);
        startActivity(intent);
    }

    @Override
    public void onStop() {
        super.onStop();

        mFoodViewModel.setFoodState(mFoodCardsRV.getLayoutManager().onSaveInstanceState());
    }

    @Override
    public void onStart() {
        super.onStart();

        Parcelable foodState = mFoodViewModel.getFoodState();

        if(foodState != null)
            mFoodCardsRV.getLayoutManager().onRestoreInstanceState(foodState);
    }
}
