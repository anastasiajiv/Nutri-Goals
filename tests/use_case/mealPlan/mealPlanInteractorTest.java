package tests.use_case.mealPlan;

import src.data_access.FileUserDataAccessObject;
import src.data_access.InMemoryMealPlanAccessObject;
import src.entity.CommonUserFactory;
import src.entity.Recipe;
import src.entity.UserFactory;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class mealPlanInteractorTest {
    private FileUserDataAccessObject fileUserDAO;
    private final String csvFilePath = "./users.csv";
    private final String csvMealPlanFilePath = "./mealplan.csv";  // might be wrong
    private final UserFactory userFactory = new CommonUserFactory();

    @BeforeEach
    void setUp() throws IOException {
        // create a new fileUserDAO
        this.fileUserDAO = new FileUserDataAccessObject(this.csvFilePath, this.csvMealPlanFilePath);
    }

    @Test
    void fetchRecipeNutritionalInfo() {
        String recipeID = "1003464";
        HashMap<String, Double> nutritionalInfo = new HashMap<>();
        nutritionalInfo.put("Calories", 316.49);
        nutritionalInfo.put("Fat", 12.09);
        nutritionalInfo.put("Saturated Fat", 3.98);
        nutritionalInfo.put("Carbohydrates", 49.25);
        nutritionalInfo.put("Net Carbohydrates", 46.76);
        nutritionalInfo.put("Sugar", 21.98);
        nutritionalInfo.put("Cholesterol", 1.88);
        nutritionalInfo.put("Sodium", 279.1);
        nutritionalInfo.put("Protein", 3.79);

        HashMap<String, Double> returnedInfo = this.fileUserDAO.getRecipeNutritionalInfo(recipeID);

        for (Map.Entry<String, Double> entry : nutritionalInfo.entrySet()) {
            assertEquals(nutritionalInfo.get(entry), returnedInfo.get(entry));
        }
    }
}
