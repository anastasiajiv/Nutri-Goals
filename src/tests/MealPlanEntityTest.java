package src.tests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

import src.entity.*;

public class MealPlanEntityTest {

    private Ingredient ingredient;

    private Recipe breakfast;

    private Recipe lunch;

    private Recipe dinner;

    private MealPlan mealplan;

    @BeforeEach
    void init() {

        ingredient = new CommonIngredient(123, "Butter", "2.0 tablespoons");
        ArrayList<Ingredient> ingredientArrayList = new ArrayList<>();
        ingredientArrayList.add(ingredient);
        HashMap<String, Double> nutritionalinfo = new HashMap<>();
        nutritionalinfo.put("Iron", 8.0);


        breakfast =  new CommonRecipe(123, "Bagels with Cream Cheese", ingredientArrayList,
                "Toast on high for 3 minutes.", "breakfast", nutritionalinfo, "link");

        lunch =  new CommonRecipe(456, "Salad", ingredientArrayList,
                "Mix in bowl.", "lunch", nutritionalinfo, "link");


        dinner =  new CommonRecipe(678, "Pasta with Tomato Sauce", ingredientArrayList,
                "Boil for 7 minutes.", "dinner", nutritionalinfo, "link");

        mealplan = new CommonMealPlan(breakfast, lunch, dinner);


    }


    @Test
    void getBreakfast() {
        assertEquals(this.breakfast, mealplan.getBreakfast());

    }

    @Test
    void getLunch() {
        assertEquals(this.lunch, mealplan.getLunch());

    }

    @Test
    void getDinner() {
        assertEquals(this.dinner, mealplan.getDinner());

    }









}
