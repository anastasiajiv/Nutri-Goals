package src.use_case.preferences;

public class PreferencesOutputData {
    private final String dietaryChoice;

    private final String allergiesChoice;
    private final String conditionsChoice;

    public PreferencesOutputData(String dietaryChoice, String allergiesChoice, String conditionsChoice){
        this.dietaryChoice = dietaryChoice;
        this.allergiesChoice = allergiesChoice;
        this.conditionsChoice = conditionsChoice;
    }
    public String getDietaryChoice(){return dietaryChoice;}
    public String getAllergiesChoice(){return allergiesChoice;}
    public String getConditionsChoice(){return conditionsChoice;}
}
