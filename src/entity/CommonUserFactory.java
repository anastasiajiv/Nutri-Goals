package src.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class CommonUserFactory implements UserFactory {


    @Override
    public User create(int userId,
                       String name,
                       String password,
                       LocalDateTime creationTime,
                       HashMap<String, Boolean> gender,
                       double userHeight,
                       double userWeight,
                       int userAge,
                       int userExerciseLevel,
                       HashMap<String, Boolean> dietary,
                       HashMap<String, Boolean> allergies,
                       HashMap<String, String> conditions,
                       ArrayList<String> trackedNutrients,
                       HashMap<String, Boolean> weightGoal,
                       String paceType,
                       int requiredCalories) {

        return new CommonUser
                (userId,
                name,
                password,
                creationTime,
                gender,
                userHeight,
                userWeight,
                userAge,
                userExerciseLevel,
                dietary,
                allergies,
                conditions,
                trackedNutrients,
                weightGoal,
                        paceType,
                        requiredCalories);
    }
}
