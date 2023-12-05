package src.view;

import src.interface_adapters.mealplan.MealPlanController;
import src.interface_adapters.mealplan.MealPlanState;
import src.interface_adapters.mealplan.MealPlanViewModel;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;



public class MealPlanView extends JPanel implements ActionListener, PropertyChangeListener {
    private final MealPlanViewModel mealPlanViewModel;

    public final String viewName = "Meal Plan";

    private final MealPlanController mealPlanController;

    final JButton getmealplan;


    public MealPlanView(MealPlanController mealPlanController, MealPlanViewModel mealPlanViewModel){
        this.mealPlanController = mealPlanController;
        this.mealPlanViewModel = mealPlanViewModel;
        mealPlanViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Get Meal Plan");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);



        JPanel buttons = new JPanel();

        getmealplan = new JButton(mealPlanViewModel.MEALPLAN_BUTTON_LABEL);

        buttons.add(getmealplan);

        getmealplan.addActionListener(                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(getmealplan)) {
                            MealPlanState currentState = mealPlanViewModel.getState();
                            //mealPlanController.execute(//TODO get id);
                            String mealplan = currentState.getMealplan();
                            showmealplan(mealplan);








                        }
                    }
                }
        );











    }

    public void showmealplan(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
