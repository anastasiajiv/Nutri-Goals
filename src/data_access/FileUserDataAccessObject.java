package src.data_access;

import java.io.*;

import java.net.URI;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;
import src.entity.CommonUserFactory;
import src.entity.MealPlan;
import src.entity.User;
import src.entity.UserFactory;
import src.use_case.login.LoginUserDataAccessInterface;
import src.use_case.mealPlan.MealPlanDataAccessInterface;
import src.use_case.preferences.PreferencesUserDataAccessInterface;

import src.use_case.signup.SignupUserDataAccessInterface;
import src.use_case.trackedNutrients.TrackedNutrientsUserDataAccessInterface;
import src.use_case.weightgoal.WeightGoalUserDataInterface;
import src.use_case.preferences.PreferencesUserDataAccessInterface;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient;
import java.io.IOException;
import src.entity.MealPlan;
import src.entity.CommonMealPlan;
import src.entity.Recipe;
import src.entity.CommonRecipe;
import src.entity.Ingredient;
import src.entity.CommonIngredient;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient;
import java.io.IOException;

import java.io.IOException;


/*import org.json.JSONArray;
import org.json.JSONObject;*/


import org.json.JSONArray;
import org.json.JSONObject;



public class FileUserDataAccessObject implements SignupUserDataAccessInterface,
        LoginUserDataAccessInterface,
        WeightGoalUserDataInterface,
        PreferencesUserDataAccessInterface,
        TrackedNutrientsUserDataAccessInterface,
        MealPlanDataAccessInterface {

    private final Map<String, Integer> headersmealplan = new LinkedHashMap<>();

    public Map<Integer, MealPlan> mealplanaccounts = new HashMap<>();

    private final String csvFilePath;
    private final String csvMealPlanFilePath;
    private final FileCsvBuilder csvBuilder;
    public Map<Integer, User> accounts = new HashMap<>();
    private final  MealPlancsvbuilder mealplancsvBuilder;


    public FileUserDataAccessObject(String csvFilePath, String csvMealPlanFilePath) {
        this.csvFilePath = csvFilePath;
        this.csvBuilder = new FileCsvBuilder(csvFilePath);
        this.csvMealPlanFilePath = csvMealPlanFilePath;
        this.accounts = new HashMap<>();
        this.mealplancsvBuilder = new MealPlancsvbuilder(csvMealPlanFilePath);

        loadUserDataFromCsv();

    }

    // SignUp use case methods


    @Override
    public Boolean saveUserSignUpData(int userID,
                                      String username,
                                      String password,
                                      LocalDateTime creationTime) {
        UserFactory userFactory = new CommonUserFactory();
        User newUser = userFactory.createdDefaultUser(userID, username);
        newUser.setPassword(password);
        newUser.setCreationTime(creationTime);

        accounts.put(userID, newUser);
        loadUserDataFromCsv(); // IMPORTANT -> This updates accounts map upon every run

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
    public Boolean saveWeightGoalData ( int userID,
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
        User curr_user = getAccountByUserID(userID); //change to update from setter
        curr_user.setGender(gender);
        curr_user.setUserHeight(height);
        curr_user.setUserWeight(weight);
        curr_user.setUserAge(age);
        curr_user.setUserExerciseLvl(exerciseLvl);
        curr_user.setPaceType(paceType);
        curr_user.setWeightGoalType(weightGoal);

        accounts.put(userID, curr_user);


        // Now compute req calories
        double requiredCalories = computedRequiredCalories(userID);
        // Save this new data
        curr_user.setRequiredCalories(requiredCalories);
        // Update user into accounts map to account for newly updated req calories

        accounts.put(userID, curr_user);

        // Save updated user values into the Csv file
        return csvBuilder.buildCsv(curr_user, 1);


    }

    @Override
    public User getAccountByUserID(int userID) {
        return accounts.get(userID);
    }

    @Override
    public double computedRequiredCalories ( int userID){
        User user = getAccountByUserID(userID);
        double reqCalories = getBMR(userID);

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
    public Boolean existByUserID (int userID){
        return accounts.containsKey(userID);
    }

    public double getBMR ( int userID){
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
        } else if (Boolean.valueOf(user.isFemale())) {
            userBMR = (10 * user.getUserWeight()) + (6.25 * user.getUserHeight()) - (5 * user.getUserAge()) - 161;
        }
        return getBMRAfterActivityMultiplier(userID, userBMR);
    }
    public double getBMRAfterActivityMultiplier ( int userID, double userBMR){
        User user = accounts.get(userID);
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
    public Boolean savePreferences(int userID, HashMap<String, Boolean> dietary,
                                   HashMap<String, Boolean> allergies,
                                   HashMap<String, String> conditions){
        User current_user = getAccountByUserID(userID);
        current_user.setDietary(dietary);
        current_user.setAllergies(allergies);
        current_user.setConditions(conditions);
        accounts.put(userID, current_user);
        return csvBuilder.buildCsv(current_user, 1);

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
        User currentUser = getAccountByUserID(userID);
        currentUser.setTrackedNutrients(trackedNutrients);
        accounts.put(userID, currentUser);
        return csvBuilder.buildCsv(currentUser, 1);
    }


    //private HashMap<String, Float> getRecipeNutritionalInfo(String recipeID) {

    //@Override
    public ArrayList<String> getUserTrackedNutrientsData(int userID) {
        return accounts.get(userID).getTrackedNutrients();
    }



    /*private HashMap<String, Float> getRecipeNutritionalInfo(String recipeID) {

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
    }*/


    @Override
    public String Breakfast(int identifier) {
        //find all necessary sorting attributes and do calculations
        User user = getAccountByUserID(identifier);
        int daily_cal = (int) computedRequiredCalories(identifier);
        int breakfast_cals = Math.round((daily_cal/5));
        String dietary = user.userSpecifiedDietary();
        List<String> allergies = user.userSpecifiedAllergies();
        HashMap<String, Double> conditions = user.userSpecifiedConditions();



        //conditions made to be called
        StringBuilder conditionsaccum = new StringBuilder();
        for (Map.Entry<String, Double> entry : conditions.entrySet()){
            String condition = entry.getKey();
            int amount = (int) Math.round(entry.getValue());
            conditionsaccum.append("max" + condition + "=" + amount + "&");
        }

        //make allergies into format for api call
        StringBuilder allergiesaccum = new StringBuilder();
        for (int i = 0; i < allergies.size(); i ++ ){
            if (i < allergies.size() - 1){
                allergiesaccum.append(allergies.get(i) + ",");}
            else {
                allergiesaccum.append(allergies.get(i));

            }

        }

        //calories depending on what weight goal

        String calorietype = "max";
        if (user.getWeightGoalType().equals("gainWeight")) {
            calorietype = "min";}




        // diet and calories and allergies and conditions filtering through API call
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.spoonacular.com/recipes/complexSearch?&type=breakfast&number=1&" +
                        calorietype + "Calories=" + breakfast_cals +"&diet="+ dietary + "&" + conditionsaccum + "intolerances="+ allergiesaccum))
                .header("X-API-Host", "https://api.spoonacular.com")
                .header("X-API-Key", "0702028f1e12446ca891a3eb2f36fd0e")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String recipe = response.body();


        //return recipe;


        //Find recipe id
        String jsonstring = "" + recipe;
        JSONObject json = new JSONObject(jsonstring);
        JSONArray recipearray = json.getJSONArray("results");
        JSONObject firstresult = recipearray.getJSONObject(0);
        int recipeid = firstresult.getInt("id");
        // String recipeID = String.valueOf(firstresult.getInt("id"));



        // Use recipe id to get recipe information

        HttpRequest request1 = HttpRequest.newBuilder().uri(URI.create("https://api.spoonacular.com/recipes/" + recipeid + "/information"))
                .header("X-API-Host", "https://api.spoonacular.com")
                .header("X-API-Key", "0702028f1e12446ca891a3eb2f36fd0e")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response1 = null;
        try {
            response1 = HttpClient.newHttpClient().send(request1, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String recipeinfo = response1.body();


        return recipeinfo;




    }

    // takes in result from Breakfast()
    @Override
    public List<Ingredient> CreateIngredients(String recipe){
        String jsonString ="" + recipe;
        JSONObject json = new JSONObject(jsonString);

        JSONArray ingredients = json.getJSONArray("extendedIngredients");
        List<Ingredient> list = new ArrayList<>();




        for (int i = 0; i < (ingredients.length() - 1);i ++){
            JSONObject ingredient = ingredients.getJSONObject(i);

            int id = ingredient.getInt("id");
            String name = ingredient.getString("name");
            String amount = String.valueOf(ingredient.getInt("amount"));
            String unit = ingredient.getString("unit");

            Ingredient finalingredient = new CommonIngredient(id, name, amount + unit);


            list.add(finalingredient);


        }
        return list;


    }

    @Override
    public Recipe CreateRecipeBreakfast(List<Ingredient> ingredients, String recipe, int userID) {
        String jsonString = "" + recipe;
        JSONObject json = new JSONObject(jsonString);
        Integer RecipeID = json.getInt("id");
        String name = json.getString("title");
        String instructions = json.getString("summary");
        HashMap<String, Double> nutritionalInfo = getRecipeNutritionalInfo(String.valueOf(RecipeID), userID);
        String link = json.getString("sourceUrl");
        String type = "breakfast";

        return new CommonRecipe(RecipeID, name, ingredients, instructions, type, nutritionalInfo, link);
    }


    @Override
    public String Lunch(int identifier) {
        User user = getAccountByUserID(identifier);
        int daily_cal = (int) computedRequiredCalories(identifier);
        int lunch_cals = Math.round((daily_cal/5)) * 2;
        String dietary = user.userSpecifiedDietary();
        List<String> allergies = user.userSpecifiedAllergies();
        HashMap<String, Double> conditions = user.userSpecifiedConditions();

        //conditions into format for api call

        StringBuilder conditionsaccum = new StringBuilder();
        for (Map.Entry<String, Double> entry : conditions.entrySet()){
            String condition = entry.getKey();
            int amount = (int) Math.round(entry.getValue());
            conditionsaccum.append("max" + condition + "=" + amount + "&");
        }
        //calories depending on what weight goal



        //make allergies into format for api call
        StringBuilder allergiesaccum = new StringBuilder();
        for (int i = 0; i < allergies.size(); i ++ ){
            if (i < allergies.size() - 1){
                allergiesaccum.append(allergies.get(i) + ",");}
            else {
                allergiesaccum.append(allergies.get(i));

            }

        }


        // diet and calories and allergies and conditions filtering through API call
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.spoonacular.com/recipes/complexSearch?&type=salad,soup&number=1&maxCalories=" + lunch_cals +"&diet="+ dietary + "&" + conditionsaccum + "intolerances="+ allergiesaccum))
                .header("X-API-Host", "https://api.spoonacular.com")
                .header("X-API-Key", "0702028f1e12446ca891a3eb2f36fd0e")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String recipe = response.body();

        // Find recipe id
        String jsonstring = "" + recipe;
        JSONObject json = new JSONObject(jsonstring);
        JSONArray recipearray = json.getJSONArray("results");
        JSONObject firstresult = recipearray.getJSONObject(0);
        int recipeid = firstresult.getInt("id");
        //String recipeID = String.valueOf(firstresult.getInt("id"));

        // Use recipe id to get recipe information

        HttpRequest request1 = HttpRequest.newBuilder().uri(URI.create("https://api.spoonacular.com/recipes/" + recipeid + "/information"))
                .header("X-API-Host", "https://api.spoonacular.com")
                .header("X-API-Key", "0702028f1e12446ca891a3eb2f36fd0e")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response1 = null;
        try {
            response1 = HttpClient.newHttpClient().send(request1, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String recipeinfo = response1.body();





        return recipeinfo;
    }


    @Override
    public Recipe CreateRecipeLunch(List<Ingredient> ingredients, String recipe, int userID) {
        String jsonString ="" + recipe;
        JSONObject json = new JSONObject(jsonString);
        Integer id = json.getInt("id");
        String name = json.getString("title");
        String instructions = json.getString("summary");
        HashMap<String, Double> nutritionalInfo = getRecipeNutritionalInfo(String.valueOf(id),userID);
        String link = json.getString("sourceUrl");
        String type = "lunch";

        return new CommonRecipe(id, name, ingredients, instructions, type, nutritionalInfo, link);
    }


    @Override
    public String Dinner(int identifier) {
        User user = getAccountByUserID(identifier);
        int daily_cal = (int) computedRequiredCalories(identifier);
        int dinner_cals = Math.round((daily_cal/5)) * 2;
        String dietary = user.userSpecifiedDietary();
        List<String> allergies = user.userSpecifiedAllergies();
        HashMap<String, Double> conditions = user.userSpecifiedConditions();


        StringBuilder conditionsaccum = new StringBuilder();
        for (Map.Entry<String, Double> entry : conditions.entrySet()){
            String condition = entry.getKey();
            int amount = (int) Math.round(entry.getValue());
            conditionsaccum.append("max" + condition + "=" + amount + "&");
        }
        //calories depending on what weight goal

        //make allergies into format for api call
        StringBuilder allergiesaccum = new StringBuilder();
        for (int i = 0; i < allergies.size(); i ++ ){
            if (i < allergies.size() - 1){
                allergiesaccum.append(allergies.get(i) + ",");}
            else {
                allergiesaccum.append(allergies.get(i));

            }

        }


        // String calorietype = "max";
        //if (user.getWeightGoalType().equals("gainWeight")) {
        //calorietype = "min";}


        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.spoonacular.com/recipes/complexSearch?&type=maincourse&number=1&maxCalories=" + dinner_cals +"&diet="+ dietary + "&" + conditionsaccum + "intolerances="+ allergiesaccum))
                .header("X-API-Host", "https://api.spoonacular.com")
                .header("X-API-Key", "0702028f1e12446ca891a3eb2f36fd0e")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String recipe = response.body();

        // Find recipe id
        String jsonstring = "" + recipe;
        JSONObject json = new JSONObject(jsonstring);
        JSONArray recipearray = json.getJSONArray("results");
        //return recipearray;

        JSONObject firstresult = recipearray.getJSONObject(0);
        int recipeid = firstresult.getInt("id");


        //String recipeID = String.valueOf(firstresult.getInt("id"));

        //Use recipe id to get recipe information

        HttpRequest request1 = HttpRequest.newBuilder().uri(URI.create("https://api.spoonacular.com/recipes/" + recipeid + "/information"))
                .header("X-API-Host", "https://api.spoonacular.com")
                .header("X-API-Key", "0702028f1e12446ca891a3eb2f36fd0e")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response1 = null;
        try {
            response1 = HttpClient.newHttpClient().send(request1, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String recipeinfo = response1.body();


        return recipeinfo;


    }

    @Override
    public Recipe CreateRecipeDinner(List<Ingredient> ingredients, String recipe, int userID) {
        String jsonString ="" + recipe;
        JSONObject json = new JSONObject(jsonString);
        Integer id = json.getInt("id");
        String name = json.getString("title");
        String instructions = json.getString("summary");
        HashMap<String, Double> nutritionalinfo = getRecipeNutritionalInfo(String.valueOf(id), userID);
        String link = json.getString("sourceUrl");
        String type = "dinner";

        return new CommonRecipe(id, name, ingredients, instructions, type, nutritionalinfo, link);
    }



    @Override
    public MealPlan getMealPlan(int userID){
        // Breakfast
        String breakfast_api = Breakfast(userID);
        List<Ingredient> ingredientsbreak = CreateIngredients(breakfast_api);
        Recipe breakfast = CreateRecipeBreakfast(ingredientsbreak, breakfast_api, userID);
        //Lunch
        String lunch_api = Lunch(userID);
        List<Ingredient> ingredientslunch = CreateIngredients(lunch_api);
        Recipe lunch = CreateRecipeLunch(ingredientslunch, lunch_api, userID);
        // Dinner
        String dinner_api = Dinner(userID);
        List<Ingredient> ingredientsdinner = CreateIngredients(dinner_api);
        Recipe dinner = CreateRecipeDinner(ingredientsdinner, dinner_api, userID);

        MealPlan mealplan = new CommonMealPlan(breakfast, lunch, dinner);
        mealplanaccounts.put(userID, mealplan);

        return mealplan;

    }
    @Override
    public void saveMealPlantoCsv(int id){
        int userid = id;

        MealPlan currentmealplan = getmealplanAccountsbyid(id);

        mealplancsvBuilder.mealplanbuildCsv(userid, currentmealplan);


    }

    public MealPlan getmealplanAccountsbyid(int id ){
        return mealplanaccounts.get(id);


    }

    @Override
    public String displayMealPlan(MealPlan mealplan){


        Recipe breakfast = mealplan.getBreakfast();
        Recipe lunch = mealplan.getLunch();
        Recipe dinner = mealplan.getDinner();

        String breakfastname = breakfast.getRecipeName();
        String brekfastingredients = breakfast.getRecipeIngredientString();
        String breakfastinstruct = breakfast.getRecipeInstructionsDisplay();
        String breakfastlink = breakfast.getRecipeLink();
        String breakfastnutrients = breakfast.getNutritionalInfoString();

        String breakfasttotal  = "🍳 BREAKFAST: " + breakfastname + "\n" + "Ingredients :  " + brekfastingredients + "\n" + "Summary : " + breakfastinstruct +
                breakfastlink + "\n" + "Nutritional Info: " + breakfastnutrients;


        String lunchname = lunch.getRecipeName();
        String lunchingredients = lunch.getRecipeIngredientString();
        String lunchinstruct = lunch.getRecipeInstructionsDisplay();
        String lunchlink = lunch.getRecipeLink();
        String lunchnutrients = lunch.getNutritionalInfoString();
        String lunchtotal  = "LUNCH: " + lunchname + "\n" + "Ingredients : " + lunchingredients + "\n" + "Summary : " + lunchinstruct +
                "\n" + lunchlink + "\n" + "Nutritional Info: " + lunchnutrients;



        String dinnername = dinner.getRecipeName();
        String dinneringredients = dinner.getRecipeIngredientString();
        String dinnerinstruct = dinner.getRecipeInstructionsDisplay();
        String dinnerlink = dinner.getRecipeLink();
        String dinnernutrients = dinner.getNutritionalInfoString();
        String dinnertotal  = "DINNER: "+ dinnername + "\n" + "Ingredients : " + dinneringredients + "\n" + "Summary : " + dinnerinstruct +
                "\n" + dinnerlink+ "\n" + "Nutritional Info: " + dinnernutrients;



        return breakfasttotal + "\n" + "\n" + lunchtotal  + "\n" + "\n" + dinnertotal;


    }

    public void loadUserDataFromCsv() {
        try {
            File csvFile = new File(csvFilePath);

            if (!csvFile.exists()) {

                return;
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    // Parse data from CSV and save to accounts map

                    int userId = Integer.parseInt(col[0]);
                    String username = col[1];
                    String password = col[2];
                    LocalDateTime creationTime = LocalDateTime.parse(col[3]);
                    boolean male = Boolean.parseBoolean(col[4]);
                    boolean female = Boolean.parseBoolean(col[5]);
                    HashMap<String, Boolean> gender = new HashMap<>();
                    gender.put("male", male);
                    gender.put("female", female);
                    double height = Double.parseDouble(col[6]);
                    double weight = Double.parseDouble(col[7]);
                    int age = Integer.parseInt(col[8]);
                    int exerciseLvl = Integer.parseInt(col[9]);
                    HashMap<String, Boolean> dietary = convertToDict(col[10]);
                    HashMap<String, Boolean> allergies = convertToDict(col[11]);
                    HashMap<String, String> conditions = convertToDict1(col[12]);
                    HashMap<String, Boolean> weightGoals = new HashMap<>();
                    boolean maintainTypeValue = Boolean.parseBoolean(col[13]);
                    boolean loseTypeValue = Boolean.parseBoolean(col[14]);
                    boolean gainTypeValue = Boolean.parseBoolean(col[15]);
                    weightGoals.put("maintainWeight", maintainTypeValue);
                    weightGoals.put("loseWeight", loseTypeValue);
                    weightGoals.put("gainWeight", gainTypeValue);
                    String paceType = col[16];
                    double requiredCalories = Double.parseDouble(col[17]);
                    ArrayList<String> trackedNutrients = convertToArrayList(col[18]);

                    UserFactory userFactory = new CommonUserFactory();
                    User user = userFactory.createdDefaultUser(userId, username);
                    user.setCreationTime(creationTime);
                    user.setPassword(password);
                    user.setUserAge(age);
                    user.setGender(gender);
                    user.setUserHeight(height);
                    user.setUserWeight(weight);
                    user.setUserExerciseLvl(exerciseLvl);
                    user.setDietary(dietary);
                    user.setAllergies(allergies);
                    user.setConditions(conditions);
                    user.setWeightGoalType(weightGoals);
                    user.setPaceType(paceType);
                    user.setRequiredCalories(requiredCalories);
                    user.setTrackedNutrients(trackedNutrients);

                    // Add the user to the accounts map
                    accounts.put(userId, user);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error loading user data from CSV", e);
        }
    }

    public HashMap<String, Boolean> convertToDict(String string) {
        String[] dictPairs = string.split(" ");
        HashMap<String, Boolean> dict = new HashMap<>();
        for (String pair:dictPairs){
            if (pair.equals("{}")){
                return dict;
            } else {
                String[] keyValuePair = pair.split("=");
                if (keyValuePair.length == 2) {
                    dict.put(keyValuePair[0], Boolean.valueOf(keyValuePair[1]));
                }
            }
        }
        return dict;
    }

    public HashMap<String, String> convertToDict1(String string) {
        String[] dictPairs = string.split(" ");
        HashMap<String, String> dict = new HashMap<>();
        for (String pair:dictPairs){
            if (pair.equals("{}")){
                return dict;
            } else {
                String[] keyValuePair = pair.split("=");
                if (keyValuePair.length == 2) {
                    dict.put(keyValuePair[0], keyValuePair[1]);
                }
            }
        }
        return dict;
    }

    public ArrayList<String> convertToArrayList(String string) {
        String[] items = string.split(" ");
        ArrayList<String> list = new ArrayList<>();
        for (String item : items) {
            if (item.equals("{}")) {
                return list;
            } else {
                list.add(item);
            }
        }
        return list;
    }

    public HashMap<String, Double> getRecipeNutritionalInfo(String recipeID, int userID) {
        // fetch the user's tracked nutrients
        ArrayList<String> trackedNutrients = getUserTrackedNutrientsData(userID);

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

            // only place into the return hashmap if the user would like to track that nutrient
            if (trackedNutrients.contains(nutrientName)) {
                // place into the hashmap
                recipeNutritionalInfo.put(nutrientName, nutrientValue);
            }
        }
        return recipeNutritionalInfo;
    }
}