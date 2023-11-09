package src.data_access;

import java.io.*;

import java.util.LinkedHashMap;
import java.util.HashMap;
import java.util.Map;

import src.entity.User;
import src.entity.UserFactory;
import src.use_case.signup.SignupUserDataAccessInterface;

public class FileUserDataAccessObject implements SignupUserDataAccessInterface {


    private final File csvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<Integer, User> accounts = new HashMap<>();

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
        headers.put("dietaryRestriction", 8);
        headers.put("maintainWeight", 9);
        headers.put("loseWeight", 10);
        headers.put("gainWeight", 11);

        if (csvFile.length() == 0) {
            setHeaders();
        }
        else {


        }


    }

    private void setHeaders() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (User user: accounts.values()) {
                String line = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s",
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


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public boolean existById(int userId) {
        return accounts.containsKey(userId);
    }

    @Override
    public void save(User user) {
        accounts.put(user.getUserId(), user);
    }
}
