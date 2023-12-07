package src.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.entity.CommonIngredient;
import src.entity.CommonRecipe;
import src.entity.Ingredient;
import src.entity.Recipe;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RecipeEntityTests {
    private Recipe recipe;

    private Ingredient ingredient;

    @BeforeEach
    void init() {
        ingredient = new CommonIngredient(123, "Butter", "2.0 tablespoons");
        ArrayList<Ingredient> ingredientArrayList = new ArrayList<>();
        ingredientArrayList.add(ingredient);
        HashMap<String, Double> nutritionalinfo = new HashMap<>();
        nutritionalinfo.put("Iron", 8.0);


        recipe = new CommonRecipe(123, "Bagels with Cream Cheese", ingredientArrayList,
                "Toast on high for 3 minutes.", "breakfast", nutritionalinfo, "link");



    }

    @Test
    void getIDTest() {
        assertEquals(123,recipe.getRecipeId());

    }

    @Test
    void getNameTest() {
        assertEquals("Bagels with Cream Cheese", recipe.getRecipeName());
    }


    @Test
    void getIngredientsTest() {



        assertEquals(this.ingredient, recipe.getRecipeIngredients().get(0));
    }


    @Test
    void getInstructions() {
        assertEquals("Toast on high for 3 minutes.", recipe.getRecipeInstructions());
    }

    @Test
    void getType() {
        assertEquals("breakfast", recipe.getrecipeType());
    }


    @Test
    void getnutritionalinfo() {

        HashMap<String, Double> nutritionalinfo = new HashMap<>();
        nutritionalinfo.put("Iron", 8.0);
        assertEquals(nutritionalinfo, recipe.getnutritionalinfo());
    }

    @Test
    void getNutritionalInfo() {

        HashMap<String, Double> nutritionalinfo = new HashMap<>();
        nutritionalinfo.put("Iron", 8.0);
        assertEquals(nutritionalinfo, recipe.getNutritionalInfo());


    }


    @Test
    void getnutritionalinfostring() {

        HashMap<String, Double> nutritionalinfo = new HashMap<>();
        nutritionalinfo.put("Iron", 8.0);
        assertEquals(nutritionalinfo.toString(), recipe.getnutritionalinfostring());


    }

    @Test
    void getRecipeLink() {

        assertEquals("link", recipe.getrecipelink());


    }





















}
