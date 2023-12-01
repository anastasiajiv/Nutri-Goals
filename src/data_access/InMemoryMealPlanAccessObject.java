package src.data_access;

import src.entity.Ingredient;
import src.entity.MealPlan;
import src.entity.Recipe;
import src.use_case.mealPlan.MealPlanDataAccessInterface;

import java.util.HashMap;
import java.util.List;

public class InMemoryMealPlanAccessObject implements MealPlanDataAccessInterface {

    @Override
    public HashMap<String, Double> getRecipeNutritionalInfo(String recipeID) {  // temporary for testing
        return null;
    }
    @Override
    public String breakfast(int identifier) {
        return null;
    }

    @Override
    public List<Ingredient> createIngredients(String identifier) {
        return null;
    }

    @Override
    public Recipe createRecipeBreakfast(List<Ingredient> ingredients, String recipe) {
        return null;
    }

    @Override
    public String lunch(int identifier) {
        return null;
    }

    @Override
    public Recipe createRecipeLunch(List<Ingredient> ingredients, String recipe) {
        return null;
    }

    @Override
    public String dinner(int identifier) {
        return null;
    }

    @Override
    public Recipe createRecipeDinner(List<Ingredient> ingredients, String recipe) {
        return null;
    }

    @Override
    public MealPlan getMealPlan(int id) {
        return null;
    }
}
