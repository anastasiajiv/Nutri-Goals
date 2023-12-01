package src.data_access;

import java.io.*;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.*;

import src.entity.*;
import src.entity.User;
import src.entity.UserFactory;
import src.use_case.mealplan.MealPlanDataAccessInterface;
import src.use_case.signup.SignupUserDataAccessInterface;
import src.use_case.weightgoal.WeightGoalUserDataInterface;
import src.use_case.preferences.PreferencesUserDataAccessInterface;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;


// import src.entity.PreferenceInfo;


public class FileUserDataAccessObject implements SignupUserDataAccessInterface, WeightGoalUserDataInterface, PreferencesUserDataAccessInterface, MealPlanDataAccessInterface {


    File csvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    //private final Map<Integer, User> accounts = new HashMap<>();

    public Map<Integer, User> accounts = new HashMap<>(); // Testing purposes in MAIN

    private UserFactory userFactory;

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
                        "weightPaceType" +
                        "requiredCalories");

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
                String line = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s","%s","%s",
                        user.getUserId(),
                        user.getName(),
                        user.getPassword(),
                        user.getCreationTime(),
                        user.isMale(),
                        user.isFemale(),
                        user.getUserHeight(),
                        user.getUserWeight(),
                        user.getUserAge(),
                        user.getUserExcerciseLevel(),
                        "Dietary", // since get returns only those that are true
                        "Allergies",
                        "Conditions",
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
                String line = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s",
                        user.getUserId(),
                        user.getName(),
                        user.getPassword(),
                        user.getCreationTime(),
                        user.isMale(),
                        user.isFemale(),
                        user.getUserHeight(),
                        user.getUserWeight(),
                        user.getUserAge(),
                        user.getUserExcerciseLevel(),
                        user.getDietary(),
                        user.getAllergies(),
                        user.getConditions(),
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
    public boolean existById(int userId) {
        return accounts.containsKey(userId);
    }

    public User getAccountByUserId(int userId) {
        return accounts.get(userId);
    }

    @Override
    public void save(User user) {
        accounts.put(user.getUserId(), user);
    }

    @Override
    public void saveWeightGoalData(User updatedUser) { // Should only be called for existing
        assert accounts.containsKey(updatedUser.getUserId());

        int userId = updatedUser.getUserId();
        if (existById(userId)) {
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

                    if (currentUserId == userId) {
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
                                updatedUser.getUserExcerciseLevel(),
                                updatedUser.getDietary(),
                                updatedUser.getAllergies(),
                                updatedUser.getConditions(),
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
    public Boolean existByUserId(int userId) {
        return accounts.containsKey(userId);
    }

    @Override
    public String getWeightGoalType(int userId) { // For testing
        return accounts.get(userId).getWeightGoalType(); // returns the weight goal type for this user
    }

    //Weight Goal Methods to get calories user needs

    public double getRequiredCalories(int userId) throws Exception {
        User user = getAccountByUserId(userId);
        double reqCalories = getBMR(userId);

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
    public double getBMR(int userId) {
        // Men: BMR = 88.63 + (13.397 * weight in kg) + (4.799 * height in cm) - (5.677 * age in years)
        // Miffin - St Jeor Equation -> BMR = 10 * weight + 6.25 * height - 5 * age + 5
        // Women: BMR = 447.593 + (9.247 x weight in kg) + (3.098 x height in cm) – (4.330 x age in years)
        // Miffin - St Jeor Equation -> BMR = 10 * weight + 6.25 * height - 5 * age - 161


        // Harris - Benedict -> Men -> BMR = 66 + (13.7 x wt in kg) + (5 x ht in cm) - (6.8 x age in years)
        // Harris - Benedict -> Men -> BMR =  655 + (9.6 x wt in kg) + (1.8 x ht in cm) - (4.7 x age in years)
        assert existById(userId);
        double userBMR = 0;


        User user = accounts.get(userId);
        //Get BMR
        if (Boolean.valueOf(user.isMale())) {
            userBMR = (10 * user.getUserWeight()) + (6.25 * user.getUserHeight()) - (5 * user.getUserAge()) + 5;
        }
        else if (Boolean.valueOf(user.isFemale())) {
            userBMR = (10 * user.getUserWeight()) + (6.25 * user.getUserHeight()) - (5 * user.getUserAge()) - 161;
        }
        return getBMRAfterActivityMultiplier(userId, userBMR);
    }
    public double getBMRAfterActivityMultiplier(int userId, double userBMR) {
        User user = accounts.get(userId);
        double newUserBMR = userBMR;

        assert user.getUserExcerciseLevel() >= 1 && user.getUserExcerciseLevel() <=5; // Must be in the range 1-5.

        if (user.getUserExcerciseLevel() ==1) {
            newUserBMR = newUserBMR * 1.2;
        }
        else if (user.getUserExcerciseLevel() == 2) {
            newUserBMR = newUserBMR * 1.375;
        }
        else if (user.getUserExcerciseLevel() == 3) {
            newUserBMR = newUserBMR * 1.55;
        }
        else if (user.getUserExcerciseLevel() == 4) {
            newUserBMR = newUserBMR * 1.725;
        }
        else if (user.getUserExcerciseLevel() == 5) {
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
    public boolean existsByID(int id) {
        return false;
    }

    @Override
    public void saveConditions(HashMap<Integer, HashMap<String, String>> conditions){
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
    public String Breakfast(int identifier) {
        //find all necessary sorting attributes and do calculations
        User user = getAccountByUserId(identifier);
        int daily_cal = user.getRequiredCalories();
        String breakfast_cals = String.valueOf(Math.round((daily_cal/5)));
        String dietary = user.getDietary();
        List<String> allergies = user.getAllergies();
        HashMap<String, Double> conditions = user.getConditions();



                //conditions made to be called
                StringBuilder conditionsaccum = new StringBuilder();
                for (Map.Entry<String, Double> entry : conditions.entrySet()){
                    String condition = entry.getKey();
                    Double amount = entry.getValue();
                    conditionsaccum.append("max" + condition + "=" + amount + "&");
                }
                //calories depending on what weight goal

                String calorietype = "max";
                    if (user.WeightGoalType().equals("gainWeight")) {
                        calorietype = "min";}



        // diet and calories and allergies and conditions filtering through API call
                    HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create("https://api.spoonacular.com/recipes/complexSearch?&type=breakfast&number=1&" +
                                calorietype + "Calories=" + breakfast_cals +"&diet="+ dietary + "&" + conditionsaccum + "intolerances="+ allergies))
                        .header("X-RapidAPI-Host", "https://api.spoonacular.com")
                        .header("X-RapidAPI-Key", "0702028f1e12446ca891a3eb2f36fd0e")
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
                String recipeID = String.valueOf(firstresult.getInt("id"));

                // Use recipe id to get recipe information

                HttpRequest request1 = HttpRequest.newBuilder().uri(URI.create("https://api.spoonacular.com/recipes/{" + recipeID + "}/information"))
                .header("X-RapidAPI-Host", "https://api.spoonacular.com")
                .header("X-RapidAPI-Key", "0702028f1e12446ca891a3eb2f36fd0e")
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
    public Recipe CreateRecipeBreakfast(List<Ingredient> ingredients, String recipe) {
        String jsonString ="" + recipe;
        JSONObject json = new JSONObject(jsonString);
        Integer id = json.getInt("id");
        String name = json.getString("title");
        String instructions = json.getString("summary");
        HashMap<String, Float> nutritionalinfo = getRecipeNutritionalInfo(String.valueOf(id));
        String link = json.getString("sourceUrl");
        String type = "breakfast";

        return new CommonRecipe(id, name, ingredients, instructions, type, nutritionalinfo, link);



    }


    @Override
    public String Lunch(int identifier) {
        User user = getAccountByUserId(identifier);
        int daily_cal = user.getRequiredCalories();
        // TODO change names of methods **
        String lunch_cals = String.valueOf((2*(Math.round((daily_cal/5)))));
        String dietary = user.getDietary();
        List<String> allergies = user.getAllergies();
        HashMap<String, Double> conditions = user.getConditions();

        StringBuilder conditionsaccum = new StringBuilder();
        for (Map.Entry<String, Double> entry : conditions.entrySet()){
            String condition = entry.getKey();
            Double amount = entry.getValue();
            conditionsaccum.append("max" + condition + "=" + amount + "&");
        }
        //calories depending on what weight goal

        String calorietype = "max";
        if (user.WeightGoalType().equals("gainWeight")) {
            calorietype = "min";}



        // diet and calories and allergies and conditions filtering through API call
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.spoonacular.com/recipes/complexSearch?&type=maincourse&number=1&" +
                        calorietype + "Calories=" + lunch_cals +"&diet="+ dietary + "&" + conditionsaccum + "intolerances="+ allergies))
                .header("X-RapidAPI-Host", "https://api.spoonacular.com")
                .header("X-RapidAPI-Key", "0702028f1e12446ca891a3eb2f36fd0e")
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
        String jsonstring = recipe;
        JSONObject json = new JSONObject(jsonstring);
        JSONArray recipearray = json.getJSONArray("results");
        JSONObject firstresult = recipearray.getJSONObject(0);
        int recipeid = firstresult.getInt("id");
        String recipeID = String.valueOf(firstresult.getInt("id"));

        // Use recipe id to get recipe information

        HttpRequest request1 = HttpRequest.newBuilder().uri(URI.create("https://api.spoonacular.com/recipes/{" + recipeID + "}/information"))
                .header("X-RapidAPI-Host", "https://api.spoonacular.com")
                .header("X-RapidAPI-Key", "0702028f1e12446ca891a3eb2f36fd0e")
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
    public Recipe CreateRecipeLunch(List<Ingredient> ingredients, String recipe) {
        String jsonString ="" + recipe;
        JSONObject json = new JSONObject(jsonString);
        Integer id = json.getInt("id");
        String name = json.getString("title");
        String instructions = json.getString("summary");
        HashMap<String, Float> nutritionalinfo = getRecipeNutritionalInfo(String.valueOf(id));
        String link = json.getString("sourceUrl");
        String type = "lunch";

        return new CommonRecipe(id, name, ingredients, instructions, type, nutritionalinfo, link);
    }


    @Override
    public String Dinner(int identifier) {
        User user = getAccountByUserId(identifier);
        int daily_cal = user.getRequiredCalories();
        String dinner_cals = String.valueOf((2*(Math.round((daily_cal/5)))));
        String dietary = user.getDietary();
        List<String> allergies = user.getAllergies();
        HashMap<String, Double> conditions = user.getConditions();

        StringBuilder conditionsaccum = new StringBuilder();
        for (Map.Entry<String, Double> entry : conditions.entrySet()){
            String condition = entry.getKey();
            Double amount = entry.getValue();
            conditionsaccum.append("max" + condition + "=" + amount + "&");
        }
        //calories depending on what weight goal

        String calorietype = "max";
        if (user.WeightGoalType().equals("gainWeight")) {
            calorietype = "min";}


        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.spoonacular.com/recipes/complexSearch?&type=maincourse&number=1&" +
                        calorietype + "Calories=" + dinner_cals +"&diet="+ dietary + "&" + conditionsaccum + "intolerances="+ allergies))
                .header("X-RapidAPI-Host", "https://api.spoonacular.com")
                .header("X-RapidAPI-Key", "0702028f1e12446ca891a3eb2f36fd0e")
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
        String jsonstring = recipe;
        JSONObject json = new JSONObject(jsonstring);
        JSONArray recipearray = json.getJSONArray("results");
        JSONObject firstresult = recipearray.getJSONObject(0);
        int recipeid = firstresult.getInt("id");
        String recipeID = String.valueOf(firstresult.getInt("id"));

        // Use recipe id to get recipe information

        HttpRequest request1 = HttpRequest.newBuilder().uri(URI.create("https://api.spoonacular.com/recipes/{" + recipeID + "}/information"))
                .header("X-RapidAPI-Host", "https://api.spoonacular.com")
                .header("X-RapidAPI-Key", "0702028f1e12446ca891a3eb2f36fd0e")
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
    public Recipe CreateRecipeDinner(List<Ingredient> ingredients, String recipe) {
        String jsonString ="" + recipe;
        JSONObject json = new JSONObject(jsonString);
        Integer id = json.getInt("id");
        String name = json.getString("title");
        String instructions = json.getString("summary");
        HashMap<String, Float> nutritionalinfo = getRecipeNutritionalInfo(String.valueOf(id));
        String link = json.getString("sourceUrl");
        String type = "dinner";

        return new CommonRecipe(id, name, ingredients, instructions, type, nutritionalinfo, link);
    }




    @Override
    public MealPlan getMealPlan(int id){
        // Breakfast
        String breakfast_api = Breakfast(id);
        List<Ingredient> ingredientsbreak = CreateIngredients(breakfast_api);
        Recipe breakfast = CreateRecipeBreakfast(ingredientsbreak, breakfast_api);
        //Lunch
        String lunch_api = Lunch(id);
        List<Ingredient> ingredientslunch = CreateIngredients(lunch_api);
        Recipe lunch = CreateRecipeLunch(ingredientslunch, lunch_api);
        // Dinner
        String dinner_api = Dinner(id);
        List<Ingredient> ingredientsdinner = CreateIngredients(dinner_api);
        Recipe dinner = CreateRecipeLunch(ingredientsdinner, dinner_api);

        return new CommonMealPlan(breakfast, lunch, dinner);

    }




    @Override
    public String displayMealPlan(MealPlan mealplan){


        Recipe breakfast = mealplan.getBreakfast();
        Recipe lunch = mealplan.getLunch();
        Recipe dinner = mealplan.getDinner();

        String breakfastname = breakfast.getRecipeName();
        String brekfastingredients = breakfast.getrecipeIngredientstring();
        String breakfastinstruct = breakfast.getRecipeInstructions();
        String breakfastlink = breakfast.getrecipelink();
        String breakfasttotal  = "Breakfast: " + breakfastname + "\n" + "Ingredients :  " + brekfastingredients + "\n" + "Summary : " + breakfastinstruct +
                "\n" + breakfastlink;


        String lunchname = lunch.getRecipeName();
        String lunchingredients = lunch.getrecipeIngredientstring();
        String lunchinstruct = lunch.getRecipeInstructions();
        String lunchlink = lunch.getrecipelink();
        String lunchtotal  = "Lunch: " + lunchname + "\n" + "Ingredients : " + lunchingredients + "\n" + "Summary : " + lunchinstruct +
                "\n" + lunchlink;



        String dinnername = dinner.getRecipeName();
        String dinneringredients = dinner.getrecipeIngredientstring();
        String dinnerinstruct = dinner.getRecipeInstructions();
        String dinnerlink = dinner.getrecipelink();
        String dinnertotal  = "Dinner: "+ dinnername + "\n" + "Ingredients : " + dinneringredients + "\n" + "Summary : " + dinnerinstruct +
                "\n" + dinnerlink;



        return breakfasttotal + "\n" + lunchtotal  + "\n"  + dinnertotal;


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
        } catch (IOException e) {
            e.printStackTrace();
        }
        String recipe = response.body();

        // find the nutritional info
        JSONObject json = new JSONObject(recipe);
        JSONArray recipeArray = json.getJSONArray("nutrients");  // get an array of nutrients

        // initialize a storage hashmap for the nutrients
        HashMap<String, Float> recipeNutritionalInfo = new HashMap<>();

        for (int i = 0; i < recipeArray.length(); i++) {
            // each nutrient is in its own array
            JSONArray nutrientArray = recipeArray.getJSONArray(i);
            String nutrientName = nutrientArray.getString("name");
            double nutrient = nutrientArray.getDouble("amount");

            // place into the hashmap
            recipeNutritionalInfo.put(nutrientName, nutrient);
        }
        return recipeNutritionalInfo;
    }
}








