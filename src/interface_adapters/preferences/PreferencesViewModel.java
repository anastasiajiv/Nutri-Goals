package src.interface_adapters.preferences;

import src.interface_adapters.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class PreferencesViewModel extends ViewModel{
    public static final String TITLE_LABEL = "Preferences View";
    public static final String DIETARY_LABEL = "Select Dietary Option";
    public static final String ALLERGIES_LABEL = "Select Any Allergies You Have";
    public static final String CONDITIONS_LABEL = "Select Amount for a Specific Nutrient";

    public static final String PREFERENCES_BUTTON_LABEL = "preferences";

    private PreferencesState state = new PreferencesState();

    public PreferencesViewModel(){super("preferences");}

    public void setState(PreferencesState state) {this.state = state;}

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public PreferencesState getState() {
        return state;
    }
}
