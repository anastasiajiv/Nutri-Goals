package src.data_access;

import src.entity.User;
import src.use_case.signup.SignupUserDataAccessInterface;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class InMemoryUserDataAccessObject implements SignupUserDataAccessInterface {

    private final Map<Integer, User> users = new HashMap<>();

    @Override
    public void save(User user) {
        users.put(user.getUserId(), user);
    }

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
    public User getAccountByUserId ( int userId) {
        User User = null;
        return User; // fill to stop red highlights
    }

}
