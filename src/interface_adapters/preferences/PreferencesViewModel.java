package src.interface_adapters.preferences;

import src.interface_adapters.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class PreferencesViewModel extends ViewModel{

    public static final String VEGAN_LABEL = "Vegan";
    public static final String VEGETARIAN_LABEL = "Vegetarian";
    public static final String PESCETARIAN_LABEL = "Pescetarian";

    public static final String EGG_LABEL = "Egg";
    public static final String PEANUT_LABEL = "Peanut";
    public static final String SEAFOOD_LABEL = "Seafood";
    public static final String SHELLFISH_LABEL = "Shellfish";
    public static final String SESAME_LABEL = "Sesame";
    public static final String SOY_LABEL = "Soy";
    public static final String TREENUT_LABEL = "Tree Nut";
    public static final String WHEAT_LABEL = "Wheat";
    public static final String CALCIUM_LABEL = "Calcium";
    public static final String POTASSIUM_LABEL = "Potassium";
    public static final String VITAMINC_LABEL = "Vitamin C";
    public static final String VITAMIND_LABEL = "Vitamin D";
    public static final String IRON_LABEL = "Iron";
    public static final String MAGNESIUM_LABEL = "Magnesium";
    public static final String SUGAR_LABEL = "Sugar";

    public static final String NONE_LABEL = "none";
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
