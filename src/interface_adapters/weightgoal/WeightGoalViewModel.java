package src.interface_adapters.weightgoal;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class WeightGoalViewModel extends ViewModel{

//    private final WeightGoalViewModel weightGoalViewModel;
//
//    //private final
//
//    private final LoggedInViewModel loggedInViewModel;
//
//    private ViewManagerModel viewManagerModel;

    public static final String TITLE_LABEL = "Weight Goal View";

    public static final String USERID_LABEL = "UserId:"; // Track the current users userID

    public static final String GENDER_LABEL = "Select Gender";
    public static final String HEIGHT_LABEL = "Input Your Height";
    public static final String WEIGHT_LABEL = "Input Your Weight";

    public static final String AGE_LABEL = "Input Your Age";

    public static final String exerciseLVL_LABEL = "Select Exercise Level";

    public static final String weightGoalType_LABEL = "Select Your Desired Weight Goal";

    public static final String paceType_LABEL = "Input weight";

    public static final String weightGOAL_BUTTONS_LAbEL = "Weight Goal";

    private WeightGoalState state = new WeightGoalState();

    public WeightGoalViewModel() {super("Weight Goal");} // highlighted red since ViewModel class is required

    public void setState(WeightGoalState state) {this.state = state;}

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);

    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public WeightGoalState getState(){
        return state;
    }



}
