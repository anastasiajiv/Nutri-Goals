package src.entity;

import java.util.ArrayList;

public class CommonMealPlan implements MealPlan{
    public ArrayList<String> breakfast;

    public ArrayList<String> lunch;

    public ArrayList<String> dinner;


    @Override
    public ArrayList<String> getBreakfast() {
        return this.breakfast;
    }

    @Override
    public ArrayList<String> getLunch() {
        return this.lunch;
    }

    @Override
    public ArrayList<String> getDinner() {
        return this.dinner;
    }
}
