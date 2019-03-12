package com.example.munch.utils;

import android.net.Uri;
import android.util.Log;

import com.example.munch.data.Food;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

public class SpoonacularUtils{

    private static final String baseURL = "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/random?number=10&tags=vegetarian%2Cdessert";
    private static final String content_key = "Content-Type";
    private static final String content_value = "application/x-www-form-urlencoded";

    private static final Gson gson = new Gson();

    public static class SpoonacularResults{
        Food [] recipes;
    }

    /*
    public static class Recipes{
        String title;
    }

    public static class Foods{
        List<Food> food;
    }
    */

    public static String buildSpoonacularURL(){
        return Uri.parse(baseURL).buildUpon()
                .build()
                .toString();
    }

    public static List<Food> parseSpoonacularResultsJSON(String foodResultsJSON){
        SpoonacularResults results = gson.fromJson(foodResultsJSON, SpoonacularResults.class);

        List<Food> foods = null;

        if(results != null && results.recipes != null){
            foods = Arrays.asList(results.recipes);
        }

        return foods;
    }


}
