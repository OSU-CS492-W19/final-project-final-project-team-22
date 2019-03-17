package com.example.munch.utils;

import android.net.Uri;
import android.util.Log;

import com.example.munch.data.Food;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

public class SpoonacularUtils{

    private static final String baseURL = "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/random?number=10&tags=dessert";

    private static final Gson gson = new Gson();

    public static final String EXTRA_FOOD = "SpoonacularUtils.Food";

    public static class SpoonacularResults{
        Food [] recipes;
    }

    public static String buildSpoonacularURL(){
        return Uri.parse(baseURL).buildUpon()
                .build()
                .toString();
    }

    public static List<Food> parseSpoonacularResultsJSON(String foodResultsJSON){
        SpoonacularResults results = gson.fromJson(foodResultsJSON, SpoonacularResults.class);

        List<Food> foods = null;

        if(results != null && results.recipes != null){
            Log.d(SpoonacularResults.class.getSimpleName(), "FIRST: " + results.recipes[0].title);
            Log.d(SpoonacularResults.class.getSimpleName(), "SECOND: " + results.recipes[1].title);
            Log.d(SpoonacularResults.class.getSimpleName(), "THIRD: " + results.recipes[2].title);
            Log.d(SpoonacularResults.class.getSimpleName(), "FOURTH: " + results.recipes[3].title);
            foods = Arrays.asList(results.recipes);
        }

        return foods;
    }


}
