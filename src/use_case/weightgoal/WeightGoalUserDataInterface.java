package src.use_case.weightgoal;

import src.entity.User;

import java.util.HashMap;

public interface WeightGoalUserDataInterface {
    Boolean saveWeightGoalData(int userId,
                            HashMap<String, Boolean> gender,
                            double height,
                            double weight,
                            int age,
                            int exerciseLvl,
                            String paceType,
                            HashMap<String, Boolean> weightGoal);

    double computedRequiredCalories (int userId) ;

    Boolean existByUserID(int userID);

    public User getAccountByUserId ( int userId);

    //User getUserWeightGoalData(int userId);

    //Boolean existByUserId(int userId);

    //String getWeightGoalType(int userId);

}

