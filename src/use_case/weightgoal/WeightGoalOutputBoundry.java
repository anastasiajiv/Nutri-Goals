package src.use_case.weightgoal;

public interface WeightGoalOutputBoundry {
    void prepareSuccessView(WeightGoalOutputData weightGoal);
    void prepareFailView(String error);




}
