package src.use_case.preferences;
import java.util.*;
public interface PreferencesUserDataAccessInterface {
    // where for each Integer refers to userId
    // want to change the way that the parameters for each method, UserID(which is an integer should be seperate)
    void saveDietary(HashMap<Integer, HashMap<String, Boolean>> user_dietery);
    void saveConditions(HashMap<Integer, HashMap<String, String>> user_condition);

    void saveAllergies(HashMap<Integer, HashMap<String, Boolean>> user_allergies);

    boolean existById(int id);
}
