package src.use_case.signup;
import src.entity.User;

import java.time.LocalDateTime;
import java.util.HashMap;
public interface SignupUserDataAccessInterface {

    boolean existByName(String identifier);

    Boolean existByUserID(int userID);
    Boolean saveUserSignUpData(int userId,
                               String username,
                               String password,
                               LocalDateTime creationTime);
    //boolean existByName(String username);
    //void save (User user);
    public int createUserID();

    public User getAccountByUserID ( int userId);


}



