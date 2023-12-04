package src.use_case.preferences;

public interface PreferencesOutputBoundary {
    void prepareSuccessView(PreferencesOutputData user);

    void prepareFailView(String error);
}
