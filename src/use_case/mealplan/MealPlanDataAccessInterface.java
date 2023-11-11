package src.use_case.mealplan;

import java.util.ArrayList;

public interface MealPlanDataAccessInterface {
    ArrayList<String> Breakfast(String identifier);

    ArrayList<String> Lunch(String identifier);


    ArrayList<String> Dinner(String identifier);


    ArrayList<String> MealPlan(ArrayList<String> breakfast, ArrayList<String> lunch, ArrayList<String> dinner);
}
