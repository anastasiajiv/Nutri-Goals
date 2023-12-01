package src.data_access;


import java.io.*;

import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;
import src.entity.*;
import src.entity.User;
import src.entity.UserFactory;
import src.use_case.mealPlan.MealPlanDataAccessInterface;
import src.use_case.signup.SignupUserDataAccessInterface;
import src.use_case.trackedNutrients.TrackedNutrientsUserDataAccessInterface;
import src.use_case.weightgoal.WeightGoalUserDataInterface;
import src.use_case.preferences.PreferencesUserDataAccessInterface;

public class FileUserDataAccessObject implements SignupUserDataAccessInterface, WeightGoalUserDataInterface,
        PreferencesUserDataAccessInterface, TrackedNutrientsUserDataAccessInterface, MealPlanDataAccessInterface {

    File csvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    //private final Map<Integer, User> accounts = new HashMap<>();

    public Map<Integer, User> accounts = new HashMap<>(); // Testing purposes in MAIN

    private final UserFactory userFactory;

    public FileUserDataAccessObject(String csvPath, UserFactory userFactory) throws IOException{
        this.userFactory = userFactory;

        csvFile = new File(csvPath);
        headers.put("userId", 0);
        headers.put("username", 1);
        headers.put("password", 2);
        headers.put("creationTime", 3);
        headers.put("male", 4);
        headers.put("female", 5);
        headers.put("height", 6);
        headers.put("weight", 7);
        headers.put("age", 8);
        headers.put("exerciseLvl", 9);
        headers.put("dietaryRestriction1", 10);
        headers.put("allergiesRestriction1", 11);
        headers.put("conditionsRestriction1", 12);
        headers.put("maintainWeight", 13);
        headers.put("loseWeight", 14);
        headers.put("gainWeight", 15);
        headers.put("weightPaceType", 16);
        headers.put("requiredCalories", 17);
        headers.put("trackedNutrients", 18);

        if (csvFile.length() == 0) {
            setHeaders();
        }
        else {
            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();

                assert header.equals("userId," +
                        "username," +
                        "password," +
                        "creationTime," +
                        "male" +
                        "female" +
                        "height," +
                        "weight," +
                        "age," +
                        "exerciseLvl," +
                        "dietaryRestriction1," + // instead of just preferences
                        "allergiesRestriction1" +
                        "conditionsRestriction1" +
                        "maintainWeight," +
                        "loseWeight," +
                        "gainWeight," +
                        "weightPaceType," +
                        "requiredCalories," +
                        "trackedNutrients");

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    int userId = Integer.parseInt(col[headers.get("userId")]);
                    String username = String.valueOf(col[headers.get("username")]);
                    String password = String.valueOf(col[headers.get("password")]);
                    String creationTimeText = String.valueOf(col[headers.get("creationTime")]);

                    LocalDateTime ldt = LocalDateTime.parse(creationTimeText);

                    String genderKey1 = "male";
                    String genderKey2 = "female";
                    Boolean genderValue1 = Boolean.valueOf(col[headers.get("male")]);
                    Boolean genderValue2 = Boolean.valueOf(col[headers.get("female")]);

                    HashMap<String, Boolean> gender = new HashMap<>();
                    gender.put(genderKey1, genderValue1);
                    gender.put(genderKey2, genderValue2);

                    double height = Double.parseDouble(col[headers.get("height")]);
                    double weight = Double.parseDouble(col[headers.get("weight")]);
                    int age = Integer.parseInt(col[headers.get("age")]);
                    int exerciseLvl = Integer.parseInt(col[headers.get("exerciseLvl")]);

                    String dietaryKey1 = "dietaryRestriction1"; // Replace with each type of restriction
                    Boolean dietaryValue1 = Boolean.valueOf(col[headers.get("dietaryRestriction1")]);

                    HashMap<String, Boolean> dietaryRestrictions = new HashMap<>();
                    dietaryRestrictions.put(dietaryKey1, dietaryValue1);

                    String allergyKey1 = "allergiesRestriction1";
                    Boolean allergyValue1 = Boolean.valueOf(col[headers.get("allergiesRestriction1")]);

                    HashMap<String, Boolean> allergiesRestrictions = new HashMap<>();
                    allergiesRestrictions.put(allergyKey1, allergyValue1);

                    String conditionsKey1 = "conditionsRestriction1";
                    String conditionsValue1 = String.valueOf(col[headers.get("conditionsRestriction1")]);

                    HashMap<String, String> conditionsRestrictions = new HashMap<>();
                    conditionsRestrictions.put(conditionsKey1, conditionsValue1);

                    String weightGoalKey1 = "maintainWeight";
                    Boolean weightGoalValue1 = Boolean.valueOf(col[headers.get(weightGoalKey1)]);
                    String weightGoalKey2 = "loseWeight";
                    Boolean weightGoalValue2 = Boolean.valueOf(col[headers.get(weightGoalKey2)]);
                    String weightGoalKey3 = "gainWeight";
                    Boolean weightGoalValue3 = Boolean.valueOf(col[headers.get(weightGoalKey3)]);

                    HashMap<String, Boolean> weightGoal = new HashMap<>();
                    weightGoal.put(weightGoalKey1, weightGoalValue1);
                    weightGoal.put(weightGoalKey2, weightGoalValue2);
                    weightGoal.put(weightGoalKey3, weightGoalValue3);

                    String paceType = String.valueOf(col[headers.get("weightPaceType")]);

                    int requiredCalories = Integer.parseInt(col[headers.get("requiredCalories")]);


                    // waiting for next pull to properly implement

                    // not sure if this is the way to fetch the information correctly
                    ArrayList<String> trackedNutrients = new ArrayList<>();
                    // ArrayList<String> trackedNutrients = col[headers.get("trackedNutrients")];

                    User user = userFactory.create(userId,
                            username,
                            password,
                            ldt,
                            gender,
                            height,
                            weight,
                            age,
                            exerciseLvl,
                            dietaryRestrictions,
                            allergiesRestrictions,
                            conditionsRestrictions,
                            trackedNutrients,
                            weightGoal,
                            paceType,
                            requiredCalories);
                            accounts.put(userId, user);

                }
            }

        }


    }

    public void setHeaders() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (User user: accounts.values()) {
                String line = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s",
                        user.getUserId(),
                        user.getName(),
                        user.getPassword(),
                        user.getCreationTime(),
                        user.isMale(),
                        user.isFemale(),
                        user.getUserHeight(),
                        user.getUserWeight(),
                        user.getUserAge(),
                        user.getUserExerciseLevel(),
                        "Dietary", // since get returns only those that are true
                        "Allergies",
                        "Conditions",
                        user.getTrackedNutrients(),
                        user.getMaintainTypeValue(),
                        user.getLoseTypeValue(),
                        user.getGainTypeValue(),
                        user.getPaceType(),
                        user.getRequiredCalories());

                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveNewUser(User user) {
        if (accounts.containsKey(user.getUserId()) == Boolean.FALSE) { // Don't add user if they already exist

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile, true))) {
                String line = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s",
                        user.getUserId(),
                        user.getName(),
                        user.getPassword(),
                        user.getCreationTime(),
                        user.isMale(),
                        user.isFemale(),
                        user.getUserHeight(),
                        user.getUserWeight(),
                        user.getUserAge(),
                        user.getUserExerciseLevel(),
                        user.getDietary(),
                        user.getAllergies(),
                        user.getConditions(),
                        user.getTrackedNutrients(),
                        user.getMaintainTypeValue(),
                        user.getLoseTypeValue(),
                        user.getGainTypeValue(),
                        user.getPaceType(),
                        user.getRequiredCalories());
                writer.write(line);
                writer.newLine();
            } catch (IOException e) {
                System.out.println("CSV file did not update");
            }
        }
        else System.out.println("This user already exists");
    }

    @Override
    public void save(User user) {
        accounts.put(user.getUserId(), user);
    }

    @Override
    public Boolean existByUserID(int userID) {
        return accounts.containsKey(userID);
    }
    public User getAccountByUserID(int userID) {
        return accounts.get(userID);
    }

    @Override
    public void saveWeightGoalData(User updatedUser) { // Should only be called for existing
        assert accounts.containsKey(updatedUser.getUserId());

        int userID = updatedUser.getUserId();
        if (existByUserID(userID)) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(csvFile));
                StringBuilder updatedCsvContent = new StringBuilder();

                // Read the header and append it to the updated content
                String header = reader.readLine();
                updatedCsvContent.append(header).append("\n");

                // Read each line, update the line for the specified user, and append it to the updated content
                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    int currentUserId = Integer.parseInt(col[headers.get("userId")]);

                    if (currentUserId == userID) {  // added trackedNutrients
                        // Update the line for the specified user
                        String updatedLine = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s",
                                updatedUser.getUserId(),
                                updatedUser.getName(),
                                updatedUser.getPassword(),
                                updatedUser.getCreationTime(),
                                updatedUser.isMale(),
                                updatedUser.isFemale(),
                                updatedUser.getUserHeight(),
                                updatedUser.getUserWeight(),
                                updatedUser.getUserAge(),
                                updatedUser.getUserExerciseLevel(),
                                updatedUser.getDietary(),
                                updatedUser.getAllergies(),
                                updatedUser.getConditions(),
                                updatedUser.getTrackedNutrients(),
                                updatedUser.getMaintainTypeValue(),
                                updatedUser.getLoseTypeValue(),
                                updatedUser.getGainTypeValue(),
                                updatedUser.getPaceType(),
                                updatedUser.getRequiredCalories());

                        updatedCsvContent.append(updatedLine).append("\n");
                    } else {
                        // Append the unchanged line
                        updatedCsvContent.append(row).append("\n");
                    }
                }

                // Write the updated content back to the CSV file
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile))) {
                    writer.write(updatedCsvContent.toString());
                }
            } catch (IOException e) {
                throw new RuntimeException("Error updating user data in CSV file", e);
            }
        }

    }

    @Override
    public User getUserWeightGoalData(int userId) {
        return accounts.get(userId);
    }

    @Override
    public String getWeightGoalType(int userID) { // For testing
        return accounts.get(userID).getWeightGoalType(); // returns the weight goal type for this user
    }

    //Weight Goal Methods to get calories user needs

    public double getRequiredCalories(int userID) throws Exception {
        User user = getAccountByUserID(userID);
        double reqCalories = getBMR(userID);

        if (user.getWeightGoalType().equals("maintainWeight")) {
            reqCalories = reqCalories;
            }

        else if (user.getWeightGoalType().equals("loseWeight")) {
            String paceType = user.getPaceType();
            if (paceType.equals("normal")) {
                reqCalories = reqCalories + (3500 * 0.10); // 3500 calories is about 1 lb
            }
            else if (paceType.equals("fast")) {
                reqCalories = reqCalories + (3500 * 0.15);
            }
            else if (paceType.equals("extreme")) {
                reqCalories = reqCalories + (3500 * 0.20);
            }

        }
        else if (user.getWeightGoalType().equals("gainWeight")) {
            String paceType = user.getPaceType();

            if (paceType.equals("normal")) {
                reqCalories = reqCalories - (3500 * 0.10); // 3500 calories is about 1 lb
            }
            else if (paceType.equals("fast")) {
                reqCalories = reqCalories - (3500 * 0.15);
            }
            else if (paceType.equals("extreme")) {
                reqCalories = reqCalories - (3500 * 0.20);
            }
        }
        return reqCalories;
    }

    public double getBMR(int userID) {
        // Men: BMR = 88.63 + (13.397 * weight in kg) + (4.799 * height in cm) - (5.677 * age in years)
        // Miffin - St Jeor Equation -> BMR = 10 * weight + 6.25 * height - 5 * age + 5
        // Women: BMR = 447.593 + (9.247 x weight in kg) + (3.098 x height in cm) – (4.330 x age in years)
        // Miffin - St Jeor Equation -> BMR = 10 * weight + 6.25 * height - 5 * age - 161


        // Harris - Benedict -> Men -> BMR = 66 + (13.7 x wt in kg) + (5 x ht in cm) - (6.8 x age in years)
        // Harris - Benedict -> Men -> BMR =  655 + (9.6 x wt in kg) + (1.8 x ht in cm) - (4.7 x age in years)
        assert existByUserID(userID);
        double userBMR = 0;


        User user = accounts.get(userID);
        //Get BMR
        if (Boolean.valueOf(user.isMale())) {
            userBMR = (10 * user.getUserWeight()) + (6.25 * user.getUserHeight()) - (5 * user.getUserAge()) + 5;
        }
        else if (Boolean.valueOf(user.isFemale())) {
            userBMR = (10 * user.getUserWeight()) + (6.25 * user.getUserHeight()) - (5 * user.getUserAge()) - 161;
        }
        return getBMRAfterActivityMultiplier(userID, userBMR);
    }

    public double getBMRAfterActivityMultiplier(int userID, double userBMR) {
        User user = accounts.get(userID);
        double newUserBMR = userBMR;

        assert user.getUserExerciseLevel() >= 1 && user.getUserExerciseLevel() <=5; // Must be in the range 1-5.

        if (user.getUserExerciseLevel() ==1) {
            newUserBMR = newUserBMR * 1.2;
        }
        else if (user.getUserExerciseLevel() == 2) {
            newUserBMR = newUserBMR * 1.375;
        }
        else if (user.getUserExerciseLevel() == 3) {
            newUserBMR = newUserBMR * 1.55;
        }
        else if (user.getUserExerciseLevel() == 4) {
            newUserBMR = newUserBMR * 1.725;
        }
        else if (user.getUserExerciseLevel() == 5) {
            newUserBMR = newUserBMR * 1.9;
        }
        return newUserBMR;
    }

    @Override
    public void saveDietary(HashMap<Integer, HashMap<String, Boolean>> dietary){
        // element 8
        BufferedReader reader;
        BufferedWriter writer;
        try {
            reader = new BufferedReader(new FileReader(csvFile));
            writer = new BufferedWriter(new FileWriter(csvFile, false));
            reader.readLine();
            String row;
            for(Map.Entry<Integer, HashMap<String, Boolean>> entry: dietary.entrySet()){
                Integer key = entry.getKey();
                HashMap<String, Boolean> value = entry.getValue();
                while((row = reader.readLine()) != null){
                    String[] col = row.split(",");
                    if (col[0].equals(String.valueOf(key))) {
                        col[10] = String.valueOf(value);
                        String updated_dietary = String.join(",", col);
                        writer.write(updated_dietary);
                    }
                }
            }
            writer.close();
        } catch (IOException e){
            System.out.println("Error, could not save dietary properly.");
        }

    }

    @Override
    public void saveAllergies(HashMap<Integer, HashMap<String, Boolean>> allergies){
        //element 10
        BufferedReader reader;
        BufferedWriter writer;
        try {
            reader = new BufferedReader(new FileReader(csvFile));
            writer = new BufferedWriter(new FileWriter(csvFile));
            reader.readLine();
            String row;
            for(Map.Entry<Integer, HashMap<String, Boolean>> entry: allergies.entrySet()){
                Integer key = entry.getKey();
                HashMap<String, Boolean> value = entry.getValue();
                while((row = reader.readLine()) != null){
                    String[] col = row.split(",");
                    if (col[0].equals(String.valueOf(key))){
                        col[11] = String.valueOf(value);// or col[10]
                        String updated_allergies = String.join(",", col);
                        writer.write(updated_allergies);
                    }
                }
            }
            writer.close();
        } catch (IOException e){
            System.out.println("Error, could not save allergies properly.");
        }

    }

    @Override
    public void saveConditions(HashMap<Integer, HashMap<String, String>> conditions) {
        BufferedReader reader;
        BufferedWriter writer;
        try {
            reader = new BufferedReader(new FileReader(csvFile));
            writer = new BufferedWriter(new FileWriter(csvFile));
            reader.readLine();
            String row;
            for(Map.Entry<Integer, HashMap<String, String>> entry: conditions.entrySet()){
                Integer key = entry.getKey();
                HashMap<String, String> value = entry.getValue();
                while((row = reader.readLine()) != null){
                    String[] col = row.split(",");
                    if (col[0].equals(String.valueOf(key))){
                        col[12] = String.valueOf(value);
                        String updated_conditions = String.join(",", col);
                        writer.write(updated_conditions);
                    }
                }
            }
            writer.close();
        } catch (IOException e){
            System.out.println("Error, could not save conditions properly.");
        }
    }

    @Override
    public Boolean saveTrackedNutrientsData(ArrayList<String> trackedNutrients, int userID) {
        // userID is already checked for validity in trackedNutrients interactor

        try {
            // initialize a reader and writer to edit the csv file
            BufferedReader reader = new BufferedReader(new FileReader(csvFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile));

            // skip the first line and initialize the row variable
            reader.readLine();
            String row;

            // iterate through the csv
            while ((row = reader.readLine()) != null) {

                // split each row in the csv into their corresponding columns
                String[] col = row.split(",");

                // if the ID is a match, set the user's trackedNutrients
                if (col[0].equals(String.valueOf(userID))) {

                    // column 18 is set to store trackedNutrients
                    col[18] = String.valueOf(trackedNutrients);
                    // turn the String[] back into a String to write back
                    String updated_line = String.join(",", col);
                    // use the buffered writer to add the line back into the csv
                    writer.write(updated_line);
                }
            }

            // close the writer after the line has/has not been edited
            writer.close();
            // confirm that the data has been properly saved
            return true;

        } catch (IOException e) {  // if there is an issue accessing the csv

            // display an error message to the same convention as other save methods
            System.out.println("Error, could not save trackedNutrients properly.");
            // error has occurred, return false
            return false;

        }
    }

    @Override
    public ArrayList<String> getUserTrackedNutrientsData(int userID) {
        return accounts.get(userID).getTrackedNutrients();
    }

    public HashMap<String, Double> getRecipeNutritionalInfo(String recipeID) {
        // format the API request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.spoonacular.com/recipes/"+ recipeID +"/information?includeNutrition=true"))
                .header("x-api-host", "https://api.spoonacular.com")
                .header("x-api-key", "7fbb8c718e724bb491e1e9a89c746713 ")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        // attempt to fetch from the API
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        assert response != null;  // ensure that the recipe was fetched correctly
        String recipe = response.body();

        // find the nutritional info
        JSONObject json = new JSONObject(recipe);
        JSONObject recipeOuter = json.getJSONObject("nutrition");  // get an array of nutrients
        JSONArray recipeArray = recipeOuter.getJSONArray("nutrients");

        // initialize a storage hashmap for the nutrients
        HashMap<String, Double> recipeNutritionalInfo = new HashMap<>();

        for (int i = 0; i < recipeArray.length(); i++) {
            // each nutrient is in its own array
            JSONObject nutrientArray = recipeArray.getJSONObject(i);
            String nutrientName = nutrientArray.getString("name");
            double nutrientValue = nutrientArray.getDouble("amount");

            // place into the hashmap
            recipeNutritionalInfo.put(nutrientName, nutrientValue);
        }
        return recipeNutritionalInfo;
    }

    @Override
    public String breakfast(int identifier) {
        return null;
    }

    @Override
    public List<Ingredient> createIngredients(String identifier) {
        return null;
    }

    @Override
    public Recipe createRecipeBreakfast(List<Ingredient> ingredients, String recipe) {
        return null;
    }

    @Override
    public String lunch(int identifier) {
        return null;
    }

    @Override
    public Recipe createRecipeLunch(List<Ingredient> ingredients, String recipe) {
        return null;
    }

    @Override
    public String dinner(int identifier) {
        return null;
    }

    @Override
    public Recipe createRecipeDinner(List<Ingredient> ingredients, String recipe) {
        return null;
    }

    @Override
    public MealPlan getMealPlan(int id) {
        return null;
    }
}
