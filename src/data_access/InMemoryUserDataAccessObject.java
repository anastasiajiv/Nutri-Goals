package src.data_access;

import src.entity.User;
import src.use_case.login.LoginUserDataAccessInterface;
import src.use_case.signup.SignupUserDataAccessInterface;
import src.use_case.weightgoal.WeightGoalUserDataInterface;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import java.time.LocalDateTime;
public class InMemoryUserDataAccessObject implements SignupUserDataAccessInterface, LoginUserDataAccessInterface, WeightGoalUserDataInterface {

    private Map<Integer, User> users = new HashMap<>();


    /**
     * @param userId
     * @param gender
     * @param height
     * @param weight
     * @param age
     * @param exerciseLvl
     * @param paceType
     * @param weightGoal
     * @return
     */
    @Override
    public Boolean saveWeightGoalData(int userId,
                                      HashMap<String, Boolean> gender,
                                      double height, double weight,
                                      int age,
                                      int exerciseLvl,
                                      String paceType,
                                      HashMap<String, Boolean> weightGoal) {
        return null;
    }

    /**
     * @param userID
     * @return
     */
    @Override
    public double computedRequiredCalories(int userID) {
        return 0;
    }

    /**
     * @param identifier
     * @return
     */
    @Override
    public boolean existByName(String identifier) {
        return users.containsKey(identifier);
    }

    //SignUp InMemory
    @Override
    public Boolean existByUserID(int userId) {
        return users.containsKey(userId);
    }

    /**
     * @param username
     * @return
     */
    @Override
    public User get(String username) {
        User user = null;
        for (Integer key: users.keySet()) {
            User value = users.get(key);
            String name = value.getName();
            if (name.equals(username)){
                user = value;
            }
        }
        assert (user != null);
        return user;
    }

    @Override
    public Boolean saveUserSignUpData(int userId,
                                      String username,
                                      String password,
                                      LocalDateTime creationTime) {
        return Boolean.TRUE;
    }

    public int createUserID(){
        return 1;
    }

    @Override
    public User getAccountByUserID(int userId) {
        return users.get(userId);
    }



}
