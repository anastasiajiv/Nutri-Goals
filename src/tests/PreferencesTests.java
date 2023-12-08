
package src.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.data_access.FileUserDataAccessObject;
import src.entity.CommonUserFactory;
import src.entity.UserFactory;
import java.util.HashMap;
import src.entity.User;
import src.interface_adapters.ViewManagerModel;
import src.interface_adapters.logged_in.LoggedInViewModel;
import src.interface_adapters.preferences.PreferencesPresenter;
import src.interface_adapters.preferences.PreferencesViewModel;
import src.use_case.preferences.PreferencesInputData;
import src.use_case.preferences.PreferencesInteractor;
import src.use_case.preferences.PreferencesOutputBoundary;
import src.use_case.preferences.PreferencesOutputData;

import java.time.LocalDateTime;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
public class PreferencesTests {

    private final String testCsvFilePath = "./test_signUpUsers.csv";

    private final String testMealPlanFilePath = "./meal_plan.csv";

    private final UserFactory userFactory = new CommonUserFactory();

    HashMap<String, Boolean> testDietaryInput;

    HashMap<String, Boolean> testAllergiesInput;

    HashMap<String, String> testConditionsInput;

    Map<Integer, User> accounts = new HashMap<>();

    FileUserDataAccessObject userDataAccessObject = new FileUserDataAccessObject(testCsvFilePath,
            testMealPlanFilePath);


    @Test
    void savePreferencesData_saveUserAndCsv(){
        User currUser = userDataAccessObject.getAccountByUserID(4);
        int userID = 4;
        HashMap<String, Boolean> dietaryTest = new HashMap<>();
        dietaryTest.put("Vegan", Boolean.FALSE);
        dietaryTest.put("Vegetarian", Boolean.TRUE);
        dietaryTest.put("Pescatarian", Boolean.FALSE);
        dietaryTest.put("none1", Boolean.FALSE);
        currUser.setDietary(dietaryTest);

        HashMap<String, Boolean> allergiesTest = new HashMap<>();
        allergiesTest.put("Egg", Boolean.FALSE);
        allergiesTest.put("Peanut", Boolean.TRUE);
        allergiesTest.put("Seafood", Boolean.FALSE);
        allergiesTest.put("Soy", Boolean.FALSE);
        allergiesTest.put("Tree Nut", Boolean.TRUE);
        allergiesTest.put("Wheat", Boolean.FALSE);
        allergiesTest.put("none", Boolean.FALSE);
        currUser.setAllergies(allergiesTest);

        HashMap<String, String> conditionsTest = new HashMap<>();
        conditionsTest.put("Calcium", "high");
        conditionsTest.put("Potassium", "average");
        conditionsTest.put("Vitamin C", "average");
        conditionsTest.put("Vitamin D", "low");
        conditionsTest.put("Iron", "average");
        conditionsTest.put("Magnesium", "low");
        conditionsTest.put("Sugar", "low");
        currUser.setConditions(conditionsTest);
        Boolean setPreferences = userDataAccessObject.savePreferences(userID, dietaryTest,
                allergiesTest, conditionsTest);

        // assertion statements
        //testing that the preferences have been set in the csv
        assertTrue(setPreferences);
        // testing that the user can be found by ID
        assertNotNull(userDataAccessObject.getAccountByUserID(userID));
        // testing that the right preferences is set in terms of dietary
        User testUser = userDataAccessObject.getAccountByUserID(userID);
        assertEquals(testUser.getDietary(), dietaryTest);
        // testing that the right preference is set in terms of allergies
        assertEquals(testUser.getAllergies(), allergiesTest);
        // testing that the right preference is set in terms of conditions
        assertEquals(testUser.getConditions(), conditionsTest);

    }

