package src.entity;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonUser implements User{

    private final int userId;

    //private HashMap<String, Boolean> restrictions = new HashMap<>();
    private HashMap<String, Boolean> dietary = new HashMap<>();

    private HashMap<String, Boolean> allergies = new HashMap<>();

    private HashMap<String, String> conditions = new HashMap<>();
    private final String name;

    private String password;

    private LocalDateTime creationTime;

    HashMap<String, Boolean> gender; // remove final since they are subject to change.
    //Not sure if this is a good design decision

    private double userHeight;

    private double userWeight;

    private int userAge;

    private int userExcerciseLevel;

    private HashMap<String, Boolean> weightGoalType;

    private String paceType;

    private int requiredCalories;




    public CommonUser(int userId,
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
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.creationTime = creationTime;
        this.gender = gender;
        this.userHeight  = userHeight;
        this.userWeight = userWeight;
        this.userAge = userAge;
        this.userExcerciseLevel = userExcerciseLevel;
        this.dietary = dietary;
        this.allergies = allergies;
        this.conditions = conditions;
        this.weightGoalType = weightGoalType;
        this.paceType = paceType;
        this.requiredCalories = requiredCalories;

    }

//
//    public CommonUser(int userId, String name) {
//        this();  // Call the default constructor to set default values
//        this.userId = userId;
//        this.name = name;
//    }
//
//    // Constructor with full attribute set
//    public CommonUser(int userId, String name, String password, LocalDateTime creationTime,
//                      HashMap<String, Boolean> gender, double userHeight, double userWeight,
//                      int userAge, int userExcerciseLevel, HashMap<String, Boolean> restrictions,
//                      HashMap<String, Boolean> weightGoalType, int requiredCalories) {
//        this.userId = userId;
//        this.name = name;
//        this.password = password;
//        this.creationTime = creationTime;
//        this.gender = gender;
//        this.userHeight = userHeight;
//        this.userWeight = userWeight;
//        this.userAge = userAge;
//        this.userExcerciseLevel = userExerciseLevel;
//        this.restrictions = restrictions;
//        this.weightGoalType = weightGoalType;
//        this.requiredCalories = requiredCalories;
//
//   }
//


    @Override
    public int getUserId() {
        return userId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public LocalDateTime getCreationTime() {
        return creationTime;
    }
    @Override
    public HashMap<String, Boolean> getGender() {
        return this.gender;
    }

    public String getGenderType() {
        for (Map.Entry<String, Boolean> entry: gender.entrySet()) {
            if (entry.getValue() == Boolean.TRUE);
            return entry.getKey();
        }
        return "User did not input Gender";
    }

    public String isMale() {
        if (gender.get("male") != null) {
            return String.valueOf(gender.get("male"));
        }
        return String.valueOf(Boolean.FALSE);
    }

    public String isFemale() {
        if (gender.get("female") != null){
            return String.valueOf(gender.get("female"));
        }
        return String.valueOf(Boolean.FALSE);
    }
    @Override
    public double getUserHeight() {
        return userHeight;
    }

    @Override
    public double getUserWeight() {
        return userWeight;
    }

    @Override
    public int getUserAge() {
        return userAge;
    }

    @Override
    public int getUserExcerciseLevel() {
        return userExcerciseLevel;
    }

    @Override
    public void setDietary() {

    }

    //@Override
    //public HashMap<String, Boolean> getUserRestriction() {
    //    return restrictions; // Change to return keys with True values.
    //}
    public void setDietary(HashMap<String, Boolean> dietary){
        this.dietary = dietary;
    }
    @Override
    public String getDietary() {
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

    @Override
    public void setAllergies() {

    }

    public void setAllergies(HashMap<String, Boolean> allergies){
        this.allergies = allergies;
    }

    @Override
    public List<String> getAllergies(){
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

    @Override
    public void setConditions() {

    }

    public void setConditions(HashMap<String, String> conditions){
        this.conditions = conditions;
    }
    @Override
    public HashMap<String, Double> getConditions(){
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

    @Override
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
    @Override
    public HashMap<String, Boolean> WeightGoalType() {
        return weightGoalType;
    }

    @Override
    public String getWeightGoalType() {
        for (Map.Entry<String, Boolean> entry: weightGoalType.entrySet()) {
            if (entry.getValue() == Boolean.TRUE){
                return entry.getKey();
            }
        }
        return "WeightGoalType not established";
    }

    public String getMaintainTypeValue() {
        if (weightGoalType.get("maintainWeight") != null){
            return String.valueOf(weightGoalType.get("maintainWeight"));
        }
        else return String.valueOf(Boolean.FALSE);
    }

    public String getLoseTypeValue() {
        if (weightGoalType.get("loseWeight") != null) {
            return String.valueOf(weightGoalType.get("loseWeight"));
        }
        else return String.valueOf(Boolean.FALSE);
    }

    public String getGainTypeValue() {
        if (weightGoalType.get("gainWeight") != null) {
            return String.valueOf(weightGoalType.get("gainWeight"));
        }
        else return String.valueOf(Boolean.FALSE);
    }
    @Override
    public String getPaceType() {
        return paceType;
    }



    public int getRequiredCalories() {
        return requiredCalories;
    }


    //SETTERS -> These will allow us to update user information after creating a default user.
    @Override
    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }


    @Override
    public void setGender(HashMap<String, Boolean> gender) {
        this.gender = gender;
    }
    @Override
    public void setUserHeight(double userheight) {
        this.userHeight = userheight;
    }

    @Override
    public void setUserWeight(double userWeight) {
        this.userWeight = userWeight;
    }

    @Override
    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }
    @Override
    public void setUserExerciseLvl(int userExerciseLvl) {
        this.userExcerciseLevel = userExerciseLvl;
    }

    @Override
    public void setWeightGoalType(HashMap<String, Boolean> weightGoalType) {
        this.weightGoalType = weightGoalType;
    }

    //@Override
    //public void setRestrictions(HashMap<String, Boolean> restrictions) {
    //    this.restrictions = restrictions;
    //}

    @Override
    public void setPaceType(String paceType) {
        this.paceType = paceType;
    }

    @Override
    public void setRequiredCalories(int requiredCalories) {
        this.requiredCalories = requiredCalories;
    }

}
