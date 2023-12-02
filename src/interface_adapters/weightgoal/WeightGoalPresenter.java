package src.interface_adapters.weightgoal;

import src.use_case.weightgoal.WeightGoalOutputBoundry;
import src.use_case.weightgoal.WeightGoalOutputData;

public class WeightGoalPresenter implements WeightGoalOutputBoundry {

    private final WeightGoalViewModel weightGoalViewModel; // currently we are on the weightGoal view

    private final LoggedInViewModel loggedInViewModel;

    private ViewManagerModel viewManagerModel;

    public WeightGoalPresenter(ViewManagerModel viewManagerModel,
                               LoggedInViewModel loggedInViewModel,
                               WeightGoalViewModel weightGoalViewModel) {
        this.viewManagerModel = viewManagerModel; // class to handle switching viewmodels?
        this.loggedInViewModel = loggedInViewModel;
        this.weightGoalViewModel = weightGoalViewModel;
    }


    @Override
    public void prepareSuccessView(WeightGoalOutputData weightGoal) {
        // On success, after the user is done inputs it will take them back to the
        // loggedInView where the buttons for the other use cases will be present

        
    }

    @Override
    public void prepareFailView(String error) {

    }
}
