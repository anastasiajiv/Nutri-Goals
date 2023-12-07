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
import src.use_case.signup.SignupInputData;
import src.use_case.signup.SignupInteractor;
import src.use_case.signup.SignupOutputBoundary;
import src.use_case.signup.SignupOutputData;
import src.use_case.weightgoal.WeightGoalInputData;
import src.use_case.weightgoal.WeightGoalInteractor;
import src.use_case.weightgoal.WeightGoalOutputBoundry;
import src.use_case.weightgoal.WeightGoalOutputData;

import java.time.LocalDateTime;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class SignUpUserTests {

    //private FileUserDataAccessObject userDataAccessObject;

    private final String testCsvFilePath = "./test_signUpUsers.csv";

    private final String testMealPlanFilePath = "./meal_plan.csv";

    private HashMap<Integer, User> accounts = new HashMap<>();

    UserFactory userFactory = new CommonUserFactory();


    FileUserDataAccessObject userDataAccessObject = new FileUserDataAccessObject(testCsvFilePath,
            testMealPlanFilePath);


    @Test
    void saveUserSignUpData_saveUserAndCsv() {
        // Arrange
        int userID = 3;
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
        int exerciseLvl = 3;
        String paceType = "normal";

        HashMap<String, Boolean> weightGoal = new HashMap<>();
        weightGoal.put("maintainWeight", Boolean.FALSE);
        weightGoal.put("loseWeight", Boolean.TRUE);
        weightGoal.put("gainWeight", Boolean.FALSE);

        WeightGoalInputData weightGoalInputData = new WeightGoalInputData(13,
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
                int userId = 13;
                HashMap<String, Boolean> gender = new HashMap<>();
                gender.put("male", Boolean.TRUE);
                gender.put("female", Boolean.FALSE);
                double height = 175.5;
                double weight = 65.5;
                int age = 19;
                int exerciseLvl = 3;
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
        User currUser = userDataAccessObject.getAccountByUserID(13);
        System.out.println(currUser.getGender());
        System.out.println(userDataAccessObject.computedRequiredCalories(13));
    }

}