    @Test
    void PreferencesInteractor(){

        int userID = 4;
        HashMap<String, Boolean> testDietaryInput = new HashMap<>();
        testDietaryInput.put("Vegan", Boolean.FALSE);
        testDietaryInput.put("Vegetarian", Boolean.TRUE);
        testDietaryInput.put("Pescetarian", Boolean.FALSE);
        testDietaryInput.put("none1", Boolean.FALSE);

        HashMap<String, Boolean> testAllergiesInput = new HashMap<>();
        testAllergiesInput.put("Egg", Boolean.FALSE);
        testAllergiesInput.put("Peanut", Boolean.TRUE);
        testAllergiesInput.put("Seafood", Boolean.FALSE);
        testAllergiesInput.put("Soy", Boolean.FALSE);
        testAllergiesInput.put("Tree Nut", Boolean.TRUE);
        testAllergiesInput.put("Wheat", Boolean.FALSE);
        testAllergiesInput.put("none", Boolean.FALSE);

        HashMap<String, String> testConditionsInput = new HashMap<>();
        testConditionsInput.put("Calcium", "high");
        testConditionsInput.put("Potassium", "average");
        testConditionsInput.put("Vitamin C", "average");
        testConditionsInput.put("Vitamin D", "low");
        testConditionsInput.put("Iron", "average");
        testConditionsInput.put("Magnesium", "low");
        testConditionsInput.put("Sugar", "low");

        PreferencesInputData preferencesInputData = new PreferencesInputData(userID, testDietaryInput,
                testConditionsInput, testAllergiesInput);

        PreferencesOutputBoundary successPresenter = new PreferencesOutputBoundary() {
            @Override
            public void prepareSuccessView(PreferencesOutputData user) {

                user.getID();

            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");

            }
        };

        PreferencesInteractor interactor = new PreferencesInteractor(this.userDataAccessObject, successPresenter);

        interactor.execute(preferencesInputData);
    }
}

