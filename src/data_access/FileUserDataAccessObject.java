package src.data_access;


import java.io.*;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.HashMap;
import java.util.Map;

import src.entity.User;
import src.entity.UserFactory;
import src.use_case.signup.SignupUserDataAccessInterface;
import src.use_case.weightgoal.WeightGoalUserDataInterface;

public class FileUserDataAccessObject implements SignupUserDataAccessInterface, WeightGoalUserDataInterface {


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
        headers.put("maintainWeight", 11);
        headers.put("loseWeight", 12);
        headers.put("gainWeight", 13);
        headers.put("requiredCalories", 14);
        headers.put("nutrients", 15); // added nutrients header; holds the nutrients the user wants to track

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
                        "dietaryRestriction1," +
                        "maintainWeight," +
                        "loseWeight," +
                        "gainWeight," +
                        "requiredCalories," + // added nutrients header
                        "nutrients");

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

                    String restrictionKey1 = "dietaryRestriction1"; // Replace with each type of restriction
                    Boolean restrictionValue1 = Boolean.valueOf(col[headers.get("dietaryRestriction1")]);

                    HashMap<String, Boolean> restrictions = new HashMap<>();
                    restrictions.put(restrictionKey1, restrictionValue1);

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

                    int requiredCalories = Integer.parseInt(col[headers.get("requiredCalories")]);



                    /* logistics of implementation: nutrients attribute only holds key-value pairs for those
                    that want to be tracked, if headers must be made by default, should it be changed?

                    Or: should nutrients that don't want to be tracked be initialized as 0 and just never
                    be updated? Might not be efficient.
                    */

                    // added nutrients
                    String nutrientsKey1 = "nutrient1"; // replace with each type of nutrient tracked
                    // ...
                    HashMap<String, Float> nutrients = new HashMap<>();
                    // ... I don't know what this chunk of code is doing, so I will leave the rest...


                    User user = userFactory.create(userId,
                            username,
                            password,
                            ldt,
                            gender,
                            height,
                            weight,
                            age,
                            exerciseLvl,
                            restrictions,
                            nutrients,
                            weightGoal,
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

            for (User user: accounts.values()) { // added nutrients
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
                        user.getUserExerciseLevel(),
                        user.getUserRestriction(),
                        user.getNutrients(),
                        user.getMaintainTypeValue(),
                        user.getLoseTypeValue(),
                        user.getGainTypeValue(),
                        user.getRequiredCalories(),
                        user.getNutrients());

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
                        user.getUserExerciseLevel(),
                        user.getUserRestriction(),
                        user.getMaintainTypeValue(),
                        user.getLoseTypeValue(),
                        user.getGainTypeValue(),
                        user.getRequiredCalories(),
                        user.getNutrients());
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

                    if (currentUserId == userId) {  // added nutrients
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
                                updatedUser.getUserRestriction(),
                                updatedUser.getMaintainTypeValue(),
                                updatedUser.getLoseTypeValue(),
                                updatedUser.getGainTypeValue(),
                                updatedUser.getRequiredCalories(),
                                updatedUser.getNutrients());

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

    // note: need a getNutrients method to return the nutrients that want to be tracked?

    @Override
    public User getUserWeightGoalData(int userId) {
        return accounts.get(userId);
    }

    @Override
    public Boolean existByUserId(int userId) {
        return accounts.containsKey(userId);
    }

    @Override
    public String getWeightGoalType(int userId) {
        return accounts.get(userId).getWeightGoalType(); // returns the weight goal type for this user
    }

    //Weight Goal Methods to get calories user needs

    public double getRequiredCalories(int userId) throws Exception {
        User user = getAccountByUserId(userId);

        if (user.getWeightGoalType().equals("maintainWeight")) {
            double reqCalories = getBMR(userId);
            return reqCalories;
        }
        else if (user.getWeightGoalType().equals("loseWeight")) {
            double reqCalories = getBMR(userId);
            // Check
        }
        else if (user.getWeightGoalType().equals("gainWeight")) {
            return 0;
        }
        return 0;
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
}
