package src.data_access;

import src.entity.User;

import java.io.*;
import java.util.Map;
public class FileCsvBuilder {

    private final String csvFilePath;

    public FileCsvBuilder(String csvFilePath) {
        this.csvFilePath = csvFilePath;
    }

    public Boolean buildCsv(User user, int saveType) {
        // 0 -> first initial save
        // 1 -> to update existing user
        try {
            File csvFile = new File(csvFilePath);

            if (!csvFile.exists()) {
                // If the CSV file does not exist, create it and write the headers
                setHeaders(csvFile);
                saveUsersToCsv(user, csvFile);
            }else {
                if (saveType == 0) {
                    saveUsersToCsv(user, csvFile);
                } else {
                    updateUsersToCsv(user, csvFile);
                }
            }
            return true;
        } catch (IOException e) {
            System.out.println("Error, Could not save or update data properly");
            return false;

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

        if (!isUserIdAlreadyInCsv(user.getUserId(), csvFile)) {
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
    private boolean isUserIdAlreadyInCsv(int userId, File csvFile) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            // Skip the header line
            reader.readLine();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(",");
                int existingUserId = Integer.parseInt(columns[0].trim());
                if (existingUserId == userId) {
                    return true; // User ID already exists in the CSV file
                }
            }
        }
        return false; // User ID not found in the CSV file
    }

    private void updateUsersToCsv(User user, File csvFile) throws IOException {
        int userId = user.getUserId();

        BufferedReader reader = new BufferedReader(new FileReader(csvFile));
        StringBuilder updatedCsvContent = new StringBuilder();

        String header = reader.readLine();
        updatedCsvContent.append(header).append(System.lineSeparator());

        String line;
        while ((line = reader.readLine()) != null) {
            // Split the line into columns
            String[] columns = line.split(",");

            // Check if the userId matches
            if (Integer.parseInt(columns[0]) == userId) {
                // Update the columns with the new user information
                columns[1] = user.getName();
                columns[2] = user.getPassword();
                columns[3] = user.getCreationTime().toString();
                columns[4] = String.valueOf(user.isMale());
                columns[5] = String.valueOf(user.isFemale());
                columns[6] = String.valueOf(user.getUserHeight());
                columns[7] = String.valueOf(user.getUserWeight());
                columns[8] = String.valueOf(user.getUserAge());
                columns[9] = String.valueOf(user.getUserExerciseLevel());
                columns[10] = String.valueOf(user.getDietary());
                columns[11] = String.valueOf(user.getMaintainTypeValue());
                columns[12] = String.valueOf(user.getLoseTypeValue());
                columns[13] = String.valueOf(user.getGainTypeValue());
                columns[14] = user.getPaceType();
                columns[15] = String.valueOf(user.getRequiredCalories());

                // Join the columns back into a line
                line = String.join(",", columns);


            }
            updatedCsvContent.append(line).append(System.lineSeparator());
        }
        reader.close();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile))) {
            writer.write(updatedCsvContent.toString());
        }
    }

}

