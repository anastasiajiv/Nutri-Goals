package src.interface_adapters.weightgoal;

import java.util.HashMap;

public class WeightGoalState {

    private int userID = 0; // We should be retrieving this userID from the view
                            // (preferably not as a user input) - method should be used in view and state

    private HashMap<String, Boolean> gender = new HashMap<>();

    private String isMale = null;

    private String isFemale = null;

    private double height = 0.0;

    private double weight = 0.0;

    private int age = 0;

    private int exerciseLvl = 0;

    private String paceType = null;

    private String weightGoalError = null;

    private HashMap<String, Boolean> weightGoal = new HashMap<>();

    public WeightGoalState(WeightGoalState copy) {
            userID = copy.userID;
            gender = copy.gender;
            isMale = copy.isMale;
            isFemale = copy.isFemale;
            height = copy.height;
            weight = copy.weight;
            age = copy.age;
            exerciseLvl = copy.exerciseLvl;
            paceType = copy.paceType;
            weightGoalError = copy.weightGoalError;
            weightGoal = copy.weightGoal;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.

    //Getter methods
    public WeightGoalState(){}

    public int getUserId() {return userID;}

    public HashMap<String, Boolean> getGender() {
        return gender;
    }

    public String getIsMale() {
        if (gender.get("male") != null) {
            return String.valueOf(gender.get("male"));
        }
        return String.valueOf(Boolean.FALSE);
    }

    public String getIsFemale() {
        if (gender.get("female") != null) {
            return String.valueOf(gender.get("female"));
        }
        return String.valueOf(Boolean.FALSE);
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }
    public int getAge() {
        return age;
    }

    public int getExerciseLvl() {
        return exerciseLvl;
    }

    public String getPaceType() {
        return paceType;
    }

    public String getWeightGoalError() { // Testing prepareFailView param, but this should be changed in the future
                                        // Need this as a string because the prepareFailView takes in a String
        return weightGoalError;
    }

    public HashMap<String, Boolean> getWeightGoal() {
        return weightGoal;
    }





    //Setter methods
    public void setUserId(int userID) {
        this.userID = userID;
    }

    public void setGender(HashMap<String, Boolean> gender) {
        this.gender = gender;
    }

    public void setHeight(double height){
        this.height = height;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setExerciseLvl(int exerciseLvl) {
        this.exerciseLvl = exerciseLvl;
    }

    public void setPaceType(String paceType){
        this.paceType = paceType;
    }

    public void setWeightGoal(HashMap<String, Boolean> weightGoal) {
        this.weightGoal = weightGoal;
    }

    public void setWeightGoalError(String weightGoalError) {
        this.weightGoalError = weightGoalError;
    }

    public void setIsMale(String isMale) {this.isMale = isMale;}

    public void setIsFemale(String isFemale) {this.isFemale = isFemale;}




}
