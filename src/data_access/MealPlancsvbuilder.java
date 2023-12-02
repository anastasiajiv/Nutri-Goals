package src.data_access;

import src.entity.MealPlan;

import java.io.*;
import java.util.Map;

public class MealPlancsvbuilder {
    private final String csvFilePath;

    public MealPlancsvbuilder(String csvFilePath) {
        this.csvFilePath = csvFilePath;
    }




    public void mealplanbuildCsv(int id, MealPlan mealplan) {

        try {
            File csvFile = new File(csvFilePath);

            if (!csvFile.exists()) {
                // If the CSV file does not exist, create it and write the headers
                setHeaders(csvFile);
                saveMealPlanToCsv(id, mealplan, csvFile);

            }
            saveMealPlanToCsv(id, mealplan, csvFile);



        } catch (IOException e) {
            System.out.println("Error, Could not save or update data properly");

        }
    }


    private void setHeaders(File csvFile) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile));
        writer.write("userId," + // 0
                "mealplan" );
        writer.newLine();
        writer.close();
    }


    private void saveMealPlanToCsv(int id, MealPlan mealplan, File csvFile) throws IOException { // make common method


            try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile, true))) {
                String line = String.format("%s,%s",
                        id,
                       mealplan);

                writer.write(line);
                writer.newLine();

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
        return false;




}}


