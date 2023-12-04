package src.view;

import src.interface_adapters.mealPlan.MealPlanController;
import src.interface_adapters.mealPlan.MealPlanState;
import src.interface_adapters.mealPlan.MealPlanViewModel;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;



public class MealPlanView extends JPanel implements ActionListener, PropertyChangeListener {
    private final MealPlanViewModel mealPlanViewModel;
    final JTextField IDinputfield = new JTextField();
    private final JLabel IDErrorfield = new JLabel();

    private final MealPlanController mealPlanController;

    final JButton getmealplan;


    public MealPlanView(MealPlanController mealPlanController, MealPlanViewModel mealPlanViewModel){
        this.mealPlanController = mealPlanController;
        this.mealPlanViewModel = mealPlanViewModel;
        mealPlanViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Get Meal Plan");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel("User ID"), IDinputfield);

        JPanel buttons = new JPanel();

        getmealplan = new JButton(mealPlanViewModel.MEALPLAN_BUTTON_LABEL);

        buttons.add(getmealplan);

        getmealplan.addActionListener(                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(getmealplan)) {
                            MealPlanState currentState = mealPlanViewModel.getState();

                            //mealPlanController.execute();
                                    //String currentState.getId()


                        }
                    }
                }
        );










    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
