package src.tests;

import org.junit.jupiter.api.Test;
import src.data_access.FileUserDataAccessObject;
import src.entity.CommonUserFactory;
import src.entity.User;
import src.entity.UserFactory;
import src.use_case.login.LoginInputData;
import src.use_case.login.LoginInteractor;
import src.use_case.login.LoginOutputBoundary;
import src.use_case.login.LoginOutputData;
import src.use_case.preferences.PreferencesInputData;
import src.use_case.preferences.PreferencesInteractor;
import src.use_case.preferences.PreferencesOutputBoundary;
import src.use_case.preferences.PreferencesOutputData;
import src.use_case.signup.SignupInputData;
import src.use_case.signup.SignupInteractor;
import src.use_case.signup.SignupOutputBoundary;
import src.use_case.signup.SignupOutputData;
import src.use_case.trackedNutrients.TrackedNutrientsInputData;
import src.use_case.trackedNutrients.TrackedNutrientsInteractor;
import src.use_case.trackedNutrients.TrackedNutrientsOutputBoundary;
import src.use_case.trackedNutrients.TrackedNutrientsOutputData;
import src.use_case.weightgoal.WeightGoalInputData;
import src.use_case.weightgoal.WeightGoalInteractor;
import src.use_case.weightgoal.WeightGoalOutputBoundry;
import src.use_case.weightgoal.WeightGoalOutputData;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class SignUpUserTests {

    //private FileUserDataAccessObject userDataAccessObject;

    private final String testCsvFilePath = "./test_users.csv";

    private final String testMealPlanFilePath = "./meal_plan.csv";

    private HashMap<Integer, User> accounts = new HashMap<>();

    UserFactory userFactory = new CommonUserFactory();


    FileUserDataAccessObject userDataAccessObject = new FileUserDataAccessObject(testCsvFilePath,
            testMealPlanFilePath);


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
        assertEquals("TestUser", userDataAccessObject.getAccountByUserID(userID).getName());
        assertEquals("TestPassword", userDataAccessObject.getAccountByUserID(userID).getPassword());
        // Add asserts within the mock database to check for each attribute
    }


    @Test
    void signUpSuccessView() {
        SignupInputData signupInputData = new SignupInputData("TestUser", "TestPassword", "TestPassword");
        SignupOutputBoundary successPresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                String username = "TestUser";
                String password = "TestPassword";
                String repeatPassword = "TestPassword";
                user.getCreationUserID();
                user.getUsername();
                user.setCreationTime(String.valueOf(LocalDateTime.now()));
                user.getCreationTime();

            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };





        SignupInteractor interactor = new SignupInteractor(userDataAccessObject, successPresenter, userFactory);
        interactor.execute(signupInputData);

    }



    @Test
    void loginUserTests() {
        LoginInputData loginInputData = new LoginInputData("TestUser", "TestPassword");

        LoginOutputBoundary successPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                String username = "TestUser";
                String password = "TestPassword";
                user.getUserID();
                user.getUsername();
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };
        LoginInteractor interactor = new LoginInteractor(userDataAccessObject, successPresenter);
        interactor.execute(loginInputData);
    }

    @Test
    void weightGoalTests () {

        HashMap<String, Boolean> gender = new HashMap<>();
        gender.put("male", Boolean.TRUE);
        gender.put("female", Boolean.FALSE);

        double height = 175.5;
        double weight = 65.5;
        int age = 19;
        int exerciseLvl = 1;
        String paceType = "normal";

        HashMap<String, Boolean> weightGoal = new HashMap<>();
        weightGoal.put("maintainWeight", Boolean.FALSE);
        weightGoal.put("loseWeight", Boolean.TRUE);
        weightGoal.put("gainWeight", Boolean.FALSE);

        WeightGoalInputData weightGoalInputData = new WeightGoalInputData(1,
                gender,
                height,
                weight,
                age,
                exerciseLvl,
                paceType,
                weightGoal);

        WeightGoalOutputBoundry successPresenter = new WeightGoalOutputBoundry() {
            @Override
            public void prepareSuccessView(WeightGoalOutputData weightGoal) {
                int userId = 1;
                HashMap<String, Boolean> gender = new HashMap<>();
                gender.put("male", Boolean.TRUE);
                gender.put("female", Boolean.FALSE);
                double height = 175.5;
                double weight = 65.5;
                int age = 19;
                int exerciseLvl = 1;
                String paceType = "normal";

                HashMap<String, Boolean> weightGoaltest = new HashMap<>();
                weightGoaltest.put("maintainWeight", Boolean.FALSE);
                weightGoaltest.put("loseWeight", Boolean.TRUE);
                weightGoaltest.put("gainWeight", Boolean.FALSE);

                weightGoal.getUserID();
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };
        WeightGoalInteractor interactor = new WeightGoalInteractor(userDataAccessObject, successPresenter);
        interactor.execute(weightGoalInputData);

    }


    @Test
    void computeCals () {
        User currUser = userDataAccessObject.getAccountByUserID(1);
        System.out.println(currUser.getGender());
        System.out.println(userDataAccessObject.computedRequiredCalories(1));
    }


    @Test
    void preferencesTest() {

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

        PreferencesInputData preferencesInputData = new PreferencesInputData(1,
                dietaryTest,
                conditionsTest,
                allergiesTest);

        PreferencesOutputBoundary successPresenter = new PreferencesOutputBoundary() {
            @Override
            public void prepareSuccessView(PreferencesOutputData user) {
                int userId = 1;

                user.getID();
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };
        PreferencesInteractor interactor = new PreferencesInteractor(userDataAccessObject, successPresenter);
        interactor.execute(preferencesInputData);
    }

    @Test
    void TrackedNutrientsTest() {
        ArrayList<String> tn = new ArrayList<>();
        tn.add("Protein");
        tn.add("Carbohydrates");
        tn.add("Fats");

        TrackedNutrientsInputData trackedNutrientsInputData = new TrackedNutrientsInputData(1, tn);

        TrackedNutrientsOutputBoundary successPresenter = new TrackedNutrientsOutputBoundary() {
            @Override
            public void prepareSuccessView(TrackedNutrientsOutputData trackedNutrients) {

            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        TrackedNutrientsInteractor interactor = new TrackedNutrientsInteractor(userDataAccessObject, successPresenter);
        interactor.execute(trackedNutrientsInputData);
    }

}
