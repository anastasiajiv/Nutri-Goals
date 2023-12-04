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
    private final JCheckBox calories;
    private final JCheckBox carbohydrates;
    private final JCheckBox protein;
    private final JCheckBox fat;

    // main micronutrients presented as a combo box
    private final JCheckBox vitaminC;
    private final JCheckBox vitaminD;
    private final JCheckBox iron;
    private final JCheckBox magnesium;
    private final JCheckBox sugar;

    private final JButton confirm;

    // default constructor for the view
    public TrackedNutrientsView(TrackedNutrientsController controller, TrackedNutrientsViewModel viewModel) {
        this.trackedNutrientsController = controller;
        this.trackedNutrientsViewModel = viewModel;
        // let the view model know to listen to this view
        trackedNutrientsViewModel.addPropertyChangeListener(this);

        // set the title of the window
        JLabel title = new JLabel("Indicate any nutrients you would like to track in your meal plans");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // set the first subtitle for macronutrients
        JLabel macro_subtitle = new JLabel("Macronutrients");
        // create the panel and associated buttons
        JPanel macro_checkBox = new JPanel();
        this.calories = new JCheckBox(TrackedNutrientsViewModel.CALORIES_LABEL);
        this.carbohydrates = new JCheckBox(TrackedNutrientsViewModel.CARBOHYDRATES_LABEL);
        this.protein = new JCheckBox(TrackedNutrientsViewModel.PROTEIN_LABEL);
        this.fat = new JCheckBox(TrackedNutrientsViewModel.FAT_LABEL);
        macro_checkBox.add(this.calories);
        macro_checkBox.add(this.carbohydrates);
        macro_checkBox.add(this.protein);
        macro_checkBox.add(this.fat);

        // set the second subtitle for micronutrients
        JLabel micro_subtitle = new JLabel("Select Micronutrients");
        // create the panel and associated buttons
        JPanel micro_checkBox = new JPanel();
        this.vitaminC = new JCheckBox(TrackedNutrientsViewModel.VITAMINC_LABEL);
        this.vitaminD = new JCheckBox(TrackedNutrientsViewModel.VITAMIND_LABEL);
        this.iron = new JCheckBox(TrackedNutrientsViewModel.IRON_LABEL);
        this.magnesium = new JCheckBox(TrackedNutrientsViewModel.MAGNESIUM_LABEL);
        this.sugar = new JCheckBox(TrackedNutrientsViewModel.SUGAR_LABEL);
        micro_checkBox.add(this.vitaminC);
        micro_checkBox.add(this.vitaminD);
        micro_checkBox.add(this.iron);
        micro_checkBox.add(this.magnesium);
        micro_checkBox.add(this.sugar);

        // add all the buttons
        JPanel buttons = new JPanel();
        this.confirm = new JButton("Confirm");
        buttons.add(this.confirm);
        // add a new action listener for the confirm button; send off input data to controller
        this.confirm.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(confirm)) {
                            TrackedNutrientsState currentState = trackedNutrientsViewModel.getState();

                            // initialize the arrayList of nutrients to be tracked
                            ArrayList<String> trackedNutrients = new ArrayList<>();
                            // call the helper variable on each macro/micronutrient
                            nutrientSelected(trackedNutrients, "calories", calories);
                            nutrientSelected(trackedNutrients, "carbohydrates", carbohydrates);
                            nutrientSelected(trackedNutrients, "protein", protein);
                            nutrientSelected(trackedNutrients, "fat", fat);
                            nutrientSelected(trackedNutrients, "vitaminC", vitaminC);
                            nutrientSelected(trackedNutrients, "vitaminD", vitaminD);
                            nutrientSelected(trackedNutrients, "iron", iron);
                            nutrientSelected(trackedNutrients, "magnesium", magnesium);
                            trackedNutrients = nutrientSelected(trackedNutrients, "sugar", sugar);

                            // hand off the input data to the controller
                            trackedNutrientsController.execute(currentState.getUserID(),
                                    currentState.getTrackedNutrients));
                        }
                    }
                }
        );
        // add the titles, subtitles, buttons; set up view
        this.add(title);
        this.add(macro_subtitle);
        this.add(macro_checkBox);
        this.add(micro_subtitle);
        this.add(micro_checkBox);
    }

    // for any buttons that aren't implemented yet; testing purposes
    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showMessageDialog(this, "Not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // check to see if the user is properly logged in
        SignupState state = (SignupState) evt.getNewValue();
        if (state.getUsernameError() != null) {
            JOptionPane.showMessageDialog(this, state.getUsernameError());
        }
    }

    // helper method to check if each nutrient was selected
    private ArrayList<String> nutrientSelected(ArrayList<String> list, String name, JCheckBox nutrient) {
        // if selected, add the nutrient to the arraylist
        if (nutrient.isSelected())
            list.add(name);
        return list;
    }
}