package src.interface_adapters.trackedNutrients;

import src.use_case.trackedNutrients.TrackedNutrientsInputBoundary;
import src.use_case.trackedNutrients.TrackedNutrientsInputData;

public class TrackedNutrientsController {
    private TrackedNutrientsInputBoundary trackedNutrientsInteractor;

    // default constructor
    public TrackedNutrientsController(TrackedNutrientsInputBoundary trackedNutrientsInteractor) {
        this.trackedNutrientsInteractor = trackedNutrientsInteractor;
    }

    // default execute method; taking input data to hand to the interactor
    public void execute()
}
