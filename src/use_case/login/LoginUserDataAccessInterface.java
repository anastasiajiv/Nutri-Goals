package src.use_case.login;
import src.entity.User;

public interface LoginUserDataAccessInterface {
    boolean existByName(String identifier);
    Boolean existByUserID(int userID);

    void save(User user);

    User get(String username);
}
