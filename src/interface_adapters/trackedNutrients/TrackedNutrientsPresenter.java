package src.interface_adapters.trackedNutrients;

import src.interface_adapters.ViewManagerModel;
import src.use_case.trackedNutrients.TrackedNutrientsOutputBoundary;
import src.use_case.trackedNutrients.TrackedNutrientsOutputData;
import src.interface_adapters.logged_in.LoggedInState;
import src.interface_adapters.logged_in.LoggedInViewModel;

import javax.swing.text.View;

public class TrackedNutrientsPresenter implements TrackedNutrientsOutputBoundary {
    private final TrackedNutrientsViewModel trackedNutrientsViewModel;
    private final LoggedInViewModel loggedInViewModel;
    private ViewManagerModel viewManagerModel;

    // default constructor
    public TrackedNutrientsPresenter(ViewManagerModel viewManagerModel,
                                     TrackedNutrientsViewModel trackedNutrientsViewModel,
                                     LoggedInViewModel loggedInViewModel) {
        this.trackedNutrientsViewModel = trackedNutrientsViewModel;
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
    }

    @Override
    public void prepareSuccessView(TrackedNutrientsOutputData response) {
        // fetch the logged in view model's state and set it, fire property change
        LoggedInState loggedInState = this.loggedInViewModel.getState();
        this.loggedInViewModel.setState(loggedInState);
        this.loggedInViewModel.firePropertyChanged();

        // change the current view
        this.viewManagerModel.setActiveView(this.loggedInViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        // fetch the current state and send back the error
        TrackedNutrientsState trackedNutrientsState = this.trackedNutrientsViewModel.getState();
        trackedNutrientsState.setTrackedNutrientsError(error);
        this.trackedNutrientsViewModel.firePropertyChanged();
    }
}
