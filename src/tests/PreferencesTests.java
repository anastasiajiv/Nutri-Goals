package src.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.data_access.FileUserDataAccessObject;
import src.entity.CommonUserFactory;
import src.entity.UserFactory;
import java.util.HashMap;
import src.entity.User;
import static org.junit.jupiter.api.Assertions.*;
public class PreferencesTests {
    private FileUserDataAccessObject userDataAccessObject;
    private final String testCsvFilePath = "./test_users.csv";

    private final String testMealPlanFilePath = "./meal_plan.csv";

    private final UserFactory userFactory = new CommonUserFactory();

    @BeforeEach
    void setUp() {
        this.userDataAccessObject = new FileUserDataAccessObject(testCsvFilePath, testMealPlanFilePath, userFactory);
    }

    @Test
    void savePreferencesData_saveUserAndCsv(){
        int userID = 4;
        HashMap<String, Boolean> dietaryTest = new HashMap<>();
        dietaryTest.put("Vegan", Boolean.FALSE);
        dietaryTest.put("Vegetarian", Boolean.TRUE);
        dietaryTest.put("Pescatarian", Boolean.FALSE);
        dietaryTest.put("none1", Boolean.FALSE);

        HashMap<String, Boolean> allergiesTest = new HashMap<>();
        allergiesTest.put("Egg", Boolean.FALSE);
        allergiesTest.put("Peanut", Boolean.TRUE);
        allergiesTest.put("Seafood", Boolean.FALSE);
        allergiesTest.put("Soy", Boolean.FALSE);
        allergiesTest.put("Tree Nut", Boolean.TRUE);
        allergiesTest.put("Wheat", Boolean.FALSE);
        allergiesTest.put("none", Boolean.FALSE);

        HashMap<String, String> conditionsTest = new HashMap<>();
        conditionsTest.put("Calcium", "high");
        conditionsTest.put("Potassium", "average");
        conditionsTest.put("Vitamin C", "average");
        conditionsTest.put("Vitamin D", "low");
        conditionsTest.put("Iron", "average");
        conditionsTest.put("Magnesium", "low");
        conditionsTest.put("Sugar", "low");
        Boolean setPreferences = userDataAccessObject.savePreferences(userID, dietaryTest,
                allergiesTest, conditionsTest);

        // assertion statements
        //testing that the preferences have been set in the csv
        assertTrue(setPreferences);
        // testing that the user can be found by ID
        assertNotNull(userDataAccessObject.getAccountByUserID(userID));
        // testing that the right preferences is set in terms of dietary
        User testUser = userDataAccessObject.getAccountByUserID(userID);
        assert(testUser.getDietary().equals(dietaryTest));
        // testing that the right preference is set in terms of allergies
        assert(testUser.getAllergies().equals(allergiesTest));
        // testing that the right preference is set in terms of conditions
        assert(testUser.getConditions().equals(conditionsTest));

    }
}
