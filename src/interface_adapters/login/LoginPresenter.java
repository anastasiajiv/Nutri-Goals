package src.interface_adapters.login;

import src.interface_adapters.logged_in.LoggedInState;
import src.interface_adapters.logged_in.LoggedInViewModel;
import src.interface_adapters.ViewManagerModel;
import src.interface_adapters.mealplan.MealPlanState;
import src.interface_adapters.mealplan.MealPlanViewModel;
import src.interface_adapters.preferences.PreferencesState;
import src.interface_adapters.preferences.PreferencesViewModel;
import src.interface_adapters.trackedNutrients.TrackedNutrientsState;
import src.interface_adapters.trackedNutrients.TrackedNutrientsViewModel;
import src.interface_adapters.weightgoal.WeightGoalState;
import src.interface_adapters.weightgoal.WeightGoalViewModel;
import src.use_case.login.LoginOutputBoundary;
import src.use_case.login.LoginOutputData;

public class LoginPresenter implements LoginOutputBoundary{
    private final LoginViewModel loginViewModel;
    private final LoggedInViewModel loggedInViewModel;
    private ViewManagerModel viewManagerModel;

    private final PreferencesViewModel preferencesViewModel;
    private final TrackedNutrientsViewModel trackedNutrientsViewModel;


    private final WeightGoalViewModel weightGoalViewModel; // View model for my weight goal 🙏🏽

    private final MealPlanViewModel mealPlanViewModel;


    public LoginPresenter(ViewManagerModel viewManagerModel,
                          LoggedInViewModel loggedInViewModel,
                          LoginViewModel loginViewModel,
                          PreferencesViewModel preferencesViewModel,
                          WeightGoalViewModel weightGoalViewModel,
                          TrackedNutrientsViewModel trackedNutrientsViewModel, MealPlanViewModel mealPlanViewModel) {

        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.loginViewModel = loginViewModel;
        this.preferencesViewModel = preferencesViewModel;
        this.trackedNutrientsViewModel = trackedNutrientsViewModel;
        this.weightGoalViewModel = weightGoalViewModel;
        this.mealPlanViewModel = mealPlanViewModel;

    }

    @Override
    public void prepareSuccessView(LoginOutputData response) {
        // On success, switch to the logged in view.

        LoggedInState loggedInState = loggedInViewModel.getState();
        PreferencesState preferencesState = preferencesViewModel.getState();
        WeightGoalState weightGoalState = weightGoalViewModel.getState();
        TrackedNutrientsState trackedNutrientsState = trackedNutrientsViewModel.getState();
        MealPlanState mealPlanState = mealPlanViewModel.getState();
        loggedInState.setUsername(response.getUsername());
        loggedInState.setUserID(response.getUserID());
        preferencesState.setUserID(response.getUserID());
        weightGoalState.setUserId(response.getUserID());
        trackedNutrientsState.setUserID(response.getUserID());
        mealPlanState.setId(response.getUserID());
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
