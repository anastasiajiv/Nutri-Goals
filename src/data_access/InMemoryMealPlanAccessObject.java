package src.data_access;

import src.entity.Ingredient;
import src.entity.MealPlan;
import src.entity.Recipe;
import src.use_case.mealPlan.MealPlanDataAccessInterface;

import java.util.HashMap;
import java.util.List;

public class InMemoryMealPlanAccessObject implements MealPlanDataAccessInterface {

    @Override
    public String Breakfast(int identifier) {
        return null;
    }

    @Override
    public List<Ingredient> CreateIngredients(String identifier) {
        return null;
    }

    @Override
    public Recipe CreateRecipeBreakfast(List<Ingredient> ingredients, String recipe, int userID) {
        return null;
    }

    @Override
    public String Lunch(int identifier) {
        return null;
    }

    @Override
    public Recipe CreateRecipeLunch(List<Ingredient> ingredients, String recipe, int userID) {
        return null;
    }

    @Override
    public String Dinner(int identifier) {
        return null;
    }

    @Override
    public Recipe CreateRecipeDinner(List<Ingredient> ingredients, String recipe, int userID) {
        return null;
    }

    @Override
    public MealPlan getMealPlan(int id) {
        return null;
    }

    @Override
    public Boolean existByUserID(int userID) {
        return null;
    }

    @Override
    public String displayMealPlan(MealPlan mealplan) {
        return null;
    }

    @Override
    public void saveMealPlantoCsv(int id) {

    }
}
