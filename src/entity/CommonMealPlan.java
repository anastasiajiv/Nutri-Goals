package src.entity;

import java.util.ArrayList;
import java.util.HashMap;

public class CommonMealPlan implements MealPlan {
    public Recipe breakfast;

    public Recipe lunch;

    public Recipe dinner;
    public HashMap<String, Float> cumulativeNutritionalInfo;

    public CommonMealPlan(Recipe breakfast, Recipe lunch, Recipe dinner, HashMap<String, Float> nutritionalInfo){
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.dinner = dinner;
        this.cumulativeNutritionalInfo = nutritionalInfo;
    }

    @Override
    public Recipe getBreakfast() {
        return this.breakfast;
    }

    @Override
    public Recipe getLunch() {
        return this.lunch;
    }

    @Override
    public Recipe getDinner() {
        return this.dinner;
    }

    @Override
    public HashMap<String, Float> getCumulativeNutritionalInfo() {
        return this.cumulativeNutritionalInfo;
    }
}
