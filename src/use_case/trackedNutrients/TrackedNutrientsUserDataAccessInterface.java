package src.use_case.trackedNutrients;

import src.entity.User;

public interface TrackedNutrientsUserDataAccessInterface {
    void saveTrackedNutrientsData(ArrayList<String>, int userID);

    User getUserTrackedNutrientsData(int userID);  // may remove

    boolean existByUserID(int userID);
}