package src.entity;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonUser implements User {

    private final int userId;
    // private HashMap<String, Boolean> restrictions = new HashMap<>();
    private HashMap<String, Boolean> dietary = new HashMap<>();
    private ArrayList<String> trackedNutrients = new ArrayList<>();
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
    private int userExerciseLevel;
    private HashMap<String, Boolean> weightGoalType;
    private String paceType;

    private double requiredCalories;



    // main User constructor
    public CommonUser(int userId,
                      String name,
                      String password,
                      LocalDateTime creationTime,
                      HashMap<String, Boolean> gender,
                      double userHeight,
                      double userWeight,
                      int userAge,
                      int userExerciseLevel,
                      HashMap<String, Boolean> dietary,
                      HashMap<String, Boolean> allergies,
                      HashMap<String, String> conditions,
                      ArrayList<String> trackedNutrients,
                      HashMap<String, Boolean> weightGoalType,
                      String paceType,
                      double requiredCalories) {

        this.userId = userId;
        this.name = name;
        this.password = password;
        this.creationTime = creationTime;
        this.gender = gender;
        this.userHeight  = userHeight;
        this.userWeight = userWeight;
        this.userAge = userAge;
        this.userExerciseLevel = userExerciseLevel;
        this.dietary = dietary;
        this.allergies = allergies;
        this.conditions = conditions;
        this.trackedNutrients = trackedNutrients;
        this.weightGoalType = weightGoalType;
        this.paceType = paceType;
        this.requiredCalories = requiredCalories;

    }

    // retrieves UserID
    @Override
    public int getUserId() {
        return userId;
    }

    // retrieves the user's name
    @Override
    public String getName() {
        return name;
    }

    // retrieves the user's password
    @Override
    public String getPassword() {
        return password;
    }

    // retrievers the user's creation time
    @Override
    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    // retrieve the user's gender (call getGenderType first to see if the value is specified)
    @Override
    public HashMap<String, Boolean> getGender() {
        return this.gender;
    }


    // returns true if the user identified that they are male
    public String isMale() {
        if (gender.get("male") != null) {
            return String.valueOf(gender.get("male"));
        }
        return String.valueOf(Boolean.FALSE);
    }

    // returns true if the user identified that they are female
    public String isFemale() {
        if (gender.get("female") != null) {
            return String.valueOf(gender.get("female"));
        }
        return String.valueOf(Boolean.FALSE);
    }

    // retrieves the user's height
    @Override
    public double getUserHeight() {
        return userHeight;
    }

    // retrieves the user's weight
    @Override
    public double getUserWeight() {
        return userWeight;
    }

    // retrieves the user's age
    @Override
    public int getUserAge() {
        return userAge;
    }

    // retrieves the user's exercise level
    @Override
    public int getUserExerciseLevel() {
        return userExerciseLevel;
    }



    //@Override
    //public HashMap<String, Boolean> getUserRestriction() {
    //    return restrictions; // Change to return keys with True values.
    //}

    public void setDietary(HashMap<String, Boolean> dietary){
        this.dietary = dietary;
    }

    @Override
    public HashMap<String, Boolean> getDietary() {
        return this.dietary;
    }

    public void setAllergies(HashMap<String, Boolean> allergies){
        this.allergies = allergies;
    }

    @Override
    public HashMap<String, Boolean> getAllergies(){
        return this.allergies;
    }
    public void setConditions(HashMap<String, String> conditions){
        this.conditions = conditions;
    }
    @Override
    public HashMap<String, String> getConditions(){
        return this.conditions;
    }

    public String userSpecifiedDietary() {
        String user_diet = new String();
        for (Map.Entry<String, Boolean> map : this.getDietary().entrySet()) {
            String key = map.getKey();
            Boolean value = map.getValue();
            if ((value == Boolean.TRUE) && !key.equals("none")) {
                user_diet = key;
            } else if (key.equals("none1") && (value == Boolean.TRUE)){
                user_diet = "";
            }
        }
        return user_diet;
    }


    //this would return a list of all allergies the user clicked on only
    public List<String> userSpecifiedAllergies(){
        List<String> user_allergies = new ArrayList<>();
        for (Map.Entry<String, Boolean> map: this.getAllergies().entrySet()){
            String key = map.getKey();
            Boolean value = map.getValue();
            if ((value == Boolean.TRUE) && !key.equals("none")){
                user_allergies.add(key);
            } else if (key.equals("none") && (value == Boolean.TRUE)){
                user_allergies.add("");
            }
        }
        return user_allergies;
    }


    //return a daily intake amount depending on user selection
    public HashMap<String, Double> userSpecifiedConditions(){

        HashMap<String, Double> user_conditions = new HashMap<>();
        user_conditions.put("Calcium", this.getCalciumValue());
        user_conditions.put("Potassium", this.getPotassiumValue());
        user_conditions.put("VitaminC", this.getVitaminCValue());
        user_conditions.put("VitaminD", this.getVitaminDValue());
        user_conditions.put("Iron", this.getIronValue());
        user_conditions.put("Magnesium", this.getMagnesiumValue());
        user_conditions.put("Sugar", this.getSugarValue());
        return user_conditions;
    }


    public Double getCalciumValue(){
        String value = getConditions().get("Calcium");


        int age = getUserAge();

        // initialize a double to store the recommended daily average intake depending on the user's information
        double daily_value;

        // specifies the daily calcium intake based on sex and age
        if ((isMale().equals("true") && (age > 12 && age < 71)) || (isFemale().equals("true") && age > 12 && age < 51)){
            // calculates the daily intake based on what intake type the user specified (low, average, high)
            if (value.equals("low")){
                daily_value = 1000 - 0.20 * 1000;
            } else if (value.equals("average")) {
                daily_value = 1000;
            } else {
                daily_value = 1000 + 0.20 * 1000;
            }
        } else if ((isMale().equals("true") && age > 70) || (isFemale().equals("true") && age > 50)){
            if (value.equals("low")){
                daily_value = 1200 - 0.20 * 1200;
            } else if (value.equals("average")) {
                daily_value = 1200;
            } else {
                daily_value = 1200 + 0.20 * 1200;
            }
        } else {
            // an improper age group and/or sex has been specified by the user, call an exception
            daily_value = 0.0;
            // Note: later replace this println for a custom exception to be handled in another layer
            System.out.println("Could not set Calcium level.");
        }

        // if the daily value has been properly calculated, return the value
        //assert(daily_value != 0.0);
        return (daily_value / 3);
    }


    public Double getPotassiumValue(){
        String value = getConditions().get("Potassium");
        double daily_value;
        if (isMale().equals("true")) {
            if (value.equals("low")) {
                daily_value = 3400 - 0.20 * 3400;
            } else if (value.equals("average")) {
                daily_value = 3400;
            } else {
                daily_value = 3400 + 0.20 * 3400;
            }
        } else if (isFemale().equals("true")){
            if (value.equals("low")) {
                daily_value = 2600 - 0.20 * 2600;
            } else if (value.equals("average")) {
                daily_value = 2600;
            } else {
                daily_value = 2600 + 0.2 * 2600;
            }
        } else {
            daily_value = 0.0;
            System.out.println("Could not set Potassium level.");
        }
        //assert(daily_value != 0.0);
        return (daily_value / 3);
    }



    public Double getVitaminCValue(){
        String value = getConditions().get("VitaminC");
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
            System.out.println("Could not set VitaminC level.");
        }
        //assert(daily_value != 0.0);
        return (daily_value / 3);
    }


    public Double getVitaminDValue() {
        String value = getConditions().get("VitaminD");
        double daily_value;
        int age = getUserAge();
        if ((isMale().equals("true") && age > 12 && age < 71) || (isFemale().equals("true"))){
            if (value.equals("low")) {
                daily_value = (600 * 0.67) - 0.20 * (600 * 0.67);
            } else if (value.equals("average")) {
                daily_value = (600 * 0.67);
            } else {
                daily_value = (600 * 0.67) + 0.20 * (600 * 0.67);
            }
        } else if ((isMale().equals("true") && age > 70)){
            if (value.equals("low") ){
                daily_value = (800 * 0.67) - 0.20 * (800 * 0.67);
            } else if (value.equals("average")) {
                daily_value = (800 * 0.67);
            } else {
                daily_value = (800 * 0.67) + 0.20 * (800 * 0.67);
            }
        } else {
            daily_value = 0.0;
            System.out.println("Could not set VitaminD level.");
        }
        //assert(daily_value != 0.0);
        return (daily_value / 3);
    }


    public Double getIronValue(){
        String value = getConditions().get("Iron");
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
        } else if (isFemale().equals("true") && age > 12 && age < 51){
            if (value.equals("low")){
                daily_value = 18 - 0.20 * 18;

            } else if (value.equals("average")){
                daily_value = 18;

            } else {
                daily_value = 18 + 0.20 * 18;

            }
        } else {
            daily_value = 0.0;
            System.out.println("Could not set Iron level.");

        }
        return (daily_value / 3);
    }

    public Double getMagnesiumValue(){
        String value = getConditions().get("Magnesium");
        double daily_value;
        int age = getUserAge();
        if (isMale().equals("true") && age > 12 && age< 31){
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
        } else if (isFemale().equals("true") && age > 12 && age < 31){
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

        return (daily_value / 3);

    }

    //sugar is measured in grams not mg
    public Double getSugarValue(){
        String value = getConditions().get("Sugar");
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
        //assert(daily_value != 0.0);
        return (daily_value / 3);
    }


    @Override
    public ArrayList<String> getTrackedNutrients() {
        return trackedNutrients;
    }

    // retrieves the user's weight goal type (lose, gain, maintain)
    @Override
    public String getWeightGoalType() {
        for (Map.Entry<String, Boolean> entry: weightGoalType.entrySet()) {
            if (entry.getValue() == Boolean.TRUE) {
                return entry.getKey();
            }
        }
        return "WeightGoalType not established";
    }

    // retrieves the user's maintain weight goal value
    public String getMaintainTypeValue() {
        if (weightGoalType.get("maintainWeight") != null){
            return String.valueOf(weightGoalType.get("maintainWeight"));
        }
        else return String.valueOf(Boolean.FALSE);
    }

    // retrieves the user's weight loss goal value
    public String getLoseTypeValue() {
        if (weightGoalType.get("loseWeight") != null) {
            return String.valueOf(weightGoalType.get("loseWeight"));
        }
        else return String.valueOf(Boolean.FALSE);
    }

    // retrieves the user's weight gain goal value
    public String getGainTypeValue() {
        if (weightGoalType.get("gainWeight") != null) {
            return String.valueOf(weightGoalType.get("gainWeight"));
        }
        else return String.valueOf(Boolean.FALSE);
    }

    // retrieves the user's pace type
    @Override
    public String getPaceType() {
        return paceType;
    }



    public double getRequiredCalories() {


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
        this.userExerciseLevel = userExerciseLvl;
    }

    @Override
    public void setWeightGoalType(HashMap<String, Boolean> weightGoalType) {
        this.weightGoalType = weightGoalType;
    }

    @Override
    public void setPaceType(String paceType) {
        this.paceType = paceType;
    }

    @Override
    public void setRequiredCalories(double requiredCalories) {
        this.requiredCalories = requiredCalories;
    }


    // sets the nutrients the user would like to track
    @Override
    public void setTrackedNutrients(ArrayList<String> trackedNutrients) {
        this.trackedNutrients = trackedNutrients;
    }
}

