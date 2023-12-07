package src.use_case.trackedNutrients;

import src.entity.User;

import java.util.ArrayList;

public interface TrackedNutrientsUserDataAccessInterface {

    Boolean saveTrackedNutrientsData(ArrayList<String> trackedNutrients, int userID);

    ArrayList<String> getUserTrackedNutrientsData(int userID);

    Boolean existByUserID(int userID);
    User getAccountByUserID(int userID);
}