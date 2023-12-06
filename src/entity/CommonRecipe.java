package src.entity;

import java.util.HashMap;
import java.util.List;

public class CommonRecipe implements Recipe {

    private final int recipeID;

    private final String recipeName;

    private final List<Ingredient> recipeIngredients;

    private final String recipeInstructions;

    private final String recipeType;

    private final HashMap<String, Double> nutritionalInfo;

    private final String recipeLink;


    public CommonRecipe(int recipeID, String recipeName, List<Ingredient> recipeIngredients,
                        String recipeInstructions, String recipeType, HashMap<String, Double> nutritionalInfo,
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
    public String getRecipeInstructionsDisplay(){
        String[] strArray = recipeInstructions.split("\\.");
        StringBuilder sb = new StringBuilder();

        for(String s: strArray){
            sb.append(s + "\n");
        }

        String sb1 = sb.toString();

        String str1 = sb1.replaceAll("[<\b>]", "");

        //String str2 = str1.replaceAll(, )

        return str1;






    }

    @Override
    public String getRecipeType() {
        return recipeType;
    }

    @Override
    public HashMap<String, Double> getNutritionalInfo() {
        return nutritionalInfo;
    }

    @Override
    public String getRecipeLink() {
        return recipeLink;
    }

    @Override
    public String getNutritionalInfoString() {
        return nutritionalInfo.toString();
    }

    @Override
    public String getRecipeIngredientString() {

        StringBuilder sb = new StringBuilder();
        for (Ingredient ingredient : this.recipeIngredients) {
            String ingredientstring = ingredient.getName() + "amount : " + ingredient.getAmount() + ", ";
            sb.append(ingredientstring);
        }
        return sb.toString();
    }
//    @Override
//    public HashMap<Integer, HashMap<String, ArrayList<String>>> savedRecipes() {
//        return savedRecipes;
//    }
}