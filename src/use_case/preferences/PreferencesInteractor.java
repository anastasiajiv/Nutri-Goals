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


    @Override
    public void execute(PreferencesInputData preferencesInputData){
        int userId = preferencesInputData.getUserId();
        HashMap<String, Boolean> dietary = preferencesInputData.getDietary();
        HashMap<String, String> conditions = preferencesInputData.getConditions();
        HashMap<String, Boolean> allergies = preferencesInputData.getAllergies();
        int userID = preferencesInputData.getUserId();
        if (!userDataAccessObject.existByUserID(userID)){
            preferencesPresenter.prepareFailView("Unable to add preferences as user does not exist.Please try again.");
        } else {
            userDataAccessObject.savePreferences(userId, dietary, allergies, conditions);

            PreferencesOutputData preferencesOutputData = new PreferencesOutputData(userId);
            preferencesPresenter.prepareSuccessView(preferencesOutputData);

        }
    }
}
