package src.tests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.data_access.FileUserDataAccessObject;
import src.entity.CommonUserFactory;
import src.entity.UserFactory;

import java.time.LocalDateTime;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
//Mainly fileDAO and user entity tests

public class WeightGoalTests { // TODO : Create a mock database to hold the accounts hashmap
                              // TODO: Test that use case interactor calls are appropriately interacting with each other
    private FileUserDataAccessObject userDataAccessObject;
    private final String testCsvFilePath = "./test_users.csv";

    private final String testMealPlanFilePath = "./meal_plan.csv";

    private final UserFactory userFactory = new CommonUserFactory();

    @BeforeEach
    void setUp() {
        this.userDataAccessObject = new FileUserDataAccessObject(testCsvFilePath, testMealPlanFilePath, userFactory);
    }

    @Test
    void saveUserSignUpData_saveUserAndCsv() {
        // Arrange
        int userId = 1;
        String username = "TestUser";
        String password = "TestPassword";
        LocalDateTime creationTime = LocalDateTime.now();


        userDataAccessObject.saveUserSignUpData(userId, username, password, creationTime);

        // Asserts
        assertTrue(userDataAccessObject.existByUserID(userId));
        assertNotNull(userDataAccessObject.getAccountByUserId(userId));
        // Add asserts within the mock database to check for each attribute
    }

    @Test
    void saveWeightGoalData_saveUserAndCsv() {

        int userId = 1;
        HashMap<String, Boolean> gender = new HashMap<>();
        double height = 170.0;
        double weight = 70.0;
        int age = 25;
        int exerciseLvl = 3;
        String paceType = "normal";
        HashMap<String, Boolean> weightGoal = new HashMap<>();
        weightGoal.put("maintainWeight", Boolean.FALSE);
        weightGoal.put("loseWeight", Boolean.TRUE);
        weightGoal.put("gainWeight", Boolean.FALSE);


        userDataAccessObject.saveWeightGoalData(userId, gender, height, weight, age, exerciseLvl, paceType, weightGoal);

        // Assert statemnets
        assertTrue(userDataAccessObject.existByUserID(userId));
        assertNotNull(userDataAccessObject.getAccountByUserId(userId));

    }
//
//    @Test
//    void saveNewUser_saveUserAndCsv() {
//
//        User newUser = userFactory.createdDefaultUser(2, "NewUser");
//        newUser.setPassword("newPassword");
//        newUser.setCreationTime(LocalDateTime.now());
//
//
//        //userDataAccessObject.(newUser);
//
//
//        assertTrue(userDataAccessObject.existById(newUser.getUserId()));
//        assertNotNull(userDataAccessObject.getAccountByUserId(newUser.getUserId()));
//        //Add assert statements to match attributes
//    }


}
