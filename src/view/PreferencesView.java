package src.view;

import src.interface_adapters.preferences.PreferencesController;
import src.interface_adapters.preferences.PreferencesState;
import src.interface_adapters.preferences.PreferencesViewModel;
import src.interface_adapters.signup.SignupState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.*;
public class PreferencesView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "preferences";
    private final PreferencesViewModel preferencesViewModel;
    private final PreferencesController preferencesController;

    private final JRadioButton vegan;

    private final JRadioButton vegetarian;

    private final JRadioButton pescetarian;

    private final JCheckBox egg;
    private final JCheckBox peanut;

    private final JCheckBox seafood;

    private final JCheckBox sesame;

    private final JCheckBox shellfish;

    private final JCheckBox soy;

    private final JCheckBox treaNut;

    private final JCheckBox wheat;

    private final JComboBox<String> calcium;

    private final JComboBox<String> potassium;

    private final JComboBox<String> vitaminC;
    private final JComboBox<String> vitaminD;
    private final JComboBox<String> iron;
    private final JComboBox<String> magnesium;
    private final JComboBox<String> sugar;

    private final JButton confirm;

    public PreferencesView(PreferencesController controller, PreferencesViewModel preferencesViewModel) {
        this.preferencesController = controller;
        this.preferencesViewModel = preferencesViewModel;
        preferencesViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Here you can indicate any dietary needs or if you have any allergies or nutrients" +
                "you want considered");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitle1 = new JLabel("Dietary Restrictions");

        JPanel radioButtons = new JPanel();
        vegan = new JRadioButton(PreferencesViewModel.VEGAN_LABEL);
        radioButtons.add(vegan);
        vegetarian = new JRadioButton(PreferencesViewModel.VEGETARIAN_LABEL);
        radioButtons.add(vegetarian);
        pescetarian = new JRadioButton(PreferencesViewModel.PESCETARIAN_LABEL);
        radioButtons.add(pescetarian);

        JPanel checkBox = new JPanel();
        JLabel subtitle2 = new JLabel("Select Any Allergies");
        egg = new JCheckBox(PreferencesViewModel.EGG_LABEL);
        checkBox.add(egg);
        peanut = new JCheckBox(PreferencesViewModel.PEANUT_LABEL);
        checkBox.add(peanut);
        seafood = new JCheckBox(PreferencesViewModel.SEAFOOD_LABEL);
        checkBox.add(seafood);
        sesame = new JCheckBox(PreferencesViewModel.SESAME_LABEL);
        checkBox.add(sesame);
        shellfish = new JCheckBox(PreferencesViewModel.SHELLFISH_LABEL);
        checkBox.add(shellfish);
        soy = new JCheckBox(PreferencesViewModel.SOY_LABEL);
        checkBox.add(soy);
        treaNut = new JCheckBox(PreferencesViewModel.TREANUT_LABEL);
        checkBox.add(treaNut);
        wheat =  new JCheckBox(PreferencesViewModel.WHEAT_LABEL);
        checkBox.add(wheat);
        JLabel subtitle3 = new JLabel("Indicate the Daily Intake you Want for Certain Nutrients.");
        String[] levels = {"low", "average", "high"};

        JPanel dropBox = new JPanel();
        JLabel calcium1 = new JLabel("Calcium: ");
        calcium = new JComboBox<>(levels);
        dropBox.add(calcium1);
        dropBox.add(calcium);

        JLabel potassium1 = new JLabel("Potassium: ");
        potassium = new JComboBox<>(levels);
        dropBox.add(potassium1);
        dropBox.add(potassium);

        JLabel vitaminC1 = new JLabel("VitaminC: ");
        vitaminC = new JComboBox<>(levels);
        dropBox.add(vitaminC1);
        dropBox.add(vitaminC);

        JLabel vitaminD1 = new JLabel("VitaminD: ");
        vitaminD = new JComboBox<>(levels);
        dropBox.add(vitaminD1);
        dropBox.add(vitaminD);

        JLabel iron1 = new JLabel("Iron: ");
        iron = new JComboBox<>(levels);
        dropBox.add(iron1);
        dropBox.add(iron);

        JLabel magnesium1 = new JLabel("Magnesium: ");
        magnesium = new JComboBox<>(levels);
        dropBox.add(magnesium1);
        dropBox.add(magnesium);

        JLabel sugar1 = new JLabel("Sugar: ");
        sugar = new JComboBox<>(levels);
        dropBox.add(sugar1);
        dropBox.add(sugar);

        JPanel buttons = new JPanel();
        confirm = new JButton("Confirm");
        buttons.add(confirm);
        confirm.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(confirm)){
                            PreferencesState currentState = preferencesViewModel.getState();
                            HashMap<String, Boolean> dietaryMap = new HashMap<>();
                            dietaryMap.put("vegan", vegan.isSelected());
                            dietaryMap.put("vegetarian", vegetarian.isSelected());
                            dietaryMap.put("pescetarian", pescetarian.isSelected());
                            currentState.setDietaryMap(dietaryMap);

                            HashMap<String, Boolean> allergiesMap = new HashMap<>();
                            allergiesMap.put("egg", egg.isSelected());
                            allergiesMap.put("peanut", peanut.isSelected());
                            allergiesMap.put("seafood", seafood.isSelected());
                            currentState.setAllergiesMap(allergiesMap);

                            HashMap<String, String> conditionsMap = new HashMap<>();
                            conditionsMap.put("calcium", (String) calcium.getSelectedItem());
                            conditionsMap.put("potassium", (String) potassium.getSelectedItem());
                            conditionsMap.put("vitaminC", (String) vitaminC.getSelectedItem());
                            conditionsMap.put("vitaminD", (String) vitaminD.getSelectedItem());
                            conditionsMap.put("iron", (String) iron.getSelectedItem());
                            conditionsMap.put("sugar", (String) sugar.getSelectedItem());
                            currentState.setConditionsMap(conditionsMap);

                            preferencesController.execute(currentState.getUserID(), currentState.getDietaryMap(),
                                    currentState.getConditionsMap(), currentState.getAllergiesMap());
                        }
                    }
                }
        );
        this.add(title);
        this.add(subtitle1);
        this.add(radioButtons);
        this.add(subtitle2);
        this.add(checkBox);
        this.add(subtitle3);
        this.add(dropBox);

    }

    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showConfirmDialog(this, "Cancel not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SignupState state = (SignupState) evt.getNewValue();
        if (state.getUsernameError() != null) {
            JOptionPane.showMessageDialog(this, state.getUsernameError());
        }
    }

}
