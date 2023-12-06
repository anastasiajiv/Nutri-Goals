package src.entity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface Recipe {

    int getRecipeID();

    String getRecipeName();

    List<Ingredient> getRecipeIngredients(); // Using java.lang DOUBLE -> object

    String getRecipeInstructions();

    String getRecipeInstructionsDisplay();

    String getRecipeType();

    HashMap<String, Double> getNutritionalInfo();

    String getNutritionalInfoString();


    String getRecipeLink();

    String getRecipeIngredientString();


//    String getBreakfast();
//
//    String getLunch();
//
//    String getDinner();

    // HashMap<Integer, HashMap<String, ArrayList<String>>> savedRecipes(); // {1: {Breakfeast: [RECIPE], Lunch:[RECIPE]},
    // 2:{Breakfeast: [RECIPE], Lunch:[RECIPE]}}
}
