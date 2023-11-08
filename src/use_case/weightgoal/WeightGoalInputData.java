package src.use_case.weightgoal;

public class WeightGoalInputData {

    private final int userId;
    private final double userHeight;

    private final double userWeight;

    private final int userAge;

    private final int exerciseLevel;




    public WeightGoalInputData(int userId, double userHeight, double userWeight, int userAge, int exerciseLevel) {
        this.userId = userId;
        this.userHeight = userHeight;
        this.userWeight = userWeight;
        this.userAge = userAge;
        this.exerciseLevel = exerciseLevel;
    }

    int getUserId(){
        return userId;
    }

    double getUserHeight(){
        return userHeight;
    }

    double getUserWeight(){
        return userWeight;
    }

    int getUserAge(){
        return userAge;
    }

    int getExerciseLevel(){
        return exerciseLevel;
    }

}
