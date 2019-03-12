package com.example.munch.data;

import java.io.Serializable;

public class Food implements Serializable {
    public String title;
    public String instructions;
    public String image;

    public String spoonacularSourceUrl;

    public int spoonacularScore;
    public int preparationMinutes;
    public int cookingMinutes;
    public int readyInMinutes;
}
