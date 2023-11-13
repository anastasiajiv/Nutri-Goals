package src.use_case.nutrients;

import java.util.HashMap;

import src.entity.User;
import java.util.ArrayList;
import java.util.Map;

public class NutrientsInputData {
    private final int userID;

    // depending on implementation of headers, might need to adjust attribute type of nutrients
    private HashMap<String, Float> nutrients;  // <nutrient name, value accumulated/tracked>

    public NutrientsInputData(int userID, HashMap<String, Float> nutrients) {
        this.userID = userID;
        this.nutrients = nutrients;
    }

    // returns Strings of the nutrients wanted to be tracked; only names, *no values*
    ArrayList<String> getNutrientsTracked() {  // returning ArrayList, can later change
        // returns arraylist of all keys in nutrients (nutrient names)
        return new ArrayList<String>(this.nutrients.keySet());
    }

    HashMap<String, Float> getNutrients() {
        return this.nutrients;
    }

    int getUserID() {
        return this.userID;
    }
}
