package src.use_case.weightgoal;

import java.util.HashMap;
import java.util.Map;

public class WeightGoalInputData {
    // required input from the GUI is gender, height, weight, age, exercise lvl, weightGoal, paceType, and
    // reqCalories attributes
    private final int userId;

    private HashMap<String, Boolean> gender;

    private double height;

    private double weight;

    private int age;

    private int exerciseLvl;

    private String paceType;



    private final HashMap<String, Boolean> weightGoal; // Hashmap<maintainWeight, False>  for all weightGoalTypes




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

    HashMap<String, Boolean> getGender() {
        return this.gender;
    }

    double getWeight() {
       return this.weight;
    }


    HashMap<Integer, HashMap<String, Boolean>> getWeightGoalLol() {
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

    HashMap<String, Boolean> getWeightGoal(){
       return this.weightGoal;
    }


    int getUserId(){
        return this.userId;
    }

}
