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

    String isFemale();

    String isMale();

    double getUserHeight();

    double getUserWeight();

    int getUserAge();

    int getUserExerciseLevel(); // Exercise level will be in a range from 1 to 5 --> Specify each levels
                                // logistics in the ReadMe file

    String getDietary();

    List<String> getAllergies();

    ArrayList<String> getTrackedNutrients(); // <Nutrient name, current value being tracked>

    HashMap<String, Double> getConditions();

    //HashMap<String, Boolean> getUserRestriction(); // Restriction type will be stored as a hashmap <String(Restriction Type),
    // Boolean(Weather user clicked this option or not)>

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

    void setDietary(HashMap<String, Boolean> dietary);

    void setConditions(HashMap<String, String> conditions);

    void setAllergies(HashMap<String, Boolean> allergies);

    void setPaceType(String paceType);


    void setRequiredCalories(double requiredCalories);


}
