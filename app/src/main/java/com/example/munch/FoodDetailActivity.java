package com.example.munch;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.munch.data.Food;
import com.example.munch.utils.SpoonacularUtils;

public class FoodDetailActivity extends AppCompatActivity {

    private ImageView mImage;
    private TextView mFoodTitle;
    private TextView mFoodDescription;
    private TextView mSpoonacularScore;
    private TextView mCookingMinutes;

    private FoodViewModel mFoodViewModel;
    private Food mFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);

        mImage = findViewById(R.id.iv_food_photo);
        mFoodTitle = findViewById(R.id.tv_food_name);
        mFoodDescription = findViewById(R.id.tv_food_description);
        mSpoonacularScore = findViewById(R.id.tv_spoonacular_score);
        mCookingMinutes = findViewById(R.id.tv_cooking_minutes);

        mFoodViewModel = ViewModelProviders.of(this).get(FoodViewModel.class);

        mFood = null;
        Intent intent = getIntent();
        //note: possible check is for extra food repo
        if(intent != null && intent.hasExtra(SpoonacularUtils.EXTRA_FOOD)){
            mFood = (Food) intent.getSerializableExtra(SpoonacularUtils.EXTRA_FOOD);

            Glide.with(mImage)
                    .load(mFood.image)
                    .into(mImage);

            mFoodTitle.setText(mFood.title);
            mFoodDescription.setText(mFood.instructions);

            int totalTime = mFood.preparationMinutes + mFood.cookingMinutes + mFood.readyInMinutes;

            String spoonScoreString = Integer.toString(mFood.spoonacularScore);
            mSpoonacularScore.setText("Spoonacular Score: " + spoonScoreString);

            if(mFood.spoonacularScore >= 80){
                mSpoonacularScore.setTextColor(Color.GREEN);
            }
            else if(mFood.spoonacularScore >= 60){
                mSpoonacularScore.setTextColor(Color.YELLOW);
            }
            else if(mFood.spoonacularScore < 60){
                mSpoonacularScore.setTextColor(Color.RED);
            }


            String totalTimeString = Integer.toString(totalTime);
            mCookingMinutes.setText("Total Time: " + totalTimeString + " minutes");

            //Log.d(FoodDetailActivity.class.getSimpleName(), "Spoon is: " + spoonScoreString + "total time is: " + totalTimeString);
        }

    }

}
