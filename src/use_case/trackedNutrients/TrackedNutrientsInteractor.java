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

    @Override
    public void execute(TrackedNutrientsInputData trackedNutrientsInputData) {
        // get the associated values needed from the input data object
        int userID = trackedNutrientsInputData.getUserID();
        ArrayList<String> trackedNutrients = trackedNutrientsInputData.getTrackedNutrients();

        // if the user does not exist
        if (!userDataAccessObject.existByUserID(userID)) {
            // call the presenter to fail view, most likely will never happen
            // trackedNutrientsPresenter.prepareFailView("User cannot be found");
        } else {  // user exists

        }
    }


}
