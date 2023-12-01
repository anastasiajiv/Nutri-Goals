package src.use_case.preferences;

public class PreferencesOutputBoundary {
    void prepareSuccessView(PreferencesOutputData user);

    void prepareFailView(String error);
}
