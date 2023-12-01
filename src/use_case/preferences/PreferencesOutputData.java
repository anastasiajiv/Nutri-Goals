package src.use_case.preferences;

import java.util.*;
public class PreferencesOutputData {
    private final HashMap<String, Boolean> dietaryChoice;

    private final HashMap<String, Boolean> allergiesChoice;
    private final HashMap<String, String> conditionsChoice;

    public PreferencesOutputData(HashMap<String, Boolean> dietaryChoice,
                                 HashMap<String, Boolean> allergiesChoice,
                                 HashMap<String, String> conditionsChoice){
        this.dietaryChoice = dietaryChoice;
        this.allergiesChoice = allergiesChoice;
        this.conditionsChoice = conditionsChoice;
    }
    public HashMap<String, Boolean> getDietaryChoice(){return dietaryChoice;}
    public HashMap<String, Boolean> getAllergiesChoice(){return allergiesChoice;}
    public HashMap<String, String> getConditionsChoice(){return conditionsChoice;}
}
