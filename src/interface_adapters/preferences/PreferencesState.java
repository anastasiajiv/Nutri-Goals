package src.interface_adapters.preferences;
import java.util.*;
public class PreferencesState {
    private HashMap<String, Boolean> dietary = null;
    private HashMap<String, Boolean> allergies = null;
    private HashMap<String, String> conditions = null;

    public PreferencesState(PreferencesState copy){
        dietary = copy.dietary;
        allergies = copy.allergies;
        conditions = copy.conditions;
    }

    public PreferencesState(){}

    public void setDietaryMap(HashMap<String, Boolean> dietaryMap){this.dietary = dietaryMap;}
    public HashMap<String, Boolean> getDietaryMap(){return dietary;}

    public void setAllergiesMap(HashMap<String, Boolean> allergiesMap){this.allergies = allergiesMap;}
    public HashMap<String, Boolean> getAllergiesMap(){return allergies;}
    public void setConditionsMap(HashMap<String, String> conditionsMap){this.conditions =conditionsMap;}
    public HashMap<String, String> getConditionsMap(){return conditions;}

}
