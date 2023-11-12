package src.use_case.nutrients;

import src.entity.User;

public interface NutrientsUserDataAccessInterface {
    void saveNutrientsData(User user);

    User getUserNutrientsData(int userID);

    Boolean existByUserID(int userID);
}
