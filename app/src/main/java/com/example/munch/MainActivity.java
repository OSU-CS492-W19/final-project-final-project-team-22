package com.example.munch;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
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

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView mFoodCardsRV;

    private FoodAdapter mAdapter;
    private FoodViewModel mViewModel;
    private BottomNavigationView mMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMenu = findViewById(R.id.navigationView);
        mMenu.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_my_profile:
                                //Future Implementation if we have time to do this
                                return true;
                            case R.id.navigation_my_home:
                                //Nothing happens, you're already home.
                                return true;
                            case R.id.navigation_my_kitchen:
                                Log.d(MainActivity.class.getSimpleName(), "in navigation my kitchen logic");
                                Intent intent = new Intent(getApplicationContext(), MyKitchenActivity.class);
                                startActivity(intent);
                                return true;
                        }
                        return true;
                    }
                });



        //Set member variables to elements in the activity_main.xml file.
        mFoodCardsRV = findViewById(R.id.rv_food_cards);

        mAdapter = new FoodAdapter();
        mFoodCardsRV.setAdapter(mAdapter);

        //mFoodCardsRV.setHasFixedSize(true);
        mFoodCardsRV.setLayoutManager(new LinearLayoutManager((this)));

        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(mFoodCardsRV);


        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.RIGHT, ItemTouchHelper.LEFT) {
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
                }
                //FIXME: This doesn't work yet, pls fix please
                else if(direction == ItemTouchHelper.LEFT){
                    int position = viewHolder.getAdapterPosition();
                    Log.d(MainActivity.class.getSimpleName(), "position is: " + position);
                    //mAdapter.removeFood(position);

                    //mAdapter.notifyItemRemoved(position);
                    //mAdapter.notifyItemRangeRemoved(position, mAdapter.getItemCount());
                }
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(mFoodCardsRV);


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
