package src.use_case.weightgoal;

import src.entity.User;

public interface WeightGoalUserDataInterface {
    void saveWeightGoalData(User user);

    String getUserWeightGoalData(int userId);
    }

