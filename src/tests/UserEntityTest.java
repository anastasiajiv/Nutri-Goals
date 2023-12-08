package src.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.entity.CommonUserFactory;
import src.entity.User;
import src.entity.UserFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;


public class UserEntityTest {

    private User user;

    @BeforeEach
    public void setUp() {

        user = new CommonUserFactory().createdDefaultUser(1, "Aarya");
    }


    @Test
    public void testSetters() {
        user.setPassword("newPassword");
        user.setCreationTime(LocalDateTime.MIN);
        HashMap<String, Boolean> gender = new HashMap<>();
        gender.put("male", Boolean.TRUE);
        gender.put("female", Boolean.FALSE);
        user.setGender(gender);
        user.setUserHeight(175.5);
        user.setUserWeight(65.5);
        user.setUserAge(19);
        user.setUserExerciseLvl(2);
        user.setPaceType("normal");
        HashMap<String, Boolean> weightGoal = new HashMap<>();
        weightGoal.put("maintainWeight", Boolean.FALSE);
        weightGoal.put("loseWeight", Boolean.TRUE);
        weightGoal.put("gainWeight", Boolean.FALSE);
        user.setWeightGoalType(weightGoal);
        assertEquals(user.getWeightGoalType(), "loseWeight");




        //asserts
        assertEquals("newPassword", user.getPassword());
        assertEquals(LocalDateTime.MIN, user.getCreationTime());
        assertEquals("false", user.isFemale());
        assertEquals("true", user.isMale());

        HashMap<String, Boolean> testgender = new HashMap<>();
        testgender.put("male", Boolean.TRUE);
        testgender.put("female", Boolean.FALSE);

        assertEquals(testgender, user.getGender());
        assertEquals(175.5, user.getUserHeight());
        assertEquals(65.5, user.getUserWeight());
        assertEquals(19, user.getUserAge());
        assertEquals(2, user.getUserExerciseLevel());
        assertEquals("normal", user.getPaceType());

        HashMap<String, Boolean> testWeightGoal = new HashMap<>();
        testWeightGoal.put("maintainWeight", Boolean.FALSE);
        testWeightGoal.put("loseWeight", Boolean.TRUE);
        testWeightGoal.put("gainWeight", Boolean.FALSE);

        assertEquals(testWeightGoal, weightGoal);

        HashMap<String, Boolean> dietary = new HashMap<>();
        dietary.put("Vegetarian", Boolean.FALSE);
        dietary.put("Vegan", Boolean.FALSE);
        dietary.put("Pescatarian", Boolean.FALSE);
        dietary.put("none1", Boolean.TRUE);

        user.setDietary(dietary);
        HashMap<String, Boolean> allergies = new HashMap<>();
        allergies.put("Eggs", Boolean.FALSE);
        allergies.put("Sesame", Boolean.FALSE);
        allergies.put("Shellfish", Boolean.FALSE);
        allergies.put("Wheat", Boolean.FALSE);
        allergies.put("Peanut", Boolean.FALSE);
        allergies.put("Seafood", Boolean.TRUE);
        allergies.put("none", Boolean.TRUE);
        allergies.put("TreeNut", Boolean.FALSE);
        user.setAllergies(allergies);
        HashMap<String, String> condition = new HashMap<>();
        condition.put("Magnesium", "low");
        condition.put("Iron", "average");
        condition.put("Calcium", "high");
        condition.put("VitaminD", "high");
        condition.put("VitaminC", "average");
        condition.put("Sugar", "low");
        condition.put("Potassium", "average");

        user.setConditions(condition);

        ArrayList<String> trackedNutrients = new ArrayList<>();
        trackedNutrients.add("Protien");
        trackedNutrients.add("Carbohydrates");
        trackedNutrients.add("Calories");
        trackedNutrients.add("Fat");
        trackedNutrients.add("VitaminC");
        trackedNutrients.add("VitaminD");
        trackedNutrients.add("Iron");
        trackedNutrients.add("Magnesium");
        trackedNutrients.add("Sugar");

        user.setTrackedNutrients(trackedNutrients);




        // Add more assertions for other setters
    }

    @Test
    public void testGetters() {
        assertEquals(1, user.getUserId());
        assertEquals("Aarya", user.getName());
        assertEquals(new ArrayList<String>(), user.getTrackedNutrients());
        // Add more assertions for other getters
    }
    @Test
    public void testNullGenderTypes() {
        User userTestNull = new CommonUserFactory().createdDefaultUser(2, "Aarya");
        userTestNull.setGender(new HashMap<>());
        assertEquals(Boolean.valueOf(userTestNull.isFemale()), Boolean.FALSE);
        assertEquals(Boolean.valueOf(userTestNull.isMale()), Boolean.FALSE);
    }

    @Test
    public void testNullWeightGoalTypes () {
        User userWeightTypeNull = new CommonUserFactory().createdDefaultUser(3, "RA");
        userWeightTypeNull.setGender(new HashMap<>());
        assertEquals(userWeightTypeNull.getWeightGoalType(), "WeightGoalType not established");
    }

    @Test
    public void testUserSpecifiedDietary() {
        user.userSpecifiedDietary();
    }

    @Test
    public void testUserSpecifiedAllergies() {
        // Test the userSpecifiedAllergies method
        // Ensure it returns the expected value for different scenarios
        user.userSpecifiedAllergies();
    }

    @Test
    public void testUserSpecifiedConditions() {
        // Test the userSpecifiedConditions method
        // Ensure it returns the expected value for different scenarios
        user.userSpecifiedConditions();
    }

    // Add more tests for other methods

    @Test
    public void testRequiredCaloriesCalculation() {
        // Test the getRequiredCalories method
        // Ensure it returns the expected value for different scenarios
    }

    // Add more tests as needed

    @Test
    public void testWholeEntity() {
        // Test the whole entity by creating a user with specific parameters
        // and asserting that the getters return the expected values
    }

    @Test
    void testCalciumLow() {
        HashMap<String, String> condition = new HashMap<>();
        condition.put("Magnesium", "low");
        condition.put("Iron", "average");
        condition.put("Calcium", "low");
        condition.put("VitaminD", "high");
        condition.put("VitaminC", "average");
        condition.put("Sugar", "low");
        condition.put("Potassium", "average");
        user.setConditions(condition);
        user.userSpecifiedConditions();
    }

    @Test
    void testCalciumAverage() {
        HashMap<String, String> condition = new HashMap<>();
        condition.put("Magnesium", "low");
        condition.put("Iron", "average");
        condition.put("Calcium", "average");
        condition.put("VitaminD", "high");
        condition.put("VitaminC", "average");
        condition.put("Sugar", "low");
        condition.put("Potassium", "average");
        testUserSpecifiedConditions();
    }
}
