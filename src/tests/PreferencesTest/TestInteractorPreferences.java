package src.tests.PreferencesTest;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import src.data_access.FileUserDataAccessObject;
import src.entity.CommonUserFactory;
import src.entity.UserFactory;
import src.use_case.preferences.PreferencesInputData;
import org.junit.Before;
import java.util.HashMap;
import org.junit.*;
import static org.junit.Assert.assertEquals;
public class TestInteractorPreferences {

    private FileUserDataAccessObject userDataAccessObject;
    private final String testCsvFilePath = "./test_users.csv";

    private final String testMealPlanFilePath = "./meal_plan.csv";

    private final UserFactory userFactory = new CommonUserFactory();

    @BeforeEach
    void setUp() {
        this.userDataAccessObject = new FileUserDataAccessObject(testCsvFilePath, testMealPlanFilePath, userFactory);
    }




}
