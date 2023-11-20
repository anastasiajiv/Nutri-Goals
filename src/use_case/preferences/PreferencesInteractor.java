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
        HashMap<Integer, HashMap<String, Boolean>> dietary = preferencesInputData.getDietary();
        HashMap<Integer, HashMap<String, String>> conditions = preferencesInputData.getConditions();
        HashMap<Integer, HashMap<String, Boolean>> allergies = preferencesInputData.getAllergies();
        int userID = preferencesInputData.getUserId();
        if (!userDataAccessObject.existById(userID)){
            preferencesPresenter.prepareFailView("Unable to add preferences as user does not exist.Please try again.");
        } else {
            userDataAccessObject.saveDietary(dietary);
            userDataAccessObject.saveConditions(conditions);
            userDataAccessObject.saveAllergies(allergies);
            preferencesPresenter.prepareSuccessView("Your preferences have successfully been added.");
        }
    }
}
