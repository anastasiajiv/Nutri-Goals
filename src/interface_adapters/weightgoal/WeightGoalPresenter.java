package src.interface_adapters.weightgoal;

import src.interface_adapters.ViewManagerModel;
import src.interface_adapters.logged_in.LoggedInState;
import src.interface_adapters.logged_in.LoggedInViewModel;
import src.use_case.weightgoal.WeightGoalOutputBoundry;
import src.use_case.weightgoal.WeightGoalOutputData;

// TODO: Add the View files and SignUp, LogIn, LoggedIn interface adapters to test this part of the UI

public class WeightGoalPresenter implements WeightGoalOutputBoundry {

    private final WeightGoalViewModel weightGoalViewModel; // currently we are on the weightGoal view

    private final LoggedInViewModel loggedInViewModel; // Need to add this class to file path

    private ViewManagerModel viewManagerModel;// Need to add this class to file path

    public WeightGoalPresenter(ViewManagerModel viewManagerModel,// Need to add this class to file path
                               LoggedInViewModel loggedInViewModel,// Need to add this class to file path
                               WeightGoalViewModel weightGoalViewModel) {
        this.viewManagerModel = viewManagerModel; // class to handle switching viewmodels?
        this.loggedInViewModel = loggedInViewModel;
        this.weightGoalViewModel = weightGoalViewModel;
    }


    @Override
    public void prepareSuccessView(WeightGoalOutputData response) {
        // On success, after the user is done inputs it will take them back to the
        // loggedInView where the buttons for the other use cases will be present

        LoggedInState loggedInState = loggedInViewModel.getState();// Need to add this class to file path
        loggedInState.setUserID(response.getUserID()); // keep a track of the user who is currently logged in by userID // Need to add this class to file path
        this.loggedInViewModel.setState(loggedInState);
        this.loggedInViewModel.firePropertyChanged();// Need to add this class to file path

        this.viewManagerModel.setActiveView(loggedInViewModel.getViewName());// Need to add this class to file path
        this.viewManagerModel.firePropertyChanged();// Need to add this class to file path
        
    }


    @Override
    public void prepareFailView(String error) {
        WeightGoalState weightGoalState = weightGoalViewModel.getState();
        weightGoalState.setWeightGoalError(error);
        weightGoalViewModel.firePropertyChanged();
    }
}
