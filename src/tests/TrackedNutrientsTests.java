package src.tests;

import org.junit.jupiter.api.BeforeEach;
import src.data_access.FileUserDataAccessObject;
import src.data_access.InMemoryUserDataAccessObject_Test;
import src.entity.User;
import org.junit.jupiter.api.Test;
import src.use_case.trackedNutrients.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TrackedNutrientsTests {
    // TrackedNutrients use case takes an ArrayList<String> of nutrients the user would like to track
    // sets the user's trackedNutrients attribute to the given array list

    private InMemoryUserDataAccessObject_Test fileUserDAO;
    private final String csvFilePath = "./test_users.csv";
    private final String csvMealPlanFilePath = "./test_mealplan.csv";  // might be wrong name
    //private final UserFactory userFactory = new CommonUserFactory();

    @BeforeEach
    void setUp() {
        // create a new fileUserDAO
        this.fileUserDAO = new InMemoryUserDataAccessObject_Test();
    }

    @Test
    void saveUserData() {
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
    void saveUserTrackedNutrientsData_empty() {
        saveUserData();
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
    void saveUserTrackedNutrientsData_nonEmpty() {
        saveUserData();
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
    void successTest() {
        saveUserData();
        TrackedNutrientsOutputBoundary successPresenter = new TrackedNutrientsOutputBoundary() {
            @Override
            public void prepareSuccessView(TrackedNutrientsOutputData trackedNutrients) {
                ArrayList<String> tn = new ArrayList<>();
                tn.add("Protein");
                tn.add("Carbohydrates");
                tn.add("Fats");
                int userID = 101;

                // assertEquals(userID, trackedNutrients.getUserID());
                assertEquals(tn, fileUserDAO.getUserTrackedNutrientsData(userID));
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        ArrayList<String> trackedNutrients = new ArrayList<>();
        trackedNutrients.add("Protein");
        trackedNutrients.add("Carbohydrates");
        trackedNutrients.add("Fats");
        int userID = 101;

        TrackedNutrientsInputData inputData = new TrackedNutrientsInputData(userID, trackedNutrients);
        TrackedNutrientsInteractor interactor = new TrackedNutrientsInteractor(fileUserDAO, successPresenter);

        interactor.execute(inputData);
    }

    @Test
    void failureTest() {
        saveUserData();
        TrackedNutrientsOutputBoundary successPresenter = new TrackedNutrientsOutputBoundary() {
            @Override
            public void prepareSuccessView(TrackedNutrientsOutputData trackedNutrients) {
                ArrayList<String> tn = new ArrayList<>();
                tn.add("Protein");
                tn.add("Carbohydrates");
                tn.add("Fats");
                int userID = 101;

                // assertEquals(userID, trackedNutrients.getUserID());
                assertEquals(tn, fileUserDAO.getUserTrackedNutrientsData(userID));
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("UserID cannot be found.", error);
            }
        };

        ArrayList<String> trackedNutrients = new ArrayList<>();
        trackedNutrients.add("Protein");
        trackedNutrients.add("Carbohydrates");
        trackedNutrients.add("Fats");
        int userID = 101;

        // add wrong ID
        TrackedNutrientsInputData inputData = new TrackedNutrientsInputData(100, trackedNutrients);
        TrackedNutrientsInteractor interactor = new TrackedNutrientsInteractor(fileUserDAO, successPresenter);

        interactor.execute(inputData);
    }
}
