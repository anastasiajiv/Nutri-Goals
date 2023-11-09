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

    public Map<Integer, User> accounts = new HashMap<>();

    private UserFactory userFactory;

    public FileUserDataAccessObject(String csvPath, UserFactory userFactory) throws IOException{
        this.userFactory = userFactory;

        csvFile = new File(csvPath);
        headers.put("userId", 0);
        headers.put("username", 1);
        headers.put("password", 2);
        headers.put("creationTime", 3);
        headers.put("height", 4);
        headers.put("weight", 5);
        headers.put("age", 6);
        headers.put("exerciseLvl", 7);
        headers.put("dietaryRestriction1", 8);
        headers.put("maintainWeight", 9);
        headers.put("loseWeight", 10);
        headers.put("gainWeight", 11);

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
                        "height," +
                        "weight," +
                        "age," +
                        "exerciseLvl," +
                        "dietaryRestriction1," +
                        "maintainWeight," +
                        "loseWeight," +
                        "gainWeight");

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    int userId = Integer.parseInt(col[headers.get("userId")]);
                    String username = String.valueOf(col[headers.get("username")]);
                    String password = String.valueOf(col[headers.get("password")]);
                    String creationTimeText = String.valueOf(col[headers.get("creationTime")]);

                    LocalDateTime ldt = LocalDateTime.parse(creationTimeText);

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

                    User user = userFactory.create(userId,
                            username,
                            password,
                            ldt,
                            height,
                            weight,
                            age,
                            exerciseLvl,
                            restrictions,
                            weightGoal);
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
                String line = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s",
                        user.getUserId(),
                        user.getName(),
                        user.getPassword(),
                        user.getCreationTime(),
                        user.getUserHeight(),
                        user.getUserWeight(),
                        user.getUserAge(),
                        user.getUserExcerciseLevel(),
                        user.getUserRestriction(),
                        user.getMaintainTypeValue(),
                        user.getLoseTypeValue(),
                        user.getGainTypeValue());

                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveNewUser(User user) {
        if (accounts.containsKey(user.getUserId()) == Boolean.FALSE) { // Dont add user if they already exist

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile, true))) {
                String line = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s",
                        user.getUserId(),
                        user.getName(),
                        user.getPassword(),
                        user.getCreationTime(),
                        user.getUserHeight(),
                        user.getUserWeight(),
                        user.getUserAge(),
                        user.getUserExcerciseLevel(),
                        user.getUserRestriction(),
                        user.getMaintainTypeValue(),
                        user.getLoseTypeValue(),
                        user.getGainTypeValue());
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

    @Override
    public void save(User user) {
        accounts.put(user.getUserId(), user);
    }

    @Override
    public void saveWeightGoalData(User user) {

    }

    @Override
    public String getUserWeightGoalData(int userId) {
        return null;
    }
}
