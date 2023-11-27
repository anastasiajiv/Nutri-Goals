package src.use_case.mealplan;

import src.entity.Ingredient;
import src.entity.Recipe;

import java.util.ArrayList;
import java.util.List;

public interface MealPlanDataAccessInterface {
    String Breakfast(int identifier);

    List<Ingredient> CreateIngredientbreakfast(String identifier);

    Recipe CreateRecipeBreakfast(List<Ingredient>, );






    String Lunch(int identifier);

    List<Ingredient> CreateIngredientlunch(String identifier);


    String Dinner(int identifier);


    List<Ingredient> CreateIngredientdinner(String identifier);

    ArrayList<String> MealPlan(ArrayList<String> breakfast, ArrayList<String> lunch, ArrayList<String> dinner);


}
