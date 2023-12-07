package src.entity;

import java.util.HashMap;

public class CommonMealPlan implements MealPlan {
    public Recipe breakfast;
    public Recipe lunch;
    public Recipe dinner;

    public CommonMealPlan(Recipe breakfast, Recipe lunch, Recipe dinner){
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.dinner = dinner;
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
}
