package src.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PreferenceInfo extends CommonUser{

    public PreferenceInfo(int userId,
                          String name,
                          String password,
                          LocalDateTime creationTime,
                          HashMap<String, Boolean> gender,
                          double userHeight,
                          double userWeight,
                          int userAge,
                          int userExcerciseLevel,
                          HashMap<String, Boolean> dietary,
                          HashMap<String, Boolean> allergies,
                          HashMap<String, String> conditions,
                          HashMap<String, Boolean> weightGoalType,
                          String paceType,
                          int requiredCalories) {
        super(userId,name,password,creationTime,gender,userHeight,userWeight, userAge, userExcerciseLevel
        dietary,allergies,conditions,weightGoalType,paceType,requiredCalories);
    }

    //this should return only one option instead of the hashmap of dietary and if the user chose them
    public String userSpecifiedDietary() {
        String user_diet = new String();
        for (Map.Entry<String, Boolean> map : dietary.entrySet()) {
            String key = map.getKey();
            Boolean value = map.getValue();
            if (value == Boolean.TRUE) {
                user_diet = key;
            }
        }
        return user_diet;
    }

    //this would return a list of all allergies the user clicked on only
    public List<String> userSpecifiedAllergies(){
        List<String> user_allergies = new ArrayList<>();
        for (Map.Entry<String, Boolean> map: allergies.entrySet()){
            String key = map.getKey();
            Boolean value = map.getValue();
            if (value == Boolean.TRUE){
                user_allergies.add(key);
            }
        }
        return user_allergies;
    }

    //return a daily intake amount depending on user selection
    public HashMap<String, Double> userSpecifiedConditions(){
        HashMap<String, Double> user_conditions = new HashMap<>();
        user_conditions.put("Calcium", getCalciumValue());
        user_conditions.put("Potassium", getPotassiumValue());
        user_conditions.put("Vitamin C", getVitaminCValue());
        user_conditions.put("Vitamin D", getVitaminDValue());
        user_conditions.put("Iron", getIronValue());
        user_conditions.put("Magnesium", getMagnesiumValue());
        user_conditions.put("Sugar", getSugarValue());
        return user_conditions;
    }


}
