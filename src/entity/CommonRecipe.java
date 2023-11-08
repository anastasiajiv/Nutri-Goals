package src.entity;

import java.util.ArrayList;
import java.util.HashMap;

public class CommonRecipe implements Recipe {

    private final int recipeId;

    private final String recipeName;

    private final HashMap<String, Double> recipeIngredients;

    private final String recipeInstructions;

    private final HashMap<Integer, HashMap<String, ArrayList<String>>> savedRecipes;

    public CommonRecipe(int recipeId, String recipeName, HashMap<String, Double> recipeIngredients, String recipeInstructions, HashMap<Integer, HashMap<String, ArrayList<String>>> savedRecipes) {
        this.recipeId = recipeId;
        this.recipeName = recipeName;
        this.recipeIngredients = recipeIngredients;
        this.recipeInstructions = recipeInstructions;
        this.savedRecipes = savedRecipes;
    }


    @Override
    public int getRecipeId() {
        return recipeId;
    }

    @Override
    public String getRecipeName() {
        return recipeName;
    }

    @Override
    public HashMap<String, Double> getRecipeIngredients() {
        return recipeIngredients;
    }

    @Override
    public String getRecipeInstructions() {
        return recipeInstructions;
    }

    @Override
    public HashMap<Integer, HashMap<String, ArrayList<String>>> savedRecipes() {
        return savedRecipes;
    }
}
