package src.use_case.trackedNutrients;

public class TrackedNutrientsInteractor implements TrackedNutrientsInputBoundary {
    final TrackedNutrientsUserDataAccessInterface userDataAccessObject;  // name?
    final TrackedNutrientsOutputBoundary trackedNutrientsPresenter;

    public TrackedNutrientsInteractor(TrackedNutrientsUserDataAccessInterface userDataAccessInterface,
                                      TrackedNutrientsOutputBoundary trackedNutrientsOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.trackedNutrientsPresenter = trackedNutrientsOutputBoundary;
    }

    @Override
    public void execute(TrackedNutrientsInputData trackedNutrientsInputData) {
        // use input data to save to User's attribute
    }


}
