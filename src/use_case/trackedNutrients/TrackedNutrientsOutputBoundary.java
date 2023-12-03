package src.use_case.trackedNutrients;

public interface TrackedNutrientsOutputBoundary {
    void prepareSuccessView(TrackedNutrientsOutputData trackedNutrients);
    void prepareFailView(String error);
}
