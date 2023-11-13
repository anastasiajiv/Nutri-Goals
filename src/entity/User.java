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

    String getWeightGoalType();

    String getMaintainTypeValue();

    String getLoseTypeValue();

    String getGainTypeValue();



    int getRequiredCalories(); // Should default to 0



}
