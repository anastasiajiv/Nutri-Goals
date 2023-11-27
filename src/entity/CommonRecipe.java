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

    private final HashMap<String, Boolean> recipeType;


    public CommonRecipe(int recipeId, String recipeName, List<Ingredient> recipeIngredients,
                        String recipeInstructions, HashMap<String, Boolean> recipeType) {
        this.recipeId = recipeId;
        this.recipeName = recipeName;
        this.recipeIngredients = recipeIngredients;
        this.recipeInstructions = recipeInstructions;

        this.recipeType = recipeType;

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
    public HashMap<String, Boolean> recipeType() {
        return recipeType;
    }

    @Override
    public String getRecipeType() {
        for (Map.Entry<String, Boolean> entry : recipeType.entrySet()) {
            if (entry.getValue() == Boolean.TRUE) {
                return entry.getKey();
            }
        }
        return "Did not find a type";
    }


//    @Override
//    public HashMap<Integer, HashMap<String, ArrayList<String>>> savedRecipes() {
//        return savedRecipes;
//    }
}
