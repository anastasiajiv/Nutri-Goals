package src.entity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface Recipe {

    int getRecipeID();

    String getRecipeName();

    List<Ingredient> getRecipeIngredients(); // Using java.lang DOUBLE -> object

    String getRecipeInstructions();

    String getRecipeType();

    HashMap<String, Double> getNutritionalInfo();

    String getRecipeLink();



//    String getBreakfast();
//
//    String getLunch();
//
//    String getDinner();

<<<<<<< HEAD
    // HashMap<Integer, HashMap<String, ArrayList<String>>> savedRecipes(); // {1: {Breakfeast: [RECIPE], Lunch:[RECIPE]},
    // 2:{Breakfeast: [RECIPE], Lunch:[RECIPE]}}
}
=======
   // HashMap<Integer, HashMap<String, ArrayList<String>>> savedRecipes(); // {1: {Breakfast: [RECIPE], Lunch:[RECIPE]},
                                                                        // 2:{Breakfast: [RECIPE], Lunch:[RECIPE]}}
}
>>>>>>> main
