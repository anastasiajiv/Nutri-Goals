package src.interface_adapters.mealplan;

import src.interface_adapters.mealplan.MealPlanState;
import src.use_case.mealplan.ViewManagerModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class MealPlanViewModel extends ViewManagerModel {

    public final String MEALPLAN_BUTTON_LABEL = "Get Meal Plan";

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
