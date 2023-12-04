package src.data_access;

import src.entity.User;
import src.use_case.signup.SignupUserDataAccessInterface;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import java.time.LocalDateTime;
public class InMemoryUserDataAccessObject implements SignupUserDataAccessInterface {

    private final Map<Integer, User> users = new HashMap<>();


    @Override
    public Boolean existByUserID(int userId) {
        return null;
    }

    @Override
    public Boolean saveUserSignUpData(int userId, String username, String password, LocalDateTime creationTime) {
        return Boolean.TRUE;
    }

    public int createUserID(){
        return 1;
    }

    @Override
    public User getAccountByUserID(int userId) {
        return null;
    }

    public User getAccountByUserId ( int userId) {
        User User = null;
        return User; // fill to stop red highlights
    }


}
