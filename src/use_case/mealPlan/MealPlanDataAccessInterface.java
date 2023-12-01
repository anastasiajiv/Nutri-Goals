package src.use_case.mealPlan;

import src.entity.Ingredient;
import src.entity.MealPlan;
import src.entity.Recipe;

import java.util.ArrayList;
import java.util.List;

public interface MealPlanDataAccessInterface {
    String breakfast(int identifier);

    List<Ingredient> createIngredients(String identifier);

    Recipe createRecipeBreakfast(List<Ingredient> ingredients, String recipe);

    String lunch(int identifier);

    Recipe createRecipeLunch(List<Ingredient> ingredients, String recipe);

    String dinner(int identifier);

    Recipe createRecipeDinner(List<Ingredient> ingredients, String recipe);

    MealPlan getMealPlan(int id);
}