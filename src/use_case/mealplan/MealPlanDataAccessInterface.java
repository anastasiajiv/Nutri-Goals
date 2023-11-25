package src.use_case.mealplan;

import java.util.ArrayList;

public interface MealPlanDataAccessInterface {
    Recipe Breakfast(int identifier);

    Recipe Lunch(int identifier);


    Recipe Dinner(int identifier);


    ArrayList<String> MealPlan(ArrayList<String> breakfast, ArrayList<String> lunch, ArrayList<String> dinner);

    boolean existsbyid()
}
