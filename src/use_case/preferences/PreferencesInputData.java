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

    HashMap<Integer, HashMap<String, Boolean>> getDietary() {
        HashMap<Integer, HashMap<String, Boolean>> map = new HashMap<>();
        map.put(this.userId, this.dietary);
        return map;
    }

    HashMap<Integer, HashMap<String, String>> getConditions(){
        HashMap<Integer, HashMap<String, String>> map = new HashMap<>();
        map.put(this.userId, this.conditions);
        return map;
    }
    HashMap<Integer, HashMap<String, Boolean>> getAllergies(){
        HashMap<Integer, HashMap<String, Boolean>> map = new HashMap<>();
        map.put(userId, this.allergies);
        return map;
    }

    int getUserId(){ return this.userId; }
}
