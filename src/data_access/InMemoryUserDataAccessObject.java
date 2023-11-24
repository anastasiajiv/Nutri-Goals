package src.data_access;

import src.entity.User;
import src.use_case.signup.SignupUserDataAccessInterface;

import java.util.HashMap;
import java.util.Map;

public class InMemoryUserDataAccessObject implements SignupUserDataAccessInterface {

    private final Map<Integer, User> users = new HashMap<>();
    @Override
    public Boolean existByUserID(int userID) {
        return users.containsKey(userID);
    }

    @Override
    public void save(User user) {
        users.put(user.getUserId(), user);
    }
}
