package src.app;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import src.interface_adapters.login.LoginViewModel;
import src.interface_adapters.signup.SignupController;
import src.interface_adapters.signup.SignupPresenter;
import src.interface_adapters.signup.SignupViewModel;
import src.use_case.signup.SignupUserDataAccessInterface;
import src.entity.CommonUserFactory;
import src.entity.UserFactory;
import src.interface_adapters.*;
import src.use_case.signup.SignupInputBoundary;
import src.use_case.signup.SignupInteractor;
import src.use_case.signup.SignupOutputBoundary;
import src.view.SignupView;
public class SignupUseCaseFactory {

    private SignupUseCaseFactory(){}

    public static SignupView create(
            ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, SignupViewModel signupViewModel,
            SignupUserDataAccessInterface userDataAccessObject) {

        try {
            SignupController signupController = createUserSignupUseCase(viewManagerModel, signupViewModel, loginViewModel, userDataAccessObject);
            return new SignupView(signupController, signupViewModel);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }
    private static SignupController createUserSignupUseCase(ViewManagerModel viewManagerModel, SignupViewModel signupViewModel, LoginViewModel loginViewModel, SignupUserDataAccessInterface userDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel, signupViewModel, loginViewModel);

        UserFactory userFactory = new CommonUserFactory();

        SignupInputBoundary userSignupInteractor = new SignupInteractor(
                userDataAccessObject, signupOutputBoundary, userFactory);

        return new SignupController(userSignupInteractor);
    }

}
