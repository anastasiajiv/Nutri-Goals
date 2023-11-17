package src.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public interface UserFactory {
     User create(int userId,
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
                 int requiredCalories);
}
