package src.view;

import src.interface_adapters.trackedNutrients.TrackedNutrientsController;
import src.interface_adapters.trackedNutrients.TrackedNutrientsState;
import src.interface_adapters.trackedNutrients.TrackedNutrientsViewModel;
import src.interface_adapters.signup.SignupState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.*;

public class TrackedNutrientsView extends JPanel implements ActionListener, PropertyChangeListener {
    // plug in the view into the interface adapters for the use case
    public final String viewName = "trackedNutrients";
    private final TrackedNutrientsViewModel trackedNutrientsViewModel;
    private final TrackedNutrientsController trackedNutrientsController;

    // main macronutrients presented as checkboxes
    private final JCheckbox calories;
    private final JCheckbox carbohydrates;
    private final JCheckbox proteins;
    private final JCheckbox fats;

    // main micronutrients presented as a combo box
    private final JComboBox<String> vitaminC;
    private final JComboBox<String> vitaminD;
    private final JComboBox<String> iron;
    private final JComboBox<String> magnesium;
    private final JComboBox<String> sugar;

    private final JButton confirm;

    // default constructor for the view
    public TrackedNutrientsView(TrackedNutrientsController controller, TrackedNutrientsViewModel viewModel) {
        this.trackedNutrientsController = controller;
        this.trackedNutrientsViewModel = viewModel;
        // let the view model know to listen to this view
        trackedNutrientsViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Indicate any nutrients you would like to track in your meal plans");
    }
}