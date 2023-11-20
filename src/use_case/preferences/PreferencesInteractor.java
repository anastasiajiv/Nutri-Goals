package src.use_case.preferences;
import src.entity.User;
import java.util.*;
public class PreferencesInteractor implements PreferencesInputBoundary{
    final PreferencesUserDataAccessInterface userDataAccessObject;
    final PreferencesOutputBoundary preferencesPresenter;

    public PreferencesInteractor(PreferencesUserDataAccessInterface userDataAccessInterface,
                                PreferencesOutputBoundary preferencesOutputBoundary){
        this.userDataAccessObject = userDataAccessInterface;
        this.preferencesPresenter = preferencesOutputBoundary;
    }



}
