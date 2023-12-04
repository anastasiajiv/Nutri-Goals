package src.interface_adapters.preferences;

import src.interface_adapters.ViewManagerModel;
import src.use_case.preferences.PreferencesOutputBoundary;
import src.use_case.preferences.PreferencesOutputData;
import src.interface_adapters.logged_in.LoggedInState;
import src.interface_adapters.logged_in.LoggedInViewModel;
public class PreferencesPresenter implements PreferencesOutputBoundary{
    private final PreferencesViewModel preferencesViewModel;
    private final LoggedInViewModel loggedInViewModel;

    private ViewManagerModel viewManagerModel;

    public PreferencesPresenter(ViewManagerModel viewManagerModel, PreferencesViewModel preferencesViewModel,
                                LoggedInViewModel loggedInViewModel){
        this.preferencesViewModel = preferencesViewModel;
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;

    }

    @Override
    public void prepareSuccessView(PreferencesOutputData response){
        LoggedInState loggedInState = loggedInViewModel.getState();
        this.loggedInViewModel.setState(loggedInState);
        this.loggedInViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(loggedInViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error){
        PreferencesState preferencesState = preferencesViewModel.getState();
        preferencesState.setPreferencesError(error);
        preferencesViewModel.firePropertyChanged();
    }

}
