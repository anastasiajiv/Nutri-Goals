package src.use_case.weightgoal;

import src.entity.User;

public interface WeightGoalUserDataInterface {
    void saveWeightGoalData(User user);

    User getUserWeightGoalData(int userId);

    Boolean existByUserId(int userId);

    String getWeightGoalType(int userId);

}

