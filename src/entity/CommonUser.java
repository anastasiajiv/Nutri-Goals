package src.entity;
import java.time.LocalDateTime;
import java.util.HashMap;
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
    public HashMap<String, Boolean> getUserRestriction() {
        return restrictions; // Change to return keys with True values.
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

    @Override
    public void setRestrictions(HashMap<String, Boolean> restrictions) {
        this.restrictions = restrictions;
    }

    @Override
    public void setPaceType(String paceType) {
        this.paceType = paceType;
    }

    @Override
    public void setRequiredCalories(int requiredCalories) {
        this.requiredCalories = requiredCalories;
    }

}
