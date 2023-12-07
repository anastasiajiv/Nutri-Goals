package src.tests;

import org.junit.Before;
import org.junit.Test;
import src.app.LoginUseCaseFactory;
import src.app.PreferencesUseCaseFactory;
import src.app.SignupUseCaseFactory;
import src.app.TrackedNutrientsUseCaseFactory;
import src.data_access.FileUserDataAccessObject;
import src.interface_adapters.ViewManagerModel;
import src.interface_adapters.logged_in.LoggedInViewModel;
import src.interface_adapters.preferences.PreferencesViewModel;
import src.view.*;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class PreferencesViewTest {
    ViewManagerModel viewManagerModel = new ViewManagerModel();
    LoggedInViewModel loggedInViewModel = new LoggedInViewModel();
    PreferencesViewModel preferencesViewModel = new PreferencesViewModel();
    PreferencesView preferencesView;

    @Before
    public void setUp(){
        JFrame application = new JFrame("Login Example");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int width = (int) (screenSize.width * 0.9);
        int height = (int) (screenSize.height * 0.9);
        Dimension screenSize1 = new Dimension(width, height);
        application.setPreferredSize(screenSize1);
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();


        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // This keeps track of and manages which view is currently showing.


        new ViewManager(views, cardLayout, viewManagerModel);

        FileUserDataAccessObject userDataAccessObject;

        userDataAccessObject = new FileUserDataAccessObject("./help4.csv", "mealplan.csv");

        LoggedInView loggedInView = new LoggedInView(loggedInViewModel, cardLayout, views);
        views.add(loggedInView, loggedInView.viewName);

        PreferencesView preferencesView = PreferencesUseCaseFactory.create(viewManagerModel, preferencesViewModel,
                loggedInViewModel, userDataAccessObject);
        views.add(preferencesView, preferencesView.viewName);


        viewManagerModel.setActiveView(preferencesView.viewName);
        viewManagerModel.firePropertyChanged();


        application.pack();
        application.setVisible(true);


    }
    @Test
    public void testSignUpSuccess() {

        preferencesViewModel.getState().setUserID(4);
        HashMap<String, Boolean> testDietaryInput = new HashMap<>();
        testDietaryInput.put("Vegan", Boolean.FALSE);
        testDietaryInput.put("Vegetarian", Boolean.TRUE);
        testDietaryInput.put("Pescetarian", Boolean.FALSE);
        testDietaryInput.put("none1", Boolean.FALSE);

        HashMap<String, Boolean> testAllergiesInput = new HashMap<>();
        testAllergiesInput.put("Egg", Boolean.FALSE);
        testAllergiesInput.put("Peanut", Boolean.TRUE);
        testAllergiesInput.put("Seafood", Boolean.FALSE);
        testAllergiesInput.put("Soy", Boolean.FALSE);
        testAllergiesInput.put("Tree Nut", Boolean.TRUE);
        testAllergiesInput.put("Wheat", Boolean.FALSE);
        testAllergiesInput.put("none", Boolean.FALSE);

        HashMap<String, String> testConditionsInput = new HashMap<>();
        testConditionsInput.put("Calcium", "high");
        testConditionsInput.put("Potassium", "average");
        testConditionsInput.put("Vitamin C", "average");
        testConditionsInput.put("Vitamin D", "low");
        testConditionsInput.put("Iron", "average");
        testConditionsInput.put("Magnesium", "low");
        testConditionsInput.put("Sugar", "low");
        preferencesViewModel.getState().setDietaryMap(testDietaryInput);
        preferencesViewModel.getState().setAllergiesMap(testAllergiesInput);
        preferencesViewModel.getState().setConditionsMap(testConditionsInput);

        assertEquals(4, preferencesViewModel.getState().getUserID());
        assertEquals(testDietaryInput, preferencesViewModel.getState().getDietaryMap());
        assertEquals(testAllergiesInput, preferencesViewModel.getState().getAllergiesMap());
        assertEquals(testConditionsInput, preferencesViewModel.getState().getConditionsMap());
        //assertEquals("log in", viewManagerModel.getActiveView());
    }

}
