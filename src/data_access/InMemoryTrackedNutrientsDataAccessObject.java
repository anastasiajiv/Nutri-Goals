package src.data_access;

import src.use_case.trackedNutrients.TrackedNutrientsUserDataAccessInterface;

import java.util.ArrayList;

public class InMemoryTrackedNutrientsDataAccessObject implements TrackedNutrientsUserDataAccessInterface {

    @Override
    public Boolean saveTrackedNutrientsData(ArrayList<String> trackedNutrients, int userID) {
        return null;
    }

    @Override
    public ArrayList<String> getUserTrackedNutrientsData(int userID) {
        return null;
    }

    @Override
    public Boolean existByUserID(int userID) {
        return null;
    }
}
