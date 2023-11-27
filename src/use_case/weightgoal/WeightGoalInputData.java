package src.use_case.weightgoal;

import java.util.HashMap;
import java.util.Map;

public class WeightGoalInputData {
    // required input from the GUI is gender, height, weight, age, exercise lvl, weightGoal, paceType, and
    // reqCalories attributes
    private final int userId;

    private HashMap<String, Boolean> gender;

    private double height;

    private int age;

    private int exerciseLvl;

    private String paceType;



    private HashMap<String, Boolean> weightGoal; // Hashmap<maintainWeight, False>  for all weightGoalTypes




    public WeightGoalInputData(int userId,
                               HashMap<String, Boolean> gender,
                               double height,
                               double weight,
                               int age,
                               int exerciseLvl,
                               String paceType,
                               HashMap<String, Boolean> weightGoal){
        this.userId = userId;
        this.weightGoal = weightGoal;
    }

    HashMap<Integer, HashMap<String, Boolean>> getWeightGoal() {
        HashMap<Integer, HashMap<String, Boolean>> map = new HashMap<>();
        map.put(this.userId, this.weightGoal);
        return map;
    }

    double getHeight() {
        return this.height;
    }

    int getAge() {
        return this.age;
    }

    int getExerciseLvl() {
        return this.exerciseLvl;
    }

    String getPaceType() {
        return this.paceType;
    }

//    String getWeightGoalType(){
//        String strWeightGoalType = "";
//        for (Map.Entry<Integer, String> entry: weightGoalType.entrySet()) { // Should only be 1 loop
//            strWeightGoalType = entry.getValue();
//        }
//        return strWeightGoalType;
//
//    }


    int getUserId(){
        return this.userId;
    }

}
