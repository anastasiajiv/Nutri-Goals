package src.use_case.signup;
import src.entity.User;

import java.util.HashMap;
public interface SignupUserDataAccessInterface {
    boolean existById(int userId);

    void save (User user);

    Boolean existByUserId(int userId);

}



