package src.data_access;

import java.io.*;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.*;

import src.entity.CommonUserFactory;
import src.entity.User;
import src.entity.UserFactory;
import src.use_case.login.LoginUserDataAccessInterface;
import src.use_case.preferences.PreferencesUserDataAccessInterface;
import src.use_case.signup.SignupUserDataAccessInterface;
import src.use_case.trackedNutrients.TrackedNutrientsUserDataAccessInterface;
import src.use_case.weightgoal.WeightGoalUserDataInterface;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient;
import java.io.IOException;
import org.json.JSONObject;
import org.json.JSONArray;
import java.io.IOException;

public class FileUserDataAccessObject implements SignupUserDataAccessInterface,
        LoginUserDataAccessInterface,
        WeightGoalUserDataInterface,
        PreferencesUserDataAccessInterface,
        TrackedNutrientsUserDataAccessInterface {

    private final String csvFilePath;

    private final String csvMealPlanFilePath;
    private final FileCsvBuilder csvBuilder;
    public Map<Integer, User> accounts = new HashMap<>();

    private final UserFactory userFactory;

    public FileUserDataAccessObject(String csvFilePath, String csvMealPlanFilePath, UserFactory userFactory) {
        this.csvFilePath = csvFilePath;
        this.csvBuilder = new FileCsvBuilder(csvFilePath);
        this.csvMealPlanFilePath = csvMealPlanFilePath;
        this.accounts = new HashMap<>();
        this.userFactory = userFactory;

    }

    // SignUp use case methods

    @Override
    public Boolean saveUserSignUpData(int userId,
                                      String username,
                                      String password,
                                      LocalDateTime creationTime) {

        UserFactory userFactory = new CommonUserFactory();
        User newUser = userFactory.createdDefaultUser(userId, username);
        newUser.setPassword(password);
        newUser.setCreationTime(creationTime);

        accounts.put(userId, newUser);

        return csvBuilder.buildCsv(newUser, 0);

    }

    public int createUserID() {
        int lastUserID = findLastUserID();
        int newID = lastUserID + 1;
        return newID;
    }

    private int findLastUserID() {
        int lastUserID = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            File csvFile = new File(csvFilePath);
            if (csvFile.exists()) {
                reader.readLine();
                String line;
                while ((line = reader.readLine()) != null) {
                    // Assuming the first column contains the user ID
                    String[] columns = line.split(",");
                    if (columns.length > 0) {
                        lastUserID = Math.max(lastUserID, Integer.parseInt(columns[0]));
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return lastUserID;
    }


    @Override
    public Boolean saveWeightGoalData ( int userId,
                                        HashMap<String, Boolean> gender,
                                        double height,
                                        double weight,
                                        int age,
                                        int exerciseLvl,
                                        String paceType,
                                        HashMap<String, Boolean> weightGoal){

        // This save method saves the input data to the accounts map and then calls Builder to save the updated user
        // information into the csv file

        //First get the current userId
        User curr_user = getAccountByUserId(userId); //change to update from setter
        curr_user.setGender(gender);
        curr_user.setUserHeight(height);
        curr_user.setUserWeight(weight);
        curr_user.setUserAge(age);
        curr_user.setUserExerciseLvl(exerciseLvl);
        curr_user.setPaceType(paceType);
        curr_user.setWeightGoalType(weightGoal);

        accounts.put(userId, curr_user);// TODO: Discuss weather this should this go in interactor?

        // Now compute req calories
        double requiredCalories = computedRequiredCalories(userId);
        // Save this new data
        curr_user.setRequiredCalories(requiredCalories);
        // Update user into accounts map to account for newly updated req calories

        accounts.put(userId, curr_user);

        // Save updated user values into the Csv file
        return csvBuilder.buildCsv(curr_user, 1);


    }


//    public void saveNewUser(User user) {
//        if (!accounts.containsKey(user.getUserId())) {
//            // Don't add user if they already exist
//            accounts.put(user.getUserId(), user);
//            // Save the updated data to the CSV file
//            csvBuilder.buildCsv(user);
//        } else {
//            System.out.println("This user already exists");
//        }
//    }


    @Override
    public User getAccountByUserId ( int userId){
        return accounts.get(userId);
    }

    //@Override
    //public void save (User user){
    //    accounts.put(user.getUserId(), user);
    //}




    @Override
    public double computedRequiredCalories ( int userId){
        User user = getAccountByUserId(userId);
        double reqCalories = getBMR(userId);

        if (user.getWeightGoalType().equals("maintainWeight")) {
            reqCalories = reqCalories;
        } else if (user.getWeightGoalType().equals("gainWeight")) {
            String paceType = user.getPaceType();
            if (paceType.equals("normal")) {
                reqCalories = reqCalories + (3500 * 0.10); // 3500 calories is about 1 lb
            } else if (paceType.equals("fast")) {
                reqCalories = reqCalories + (3500 * 0.15);
            } else if (paceType.equals("extreme")) {
                reqCalories = reqCalories + (3500 * 0.20);
            }

        } else if (user.getWeightGoalType().equals("loseWeight")) {
            String paceType = user.getPaceType();

            if (paceType.equals("normal")) {
                reqCalories = reqCalories - (3500 * 0.10); // 3500 calories is about 1 lb
            } else if (paceType.equals("fast")) {
                reqCalories = reqCalories - (3500 * 0.15);
            } else if (paceType.equals("extreme")) {
                reqCalories = reqCalories - (3500 * 0.20);
            }
        }
        return reqCalories;
    }

    @Override
    public Boolean existByUserID ( int userID){
        return accounts.containsKey(userID);
    }

    public double getBMR ( int userId){
        // Men: BMR = 88.63 + (13.397 * weight in kg) + (4.799 * height in cm) - (5.677 * age in years)
        // Miffin - St Jeor Equation -> BMR = 10 * weight + 6.25 * height - 5 * age + 5
        // Women: BMR = 447.593 + (9.247 x weight in kg) + (3.098 x height in cm) – (4.330 x age in years)
        // Miffin - St Jeor Equation -> BMR = 10 * weight + 6.25 * height - 5 * age - 161


        // Harris - Benedict -> Men -> BMR = 66 + (13.7 x wt in kg) + (5 x ht in cm) - (6.8 x age in years)
        // Harris - Benedict -> Men -> BMR =  655 + (9.6 x wt in kg) + (1.8 x ht in cm) - (4.7 x age in years)
        assert existByUserID(userId);
        double userBMR = 0;


        User user = accounts.get(userId);
        //Get BMR
        if (Boolean.valueOf(user.isMale())) {
            userBMR = (10 * user.getUserWeight()) + (6.25 * user.getUserHeight()) - (5 * user.getUserAge()) + 5;
        } else if (Boolean.valueOf(user.isFemale())) {
            userBMR = (10 * user.getUserWeight()) + (6.25 * user.getUserHeight()) - (5 * user.getUserAge()) - 161;
        }
        return getBMRAfterActivityMultiplier(userId, userBMR);
    }
    public double getBMRAfterActivityMultiplier ( int userId, double userBMR){
        User user = accounts.get(userId);
        double newUserBMR = userBMR;


        assert user.getUserExerciseLevel() >= 1 && user.getUserExerciseLevel() <= 5; // Must be in the range 1-5.

        if (user.getUserExerciseLevel() == 1) {
            newUserBMR = newUserBMR * 1.2;
        } else if (user.getUserExerciseLevel() == 2) {
            newUserBMR = newUserBMR * 1.375;
        } else if (user.getUserExerciseLevel() == 3) {
            newUserBMR = newUserBMR * 1.55;
        } else if (user.getUserExerciseLevel() == 4) {
            newUserBMR = newUserBMR * 1.725;
        } else if (user.getUserExerciseLevel() == 5) {
            newUserBMR = newUserBMR * 1.9;
        }
        return newUserBMR;
    }

    @Override
    public Boolean savePreferences(int userId, HashMap<String, Boolean> dietary,
                               HashMap<String, Boolean> allergies,
                               HashMap<String, String> conditions){
        User current_user = getAccountByUserId(userId);
        current_user.setDietary(dietary);
        current_user.setAllergies(allergies);
        current_user.setConditions(conditions);
        accounts.put(userId, current_user);
        return csvBuilder.buildCsv(current_user, 1);

    }


    //added these
    public HashMap<String, Boolean> getDietaryFile(String userID){
        User data = accounts.get(userID);
        return data.getDietary();
    }
    public HashMap<String, Boolean> getAllergiesFile(String userID){
        User data = accounts.get(userID);
        return data.getAllergies();
    }
    public HashMap<String, String> getConditionsFile(String userID){
        User data = accounts.get(userID);
        return data.getConditions();
    }

    @Override
    public boolean existByName(String identifier) {
        for (Integer key: accounts.keySet()){
            User account = accounts.get(key);
            if (account.getName().equals(identifier)){
                return true;
            }
        }
        return false;
    }

    @Override
    public User get(String username) {
        User account = null;
        for (Integer key: accounts.keySet()){
            User value = accounts.get(key);
            String name = value.getName();
            if (name.equals(username)){
                account = value;
            }
        }
        assert(account != null);
        return account;
    }

    @Override
    public Boolean saveTrackedNutrientsData(ArrayList<String> trackedNutrients, int userID) {
        // userID is already checked for validity in trackedNutrients interactor

        try {
            // initialize a reader and writer to edit the csv file
            BufferedReader reader = new BufferedReader(new FileReader(csvFilePath));
            BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath));

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

    private HashMap<String, Float> getRecipeNutritionalInfo(String recipeID) {
        // format the API request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.spoonacular.com/recipes/"+ recipeID +"/information?includeNutrition=true"))
                .header("X-RapidAPI-Host", "https://api.spoonacular.com")
                .header("X-RapidAPI-Key", "0702028f1e12446ca891a3eb2f36fd0e")
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
        JSONArray recipeArray = json.getJSONArray("nutrients");  // get an array of nutrients

        // initialize a storage hashmap for the nutrients
        HashMap<String, Float> recipeNutritionalInfo = new HashMap<>();

        for (int i = 0; i < recipeArray.length(); i++) {
            // each nutrient is in its own array
            JSONArray nutrientArray = recipeArray.getJSONArray(i);
            String nutrientName = nutrientArray.getString(1);
            double nutrient = nutrientArray.getDouble(2);

            Float nutrientValue = BigDecimal.valueOf(nutrient).floatValue();

            // place into the hashmap
            recipeNutritionalInfo.put(nutrientName, nutrientValue);
        }
        return recipeNutritionalInfo;
    }
}
