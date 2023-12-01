package tests.use_case.trackedNutrients;

import org.junit.Before;
import src.data_access.FileUserDataAccessObject;
import src.data_access.InMemoryTrackedNutrientsDataAccessObject;
import src.entity.User;
import src.entity.CommonUserFactory;
import src.entity.UserFactory;
import org.junit.jupiter.api.Test;
import src.use_case.trackedNutrients.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class TrackedNutrientsInteractorTest {
    // TrackedNutrients use case takes an ArrayList<String> of nutrients the user would like to track
    // sets the user's trackedNutrients attribute to the given array list

    @Test
    void before() {
        // adds a default user to the CSV file for testing
        UserFactory userFactory = new CommonUserFactory();
        FileUserDataAccessObject fileUserDAO;

        try {
            fileUserDAO = new FileUserDataAccessObject("./users.csv", userFactory);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // create a new user
        User user = userFactory.create(
                101,
                "Sophia",
                "abc123",
                LocalDateTime.now(),
                new HashMap<>(),
                170.0,
                145,
                19,
                5,
                new HashMap<>(),
                new HashMap<>(),
                new HashMap<>(),
                new ArrayList<>(),
                new HashMap<>(),
                "normal",
                2000
                );

        HashMap<String, Boolean> gender = new HashMap<>();
        gender.put("female", true);
        user.setGender(gender);

        HashMap<String, String> conditions = new HashMap<>();
        conditions.put("Calcium", "average");
        conditions.put("Potassium", "average");
        conditions.put("Vitamin C", "average");
        conditions.put("Vitamin D", "average");
        conditions.put("Iron", "average");
        conditions.put("Magnesium", "average");
        conditions.put("Sugar", "average");
        user.setConditions(conditions);

        // save the user to the file
        fileUserDAO.saveNewUser(user);
    }

    @Test
    void successTest() {
        TrackedNutrientsUserDataAccessInterface userRepository = new InMemoryTrackedNutrientsDataAccessObject();
        TrackedNutrientsOutputBoundary successPresenter = new TrackedNutrientsOutputBoundary() {
            @Override
            public void prepareSuccessView(TrackedNutrientsOutputData trackedNutrients) {
                ArrayList<String> tn = new ArrayList<>();
                tn.add("Protein");
                tn.add("Carbohydrates");
                tn.add("Fats");
                int userID = 101;

                assertEquals(userID, trackedNutrients.getUserID());
                assertEquals(tn, userRepository.getUserTrackedNutrientsData(userID));
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
        TrackedNutrientsInteractor interactor = new TrackedNutrientsInteractor(userRepository, successPresenter);

        interactor.execute(inputData);
    }
}
