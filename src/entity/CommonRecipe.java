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

    private final HashMap<String, Double> nutritionalinfo;

    private final String recipelink;


    public CommonRecipe(int recipeId, String recipeName, List<Ingredient> recipeIngredients,
                        String recipeInstructions, String recipeType, HashMap<String, Double> nutritionalinfo, String recipelink) {




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
    public String getRecipeInstuctionsdisplay(){
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
    public String getrecipeType() {
        return recipeType;
    }

    @Override
    public HashMap<String, Double> getnutritionalinfo() {
        return nutritionalinfo;
    }




    @Override
    public String getnutritionalinfostring(){return nutritionalinfo.toString();}



    @Override
    public String getrecipelink() {
        return recipelink;
    }

    @Override
    public String getrecipeIngredientstring() {

        StringBuilder sb = new StringBuilder();
        for (int i =0; i < this.recipeIngredients.size(); i ++ ){

            Ingredient ingredient = recipeIngredients.get(i);
            String ingredientstring = ingredient.getName() + " amount : " + ingredient.getAmount() + ", ";
            sb.append(ingredientstring);

        }
        return sb.toString();
    }

    @Override
    public HashMap<String, Double> getNutritionalInfo() {
        return this.nutritionalinfo;
    }


//    @Override
//    public HashMap<Integer, HashMap<String, ArrayList<String>>> savedRecipes() {
//        return savedRecipes;
//    }
}
