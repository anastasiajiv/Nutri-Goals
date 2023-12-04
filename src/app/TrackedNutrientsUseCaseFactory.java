package src.app;

import src.interface_adapters.ViewManagerModel;
import src.interface_adapters.logged_in.LoggedInViewModel;
import src.interface_adapters.trackedNutrients.TrackedNutrientsController;
import src.interface_adapters.trackedNutrients.TrackedNutrientsPresenter;
import src.interface_adapters.trackedNutrients.TrackedNutrientsViewModel;
import src.use_case.trackedNutrients.TrackedNutrientsInputBoundary;
import src.use_case.trackedNutrients.TrackedNutrientsInteractor;
import src.use_case.trackedNutrients.TrackedNutrientsOutputBoundary;
import src.use_case.trackedNutrients.TrackedNutrientsUserDataAccessInterface;
import src.view.TrackedNutrientsView;

import javax.swing.*;
import java.io.IOException;

public class TrackedNutrientsUseCaseFactory {

    // default constructor; never directly instantiated
    private TrackedNutrientsUseCaseFactory() {}

    // create instances of TrackedNutrientsView
    public static TrackedNutrientsView create(
            ViewManagerModel viewManagerModel,
            TrackedNutrientsViewModel trackedNutrientsViewModel,
            LoggedInViewModel loggedInViewModel,
            TrackedNutrientsUserDataAccessInterface trackedNutrientsUserDataAccessInterface) {
        // create a new view by initializing a controller
        try {
            TrackedNutrientsController trackedNutrientsController = createTrackedNutrientsUseCase(viewManagerModel,
                    loggedInViewModel, trackedNutrientsViewModel, trackedNutrientsUserDataAccessInterface);
            return new TrackedNutrientsView(trackedNutrientsController, trackedNutrientsViewModel);
        } catch (IOException e_) {
            JOptionPane.showMessageDialog(null, "Could not open user data file");
        }
        return null;
    }

    // helper method for creating a controller
    private static TrackedNutrientsController createTrackedNutrientsUseCase(
            ViewManagerModel viewManagerModel,
            LoggedInViewModel loggedInViewModel,
            TrackedNutrientsViewModel trackedNutrientsViewModel,
            TrackedNutrientsUserDataAccessInterface trackedNutrientsUserDataAccessInterface) throws IOException {
        // create a new presenter to hand back to the controller initialization
        TrackedNutrientsOutputBoundary trackedNutrientsOutputBoundary = new TrackedNutrientsPresenter(viewManagerModel,
                trackedNutrientsViewModel, loggedInViewModel);
        // create a new interactor
        TrackedNutrientsInputBoundary trackedNutrientsInteractor = new TrackedNutrientsInteractor(
                trackedNutrientsUserDataAccessInterface, trackedNutrientsOutputBoundary);
        // return a new controller
        return new TrackedNutrientsController(trackedNutrientsInteractor);
    }
}
