package src.data_access;
import src.entity.CommonUser;
import src.entity.Recipe;
import src.use_case.mealplan.MealPlanDataAccessInterface;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import src.entity.CommonMealPlan;


public class FileDataAccess implements MealPlanDataAccessInterface {
    private final File csvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<Integer, ArrayList<String>> accounts = new HashMap<>();


    @Override
    public ArrayList<String> Breakfast(int userid) {

        CommonUser user = existsbyId(userid);
        int daily_cal = user.getRequiredCalories();
        // int breakfast_cals = Math.round((daily_cal/3)/5);
        // API call for if user.WeightGoalType() == ""
        //              maxCalories(breakfast_cals) or minCalories(breakfast_cals)
        // ArrayList preferences = user.getPreferences()
        // API call for preference
        // ArrayList<String> allergies = user.getAllergies();
        // String dietary = user.getDietary();
        // HashMap conditions = user.getConditions();
        //
        // iterate through list of preferences w api calls
        // for (
        // list of valid recipes:
        // pick one and add it to


    }

    @Override
    public Recipe Lunch(int userid) {
        return null;
    }

    @Override
    public Recipe Dinner(int userid) {
        return null;
    }

    @Override
    public ArrayList<String> MealPlan(ArrayList<String> breakfast, ArrayList<String> lunch, ArrayList<String> dinner) {
        return null;
    }

    @Override
    public boolean existsbyid(int id) {
        return users.containsKey(userId);;
    }
}


