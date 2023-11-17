package src.use_case.trackedNutrients;

public interface TrackedNutrientsOutputBoundary {
    void prepareSuccessView(TrackedNutrientsOutputData nutrients);
    void prepareFailView(String error);
}
