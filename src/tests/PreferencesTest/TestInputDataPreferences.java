package src.tests.PreferencesTest;

import org.junit.Test;
import src.use_case.preferences.PreferencesInputData;
import org.junit.Before;
import java.util.HashMap;
import org.junit.*;
import static org.junit.Assert.assertEquals;
public class TestInputDataPreferences {
    private PreferencesInputData inputData;

    HashMap<String, Boolean> testDietaryInput;

    HashMap<String, Boolean> testAllergiesInput;

    HashMap<String, String> testConditionsInput;
    @Before
    public void setUp(){
        testDietaryInput.put("Vegan", Boolean.FALSE);
        testDietaryInput.put("Vegetarian", Boolean.TRUE);
        testDietaryInput.put("Pescetarian", Boolean.FALSE);
        testDietaryInput.put("none1", Boolean.FALSE);

        testAllergiesInput.put("Egg", Boolean.FALSE);
        testAllergiesInput.put("Peanut", Boolean.TRUE);
        testAllergiesInput.put("Seafood", Boolean.FALSE);
        testAllergiesInput.put("Soy", Boolean.FALSE);
        testAllergiesInput.put("Tree Nut", Boolean.TRUE);
        testAllergiesInput.put("Wheat", Boolean.FALSE);
        testAllergiesInput.put("none", Boolean.FALSE);

        testConditionsInput.put("Calcium", "high");
        testConditionsInput.put("Potassium", "average");
        testConditionsInput.put("Vitamin C", "average");
        testConditionsInput.put("Vitamin D", "low");
        testConditionsInput.put("Iron", "average");
        testConditionsInput.put("Magnesium", "low");
        testConditionsInput.put("Sugar", "low");
        inputData = new PreferencesInputData(2, testDietaryInput, testConditionsInput, testAllergiesInput);


    }

    /*@Test
    public void testGetUserID(){
        assertEquals(2, inputData.getUserId());
    }
    @Test
    public void testGetDietary(){
        assertEquals(testDietaryInput, inputData.getDietary());
    }

    @Test
    public void testGetAllergies(){
        assertEquals(testAllergiesInput, inputData.getAllergies());
    }

    @Test
    public void testGetConditions(){
        assertEquals(testConditionsInput, inputData.getConditions());
    }*/


}
