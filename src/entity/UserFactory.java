package src.entity;

import java.time.LocalDateTime;
import java.util.HashMap;

public interface UserFactory {
     User create(String name, String password, LocalDateTime creationTime, double userHeight, double userWeight,
                 int userAge, int userExerciseLevel, HashMap<String, Boolean> userRestriction,
                 HashMap<String, Boolean> weightGoal);

}
