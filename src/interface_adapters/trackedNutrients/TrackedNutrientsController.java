package src.interface_adapters.trackedNutrients;

import src.use_case.trackedNutrients.TrackedNutrientsInputBoundary;
import src.use_case.trackedNutrients.TrackedNutrientsInputData;

import java.util.ArrayList;

public class TrackedNutrientsController {
    private TrackedNutrientsInputBoundary trackedNutrientsInteractor;

    // default constructor
    public TrackedNutrientsController(TrackedNutrientsInputBoundary trackedNutrientsInteractor) {
        this.trackedNutrientsInteractor = trackedNutrientsInteractor;
    }

    // default execute method; taking input data to hand to the interactor
    public void execute(int userID, ArrayList<String> trackedNutrients) {
        // create a new input data and hand it off to the interactor
        TrackedNutrientsInputData trackedNutrientsInputData = new TrackedNutrientsInputData(userID, trackedNutrients);
        this.trackedNutrientsInteractor.execute(trackedNutrientsInputData);
    }
}
