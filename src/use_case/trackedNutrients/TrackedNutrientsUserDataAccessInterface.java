package src.use_case.trackedNutrients;

import src.entity.User;

public interface TrackedNutrientsUserDataAccessInterface {
    void saveTrackedNutrientsData(ArrayList<String>);

    User getUserTrackedNutrientsData(int userID);  // may remove

    boolean existByUserID(int userID);
}