package src.use_case.preferences;

import java.util.*;
public class PreferencesOutputData {
    private final int userID;

    public PreferencesOutputData(int userID){
        this.userID = userID;
    }
    public int getID(){return this.userID;}
}
