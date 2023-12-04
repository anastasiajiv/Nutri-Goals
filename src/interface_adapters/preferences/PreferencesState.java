package src.interface_adapters.preferences;
import java.util.*;
public class PreferencesState {
    private int userID;
    private HashMap<String, Boolean> dietary = null;
    private HashMap<String, Boolean> allergies = null;
    private HashMap<String, String> conditions = null;
    private String preferencesError;
    public PreferencesState(PreferencesState copy){
        userID = copy.userID;
        dietary = copy.dietary;
        allergies = copy.allergies;
        conditions = copy.conditions;
        preferencesError = copy.preferencesError;
    }

    public PreferencesState(){}

    public void setUserID(int userID) {this.userID = userID;}
    public int getUserID(){return userID;}

    public void setDietaryMap(HashMap<String, Boolean> dietaryMap){this.dietary = dietaryMap;}
    public HashMap<String, Boolean> getDietaryMap(){return dietary;}

    public void setAllergiesMap(HashMap<String, Boolean> allergiesMap){this.allergies = allergiesMap;}
    public HashMap<String, Boolean> getAllergiesMap(){return allergies;}
    public void setConditionsMap(HashMap<String, String> conditionsMap){this.conditions =conditionsMap;}
    public HashMap<String, String> getConditionsMap(){return conditions;}
    public String setPreferencesError(String preferencesError){return "Unable to set preferences. Please try again.";}
}
