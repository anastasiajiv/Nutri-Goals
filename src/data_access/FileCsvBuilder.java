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
        writer.write("userId," + // 0
                "username," +//1
                "password," +//2
                "creationTime," +//3
                "male," +//4
                "female," +//5
                "height," +//6
                "weight," +//7
                "age," +//8
                "exerciseLvl," +//9
                "dietaryRestriction1," +//10
                "allergiesRestriction1," +//11
                "conditionsRestrictions1," +//12
                "maintainWeight," + //13
                "loseWeight," + //14
                "gainWeight," +// 15
                "weightPaceType," +// 16
                "requiredCalories"); //17
        writer.newLine();
        writer.close();
    }

    private void saveUsersToCsv(User user, File csvFile) throws IOException { // make common method

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

