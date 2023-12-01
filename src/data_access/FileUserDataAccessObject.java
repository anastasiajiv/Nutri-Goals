package src.data_access;

import java.io.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.HashMap;
import java.util.Map;

import src.entity.CommonUserFactory;
import src.entity.User;
import src.entity.UserFactory;
import src.use_case.preferences.PreferencesUserDataAccessInterface;
import src.use_case.signup.SignupUserDataAccessInterface;
import src.use_case.weightgoal.WeightGoalUserDataInterface;



import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient;
import java.io.IOException;
import org.json.*;

public class FileUserDataAccessObject implements SignupUserDataAccessInterface, WeightGoalUserDataInterface, PreferencesUserDataAccessInterface {

    private final String csvFilePath;
    private final FileCsvBuilder csvBuilder;
    public Map<Integer, User> accounts = new HashMap<>();

    private final UserFactory userFactory;

    public FileUserDataAccessObject(String csvFilePath, UserFactory userFactory) {
        this.csvFilePath = csvFilePath;
        this.csvBuilder = new FileCsvBuilder(csvFilePath);
        this.accounts = new HashMap<>();
        this.userFactory = userFactory;

    }


    @Override
    public void saveUserSignUpData(int userId,
                                   String username,
                                   String password,
                                   LocalDateTime creationTime) {

        UserFactory userFactory = new CommonUserFactory();
        User newUser = userFactory.createdDefaultUser(userId, username);
        newUser.setPassword(password);
        newUser.setCreationTime(creationTime);


        csvBuilder.buildCsv(newUser, 0);


        accounts.put(userId, newUser);
    }



    @Override
    public void saveWeightGoalData(int userId,
                                   HashMap<String, Boolean> gender,
                                   double height,
                                   double weight,
                                   int age,
                                   int exerciseLvl,
                                   String paceType,
                                   HashMap<String, Boolean> weightGoal) {

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

        accounts.put(userId, curr_user);

        // Now compute req calories
        double requiredCalories = computedRequiredCalories(userId);
        // Save this new data
        curr_user.setRequiredCalories(requiredCalories);
        // Update user into accounts map to account for newly updated req calories

        accounts.put(userId, curr_user);

        // Save updated user values into the Csv file
        csvBuilder.buildCsv(curr_user, 1);




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
    public Boolean existByUserId(int userId) {
        return accounts.containsKey(userId);
    }



    @Override
    public double computedRequiredCalories(int userId) {
        User user = getAccountByUserId(userId);
        double reqCalories = getBMR(userId);

        if (user.getWeightGoalType().equals("maintainWeight")) {
            reqCalories = reqCalories;
            }

        else if (user.getWeightGoalType().equals("gainWeight")) {
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
        else if (user.getWeightGoalType().equals("loseWeight")) {
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
    File csvFile = new File(csvFilePath); // consistent naming
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
        File csvFile = new File(csvFilePath);
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
    public boolean existsByID(int userId) {
        return accounts.containsKey(userId);
    }

    @Override
    public void saveConditions(HashMap<Integer, HashMap<String, String>> conditions){
        File csvFile = new File(csvFilePath);
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



}
