package src.use_case.trackedNutrients;

import java.util.ArrayList;

public class TrackedNutrientsInteractor implements TrackedNutrientsInputBoundary {
    
    final TrackedNutrientsUserDataAccessInterface userDataAccessObject;
    final TrackedNutrientsOutputBoundary trackedNutrientsPresenter;

    public TrackedNutrientsInteractor(TrackedNutrientsUserDataAccessInterface userDataAccessInterface,
                                      TrackedNutrientsOutputBoundary trackedNutrientsOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.trackedNutrientsPresenter = trackedNutrientsOutputBoundary;
    }

    // attempts to save the user-input nutrients to be tracked to the csv
    @Override
    public void execute(TrackedNutrientsInputData trackedNutrientsInputData) {
        // get the associated values needed from the input data object
        int userID = trackedNutrientsInputData.getUserID();
        ArrayList<String> trackedNutrients = trackedNutrientsInputData.getTrackedNutrients();
        
        // if user does not exist, prepare fail view
        if (!userDataAccessObject.existByUserID(userID)) {
            trackedNutrientsPresenter.prepareFailView("UserID cannot be found.");
            
        } else {  // user exists, attempt to save data to user associated with userID

            // successfully saved, prepare the success view
            userDataAccessObject.saveTrackedNutrientsData(trackedNutrients, userID);

            TrackedNutrientsOutputData trackedNutrientsOutputData = new TrackedNutrientsOutputData(userID);
            trackedNutrientsPresenter.prepareSuccessView(trackedNutrientsOutputData);
        }
    }
}
