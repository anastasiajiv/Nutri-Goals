package src.interface_adapters.preferences;

import src.use_case.ViewManagerModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class MealPlanViewModel extends ViewManagerModel {

    private MealPlanState state = new MealPlanState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public MealPlanViewModel(){}

    public void setState(MealPlanState state){this.state = state;}
    @Override
    public void firePropertyChanged() { support.firePropertyChange("state", null, this.state); }
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) { support.addPropertyChangeListener(listener); }

    public MealPlanState getState() {
        return state;
    }


}
