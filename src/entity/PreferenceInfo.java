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

    public Double getCalciumValue(){
        String value = conditions.get("Calcium");
        int age = getUserAge();
        double daily_value;

        if ((isMale().equals("true") && age > 18 && age < 71) || (isFemale().equals("true") && age > 18 && age < 51)){
            if (value.equals("low")){
                daily_value = 1000 - 0.20 * 1000;
            } else if (value.equals("average")){
                daily_value = 1000;
            } else {
                daily_value = 1000 + 0.20 * 1000;
            }
        } else if ((isMale().equals("true") && age > 70) || (isFemale().equals("true") && age > 50)){
            if (value.equals("low")){
                daily_value = 1200 - 0.20 * 1200;
            } else if (value.equals("average")){
                daily_value = 1200;
            } else {
                daily_value = 1200 + 0.20 * 1200;
            }
        } else {
            daily_value = 0.0;
            System.out.println("Could not set Calcium level.");
        }
        assert(daily_value != 0.0);
        return daily_value;
    }
    @Override
    public Double getPotassiumValue(){
        String value = conditions.get("Potassium");
        double daily_value;
        if (isMale() == "true"){
            if (value == "low"){
                daily_value = 3400 - 0.20 * 3400;
            } else if (value == "average"){
                daily_value = 3400;
            } else {
                daily_value = 3400 + 0.20 * 3400;
            }
        } else if (isFemale().equals("true")){
            if (value == "low"){
                daily_value = 2600 - 0.20 * 2600;
            } else if (value == "average"){
                daily_value = 2600;
            } else {
                daily_value = 2600 + 0.2 * 2600;
            }
        } else {
            daily_value = 0.0;
            System.out.println("Could not set Potassium level.");
        }
        assert(daily_value != 0.0);
        return daily_value;
    }

    @Override
    public Double getVitaminCValue(){
        String value = conditions.get("Vitamin C");
        double daily_value;
        if (isMale().equals("true")){
            if (value.equals("low")){
                daily_value = 90 - 0.20 * 90;
            } else if (value.equals("average")){
                daily_value = 90;
            } else {
                daily_value = 90 + 0.20 * 90;
            }
        } else if (isFemale().equals("true")){
            if (value.equals("low")){
                daily_value = 75 - 0.20 * 75;
            } else if (value.equals("average")){
                daily_value = 75;
            } else {
                daily_value = 75 + 0.20 * 75;
            }
        }else {
            daily_value = 0.0;
            System.out.println("Could not set Vitamin C level.");
        }
        assert(daily_value != 0.0);
        return daily_value;
    }

    @Override
    public Double getVitaminDValue() {
        String value = conditions.get("Vitamin D");
        double daily_value;
        int age = getUserAge();
        if ((isMale().equals("true") && age > 18 && age < 71) || (isFemale().equals("true"))){
            if (value == "low") {
                daily_value = (600 * 0.67) - 0.20 * (600 * 0.67);
            } else if (value == "average") {
                daily_value = (600 * 0.67);
            } else {
                daily_value = (600 * 0.67) + 0.20 * (600 * 0.67);
            }
        } else if ((isMale().equals("true") && age > 70)){
            if (value == "low"){
                daily_value = (800 * 0.67) - 0.20 * (800 * 0.67);
            } else if (value == "average"){
                daily_value = (800 * 0.67);
            } else {
                daily_value = (800 * 0.67) + 0.20 * (800 * 0.67);
            }
        } else {
            daily_value = 0.0;
            System.out.println("Could not set Vitamin D level.");
        }
        assert(daily_value != 0.0);
        return daily_value;
    }

    @Override
    public Double getIronValue(){
        String value = conditions.get("Iron");
        int age = getUserAge();
        double daily_value;
        if ((isMale().equals("true")) || (isFemale().equals("true") && age > 50)){
            if (value.equals("low")){
                daily_value = 8 - 0.20 * 8;
            } else if (value.equals("average")){
                daily_value = 8;
            } else {
                daily_value = 8 + 0.20 * 8;
            }
        } else if (isFemale().equals("true") && age > 18 && age < 51){
            if (value.equals("low")){
                daily_value = 18 - 0.20 * 18;
            } else if (value.equals("average")){
                daily_value = 18;
            } else {
                daily_value = 18 + 0.20 * 18;
            }
        } else {
            daily_value = 0.0;
            System.out.println("Could not set Magnesium level.");
        }
        assert(daily_value != 0.0);
        return daily_value;
    }

    @Override
    public Double getMagnesiumValue(){
        String value = conditions.get("Magnesium");
        double daily_value;
        int age = getUserAge();
        if (isMale().equals("true") && age > 18 && age< 31){
            if (value.equals("low")){
                daily_value = 400 - 0.20 * 400;
            } else if (value.equals("average")){
                daily_value = 400;
            } else {
                daily_value = 400 + 0.20 * 400;
            }
        }else if (isMale().equals("true") && age > 30){
            if (value.equals("low")){
                daily_value = 420 - 0.20 * 420;
            }else if (value.equals("average")){
                daily_value = 420;
            }else {
                daily_value = 420 + 0.20 * 420;
            }
        } else if (isFemale().equals("true") && age > 18 && age < 31){
            if (value.equals("low")){
                daily_value = 310 - 0.20 * 310;
            } else if (value.equals("average")){
                daily_value = 310;
            } else {
                daily_value = 310 + 0.20 * 310;
            }
        } else if (isFemale().equals("true") && age > 30){
            if (value.equals("low")){
                daily_value = 320 - 0.20 * 320;
            } else if (value.equals("average")){
                daily_value = 320;
            } else {
                daily_value = 320 + 0.20 * 320;
            }
        } else {
            daily_value = 0.0;
            System.out.println("Could not set Magnesium level");
        }
        assert(daily_value != 0.0);
        return daily_value;

    }

    @Override
    //sugar is measured in grams not mg
    public Double getSugarValue(){
        String value = conditions.get("Sugar");
        double daily_value;
        int age = getUserAge();
        if (isMale().equals("true") || isFemale().equals("true")){
            if (value.equals("low")){
                daily_value = 130 - 0.20 * 130;
            } else if (value.equals("average")){
                daily_value = 130;
            } else {
                daily_value = 130 + 0.20 * 130;
            }
        } else {
            daily_value = 0.0;
            System.out.println("Could not set Sugar level.");
        }
        assert(daily_value != 0.0);
        return daily_value;
    }

}
