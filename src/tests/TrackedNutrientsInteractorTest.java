//package src.tests;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import src.app.*;
//import src.data_access.FileUserDataAccessObject;
//import src.interface_adapters.ViewManagerModel;
//import src.interface_adapters.logged_in.LoggedInViewModel;
//import src.interface_adapters.login.LoginViewModel;
//import src.interface_adapters.mealPlan.MealPlanViewModel;
//import src.interface_adapters.preferences.PreferencesViewModel;
//import src.interface_adapters.signup.SignupController;
//import src.interface_adapters.signup.SignupViewModel;
//import src.interface_adapters.trackedNutrients.TrackedNutrientsViewModel;
//import src.interface_adapters.weightgoal.WeightGoalViewModel;
//import src.view.*;
//import src.interface_adapters.ViewManagerModel;
//import static org.junit.Assert.*;
//
//import javax.swing.*;
//import java.awt.*;
//import java.io.*;
//import java.nio.file.Files;
//import java.time.LocalDateTime;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.StandardCopyOption;
//
//public class SignUpViewTest {
//    SignupViewModel signupViewModel = new SignupViewModel();
//    LoginViewModel loginViewModel = new LoginViewModel();
//    ViewManagerModel viewManagerModel = new ViewManagerModel();
//    LoggedInViewModel loggedInViewModel = new LoggedInViewModel();
//    PreferencesViewModel preferencesViewModel = new PreferencesViewModel();
//    TrackedNutrientsViewModel trackedNutrientsViewModel = new TrackedNutrientsViewModel();
//    WeightGoalViewModel weightGoalViewModel = new WeightGoalViewModel();
//    MealPlanViewModel mealPlanViewModel = new MealPlanViewModel();
//    SignupView signupView;
//    private final String userDataFilePath = "./help4.csv";
//    private final String userMealDataFilePath = "mealplan.csv";
//    private String originalUserData;
//    private String originalMealPlanData;
//
//
//    @Before
//    public void setUp() throws IOException{
//
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
//
//        new ViewManager(views, cardLayout, viewManagerModel);
//
//        FileUserDataAccessObject userDataAccessObject;
//
//        userDataAccessObject = new FileUserDataAccessObject(userDataFilePath, userMealDataFilePath);
//
//
//        signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel,
//                userDataAccessObject, cardLayout, views);
//        views.add(signupView, signupView.viewName);
//
//        /*LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, loggedInViewModel,
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
//        views.add(loginView, loginView.viewName);*/
//
//
//        viewManagerModel.setActiveView(signupView.viewName);
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
//
//        signupViewModel.getState().setUsername("UserTesting4");
//        signupViewModel.getState().setPassword("PasswordTesting");
//        signupViewModel.getState().setRepeatPassword("PasswordTesting");
//        signupView.signUp.doClick();
//        assertEquals("UserTesting4", signupViewModel.getState().getUsername());
//        assertEquals("PasswordTesting", signupViewModel.getState().getPassword());
//        assertEquals("PasswordTesting", signupViewModel.getState().getRepeatPassword());
//        assertEquals("log in", viewManagerModel.getActiveView());
//        assertEquals("UserTesting4", loginViewModel.getState().getUsername());
//    }
//
//    /*@After
//    public void restoreOriginal() throws IOException{
//        String line = "";
//        int lineNumber = 0;
//        try (BufferedReader br = new BufferedReader(new FileReader(userDataFilePath))){
//            FileWriter writer = new FileWriter(userDataFilePath);
//            while ((line = br.readLine()) != null){
//                if (lineNumber == 0){
//                    writer.write(line+ "\n");
//                } else {
//                    writer.write("");
//                    writer.close();
//                }
//                lineNumber++;
//            }
//
//        } catch (IOException e){
//            e.printStackTrace();
//        }
//    }*/
//
//}