package src.use_case.signup;
import src.entity.User;

import java.util.HashMap;
public interface SignupUserDataAccessInterface {
    Boolean existByUserID(int userID);

    void save (User user);

}