//package src.tests;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import src.data_access.FileUserDataAccessObject;
//import src.entity.CommonUserFactory;
//import src.entity.UserFactory;
//import java.util.HashMap;
//import src.entity.User;
//import src.interface_adapters.ViewManagerModel;
//import src.interface_adapters.logged_in.LoggedInViewModel;
//import src.interface_adapters.preferences.PreferencesPresenter;
//import src.interface_adapters.preferences.PreferencesViewModel;
//import src.use_case.preferences.PreferencesInputData;
//import src.use_case.preferences.PreferencesInteractor;
//import src.use_case.preferences.PreferencesOutputBoundary;
//import src.use_case.preferences.PreferencesOutputData;
//
//import java.time.LocalDateTime;
//import static org.junit.jupiter.api.Assertions.*;
//public class PreferencesTests {
//    private FileUserDataAccessObject userDataAccessObject;
//    private final String testCsvFilePath = "./test_users.csv";
//
//    private final String testMealPlanFilePath = "./meal_plan.csv";
//
//    private final UserFactory userFactory = new CommonUserFactory();
//
//    HashMap<String, Boolean> testDietaryInput;
//
//    HashMap<String, Boolean> testAllergiesInput;
//
//    HashMap<String, String> testConditionsInput;
//
//    @BeforeEach
//    void setUp() {
//
//        this.userDataAccessObject = new FileUserDataAccessObject(testCsvFilePath, testMealPlanFilePath);
//        this.userDataAccessObject.loadUserDataFromCsv();
//    }
//
//    @Test
//    void SaveUserData_saveUserAndCsv(){
//        int userID = 4;
//        String username = "TestingUserName";
//        String password = "TestingPassword";
//        LocalDateTime creationTime = LocalDateTime.now();
//
//        userDataAccessObject.saveUserSignUpData(userID, username, password, creationTime);
//
//        assertTrue(userDataAccessObject.existByUserID(userID));
//        assertTrue(userDataAccessObject.existByName(username));
//        assertNotNull(userDataAccessObject.getAccountByUserID(userID));
//
//    }
//
//    @Test
//    void savePreferencesData_saveUserAndCsv(){
//        int userID = 4;
//        HashMap<String, Boolean> dietaryTest = new HashMap<>();
//        dietaryTest.put("Vegan", Boolean.FALSE);
//        dietaryTest.put("Vegetarian", Boolean.TRUE);
//        dietaryTest.put("Pescatarian", Boolean.FALSE);
//        dietaryTest.put("none1", Boolean.FALSE);
//
//        HashMap<String, Boolean> allergiesTest = new HashMap<>();
//        allergiesTest.put("Egg", Boolean.FALSE);
//        allergiesTest.put("Peanut", Boolean.TRUE);
//        allergiesTest.put("Seafood", Boolean.FALSE);
//        allergiesTest.put("Soy", Boolean.FALSE);
//        allergiesTest.put("Tree Nut", Boolean.TRUE);
//        allergiesTest.put("Wheat", Boolean.FALSE);
//        allergiesTest.put("none", Boolean.FALSE);
//
//        HashMap<String, String> conditionsTest = new HashMap<>();
//        conditionsTest.put("Calcium", "high");
//        conditionsTest.put("Potassium", "average");
//        conditionsTest.put("Vitamin C", "average");
//        conditionsTest.put("Vitamin D", "low");
//        conditionsTest.put("Iron", "average");
//        conditionsTest.put("Magnesium", "low");
//        conditionsTest.put("Sugar", "low");
//        Boolean setPreferences = userDataAccessObject.savePreferences(userID, dietaryTest,
//                allergiesTest, conditionsTest);
//
//        // assertion statements
//        //testing that the preferences have been set in the csv
//        assertTrue(setPreferences);
//        // testing that the user can be found by ID
//        assertNotNull(userDataAccessObject.getAccountByUserID(userID));
//        // testing that the right preferences is set in terms of dietary
//        User testUser = userDataAccessObject.getAccountByUserID(userID);
//        assertEquals(testUser.getDietary(), dietaryTest);
//        // testing that the right preference is set in terms of allergies
//        assertEquals(testUser.getAllergies(), allergiesTest);
//        // testing that the right preference is set in terms of conditions
//        assertEquals(testUser.getConditions(), conditionsTest);
//
//    }
//
//    @Test
//    void PreferencesInteractor(){
//        setUp();
//        int userID = 4;
//        testDietaryInput.put("Vegan", Boolean.FALSE);
//        testDietaryInput.put("Vegetarian", Boolean.TRUE);
//        testDietaryInput.put("Pescetarian", Boolean.FALSE);
//        testDietaryInput.put("none1", Boolean.FALSE);
//
//        testAllergiesInput.put("Egg", Boolean.FALSE);
//        testAllergiesInput.put("Peanut", Boolean.TRUE);
//        testAllergiesInput.put("Seafood", Boolean.FALSE);
//        testAllergiesInput.put("Soy", Boolean.FALSE);
//        testAllergiesInput.put("Tree Nut", Boolean.TRUE);
//        testAllergiesInput.put("Wheat", Boolean.FALSE);
//        testAllergiesInput.put("none", Boolean.FALSE);
//
//        testConditionsInput.put("Calcium", "high");
//        testConditionsInput.put("Potassium", "average");
//        testConditionsInput.put("Vitamin C", "average");
//        testConditionsInput.put("Vitamin D", "low");
//        testConditionsInput.put("Iron", "average");
//        testConditionsInput.put("Magnesium", "low");
//        testConditionsInput.put("Sugar", "low");
//
//        PreferencesInputData preferencesInputData = new PreferencesInputData(userID, testDietaryInput,
//                testConditionsInput, testAllergiesInput);
//
//        PreferencesOutputBoundary successPresenter = new PreferencesOutputBoundary() {
//            @Override
//            public void prepareSuccessView(PreferencesOutputData user) {
//
//                user.getID();
//
//            }
//
//            @Override
//            public void prepareFailView(String error) {
//                fail("Use case failure is unexpected.");
//
//            }
//        };
//
//        PreferencesInteractor interactor = new PreferencesInteractor(this.userDataAccessObject, successPresenter);
//
//        interactor.execute(preferencesInputData);
//
//
//    }
//}
//>>>>>>> main
