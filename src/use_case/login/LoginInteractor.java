package src.use_case.login;

import src.entity.User;

public class LoginInteractor implements LoginInputBoundary{
    final LoginUserDataAccessInterface userDataAccessObject;
    final LoginOutputBoundary loginPresenter;

    public LoginInteractor(LoginUserDataAccessInterface userDataAccessInterface,
                           LoginOutputBoundary loginOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.loginPresenter = loginOutputBoundary;
    }

    @Override
    public void execute(LoginInputData loginInputData) {
        int userID = loginInputData.getUserID();
        String username = loginInputData.getUsername();
        String password = loginInputData.getPassword();
        if (!userDataAccessObject.existByUserID(userID)) {
            loginPresenter.prepareFailView(username + ": Account does not exist" + "with the userID" + userID);
        } else {
            String pwd = userDataAccessObject.getAccountByUserId(userID).getPassword(); // At this point userID -> creationTime should already be saved in the accounts map.
            if (!password.equals(pwd)) {
                loginPresenter.prepareFailView("Incorrect password for " + username + ".");
            } else {

                User user = userDataAccessObject.getAccountByUserId(loginInputData.getUserID());

                LoginOutputData loginOutputData = new LoginOutputData(user.getUserId(), user.getName(), false);
                loginPresenter.prepareSuccessView(loginOutputData);
            }
        }
    }


}