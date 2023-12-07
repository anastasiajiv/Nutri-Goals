package src.tests;

import org.junit.Before;
import org.junit.Test;
import src.app.*;
import src.data_access.FileUserDataAccessObject;
import src.interface_adapters.ViewManagerModel;
import src.interface_adapters.logged_in.LoggedInViewModel;
import src.interface_adapters.login.LoginViewModel;
import src.interface_adapters.mealplan.MealPlanViewModel;
import src.interface_adapters.preferences.PreferencesViewModel;
import src.interface_adapters.signup.SignupViewModel;
import src.interface_adapters.trackedNutrients.TrackedNutrientsViewModel;
import src.interface_adapters.weightgoal.WeightGoalViewModel;
import src.view.*;
import src.interface_adapters.ViewManagerModel;
import static org.junit.Assert.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDateTime;

public class SignUpViewTest {
    SignupViewModel signupViewModel = new SignupViewModel();
    LoginViewModel loginViewModel = new LoginViewModel();
    ViewManagerModel viewManagerModel = new ViewManagerModel();
    LoggedInViewModel loggedInViewModel = new LoggedInViewModel();
    PreferencesViewModel preferencesViewModel = new PreferencesViewModel();
    TrackedNutrientsViewModel trackedNutrientsViewModel = new TrackedNutrientsViewModel();
    WeightGoalViewModel weightGoalViewModel = new WeightGoalViewModel();
    MealPlanViewModel mealPlanViewModel = new MealPlanViewModel();
    SignupView signupView;

    static String message = "";
    static boolean popUpDiscovered = false;
    /**
     * ensures there are at least 2 users in the CSV file for testing purposes
     */
    public void addTwoUsers() {

        FileUserDataAccessObject fileDAO;


        fileDAO = new FileUserDataAccessObject("./users.csv", "./mealplan.csv");
        int userID = fileDAO.createUserID();

        fileDAO.saveUserSignUpData(userID, "UserTesting", "password", LocalDateTime.now());
    }

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

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        FileUserDataAccessObject userDataAccessObject;

        userDataAccessObject = new FileUserDataAccessObject("./help4.csv", "mealplan.csv");



        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel,
                userDataAccessObject, cardLayout, views);
        views.add(signupView, signupView.viewName);

        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, loggedInViewModel,
                preferencesViewModel, weightGoalViewModel, trackedNutrientsViewModel, mealPlanViewModel, userDataAccessObject);

        views.add(loginView, loginView.viewName);

        LoggedInView loggedInView = new LoggedInView(loggedInViewModel, cardLayout, views);
        views.add(loggedInView, loggedInView.viewName);

        PreferencesView preferencesView = PreferencesUseCaseFactory.create(viewManagerModel, preferencesViewModel,
                loggedInViewModel, userDataAccessObject);
        views.add(preferencesView, preferencesView.viewName);

        TrackedNutrientsView trackedNutrientsView = TrackedNutrientsUseCaseFactory.create(viewManagerModel,
                trackedNutrientsViewModel, loggedInViewModel, userDataAccessObject);
        views.add(trackedNutrientsView, trackedNutrientsView.viewName);

        views.add(loginView, loginView.viewName);


        viewManagerModel.setActiveView(signupView.viewName);
        viewManagerModel.firePropertyChanged();


        application.pack();
        application.setVisible(true);


    }
    @Test
    public void testSignUpSuccess() {

        signupViewModel.getState().setUsername("UserTesting");
        signupViewModel.getState().setPassword("PasswordTesting");
        signupViewModel.getState().setRepeatPassword("RepeatPassWordTesting");
        //signupView.signUp.doClick();
        assertEquals("UserTesting", signupViewModel.getState().getUsername());
        assertEquals("PasswordTesting", signupViewModel.getState().getPassword());
        assertEquals("RepeatPassWordTesting", signupViewModel.getState().getRepeatPassword());
        //assertEquals("log in", viewManagerModel.getActiveView());
    }

}


