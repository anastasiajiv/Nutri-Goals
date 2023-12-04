package src.interface_adapters.weightgoal;

import src.use_case.weightgoal.WeightGoalInputBoundry;
import src.use_case.weightgoal.WeightGoalInputData;

import java.util.HashMap;

public class WeightGoalController {

    final WeightGoalInputBoundry weightGoalUseCaseInteractor;


    public WeightGoalController(WeightGoalInputBoundry weightGoalUseCaseInteractor) {
        this.weightGoalUseCaseInteractor = weightGoalUseCaseInteractor;
    }

    public void execute(int userID,
                        HashMap<String, Boolean> gender,
                        double height,
                        double weight,
                        int age,
                        int exerciseLvl,
                        String paceType,
                        HashMap<String, Boolean> weightGoal
                         ) { // add methods
        WeightGoalInputData weightGoalInputData = new WeightGoalInputData(userID,
                gender,
                height,
                weight,
                age,
                exerciseLvl,
                paceType,
                weightGoal);
    }
}
