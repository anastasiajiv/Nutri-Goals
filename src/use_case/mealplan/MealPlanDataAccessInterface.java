package src.use_case.mealplan;

import src.entity.Ingredient;
import src.entity.Recipe;

import java.util.ArrayList;
import java.util.List;

public interface MealPlanDataAccessInterface {
    String Breakfast(int identifier);

    List<Ingredient> CreateIngredients(String identifier);

    Recipe CreateRecipeBreakfast(List<Ingredient> ingredients, String recipe);







    String Lunch(int identifier);



    Recipe CreateRecipeLunch(List<Ingredient> ingredients, String recipe);


    String Dinner(int identifier);




    Recipe CreateRecipeDinner(List<Ingredient> ingredients, String recipe);



    ArrayList<String> MealPlan(ArrayList<String> breakfast, ArrayList<String> lunch, ArrayList<String> dinner);


}
