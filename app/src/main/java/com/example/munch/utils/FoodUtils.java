package com.example.munch.utils;

import com.example.munch.data.FavoriteFood;
import com.example.munch.data.Food;

public class FoodUtils {
    public static FavoriteFood toFavoriteFood(Food food) {
        FavoriteFood favoriteFood = new FavoriteFood();

        favoriteFood.title = food.title;
        favoriteFood.instructions = food.instructions;
        favoriteFood.image = food.image;
        favoriteFood.spoonacularSourceUrl = food.spoonacularSourceUrl;
        favoriteFood.spoonacularScore = food.spoonacularScore;
        favoriteFood.preparationMinutes = food.preparationMinutes;
        favoriteFood.cookingMinutes = food.cookingMinutes;
        favoriteFood.readyInMinutes = food.readyInMinutes;

        return favoriteFood;
    }

    public static Food toFood(FavoriteFood favoriteFood) {
        Food food = new Food();

        food.title = favoriteFood.title;
        food.instructions = favoriteFood.instructions;
        food.image = favoriteFood.image;
        food.spoonacularSourceUrl = favoriteFood.spoonacularSourceUrl;
        food.spoonacularScore = favoriteFood.spoonacularScore;
        food.preparationMinutes = favoriteFood.preparationMinutes;
        food.cookingMinutes = favoriteFood.cookingMinutes;
        food.readyInMinutes = favoriteFood.readyInMinutes;

        return food;
    }
}
