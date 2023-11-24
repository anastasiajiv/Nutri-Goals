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
                 HashMap<String, Boolean> dietary,
                 HashMap<String, Boolean> allergies,
                 HashMap<String, String> conditions,
                 ArrayList<String> trackedNutrients,
                 HashMap<String, Boolean> weightGoal,
                 String paceType,
                 int requiredCalories);

     default User createdDefaultUser(int userId, String name) {
          return create(userId,
                  name,
                  null, //password defaults to null
                  LocalDateTime.MIN,//Defaults to minimum date time value
                  new HashMap<>(),// gender defaults to empty hashmap
                  0.0, // default height to 0.0
                  0.0, //default weight to 0.p
                  0, //default age to 0
                  0, //default exercise level to 0 (NOTE: must be in range 1-5)
                  new HashMap<>(),// default restrictions to empty map
                  new HashMap<>(),
                  new HashMap<>(),
                  new HashMap<>(), // default weightgoal to empty map
                  null, // default weight gain/lose type to null
                  0 // default req calories to 0
                  );

     }




}
