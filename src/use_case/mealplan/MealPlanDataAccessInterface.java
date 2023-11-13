package src.use_case.mealplan;

import java.util.ArrayList;

public interface MealPlanDataAccessInterface {
    ArrayList<String> Breakfast(int identifier);

    ArrayList<String> Lunch(int identifier);


    ArrayList<String> Dinner(int identifier);


    ArrayList<String> MealPlan(ArrayList<String> breakfast, ArrayList<String> lunch, ArrayList<String> dinner);
}
