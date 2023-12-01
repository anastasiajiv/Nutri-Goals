package src.entity;

import org.w3c.dom.ls.LSOutput;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonRecipe implements Recipe {

    private final int recipeId;

    private final String recipeName;

    private final List<Ingredient> recipeIngredients;

    private final String recipeInstructions;

    private final String recipeType;

    private final HashMap<String, Float> nutritionalinfo;

    private final String recipelink;


    public CommonRecipe(int recipeId, String recipeName, List<Ingredient> recipeIngredients,
                        String recipeInstructions, String recipeType, HashMap<String, Float> nutritionalinfo, String recipelink) {
        this.recipeId = recipeId;
        this.recipeName = recipeName;
        this.recipeIngredients = recipeIngredients;
        this.recipeInstructions = recipeInstructions;

        this.recipeType = recipeType;

        this.nutritionalinfo = nutritionalinfo;
        this.recipelink = recipelink;

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
    public List<Ingredient> getRecipeIngredients() {
        return recipeIngredients;
    }

    @Override
    public String getRecipeInstructions() {
        return recipeInstructions;
    }

    @Override
    public String getrecipeType() {
        return recipeType;
    }

    @Override
    public HashMap<String, Float> getnutritionalinfo() {
        return nutritionalinfo;
    }

    @Override
    public String getrecipelink() {
        return recipelink;
    }


//    @Override
//    public HashMap<Integer, HashMap<String, ArrayList<String>>> savedRecipes() {
//        return savedRecipes;
//    }
}
