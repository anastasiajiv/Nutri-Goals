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
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
public class PreferencesView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "preferences";
    private final PreferencesViewModel preferencesViewModel;
    private final PreferencesController preferencesController;

    private final JRadioButton vegan;

    private final JRadioButton vegetarian;

    private final JRadioButton pescetarian;
    private final JRadioButton none1;

    private final JCheckBox egg;
    private final JCheckBox peanut;

    private final JCheckBox seafood;

    private final JCheckBox sesame;

    private final JCheckBox shellfish;

    private final JCheckBox soy;

    private final JCheckBox treeNut;

    private final JCheckBox wheat;
    private final JCheckBox none;

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
                "you want considered.");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitle1 = new JLabel("Dietary Restrictions: ");

        ButtonGroup buttonGroup = new ButtonGroup();
        JPanel radioButtons = new JPanel();
        vegan = new JRadioButton(PreferencesViewModel.VEGAN_LABEL);
        buttonGroup.add(vegan);
        radioButtons.add(vegan);
        vegetarian = new JRadioButton(PreferencesViewModel.VEGETARIAN_LABEL);
        buttonGroup.add(vegetarian);
        radioButtons.add(vegetarian);
        pescetarian = new JRadioButton(PreferencesViewModel.PESCETARIAN_LABEL);
        buttonGroup.add(pescetarian);
        radioButtons.add(pescetarian);
        none1 = new JRadioButton(PreferencesViewModel.NONE_LABEL);
        buttonGroup.add(none1);
        radioButtons.add(none1);

        JPanel checkBox = new JPanel();
        JLabel subtitle2 = new JLabel("Select Any Allergies: ");
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
        treeNut = new JCheckBox(PreferencesViewModel.TREENUT_LABEL);
        checkBox.add(treeNut);
        wheat =  new JCheckBox(PreferencesViewModel.WHEAT_LABEL);
        checkBox.add(wheat);
        none = new JCheckBox(PreferencesViewModel.NONE_LABEL);
        checkBox.add(none);
        JLabel subtitle3 = new JLabel("Indicate the Daily Intake you Want for Certain Nutrients: ");
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
        none.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent evt) {
                if (none.isSelected()){
                    egg.setSelected(false);
                    egg.setEnabled(false);
                    peanut.setSelected(false);
                    peanut.setEnabled(false);
                    seafood.setSelected(false);
                    seafood.setEnabled(false);
                    sesame.setSelected(false);
                    sesame.setEnabled(false);
                    shellfish.setSelected(false);
                    shellfish.setEnabled(false);
                    soy.setSelected(false);
                    soy.setEnabled(false);
                    treeNut.setSelected(false);
                    treeNut.setEnabled(false);
                    wheat.setSelected(false);
                    wheat.setEnabled(false);
                } else {
                    egg.setEnabled(true);
                    peanut.setEnabled(true);
                    seafood.setEnabled(true);
                    sesame.setEnabled(true);
                    shellfish.setEnabled(true);
                    soy.setEnabled(true);
                    treeNut.setEnabled(true);
                    wheat.setEnabled(true);
                }
            }
        });

        confirm.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(confirm)){
                            PreferencesState currentState = preferencesViewModel.getState();
                            HashMap<String, Boolean> dietaryMap = new HashMap<>();
                            dietaryMap.put("Vegan", vegan.isSelected());
                            dietaryMap.put("Vegetarian", vegetarian.isSelected());
                            dietaryMap.put("Pescetarian", pescetarian.isSelected());
                            dietaryMap.put("none1", none1.isSelected());
                            currentState.setDietaryMap(dietaryMap);

                            HashMap<String, Boolean> allergiesMap = new HashMap<>();
                            allergiesMap.put("Egg", egg.isSelected());
                            allergiesMap.put("Peanut", peanut.isSelected());
                            allergiesMap.put("Seafood", seafood.isSelected());
                            allergiesMap.put("Sesame", sesame.isSelected());
                            allergiesMap.put("Shellfish", shellfish.isSelected());
                            allergiesMap.put("Soy", soy.isSelected());
                            allergiesMap.put("Tree Nut", treeNut.isSelected());
                            allergiesMap.put("Wheat", wheat.isSelected());
                            allergiesMap.put("none", none.isSelected());
                            currentState.setAllergiesMap(allergiesMap);

                            HashMap<String, String> conditionsMap = new HashMap<>();
                            conditionsMap.put("Calcium", (String) calcium.getSelectedItem());
                            conditionsMap.put("Potassium", (String) potassium.getSelectedItem());
                            conditionsMap.put("Vitamin C", (String) vitaminC.getSelectedItem());
                            conditionsMap.put("Vitamin D", (String) vitaminD.getSelectedItem());
                            conditionsMap.put("Iron", (String) iron.getSelectedItem());
                            conditionsMap.put("Magnesium", (String) magnesium.getSelectedItem());
                            conditionsMap.put("Sugar", (String) sugar.getSelectedItem());
                            currentState.setConditionsMap(conditionsMap);

                            preferencesController.execute(currentState.getUserID(), currentState.getDietaryMap(),
                                    currentState.getConditionsMap(), currentState.getAllergiesMap());
                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(subtitle1);
        this.add(radioButtons);
        this.add(subtitle2);
        this.add(checkBox);
        this.add(subtitle3);
        this.add(dropBox);
        this.add(confirm);

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
