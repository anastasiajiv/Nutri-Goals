package src.entity;

import org.w3c.dom.ls.LSOutput;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonRecipe implements Recipe {

    private final int recipeID;

    private final String recipeName;

    private final List<Ingredient> recipeIngredients;

    private final String recipeInstructions;

    private final String recipeType;

    private final HashMap<String, Float> nutritionalInfo;

    private final String recipeLink;


    public CommonRecipe(int recipeID, String recipeName, List<Ingredient> recipeIngredients,
                        String recipeInstructions, String recipeType, HashMap<String, Float> nutritionalInfo,
                        String recipeLink) {
        this.recipeID = recipeID;
        this.recipeName = recipeName;
        this.recipeIngredients = recipeIngredients;
        this.recipeInstructions = recipeInstructions;
        this.recipeType = recipeType;
        this.nutritionalInfo = nutritionalInfo;
        this.recipeLink = recipeLink;

    }

    @Override
    public int getRecipeID() {
        return recipeID;
    }

    @Override
    public String getRecipeName() {
        return recipeName;
    }

    @Override
    public List<Ingredient> getRecipeIngredients() {
        return recipeIngredients;
    }

    @Override
    public String getRecipeInstructions() {
        return recipeInstructions;
    }

    @Override
    public String getRecipeType() {
        return recipeType;
    }

    @Override
    public HashMap<String, Float> getNutritionalInfo() {
        return nutritionalInfo;
    }

    @Override
    public String getRecipeLink() {
        return recipeLink;
    }


//    @Override
//    public HashMap<Integer, HashMap<String, ArrayList<String>>> savedRecipes() {
//        return savedRecipes;
//    }
}