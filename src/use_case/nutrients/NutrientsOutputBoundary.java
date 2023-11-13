package src.use_case.nutrients;

public interface NutrientsOutputBoundary {
    void prepareSuccessView(NutrientsOutputData nutrients);
    void prepareFailView(String error);
}
