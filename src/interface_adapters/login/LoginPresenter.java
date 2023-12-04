package src.interface_adapters.login;

import src.interface_adapters.logged_in.LoggedInState;
import src.interface_adapters.logged_in.LoggedInViewModel;
import src.interface_adapters.ViewManagerModel;
import src.interface_adapters.preferences.PreferencesState;
import src.interface_adapters.preferences.PreferencesViewModel;
import src.interface_adapters.signup.SignupState;
import src.use_case.login.LoginOutputBoundary;
import src.use_case.login.LoginOutputData;
import src.use_case.signup.SignupOutputBoundary;
import src.use_case.signup.SignupOutputData;
public class LoginPresenter implements LoginOutputBoundary{
    private final LoginViewModel loginViewModel;
    private final LoggedInViewModel loggedInViewModel;
    private ViewManagerModel viewManagerModel;

    private final PreferencesViewModel preferencesViewModel;

    public LoginPresenter(ViewManagerModel viewManagerModel,
                          LoggedInViewModel loggedInViewModel,
                          LoginViewModel loginViewModel,
                          PreferencesViewModel preferencesViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.loginViewModel = loginViewModel;
        this.preferencesViewModel = preferencesViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData response) {
        // On success, switch to the logged in view.

        LoggedInState loggedInState = loggedInViewModel.getState();
        PreferencesState preferencesState = preferencesViewModel.getState();
        loggedInState.setUsername(response.getUsername());
        loggedInState.setUserID(response.getUserID());
        preferencesState.setUserID(response.getUserID());
        this.loggedInViewModel.setState(loggedInState);
        this.loggedInViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(loggedInViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        LoginState loginState = loginViewModel.getState();
        loginState.setUsernameError(error);
        loginViewModel.firePropertyChanged();
    }
}
