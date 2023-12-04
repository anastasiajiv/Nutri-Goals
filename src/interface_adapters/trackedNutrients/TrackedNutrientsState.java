package src.interface_adapters.trackedNutrients;

import javax.sound.midi.Track;
import java.util.ArrayList;

public class TrackedNutrientsState {
    private int userID;
    private ArrayList<String> trackedNutrients = null;  // default to null
    private String trackedNutrientsError;

    // constructor; make a copy
    public TrackedNutrientsState(TrackedNutrientsState copy) {
        this.userID = copy.userID;
        this.trackedNutrients = copy.trackedNutrients;
        this.trackedNutrientsError = copy.trackedNutrientsError;
    }

    // default constructor; not used
    public TrackedNutrientsState() {}

    // setters
    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setTrackedNutrients(ArrayList<String> trackedNutrients) {
        this.trackedNutrients = trackedNutrients;
    }

    public String setTrackedNutrientsError(String trackedNutrientsError) {
        return "Unable to set tracked nutrients. Please try again.";
    }

    // getters
    public int getUserID() {
        return this.userID;
    }
}
