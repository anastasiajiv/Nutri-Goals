package src.use_case.trackedNutrients;

import src.entity.User;

public interface TrackedNutrientsUserDataAccessInterface {
    void saveTrackedNutrientsData(User user);

    User getUserTrackedNutrientsData(int userID);

    Boolean existByUserID(int userID);
}