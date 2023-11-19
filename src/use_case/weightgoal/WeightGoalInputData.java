package src.use_case.weightgoal;

import java.util.HashMap;
import java.util.Map;

import src.entity.User;
import src.entity.CommonUser;
public class WeightGoalInputData {

    private HashMap<Integer, String> weightGoalType;


    public WeightGoalInputData(HashMap<Integer, String> weightGoalType){
        this.weightGoalType = weightGoalType;
    }

    String getWeightGoalType(){
        String strWeightGoalType = "";
        for (Map.Entry<Integer, String> entry: weightGoalType.entrySet()) { // Should only be 1 loop
            strWeightGoalType = entry.getValue();
        }
        return strWeightGoalType;

    }

    int getUserId(){
        int userId = 0;
        for (Map.Entry<Integer, String> entry: weightGoalType.entrySet()) { // Should only be 1 loop
            userId = entry.getKey();
        }
        return userId;
    }
}
