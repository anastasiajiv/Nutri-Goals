package src.app;

import src.entity.CommonUserFactory;
import src.interface_adapters.ViewManagerModel;
import src.interface_adapters.logged_in.LoggedInViewModel;
import src.interface_adapters.login.LoginPresenter;
import src.interface_adapters.preferences.PreferencesController;
import src.interface_adapters.preferences.PreferencesPresenter;
import src.interface_adapters.preferences.PreferencesViewModel;
import src.use_case.preferences.PreferencesInputBoundary;
import src.use_case.preferences.PreferencesInteractor;
import src.use_case.preferences.PreferencesOutputBoundary;
import src.use_case.preferences.PreferencesUserDataAccessInterface;
import src.view.PreferencesView;

import javax.swing.*;
import java.io.IOException;

public class PreferencesUseCaseFactory {

    private PreferencesUseCaseFactory(){}

    public static PreferencesView create(
            ViewManagerModel viewManagerModel,
            PreferencesViewModel preferencesViewModel,
            LoggedInViewModel loggedInViewModel,
            PreferencesUserDataAccessInterface preferencesUserDataAccessInterface) {
        try {
            PreferencesController preferencesController = createPreferenceUseCase(viewManagerModel, loggedInViewModel,
                    preferencesViewModel, preferencesUserDataAccessInterface);
            return new PreferencesView(preferencesController, preferencesViewModel);
        } catch (IOException e){
            JOptionPane.showMessageDialog(null, "Could not open user data file");
        }

        return null;
    }

    private static PreferencesController createPreferenceUseCase(
            ViewManagerModel viewManagerModel,
            LoggedInViewModel loggedInViewModel,
            PreferencesViewModel preferencesViewModel,
            PreferencesUserDataAccessInterface preferencesUserDataAccessInterface) throws IOException{
        PreferencesOutputBoundary preferencesOutputBoundary = new PreferencesPresenter(viewManagerModel,
                preferencesViewModel, loggedInViewModel);

        //UserFactory userFactory = new CommonUserFactory();

        PreferencesInputBoundary preferencesInteractor = new PreferencesInteractor(preferencesUserDataAccessInterface,
                preferencesOutputBoundary);
        return new PreferencesController(preferencesInteractor);
    }

}
