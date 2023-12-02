package src.app;
import src.view.*;

import src.data_access.FileUserDataAccessObject;
import src.data_access.InMemoryUserDataAccessObject;
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

public class Main1 {
    public static void main(String[] args){
        JFrame application = new JFrame("Login Example");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();


        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        LoginViewModel loginViewModel = new LoginViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        LoggedInViewModel loggedInViewModel = new LoggedInViewModel();

        FileUserDataAccessObject userDataAccessObject;
        userDataAccessObject = new FileUserDataAccessObject("./users.csv", new CommonUserFactory());
        // This keeps track of and manages which view is currently showing.

        //WelcomePageView trial = new WelcomePageView(cardLayout, views);
        //views.add(trial, trial.viewName);

        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel,
                userDataAccessObject, cardLayout, views);
        views.add(signupView, signupView.viewName);

        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, loggedInViewModel,
                userDataAccessObject, cardLayout, views);
        views.add(loginView, loginView.viewName);

        LoggedInView loggedInView = new LoggedInView(loggedInViewModel);
        views.add(loggedInView, loggedInView.viewName);

        viewManagerModel.setActiveView(signupView.viewName);
        viewManagerModel.firePropertyChanged();
        //trial.setVisible(true);

        application.pack();
        application.setVisible(true);
    }

}
