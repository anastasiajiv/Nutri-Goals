package src.use_case.trackedNutrients;

import src.entity.User;

public interface TrackedNutrientsUserDataAccessInterface {
    void saveNutrientsData(User user);

    User getUserNutrientsData(int userID);

    Boolean existByUserID(int userID);
}