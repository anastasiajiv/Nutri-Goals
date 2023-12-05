package src.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface User {

    int getUserId();
    String getName();

    String getPassword();

    LocalDateTime getCreationTime(); // Optional to add

    HashMap<String, Boolean> getGender();

    String getGenderType();

//    String isFemale();
//
//    String isMale();

    double getUserHeight();

    double getUserWeight();

    int getUserAge();

    int getUserExerciseLevel(); // Exercise level will be in a range from 1 to 5 --> Specify each levels
                                // logistics in the ReadMe file


    //HashMap<String, Boolean> getUserRestriction(); // Restriction type will be stored as a hashmap <String(Restriction Type),
                                // Boolean(Weather user clicked this option or not)>

    // should not be set but should be changeDietary
    void setDietary(HashMap<String, Boolean> dietary);
    HashMap<String, Boolean> getDietary();

    // should not be set but should be changeAllergies
    void setAllergies(HashMap<String, Boolean> allergies);
    HashMap<String, Boolean> getAllergies();


    ArrayList<String> getTrackedNutrients(); // <Nutrient name, current value being tracked>


    // should not be set but should be changeConditions
    void setConditions(HashMap<String, String> conditions);

    HashMap<String, String> getConditions();

    //HashMap<String, Boolean> getUserRestriction(); // Restriction type will be stored as a hashmap <String(Restriction Type),
    // Boolean(Weather user clicked this option or not)>


    //HashMap<String, Boolean> WeightGoalType(); // String -> Maintain, Gain, Lose

    //HashMap<String, Double> weightPace();

    String userSpecifiedDietary(); //TODO Esha access these for MealPlan

    List<String> userSpecifiedAllergies(); //TODO Esha access this for MealPlan
    HashMap<String, Double> userSpecifiedConditions(); //TODO Esha access this for MealPlan

    Double getCalciumValue();

    Double getPotassiumValue();

    Double getVitaminCValue();
    Double getVitaminDValue();

    Double getIronValue();

    Double getMagnesiumValue();

    Double getSugarValue();


    String getWeightGoalType();

    String getMaintainTypeValue();

    String getLoseTypeValue();

    String getGainTypeValue();

    String getPaceType();


//    HashMap<String, HashMap<String, Boolean>> weightPaceType();
//    // {loseWeight : {typical : False, moderate: True, extreme: False}
//
//    HashMap<String, String> getPaceType();

    double getRequiredCalories(); // Should default to 0

    // setters
    void setPassword(String password);

    void setCreationTime(LocalDateTime creationTime);

    void setGender(HashMap<String, Boolean> gender);

    void setUserHeight(double userHeight);

    void setUserWeight(double userWeight);

    void setUserAge(int userAge);

    void setUserExerciseLvl(int userExerciseLvl);

    void setWeightGoalType(HashMap<String, Boolean> weightGoalType);

    void setTrackedNutrients(ArrayList<String> trackedNutrients);


    void setPaceType(String paceType);


    void setRequiredCalories(double requiredCalories);





}
