package src.interface_adapters.login;

import src.use_case.login.LoginInputData;
import src.use_case.login.LoginInputBoundary;
import src.use_case.signup.SignupInputBoundary;
import src.use_case.signup.SignupInputData;
public class LoginController {
    final LoginInputBoundary loginUseCaseInteractor;
    public LoginController(LoginInputBoundary loginUseCaseInteractor) {
        this.loginUseCaseInteractor = loginUseCaseInteractor;
    }

    public void execute(String username, String password) {
        LoginInputData loginInputData = new LoginInputData(username, password);

        loginUseCaseInteractor.execute(loginInputData);
    }
}
