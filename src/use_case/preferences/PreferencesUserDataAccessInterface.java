package src.use_case.preferences;
import java.util.*;
public interface PreferencesUserDataAccessInterface {
    // where for each Integer refers to userId
    void saveDietary(HashMap<Integer, HashMap<String, Boolean>> user_dietery);
    void saveConditions(HashMap<Integer, HashMap<String, String>> user_condition);

    void saveAllergies(HashMap<Integer, HashMap<String, Boolean>> user_allergies);

    Boolean existByUserID(int userID);
}
