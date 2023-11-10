package src.data_access;

import src.use_case.weightgoal.WeightGoalUserDataInterface;
import src.entity.User;
public class InMemoryWeightGoalAccessObject implements WeightGoalUserDataInterface {


    @Override
    public void saveWeightGoalData(User user) {

    }
    @Override
    public User getUserWeightGoalData(int userId) {
        return null;
    }

    @Override
    public Boolean existByUserId(int userId) {
        return null;
    }

    @Override
    public String getWeightGoalType(int userId) {
        return null;
    }


}
