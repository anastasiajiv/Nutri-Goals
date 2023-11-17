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
                       HashMap<String, Boolean> userRestriction,
                       ArrayList<String> trackedNutrients,
                       HashMap<String, Boolean> weightGoal,
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
                userRestriction,
                trackedNutrients,
                weightGoal,
                        requiredCalories);
    }
}
