package src.use_case.trackedNutrients;

import java.lang.reflect.Array;
import java.util.HashMap;

import java.util.ArrayList;

public class TrackedNutrientsInputData {
    private final int userID;

    private ArrayList<String> trackedNutrients;

    public TrackedNutrientsInputData(int userID, ArrayList<String> trackedNutrients) {
        this.userID = userID;
        this.trackedNutrients = trackedNutrients;
    }

    ArrayList<String> getTrackedNutrients() {
        return this.trackedNutrients;
    }

    int getUserID() {
        return this.userID;
    }
}
