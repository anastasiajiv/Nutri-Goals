package src.use_case.trackedNutrients;

public class TrackedNutrientsOutputData {
    
    private final int userID;

    public TrackedNutrientsOutputData(int userID) {
        this.userID = userID;
    }

    public int getUserID() {
        return this.userID;
    }

}
