package src.app;

import src.entity.CommonUserFactory;
import src.entity.UserFactory;
import src.interface_adapters.ViewManagerModel;
import src.interface_adapters.logged_in.LoggedInViewModel;
import src.interface_adapters.login.LoginController;
import src.interface_adapters.login.LoginPresenter;
import src.interface_adapters.login.LoginViewModel;
import src.interface_adapters.mealPlan.MealPlanViewModel;
import src.interface_adapters.preferences.PreferencesViewModel;
import src.interface_adapters.trackedNutrients.TrackedNutrientsViewModel;
import src.interface_adapters.weightgoal.WeightGoalViewModel;
import src.use_case.login.LoginInputBoundary;
import src.use_case.login.LoginOutputBoundary;
import src.use_case.login.LoginInteractor;
import src.use_case.login.LoginUserDataAccessInterface;
import src.view.LoginView;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class LoginUseCaseFactory {
    private LoginUseCaseFactory() {}

    public static LoginView create(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            LoggedInViewModel loggedInViewModel,
            PreferencesViewModel preferencesViewModel,
            WeightGoalViewModel weightGoalViewModel,
            TrackedNutrientsViewModel trackedNutrientsViewModel,
            MealPlanViewModel mealPlanViewModel,
            LoginUserDataAccessInterface userDataAccessObject) {

        try {
            LoginController loginController = createLoginUseCase(viewManagerModel, loginViewModel, loggedInViewModel,
                    preferencesViewModel, weightGoalViewModel, trackedNutrientsViewModel, mealPlanViewModel, userDataAccessObject);
            return new LoginView(loginViewModel, loginController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static LoginController createLoginUseCase(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            LoggedInViewModel loggedInViewModel,
            PreferencesViewModel preferencesViewModel,
            WeightGoalViewModel weightGoalViewModel,
            TrackedNutrientsViewModel trackedNutrientsViewModel,
            MealPlanViewModel mealPlanViewModel,
            LoginUserDataAccessInterface userDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel,
                loggedInViewModel,
                loginViewModel,
                preferencesViewModel,
                weightGoalViewModel,
                trackedNutrientsViewModel,
                mealPlanViewModel
                );


        UserFactory userFactory = new CommonUserFactory();

        LoginInputBoundary loginInteractor = new LoginInteractor(
                userDataAccessObject, loginOutputBoundary);

        return new LoginController(loginInteractor);
    }
}
