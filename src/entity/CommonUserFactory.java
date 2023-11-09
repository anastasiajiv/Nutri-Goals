package src.entity;

import java.time.LocalDateTime;
import java.util.HashMap;

public class CommonUserFactory implements UserFactory {


    @Override
    public User create(int userId,
                       String name,
                       String password,
                       LocalDateTime creationTime,
                       double userHeight,
                       double userWeight,
                       int userAge,
                       int userExerciseLevel,
                       HashMap<String, Boolean> userRestriction,
                       HashMap<String, Boolean> weightGoal) {

        return new CommonUser
                (userId,
                name,
                password,
                creationTime,
                userHeight,
                userWeight,
                userAge,
                userExerciseLevel,
                userRestriction,
                weightGoal);
    }
}
