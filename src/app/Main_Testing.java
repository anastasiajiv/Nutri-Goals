package src.app;
import src.interface_adapters.mealPlan.MealPlanViewModel;
import src.interface_adapters.preferences.PreferencesViewModel;
import src.interface_adapters.trackedNutrients.TrackedNutrientsViewModel;
import src.interface_adapters.weightgoal.WeightGoalViewModel;
import src.view.*;

import src.data_access.FileUserDataAccessObject;
//import src.data_access.InMemoryUserDataAccessObject;
import src.entity.CommonUserFactory;
import src.interface_adapters.login.LoginViewModel;
import src.interface_adapters.logged_in.LoggedInViewModel;
import src.interface_adapters.signup.SignupViewModel;
import src.interface_adapters.ViewManagerModel;
import src.use_case.login.LoginUserDataAccessInterface;
import src.view.LoggedInView;
import src.view.LoginView;
import src.view.SignupView;
import src.view.ViewManager;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main_Testing {
    public static void main(String[] args){
        JFrame application = new JFrame("Nutri-Goals");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int width = (int) (screenSize.width * 0.9);
        int height = (int) (screenSize.height * 0.9);
        Dimension screenSize1 = new Dimension(width, height);
        application.setPreferredSize(screenSize1);
        // application.setExtendedState(JFrame.MAXIMIZED_BOTH);
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();


        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // This keeps track of and manages which view is currently showing.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        LoginViewModel loginViewModel = new LoginViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        LoggedInViewModel loggedInViewModel = new LoggedInViewModel();
        PreferencesViewModel preferencesViewModel = new PreferencesViewModel();
        TrackedNutrientsViewModel trackedNutrientsViewModel = new TrackedNutrientsViewModel();
        WeightGoalViewModel weightGoalViewModel = new WeightGoalViewModel();
        MealPlanViewModel mealPlanViewModel = new MealPlanViewModel();

        FileUserDataAccessObject userDataAccessObject;

        userDataAccessObject = new FileUserDataAccessObject("./help2.csv", "mealplan.csv");


        WelcomePageView trial = new WelcomePageView(cardLayout, views);
        views.add(trial, trial.viewName);

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


        //trial.setVisible(true);

        // TODO: Implement for weight goals
        WeightGoalsView weightGoalsView = WeightGoalUseCaseFactory.create(viewManagerModel,
                weightGoalViewModel,
                loggedInViewModel,
                userDataAccessObject);
        views.add(weightGoalsView, weightGoalsView.viewName);


        MealPlanView mealPlanView = MealPlanUseCaseFactory.create(viewManagerModel, mealPlanViewModel, loggedInViewModel, userDataAccessObject);
        views.add(mealPlanView, mealPlanView.viewName);


        viewManagerModel.setActiveView(trial.viewName);
        viewManagerModel.firePropertyChanged();


        application.pack();
        application.setVisible(true);
        /*System.out.println(userDataAccessObject.accounts.get(5).getName());
        System.out.println(userDataAccessObject.accounts.get(5).getPassword());*/
        //System.out.println(userDataAccessObject.accounts.get(3).getCreationTime());
        //System.out.println(userDataAccessObject.accounts.get(5).);
        //System.out.println(userDataAccessObject.accounts.get(12).getPassword());
    }



}
