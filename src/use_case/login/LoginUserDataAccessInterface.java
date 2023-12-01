package src.use_case.login;


import src.entity.User;

public interface LoginUserDataAccessInterface {

    Boolean existByUserID(int userID);

    public User getAccountByUserId (int userId);
}
