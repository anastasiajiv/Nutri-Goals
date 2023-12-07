package src.data_access;

import src.entity.User;
import src.use_case.trackedNutrients.TrackedNutrientsUserDataAccessInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InMemoryTrackedNutrientsDataAccessObject implements TrackedNutrientsUserDataAccessInterface {

   public HashMap<Integer, User> users = new HashMap<>();

    @Override
    public Boolean saveTrackedNutrientsData(ArrayList<String> trackedNutrients, int userID) {
        return true;
    }

    @Override
    public ArrayList<String> getUserTrackedNutrientsData(int userID) {
        return users.get(userID).getTrackedNutrients();
    }

    @Override
    public Boolean existByUserID(int userID) {
        return users.containsKey(userID);
    }

    @Override
    public User getAccountByUserID(int userID) {
        return users.get(userID);
    }

}

