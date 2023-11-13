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


//    String getBreakfeast();
//
//    String getLunch();
//
//    String getDinner();

   // HashMap<Integer, HashMap<String, ArrayList<String>>> savedRecipes(); // {1: {Breakfeast: [RECIPE], Lunch:[RECIPE]},
                                                                        // 2:{Breakfeast: [RECIPE], Lunch:[RECIPE]}}
}
