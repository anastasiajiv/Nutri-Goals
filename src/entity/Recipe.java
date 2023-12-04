package src.entity;

import java.util.ArrayList;
import java.util.HashMap;
public interface Recipe {

    int getRecipeId();

    String getRecipeName();

    HashMap<String, Double> getRecipeIngredients(); // Using java.lang DOUBLE -> object

    String getRecipeInstructions();

    HashMap<String, Boolean> recipeType();
    String getRecipeType();

    HashMap<String, Float> getNutritionalInfo();


//    String getBreakfast();
//
//    String getLunch();
//
//    String getDinner();

   // HashMap<Integer, HashMap<String, ArrayList<String>>> savedRecipes(); // {1: {Breakfast: [RECIPE], Lunch:[RECIPE]},
                                                                        // 2:{Breakfast: [RECIPE], Lunch:[RECIPE]}}
}
