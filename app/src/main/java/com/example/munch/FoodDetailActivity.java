package com.example.munch;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.food_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_view_on_web:
                viewFoodOnWeb();
                return true;
            case R.id.action_share:
                shareFood();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void viewFoodOnWeb() {
        if (mFood != null) {
            Uri repoURI = Uri.parse(mFood.spoonacularSourceUrl);
            Intent intent = new Intent(Intent.ACTION_VIEW, repoURI);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }
    }

    public void shareFood() {
        if (mFood != null) {
            String shareText = "Hey, I found this great recipe, " + mFood.title + ", and I want you to check it out! Click here to take a look: " + mFood.spoonacularSourceUrl;
            ShareCompat.IntentBuilder.from(this)
                    .setType("text/plain")
                    .setText(shareText)
                    .setChooserTitle("How would you like to share this food?")
                    .startChooser();
        }
    }

}
