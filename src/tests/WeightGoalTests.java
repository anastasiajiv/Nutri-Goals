package src.tests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.data_access.FileUserDataAccessObject;
import src.entity.CommonUserFactory;
import src.entity.User;
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

    private HashMap<Integer, User> accounts = new HashMap<>();

    @BeforeEach
    void setUp() {

        this.userDataAccessObject = new FileUserDataAccessObject(testCsvFilePath, testMealPlanFilePath);

    }

    @Test
    void saveUserSignUpData_saveUserAndCsv() {
        // Arrange
        int userID = 1;
        String username = "TestUser";
        String password = "TestPassword";
        LocalDateTime creationTime = LocalDateTime.now();


        userDataAccessObject.saveUserSignUpData(userID, username, password, creationTime);

        // Asserts
        assertTrue(userDataAccessObject.existByUserID(userID));
        assertNotNull(userDataAccessObject.getAccountByUserID(userID));
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



        userDataAccessObject.saveWeightGoalData(userId,
                gender,
                height,
                weight,
                age,
                exerciseLvl,
                paceType,
                weightGoal);

        // Assert statemnets
        assertTrue(userDataAccessObject.existByUserID(userId));
        assertNotNull(userDataAccessObject.getAccountByUserID(userId));
        User account = userDataAccessObject.getAccountByUserID(userId);
        //assertEquals(100, userDataAccessObject.getAccountByUserID(userId).getRequiredCalories());

    }
    @Test
    void testComputeCalories() {
        int userId = 2;
        HashMap<String, Boolean> gender = new HashMap<>();
        gender.put("male", Boolean.TRUE);
        gender.put("female", Boolean.FALSE);
        double height = 170.0;
        double weight = 70.0;
        int age = 25;
        int exerciseLvl = 3;
        String paceType = "normal";
        HashMap<String, Boolean> weightGoal = new HashMap<>();
        weightGoal.put("maintainWeight", Boolean.FALSE);
        weightGoal.put("loseWeight", Boolean.TRUE);
        weightGoal.put("gainWeight", Boolean.FALSE);

        User testCals = new CommonUserFactory().createdDefaultUser(4, "testCals");
        testCals.setGender(gender);
        testCals.setUserHeight(height);
        testCals.setUserWeight(weight);
        testCals.setUserAge(age);
        testCals.setUserExerciseLvl(exerciseLvl);
        testCals.setPaceType(paceType);
        testCals.setWeightGoalType(weightGoal);

        accounts.put(testCals.getUserId(), testCals);
        System.out.println(userDataAccessObject.existByUserID(1));
//        double reqCal = userDataAccessObject.computedRequiredCalories(1);
//        testCals.setRequiredCalories(reqCal);
//        System.out.println(accounts.get(1).getRequiredCalories());



        userDataAccessObject.saveWeightGoalData(userId,
                gender,
                height,
                weight,
                age,
                exerciseLvl,
                paceType,
                weightGoal);

        // Assert statements
        assertTrue(userDataAccessObject.existByUserID(userId));
        assertNotNull(userDataAccessObject.getAccountByUserID(userId));


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
