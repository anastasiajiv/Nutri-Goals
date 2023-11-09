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

    private final double userHeight;

    private final double userWeight;

    private final int userAge;

    private final int userExcerciseLevel;

    private final HashMap<String, Boolean> weightGoalType;


    public CommonUser(int userId, String name, String password, LocalDateTime creationTime, double userHeight, double userWeight,
                      int userAge, int userExcerciseLevel, HashMap<String, Boolean> restrictions, HashMap<String, Boolean> weightGoalType) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.creationTime = creationTime;
        this.userHeight  = userHeight;
        this.userWeight = userWeight;
        this.userAge = userAge;
        this.userExcerciseLevel = userExcerciseLevel;
        this.restrictions = restrictions;
        this.weightGoalType = weightGoalType;

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
        return String.valueOf(weightGoalType.get("maintainWeight"));
    }

    public String getLoseTypeValue() {
        return String.valueOf(weightGoalType.get("loseWeight"));
    }

    public String getGainTypeValue() {
        return String.valueOf(weightGoalType.get("gainWeight"));
    }
}
