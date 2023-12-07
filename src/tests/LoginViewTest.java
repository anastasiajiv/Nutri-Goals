package src.tests;

import org.junit.Before;
import org.junit.Test;
import src.app.LoginUseCaseFactory;
import src.app.SignupUseCaseFactory;
import src.data_access.FileUserDataAccessObject;
import src.interface_adapters.ViewManagerModel;
import src.interface_adapters.logged_in.LoggedInViewModel;
import src.interface_adapters.login.LoginViewModel;
import src.interface_adapters.mealPlan.MealPlanViewModel;
import src.interface_adapters.preferences.PreferencesViewModel;
import src.interface_adapters.signup.SignupViewModel;
import src.interface_adapters.trackedNutrients.TrackedNutrientsViewModel;
import src.interface_adapters.weightgoal.WeightGoalViewModel;
import src.view.LoginView;
import src.view.SignupView;
import src.view.ViewManager;
import src.view.LoggedInView;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class LoginViewTest {
    SignupViewModel signupViewModel = new SignupViewModel();
    LoginViewModel loginViewModel = new LoginViewModel();
    ViewManagerModel viewManagerModel = new ViewManagerModel();
    LoggedInViewModel loggedInViewModel = new LoggedInViewModel();
    PreferencesViewModel preferencesViewModel = new PreferencesViewModel();
    TrackedNutrientsViewModel trackedNutrientsViewModel = new TrackedNutrientsViewModel();
    WeightGoalViewModel weightGoalViewModel = new WeightGoalViewModel();
    MealPlanViewModel mealPlanViewModel = new MealPlanViewModel();
    LoginView loginView;
    private final String userDataFilePath = "./help4.csv";
    private final String userMealDataFilePath = "mealplan.csv";



    @Before
    public void setUp() throws IOException {

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

        userDataAccessObject = new FileUserDataAccessObject(userDataFilePath, userMealDataFilePath);

        loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, loggedInViewModel,
                preferencesViewModel, weightGoalViewModel, trackedNutrientsViewModel, mealPlanViewModel, userDataAccessObject);

        views.add(loginView, loginView.viewName);

        /*LoggedInView loggedInView = new LoggedInView(loggedInViewModel, cardLayout, views);
        views.add(loggedInView, loggedInView.viewName);*/

        /*PreferencesView preferencesView = PreferencesUseCaseFactory.create(viewManagerModel, preferencesViewModel,
                loggedInViewModel, userDataAccessObject);
        views.add(preferencesView, preferencesView.viewName);

        TrackedNutrientsView trackedNutrientsView = TrackedNutrientsUseCaseFactory.create(viewManagerModel,
                trackedNutrientsViewModel, loggedInViewModel, userDataAccessObject);
        views.add(trackedNutrientsView, trackedNutrientsView.viewName);*/




        viewManagerModel.setActiveView(loginView.viewName);
        viewManagerModel.firePropertyChanged();


        application.pack();
        application.setVisible(true);


    }
    @Test
    public void testLogInSuccess() {

        loginViewModel.getState().setUserID(3);
        loginViewModel.getState().setUsername("UserTesting4");
        loginViewModel.getState().setPassword("PasswordTesting");
        loginView.logIn.doClick();
        assertEquals("UserTesting4", loginViewModel.getState().getUsername());
        assertEquals("PasswordTesting", loginViewModel.getState().getPassword());
        //assertEquals(loginViewModel.getState().getUserID(), loggedInViewModel.getState().getUserID());
        assertEquals("logged in", viewManagerModel.getActiveView());

    }
}
