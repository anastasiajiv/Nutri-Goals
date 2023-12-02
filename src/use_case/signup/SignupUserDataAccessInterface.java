package src.use_case.signup;
import src.entity.User;

import java.time.LocalDateTime;
import java.util.HashMap;
public interface SignupUserDataAccessInterface {


    Boolean existByUserID(int userID);

    Boolean saveUserSignUpData(int userId,
                            String username,
                            String password,
                            LocalDateTime creationTime);

    public int createUserID();

    public User getAccountByUserId ( int userId);


}



