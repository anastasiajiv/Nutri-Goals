package src.use_case.trackedNutrients;

public class TrackedNutrientsOutputData {
    
    private final int userID;
    private boolean useCaseFailed;

    public TrackedNutrientsOutputData(int userID, boolean useCaseFailed) {
        this.userID = userID;
        this.useCaseFailed = useCaseFailed;
    }

    public int getUserID() {
        return this.userID;
    }

}
