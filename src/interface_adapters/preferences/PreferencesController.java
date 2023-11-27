package src.interface_adapters.preferences;

import src.use_case.preferences.PreferencesInputBoundary;
import src.use_case.preferences.PreferencesInputData;
import java.util.*;
public class PreferencesController {
    private PreferencesInputBoundary preferencesInteractor;

    public PreferencesController(PreferencesInputBoundary preferencesInteractor){
        this.preferencesInteractor = preferencesInteractor;
    }

    public void execute(Integer userID, HashMap<String, Boolean> dietary, HashMap<String, String> conditions, HashMap<String,
            Boolean> allergies){
        PreferencesInputData preferencesInputData = new PreferencesInputData(userID, dietary, conditions, allergies);
    }
}
