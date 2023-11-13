package src.use_case.nutrients;

import java.util.HashMap;

import src.entity.User;
import java.util.ArrayList;
import java.util.Map;

public class NutrientsInputData {
    private final int userID;
    private HashMap<String, Boolean> nutrients;

    public NutrientsInputData(int userID, HashMap<String, Boolean> nutrients) {
        this.userID = userID;
        this.nutrients = nutrients;
    }

    // returns Strings of the nutrients wanted to be tracked
    ArrayList<String> getNutrients() {  // returning ArrayList, can later change
        ArrayList<String> nutrientsList = new ArrayList<>();

        // loop through each key and value in nutrients
        for(Map.Entry<String, Boolean> entry : this.nutrients.entrySet()) {
            String key = entry.getKey();
            Boolean value = entry.getValue();
            // if this is a nutrient that is tracked, add to the return list
            if (value) {
                nutrientsList.add(key);  // adds the string representation of the nutrient
            }
        }
        return nutrientsList;
    }

    int getUserID() {
        return this.userID;
    }
}
