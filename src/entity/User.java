package src.entity;

import java.time.LocalDateTime;
import java.util.HashMap;

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

    int getUserExcerciseLevel(); // Excersice level will be in a range from 1 - 5 --> Specify each levels
                                // logistics in the ReadMe file

    HashMap<String, Boolean> getUserRestriction(); // Restriction type will be stored as a hashmap <String(Restriction Type),
                                // Boolean(Weather user clicked this option or not)>

    HashMap<String, Boolean> WeightGoalType(); // String -> Maintain, Gain, Lose

    //HashMap<String, Double> weightPace();

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

    void setRestrictions(HashMap<String, Boolean> restrictions);

    void setPaceType(String paceType);


    void setRequiredCalories(double requiredCalories);

}
