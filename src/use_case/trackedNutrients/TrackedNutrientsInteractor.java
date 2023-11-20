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
        
        // if user does not exist, prepare fail view
        if (!userDataAccessObject.existById(userID)) {
            trackedNutrientsPresenter.prepareFailView("UserID cannot be found.");
            
        } else {  // user exists, attempt to save data to user associated with userID
            
            // get the associated values needed from the input data object
            int userID = trackedNutrientsInputData.getUserID();
            ArrayList<String> trackedNutrients = trackedNutrientsInputData.getTrackedNutrients();

            // save method returns a boolean representing if the data was saved or not
            if (userDataAccessObject.saveTrackedNutrientsData(trackedNutrients, userID)) {
                
                // successfully saved, prepare the success view
                TrackedNutrientsOutputData trackedNutrientsOutputData = new TrackedNutrientsOutputData(userID, false);
                trackedNutrientsPresenter.prepareSuccessView(trackedNutrientsOutputData);

            } else {  // save was not successful, prepare fail view
                trackedNutrientsPresenter.prepareFailView("Error with saving tracked nutrients.");
            }
        }
    }
}
