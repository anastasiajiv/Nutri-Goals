package src.tests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.data_access.FileUserDataAccessObject;
import src.entity.CommonUserFactory;
import src.entity.MealPlan;
import src.entity.UserFactory;
import src.interface_adapters.ViewManagerModel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import src.entity.User;
import src.interface_adapters.logged_in.LoggedInViewModel;
import src.interface_adapters.mealPlan.MealPlanPresenter;
import src.interface_adapters.mealPlan.MealPlanViewModel;

import src.use_case.mealPlan.*;

import static org.junit.jupiter.api.Assertions.*;


public class MealPlanTests {
    private FileUserDataAccessObject fileUserDAO;
    private final String csvFilePath = "./helptestmealplan.csv";
    private final String csvMealPlanFilePath = "./mealplan.csv";  // might be wrong
    private final UserFactory userFactory = new CommonUserFactory();

    public Map<Integer, User> accounts = new HashMap<>();


    @BeforeEach
    void setUp() {
        // create a new fileUserDAO
        this.fileUserDAO = new FileUserDataAccessObject(this.csvFilePath, this.csvMealPlanFilePath);
        this.fileUserDAO.loadUserDataFromCsv();









    }

    @Test
    void MealPlanInteractor(){
        setUp();
        int userID = 1;
        assertTrue(this.fileUserDAO.existByUserID(userID));

        MealPlanViewModel mealPlanViewModel = new MealPlanViewModel();
        LoggedInViewModel loggedInViewModel = new LoggedInViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        MealPlanInputData InputData = new MealPlanInputData(userID);

        MealPlanPresenter successPresenter = new MealPlanPresenter(mealPlanViewModel, viewManagerModel, loggedInViewModel);

        MealPlanInteractor interactor = new MealPlanInteractor(this.fileUserDAO, successPresenter);

        interactor.execute(InputData);

        assertTrue(this.fileUserDAO.existByUserIDMealPlan(userID));

        MealPlan mealplan = this.fileUserDAO.mealplanuserid(userID);

        assertEquals( "Iron - Rich Gluten Free Vegan Muffins", mealplan.getBreakfast().getRecipeName());

        assertEquals(20090 , mealplan.getBreakfast().getRecipeIngredients().get(0).getIngredientID());

        MealPlan mealplan1 = this.fileUserDAO.getMealPlan(userID);

        assertEquals(this.fileUserDAO.displayMealPlan(mealplan), this.fileUserDAO.displayMealPlan(mealplan1));




    }

//        @Test
//        void saveUserData() {
//            setUp();
//            int userID = 101;
//            String username = "TestUser";
//            String password = "TestPassword";
//            LocalDateTime creationTime = LocalDateTime.now();
//
//            // create a new user; calls builder
//            fileUserDAO.saveUserSignUpData(userID, username, password, creationTime);
//
//            // asserts that the user was successfully saved
//            assertTrue(this.fileUserDAO.existByUserID(userID));
//            assertNotNull(this.fileUserDAO.getAccountByUserID(userID));
//        }

//        @Test
//        void saveUserTrackedNutrientsData_calories() {
//            setUp();
//            int userID = 101;
//            ArrayList<String> trackedNutrients = new ArrayList<>();
//            trackedNutrients.add("Calories");
//
//            // save the tracked nutrients to the account associated with userID
//            this.fileUserDAO.saveTrackedNutrientsData(trackedNutrients, userID);
//
//            // assert that the user exists
//            assertTrue(this.fileUserDAO.existByUserID(userID));
//
//            // fetch the account and assert its not null
//            User user = this.fileUserDAO.getAccountByUserID(userID);
//            assertNotNull(user);
//
//            // assert that the tracked nutrients is empty
//            assertEquals(1,user.getTrackedNutrients().size());
//        }
//
//        @Test
//        void saveUserTrackedNutrientsData_empty() {
//            setUp();
//            int userID = 101;
//            ArrayList<String> trackedNutrients = new ArrayList<>();
//
//            // save the tracked nutrients to the account associated with userID
//            this.fileUserDAO.saveTrackedNutrientsData(trackedNutrients, userID);
//
//            // assert that the user exists
//            assertTrue(this.fileUserDAO.existByUserID(userID));
//
//            // fetch the account and assert its not null
//            User user = this.fileUserDAO.getAccountByUserID(userID);
//            assertNotNull(user);
//
//            // assert that the tracked nutrients is empty
//            assertEquals(0,user.getTrackedNutrients().size());
//        }

//        @Test
//        void fetchRecipeNutritionalInfo() {
//            saveUserTrackedNutrientsData_calories();
//
//            int userID = 101;
//            String recipeID = "1003464";
//
////        HashMap<String, Double> nutritionalInfo = new HashMap<>();
////        nutritionalInfo.put("Calories", 316.49);
////        nutritionalInfo.put("Fat", 12.09);
////        nutritionalInfo.put("Saturated Fat", 3.98);
////        nutritionalInfo.put("Carbohydrates", 49.25);
////        nutritionalInfo.put("Net Carbohydrates", 46.76);
////        nutritionalInfo.put("Sugar", 21.98);
////        nutritionalInfo.put("Cholesterol", 1.88);
////        nutritionalInfo.put("Sodium", 279.1);
////        nutritionalInfo.put("Protein", 3.79);
//
//            HashMap<String, Double> returnedInfo = this.fileUserDAO.getRecipeNutritionalInfo(recipeID, userID);
//
//            assertEquals(316.49, returnedInfo.get("Calories"));
//
////        for (Map.Entry<String, Double> entry : nutritionalInfo.entrySet()) {
////            assertEquals(nutritionalInfo.get(entry), returnedInfo.get(entry));
////        }
//        }
//    }
//    @Test
//    void successTest() {
//        SignupInputData inputData = new SignupInputData("Paul", "password", "password");
//        SignupUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();
//
//        // This creates a successPresenter that tests whether the test case is as we expect.
//        SignupOutputBoundary successPresenter = new SignupOutputBoundary() {
//            @Override
//            public void prepareSuccessView(SignupOutputData user) {
//                // 2 things to check: the output data is correct, and the user has been created in the DAO.
//                assertEquals("Paul", user.getUsername());
//                assertNotNull(user.getCreationTime()); // any creation time is fine.
//                assertTrue(userRepository.existsByName("Paul"));
//            }
//
//            @Override
//            public void prepareFailView(String error) {
//                fail("Use case failure is unexpected.");
//            }
//        };
//
//        SignupInputBoundary interactor = new SignupInteractor(userRepository, successPresenter, new CommonUserFactory());
//        interactor.execute(inputData);
//    }


}