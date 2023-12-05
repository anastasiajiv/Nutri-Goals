package src.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface Recipe {

    int getRecipeId();

    String getRecipeName();

    List<Ingredient> getRecipeIngredients(); // Using java.lang DOUBLE -> object

    String getRecipeInstructions();

    String getrecipeType();

    HashMap<String, Float> getnutritionalinfo();

    String getnutritionalinfostring();


    String getrecipelink();

    String getrecipeIngredientstring();


    HashMap<String, Float> getNutritionalInfo();


//    String getBreakfast();
//
//    String getLunch();
//
//    String getDinner();

   // HashMap<Integer, HashMap<String, ArrayList<String>>> savedRecipes(); // {1: {Breakfast: [RECIPE], Lunch:[RECIPE]},
                                                                        // 2:{Breakfast: [RECIPE], Lunch:[RECIPE]}}
}
