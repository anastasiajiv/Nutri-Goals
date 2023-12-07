package src.use_case.preferences;
import java.util.*;
public class PreferencesInputData {
    final private int userId;
    final private HashMap<String, Boolean> dietary;
    final private HashMap<String, String> conditions; //high, low, average
    final private HashMap<String, Boolean> allergies;

    public PreferencesInputData(int userId, HashMap<String, Boolean> dietary, HashMap<String, String> conditions,
                                HashMap<String, Boolean> allergies) {
        this.userId = userId;
        this.dietary = dietary;
        this.conditions = conditions;
        this.allergies = allergies;

    }

    HashMap<String, Boolean> getDietary() {
        return this.dietary;
    }

    HashMap<String, String> getConditions(){
        return this.conditions;
    }
    HashMap<String, Boolean> getAllergies(){
        return this.allergies;
    }

    public int getUserId(){ return this.userId; }
}
