package src.interface_adapters.trackedNutrients;

import src.interface_adapters.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class TrackedNutrientsViewModel extends ViewModel {
    // macronutrient labels
    public static final String CALORIES_LABEL = "Calories";
    public static final String CARBOHYDRATES_LABEL = "Carbohydrates";
    public static final String PROTEIN_LABEL = "Protein";
    public static final String FAT_LABEL = "Fat";

    // micronutrients labels
    public static final String VITAMIN_C_LABEL = "Vitamin C";
    public static final String VITAMIN_D_LABEL = "Vitamin D";
    public static final String IRON_LABEL = "Iron";
    public static final String MAGNESIUM_LABEL = "Magnesium";
    public static final String SUGAR_LABEL = "Sugar";

    // tracked nutrients label
    public static final String TRACKED_NUTRIENTS_BUTTON_LABEL = "tracked nutrients";

    // set the given state
    private TrackedNutrientsState state = new TrackedNutrientsState();

    // default constructor; calling super -> ViewModel
    public TrackedNutrientsViewModel() {
        super("tracked nutrients");
    }

    public void setState(TrackedNutrientsState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public TrackedNutrientsState getState() {
        return this.state;
    }

}
