package src.interface_adapters.preferences;

import src.interface_adapters.ViewManagerModel;
import src.use_case.preferences.PreferencesOutputBoundary;

public class PreferencesPresenter implements PreferencesOutputBoundary {
    private final PreferencesViewModel preferencesViewModel;

    private ViewManagerModel viewManagerModel;
}
