package src.data_access;

import src.entity.CommonUserFactory;
import src.entity.User;
import src.entity.UserFactory;
import src.use_case.login.LoginUserDataAccessInterface;
import src.use_case.mealPlan.MealPlanDataAccessInterface;
import src.use_case.preferences.PreferencesUserDataAccessInterface;
import src.use_case.signup.SignupUserDataAccessInterface;
import src.use_case.trackedNutrients.TrackedNutrientsUserDataAccessInterface;
import src.use_case.weightgoal.WeightGoalUserDataInterface;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class InMemoryUserDataAccessObject_Test implements
        SignupUserDataAccessInterface,
        LoginUserDataAccessInterface,
        PreferencesUserDataAccessInterface,
        WeightGoalUserDataInterface,
        TrackedNutrientsUserDataAccessInterface {

    private final HashMap<Integer, User> accounts  = new HashMap<>();


    @Override
    public boolean existByName(String username) {
        for (Integer key: accounts.keySet()) {
            User account = accounts.get(key);
            if (account.getName().equals(username)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public User get(String username) {
        User account = null;
        for (Integer key: accounts.keySet()) {
            User value = accounts.get(key);
            String name = value.getName();
            if (name.equals(username)) {
                account = value;
            }
        }
        assert(account != null);
        return account;
    }

    @Override
    public Boolean savePreferences(int userID, HashMap<String, Boolean> dietary, HashMap<String, Boolean> allergies,
                                   HashMap<String, String> conditions) {
        User current_user = getAccountByUserID(userID);
        current_user.setDietary(dietary);
        current_user.setAllergies(allergies);
        current_user.setConditions(conditions);
        accounts.put(userID, current_user);
        return true;  // by convention, just have it return true; not recording in file
    }

    @Override
    public Boolean saveTrackedNutrientsData(ArrayList<String> trackedNutrients, int userID) {
        User currentUser = getAccountByUserID(userID);
        currentUser.setTrackedNutrients(trackedNutrients);
        accounts.put(userID, currentUser);
        return true;
    }

    @Override
    public ArrayList<String> getUserTrackedNutrientsData(int userID) {
        return accounts.get(userID).getTrackedNutrients();
    }

    @Override
    public Boolean saveWeightGoalData(int userID, HashMap<String, Boolean> gender, double height, double weight, int age, int exerciseLvl, String paceType, HashMap<String, Boolean> weightGoal) {
        User curr_user = getAccountByUserID(userID);
        curr_user.setGender(gender);
        curr_user.setUserHeight(height);
        curr_user.setUserWeight(weight);
        curr_user.setUserAge(age);
        curr_user.setUserExerciseLvl(exerciseLvl);
        curr_user.setPaceType(paceType);
        curr_user.setWeightGoalType(weightGoal);
        accounts.put(userID, curr_user);

        double requiredCalories = computedRequiredCalories(userID);
        curr_user.setRequiredCalories(requiredCalories);
        accounts.put(userID, curr_user);

        return true;
    }

    @Override
    public double computedRequiredCalories(int userID) {
        User user = getAccountByUserID(userID);
        double reqCalories = getBMR(userID);

        if (user.getWeightGoalType().equals("maintainWeight")) {
            reqCalories = reqCalories;
        } else if (user.getWeightGoalType().equals("gainWeight")) {
            String paceType = user.getPaceType();
            if (paceType.equals("normal")) {
                reqCalories = reqCalories + (3500 * 0.10); // 3500 calories is about 1 lb
            } else if (paceType.equals("fast")) {
                reqCalories = reqCalories + (3500 * 0.15);
            } else if (paceType.equals("extreme")) {
                reqCalories = reqCalories + (3500 * 0.20);
            }

        } else if (user.getWeightGoalType().equals("loseWeight")) {
            String paceType = user.getPaceType();

            if (paceType.equals("normal")) {
                reqCalories = reqCalories - (3500 * 0.10); // 3500 calories is about 1 lb
            } else if (paceType.equals("fast")) {
                reqCalories = reqCalories - (3500 * 0.15);
            } else if (paceType.equals("extreme")) {
                reqCalories = reqCalories - (3500 * 0.20);
            }
        }
        return reqCalories;
    }

    private double getBMR (int userID) {
        assert existByUserID(userID);
        double userBMR = 0;


        User user = accounts.get(userID);
        //Get BMR
        if (Boolean.valueOf(user.isMale())) {
            userBMR = (10 * user.getUserWeight()) + (6.25 * user.getUserHeight()) - (5 * user.getUserAge()) + 5;
        } else if (Boolean.valueOf(user.isFemale())) {
            userBMR = (10 * user.getUserWeight()) + (6.25 * user.getUserHeight()) - (5 * user.getUserAge()) - 161;
        }
        return getBMRAfterActivityMultiplier(userID, userBMR);
    }

    private double getBMRAfterActivityMultiplier ( int userID, double userBMR){
        User user = accounts.get(userID);
        double newUserBMR = userBMR;


        assert user.getUserExerciseLevel() >= 1 && user.getUserExerciseLevel() <= 5; // Must be in the range 1-5.

        if (user.getUserExerciseLevel() == 1) {
            newUserBMR = newUserBMR * 1.2;
        } else if (user.getUserExerciseLevel() == 2) {
            newUserBMR = newUserBMR * 1.375;
        } else if (user.getUserExerciseLevel() == 3) {
            newUserBMR = newUserBMR * 1.55;
        } else if (user.getUserExerciseLevel() == 4) {
            newUserBMR = newUserBMR * 1.725;
        } else if (user.getUserExerciseLevel() == 5) {
            newUserBMR = newUserBMR * 1.9;
        }
        return newUserBMR;
    }

    @Override
    public Boolean existByUserID(int userID) {
        return accounts.containsKey(userID);
    }

    @Override
    public Boolean saveUserSignUpData(int userID, String username, String password, LocalDateTime creationTime) {
        UserFactory userFactory = new CommonUserFactory();
        User newUser = userFactory.createdDefaultUser(userID, username);
        newUser.setPassword(password);
        newUser.setCreationTime(creationTime);

        accounts.put(userID, newUser);  // omit putting to a file

        return true;
    }

    @Override
    public int createUserID() {
        return findLastUserID() + 1;
    }

    private int findLastUserID() {
        int lastUserID = 0;

        for (Integer userID : accounts.keySet()) {
            if (userID > lastUserID) {
                lastUserID = userID;
            }
        }
        return lastUserID;
    }

    @Override
    public User getAccountByUserID(int userID) {
        return accounts.get(userID);
    }
}
