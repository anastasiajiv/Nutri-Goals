package src.use_case.nutrients;

import java.util.HashMap;

public class NutrientsInputData {
    private final int userID;
    private HashMap<String, Boolean> nutrients;

    public NutrientsInputData(int userID) {
        this.userID = userID;
    }

    int getUserID() {
        return this.userID;
    }
}
