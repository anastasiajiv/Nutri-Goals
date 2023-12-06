package tests.use_case.mealPlan;

import src.data_access.FileUserDataAccessObject;
import src.data_access.InMemoryMealPlanAccessObject;
import src.entity.CommonUserFactory;
import src.entity.Recipe;
import src.entity.User;
import src.entity.UserFactory;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class mealPlanInteractorTest {
    private FileUserDataAccessObject fileUserDAO;
    private final String csvFilePath = "./users.csv";
    private final String csvMealPlanFilePath = "./mealplan.csv";  // might be wrong
    private final UserFactory userFactory = new CommonUserFactory();

    @BeforeEach
    void setUp() {
        // create a new fileUserDAO
        this.fileUserDAO = new FileUserDataAccessObject(this.csvFilePath, this.csvMealPlanFilePath);
    }

    @Test
    void saveUserData() {
        setUp();
        int userID = 101;
        String username = "TestUser";
        String password = "TestPassword";
        LocalDateTime creationTime = LocalDateTime.now();

        // create a new user; calls builder
        fileUserDAO.saveUserSignUpData(userID, username, password, creationTime);

        // asserts that the user was successfully saved
        assertTrue(this.fileUserDAO.existByUserID(userID));
        assertNotNull(this.fileUserDAO.getAccountByUserID(userID));
    }

    @Test
    void saveUserTrackedNutrientsData_calories() {
        setUp();
        int userID = 101;
        ArrayList<String> trackedNutrients = new ArrayList<>();
        trackedNutrients.add("Calories");

        // save the tracked nutrients to the account associated with userID
        this.fileUserDAO.saveTrackedNutrientsData(trackedNutrients, userID);

        // assert that the user exists
        assertTrue(this.fileUserDAO.existByUserID(userID));

        // fetch the account and assert its not null
        User user = this.fileUserDAO.getAccountByUserID(userID);
        assertNotNull(user);

        // assert that the tracked nutrients is empty
        assertEquals(1,user.getTrackedNutrients().size());
    }

    @Test
    void saveUserTrackedNutrientsData_empty() {
        setUp();
        int userID = 101;
        ArrayList<String> trackedNutrients = new ArrayList<>();

        // save the tracked nutrients to the account associated with userID
        this.fileUserDAO.saveTrackedNutrientsData(trackedNutrients, userID);

        // assert that the user exists
        assertTrue(this.fileUserDAO.existByUserID(userID));

        // fetch the account and assert its not null
        User user = this.fileUserDAO.getAccountByUserID(userID);
        assertNotNull(user);

        // assert that the tracked nutrients is empty
        assertEquals(0,user.getTrackedNutrients().size());
    }

    @Test
    void fetchRecipeNutritionalInfo() {
        saveUserTrackedNutrientsData_calories();

        int userID = 101;
        String recipeID = "1003464";

//        HashMap<String, Double> nutritionalInfo = new HashMap<>();
//        nutritionalInfo.put("Calories", 316.49);
//        nutritionalInfo.put("Fat", 12.09);
//        nutritionalInfo.put("Saturated Fat", 3.98);
//        nutritionalInfo.put("Carbohydrates", 49.25);
//        nutritionalInfo.put("Net Carbohydrates", 46.76);
//        nutritionalInfo.put("Sugar", 21.98);
//        nutritionalInfo.put("Cholesterol", 1.88);
//        nutritionalInfo.put("Sodium", 279.1);
//        nutritionalInfo.put("Protein", 3.79);

        HashMap<String, Double> returnedInfo = this.fileUserDAO.getRecipeNutritionalInfo(recipeID, userID);

        assertEquals(316.49, returnedInfo.get("Calories"));

//        for (Map.Entry<String, Double> entry : nutritionalInfo.entrySet()) {
//            assertEquals(nutritionalInfo.get(entry), returnedInfo.get(entry));
//        }
    }
}