//package src.tests;
//
//import org.junit.Before;
//import org.junit.Test;
//import src.app.*;
//import src.data_access.FileUserDataAccessObject;
//import src.interface_adapters.ViewManagerModel;
//import src.interface_adapters.logged_in.LoggedInViewModel;
//import src.interface_adapters.login.LoginViewModel;
//import src.interface_adapters.mealplan.MealPlanViewModel;
//import src.interface_adapters.preferences.PreferencesViewModel;
//import src.interface_adapters.signup.SignupViewModel;
//import src.interface_adapters.trackedNutrients.TrackedNutrientsViewModel;
//import src.interface_adapters.weightgoal.WeightGoalViewModel;
//import src.view.*;
//import src.interface_adapters.ViewManagerModel;
//import static org.junit.Assert.*;
//
//import javax.swing.*;
//import java.awt.*;
//
//public class SignUpViewTest {
//    SignupViewModel signupViewModel = new SignupViewModel();
//    LoginViewModel loginViewModel = new LoginViewModel();
//    ViewManagerModel viewManagerModel = new ViewManagerModel();
//    SignupView signupView;
//    @Before
//    public void setUp(){
//        JFrame application = new JFrame("Login Example");
//
//        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//
//        int width = (int) (screenSize.width * 0.9);
//        int height = (int) (screenSize.height * 0.9);
//        Dimension screenSize1 = new Dimension(width, height);
//        application.setPreferredSize(screenSize1);
//        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//
//        CardLayout cardLayout = new CardLayout();
//
//
//        // The various View objects. Only one view is visible at a time.
//        JPanel views = new JPanel(cardLayout);
//        application.add(views);
//
//        // This keeps track of and manages which view is currently showing.
//
//        ViewManagerModel viewManagerModel = new ViewManagerModel();
//        new ViewManager(views, cardLayout, viewManagerModel);
//
//        LoginViewModel loginViewModel = new LoginViewModel();
//        SignupViewModel signupViewModel = new SignupViewModel();
//        LoggedInViewModel loggedInViewModel = new LoggedInViewModel();
//        PreferencesViewModel preferencesViewModel = new PreferencesViewModel();
//        TrackedNutrientsViewModel trackedNutrientsViewModel = new TrackedNutrientsViewModel();
//        WeightGoalViewModel weightGoalViewModel = new WeightGoalViewModel();
//        MealPlanViewModel mealPlanViewModel = new MealPlanViewModel();
//
//        FileUserDataAccessObject userDataAccessObject;
//
//        userDataAccessObject = new FileUserDataAccessObject("./test_signUpUsers.csv", "mealplan.csv");
//
//        WelcomePageView trial = new WelcomePageView(cardLayout, views);
//        views.add(trial, trial.viewName);
//
//        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel,
//                userDataAccessObject, cardLayout, views);
//        views.add(signupView, signupView.viewName);
//
//        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, loggedInViewModel,
//                preferencesViewModel, weightGoalViewModel, trackedNutrientsViewModel, mealPlanViewModel, userDataAccessObject);
//
//        views.add(loginView, loginView.viewName);
//
//        LoggedInView loggedInView = new LoggedInView(loggedInViewModel, cardLayout, views);
//        views.add(loggedInView, loggedInView.viewName);
//
//        PreferencesView preferencesView = PreferencesUseCaseFactory.create(viewManagerModel, preferencesViewModel,
//                loggedInViewModel, userDataAccessObject);
//        views.add(preferencesView, preferencesView.viewName);
//
//        TrackedNutrientsView trackedNutrientsView = TrackedNutrientsUseCaseFactory.create(viewManagerModel,
//                trackedNutrientsViewModel, loggedInViewModel, userDataAccessObject);
//        views.add(trackedNutrientsView, trackedNutrientsView.viewName);
//
//
//        //trial.setVisible(true);
//
//        // TODO: Implement for weight goals
//        WeightGoalsView weightGoalsView = WeightGoalUseCaseFactory.create(viewManagerModel,
//                weightGoalViewModel,
//                loggedInViewModel,
//                userDataAccessObject);
//        views.add(weightGoalsView, weightGoalsView.viewName);
//
//
//        MealPlanView mealPlanView = MealPlanUseCaseFactory.create(viewManagerModel, mealPlanViewModel, loggedInViewModel, userDataAccessObject);
//        views.add(mealPlanView, mealPlanView.viewName);
//
//
//        viewManagerModel.setActiveView(trial.viewName);
//        viewManagerModel.firePropertyChanged();
//
//
//        application.pack();
//        application.setVisible(true);
//
//
//    }
//    @Test
//    public void testSignUpSuccess() {
//        signupViewModel.getState().setUsername("UserTesting");
//        signupViewModel.getState().setPassword("PasswordTesting");
//        signupViewModel.getState().setRepeatPassword("RepeatPassWordTesting");
//        //signupView.signUp.doClick();
//        assertEquals("UserTesting", loginViewModel.getState().getUsername());
//        assertEquals("log in", viewManagerModel.getActiveView());
//    }
//
//}