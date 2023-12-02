package src.use_case.weightgoal;

import java.util.HashMap;

public class WeightGoalOutputData {
    private final int userID;

    private HashMap<String, Boolean> gender;

    private double height;

    private double weight;

    private int age;

    private int exerciseLvl;

    private String paceType;

    private HashMap<String, Boolean> weightGoal;
    private boolean useCaseFailed;



    public WeightGoalOutputData(int userID,HashMap<String, Boolean> gender,
                                double height,
                                double weight,
                                int age,
                                int exerciseLvl,
                                String paceType,
                                HashMap<String, Boolean> weightGoal,
                                boolean useCaseFailed) {
        this.userID = userID;
        this.useCaseFailed = useCaseFailed;
    }

    public int getUserID() {
        return this.userID;
    }
}
