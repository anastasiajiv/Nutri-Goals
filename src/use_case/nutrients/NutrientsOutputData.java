package src.use_case.nutrients;

public class NutrientsOutputData {
    private final int userID;
    private boolean useCaseFailed;

    public NutrientsOutputData(int userID, boolean useCaseFailed) {
        this.userID = userID;
        this.useCaseFailed = useCaseFailed;
    }

    public int getUserID() {
        return this.userID;
    }

}
