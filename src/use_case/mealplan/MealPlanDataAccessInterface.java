package src.use_case.mealPlan;

import org.json.JSONArray;
import src.entity.Ingredient;
import src.entity.MealPlan;
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

    MealPlan getMealPlan(int id);

    Boolean existByUserID(int userID);

   String displayMealPlan(MealPlan mealplan);

    void saveMealPlantoCsv(int id);





}
