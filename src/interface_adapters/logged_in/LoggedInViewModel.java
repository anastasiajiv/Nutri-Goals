package src.interface_adapters.logged_in;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import src.interface_adapters.ViewModel;
import src.interface_adapters.login.LoginState;
public class LoggedInViewModel extends ViewModel{
    public final String TITLE_LABEL = "Logged In View";

    private LoggedInState state = new LoggedInState();


    public static final String PREFERENCES_BUTTON_LABEL = "Preferences";

    public static final String TRACKED_NUTRIENTS_BUTTON_LABEL = "Tracked Nutrients";

    //Add weight goals button to logged in view
    public static final String weightGOAL_BUTTONS_LABEL = "Weight Goals";

    public static final String LOGOUT_BUTTON_LABEL = "Log out";

    public static final String MEALPLAN_BUTTON_LABEL = "Get Meal Plan";

    private String loggedInUser;

    public LoggedInViewModel() {
        super("logged in");
    }

    public void setState(LoggedInState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public LoggedInState getState() {
        return state;
    }


    public String getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(String loggedInUser) {
        this.loggedInUser = loggedInUser;
    }
}
