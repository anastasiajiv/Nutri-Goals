package src.entity;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class CommonUser implements User{

    private final int userId;

    private HashMap<String, Boolean> restrictions = new HashMap<>();

    private final String name;

    private final String password;

    private final LocalDateTime creationTime;

    final HashMap<String, Boolean> gender;

    private final double userHeight;

    private final double userWeight;

    private final int userAge;

    private final int userExcerciseLevel;

    private final HashMap<String, Boolean> weightGoalType;

    private final int requiredCalories;

    private final CommonMealPlan mealplan;




    public CommonUser(int userId,
                      String name,
                      String password,
                      LocalDateTime creationTime,
                      HashMap<String, Boolean> gender,
                      double userHeight,
                      double userWeight,
                      int userAge,
                      int userExcerciseLevel,
                      HashMap<String, Boolean> restrictions,
                      HashMap<String, Boolean> weightGoalType,
                      int requiredCalories, CommonMealPlan mealplan) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.creationTime = creationTime;
        this.gender = gender;
        this.userHeight  = userHeight;
        this.userWeight = userWeight;
        this.userAge = userAge;
        this.userExcerciseLevel = userExcerciseLevel;
        this.restrictions = restrictions;
        this.weightGoalType = weightGoalType;
        this.requiredCalories = requiredCalories;
        this.mealplan = mealplan;

    }

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
    public int getRequiredCalories() {
        return requiredCalories;
    }

    public CommonMealPlan getmealplan(){
        return this.mealplan;
    }

}
