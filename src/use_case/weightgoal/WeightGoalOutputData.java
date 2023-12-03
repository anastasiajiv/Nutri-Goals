package src.use_case.weightgoal;

public class WeightGoalOutputData {
    private final int userID;
    private boolean useCaseFailed;

    public WeightGoalOutputData(int userID, boolean useCaseFailed) {
        this.userID = userID;
        this.useCaseFailed = useCaseFailed;
    }

    public int getUserID() {
        return this.userID;
    }
}
