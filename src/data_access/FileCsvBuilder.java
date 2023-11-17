package src.data_access;

import src.entity.User;

import java.io.*;
import java.util.Map;
public class FileCsvBuilder {

    private final String csvFilePath;

    public FileCsvBuilder(String csvFilePath) {
        this.csvFilePath = csvFilePath;
    }

    public void buildCsv(User user) {
        try {
            File csvFile = new File(csvFilePath);

            if (!csvFile.exists()) {
                // If the CSV file does not exist, create it and write the headers
                setHeaders(csvFile);
            }

            saveUsersToCsv(user, csvFile);

        } catch (IOException e) {
            throw new RuntimeException("Error building CSV", e);
        }
    }

    private void setHeaders(File csvFile) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile));
        writer.write("userId," +
                "username," +
                "password," +
                "creationTime," +
                "male," +
                "female," +
                "height," +
                "weight," +
                "age," +
                "exerciseLvl," +
                "dietaryRestriction1," +
                "maintainWeight," +
                "loseWeight," +
                "gainWeight," +
                "weightPaceType," +
                "requiredCalories");
        writer.newLine();
        writer.close();
    }

    private void saveUsersToCsv(User user, File csvFile) throws IOException {

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
                        user.getUserRestriction(),
                        user.getMaintainTypeValue(),
                        user.getLoseTypeValue(),
                        user.getGainTypeValue(),
                        user.getPaceType(),
                        user.getRequiredCalories());

                writer.write(line);
                writer.newLine();

        }


    }

}

