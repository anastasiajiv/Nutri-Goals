package src.view;

import src.interface_adapters.signup.SignupState;
import src.interface_adapters.weightgoal.WeightGoalController;
import src.interface_adapters.weightgoal.WeightGoalState;
import src.interface_adapters.weightgoal.WeightGoalViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;

public class WeightGoalsView extends JPanel implements ActionListener, PropertyChangeListener {

    private final WeightGoalViewModel weightGoalViewModel;
    private final WeightGoalController weightGoalController;

    public final String viewName = "Weight Goals";

    // Textfield for user inputs by keyboard
    final JTextField ageInputField = new JTextField(15); // create text panel to allow user to input their age
    private final JLabel ageErrorField = new JLabel();

    final JTextField heightInputField = new JTextField(15);
    private final JLabel heightErrorField = new JLabel();

    final JTextField weightInputField = new JTextField(15);
    private final JLabel weightErrorField = new JLabel();


    // Boxes for user inputs

    //Radio buttons for weight goal type since user can only select one and gender
    private final JRadioButton male;
    private final JRadioButton female;
    private final JRadioButton maintainWeight;
    private final JRadioButton loseWeight;
    private final JRadioButton gainWeight;

    //ComboBox for exercise input and paceType input
    private final JComboBox<Integer> exerciseLvl;
    private final JComboBox<String> paceType;

    private final JButton confirm;



    public WeightGoalsView(WeightGoalController weightGoalController,
                           WeightGoalViewModel weightGoalViewModel) {

        this.weightGoalViewModel = weightGoalViewModel;
        this.weightGoalController = weightGoalController;

        weightGoalViewModel.addPropertyChangeListener(this);


        JLabel title = new JLabel("Please tell us more about yourself"); // maybe add an additional
                                                                            // instructions button to guide the user
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        // Gender -> first
        JLabel subtitle1 = new JLabel("Please select your gender: ");

        subtitle1.setAlignmentX(Component.CENTER_ALIGNMENT);

        ButtonGroup buttonGroup1 = new ButtonGroup();
        JPanel radioButtons1 = new JPanel();
        //Add male button for gender selection
        male = new JRadioButton(WeightGoalViewModel.MALE_LABEL);
        buttonGroup1.add(male);
        radioButtons1.add(male);
        //Add female button for gender selection
        female = new JRadioButton(WeightGoalViewModel.FEMALE_LABEL);
        buttonGroup1.add(female);
        radioButtons1.add(female);


        // Weight Goal selection -> Last
        JLabel subtitle2 = new JLabel("Please select your desired weight goal: ");
        subtitle2.setAlignmentX(Component.CENTER_ALIGNMENT);

        ButtonGroup buttonGroup2 = new ButtonGroup();
        JPanel radioButtons2 =  new JPanel();
        //Add buttons to select weight goal
        //Add maintainWeight
        maintainWeight = new JRadioButton(WeightGoalViewModel.MAINTAINWEIGHT_LABEL);
        buttonGroup2.add(maintainWeight);
        radioButtons2.add(maintainWeight);
        //Add loseWeight
        loseWeight = new JRadioButton(WeightGoalViewModel.LOSEWEIGHT_LABEL);
        buttonGroup2.add(loseWeight);
        radioButtons2.add(loseWeight);
        //Add gainWeight
        gainWeight = new JRadioButton(WeightGoalViewModel.GAINWEIGHT_LABEL);
        buttonGroup2.add(gainWeight);
        radioButtons2.add(gainWeight);


        // Textfields for user inputs
        LabelTextPanel heightInfo = new LabelTextPanel(new JLabel(WeightGoalViewModel.HEIGHT_LABEL), heightInputField);
        LabelTextPanel weightInfo = new LabelTextPanel(new JLabel(WeightGoalViewModel.WEIGHT_LABEL), weightInputField);
        LabelTextPanel ageInfo = new LabelTextPanel(new JLabel(WeightGoalViewModel.AGE_LABEL), ageInputField);

        // Drop down options for Exercise level and Pace Type
        JLabel subtitile3 = new JLabel("Select Your current Activity Level: ");
        subtitile3.setAlignmentX(Component.CENTER_ALIGNMENT);
        //TODO: change exerciseLvl -> activity level
        Integer[] levels = {1,2,3,4,5};

        JPanel dropbox = new JPanel();
        JLabel exerciseLvl1 = new JLabel("Activity Level: ");
        exerciseLvl = new JComboBox<>(levels);
        dropbox.add(exerciseLvl1);
        dropbox.add(exerciseLvl);

        // PaceType
        JLabel subtitle4 = new JLabel("Select the pace you want to gain/lose weight: ");
        // user can select anything if they choose maintainWieght
        String[] levels2 = {"normal", "fast", "extreme"};
        subtitle4.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel dropbox2 = new JPanel();
        JLabel paceType1 = new JLabel("Pace");
        paceType = new JComboBox<>(levels2);
        dropbox2.add(paceType1);
        dropbox2.add(paceType);

        //Add confirm button
        JPanel buttons = new JPanel();
        confirm = new JButton("Confirm");
        buttons.add(confirm);

        ageInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                WeightGoalState currentState = weightGoalViewModel.getState();
                String text = ageInputField.getText() + e.getKeyChar();
                currentState.setAge(Integer.parseInt(text));// convert to int
                weightGoalViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        heightInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                WeightGoalState currentState = weightGoalViewModel.getState();
                String text = heightInputField.getText() + e.getKeyChar();
                currentState.setHeight(Double.parseDouble(text));
                weightGoalViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        weightInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                WeightGoalState currentState = weightGoalViewModel.getState();
                String text = weightInputField.getText() + e.getKeyChar();
                currentState.setWeight(Double.parseDouble(text));
                weightGoalViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });


        confirm.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(confirm)){
                            WeightGoalState currentState = weightGoalViewModel.getState();

                            HashMap<String, Boolean> gender = new HashMap<>();
                            gender.put("male", male.isSelected());
                            gender.put("female", female.isSelected());
                            currentState.setGender(gender);

                            HashMap<String, Boolean> weightGoal = new HashMap<>();
                            weightGoal.put("maintainWeight", maintainWeight.isSelected());
                            weightGoal.put("gainWeight", gainWeight.isSelected());
                            weightGoal.put("loseWeight", loseWeight.isSelected());
                            currentState.setWeightGoal(weightGoal);

                            int exerciseLvlSave = (Integer) exerciseLvl.getSelectedItem();
                            currentState.setExerciseLvl(exerciseLvlSave);
                            String paceTypeSave = (String) paceType.getSelectedItem();
                            currentState.setPaceType(paceTypeSave);

                            weightGoalController.execute(currentState.getUserId(),
                                    currentState.getGender(),
                                    currentState.getHeight(),
                                    currentState.getWeight(),
                                    currentState.getAge(),
                                    currentState.getExerciseLvl(),
                                    currentState.getPaceType(),
                                    currentState.getWeightGoal());

                        }

                    }
                }
        );

        // text input fields
        Color color = new Color(173, 216, 230);
        this.setBackground(color);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(subtitle1);// Gender selection
        this.add(radioButtons1);
        this.add(subtitle2);//Weight goal selection
        this.add(radioButtons2);
        this.add(subtitile3); //Activity level
        this.add(dropbox);
        this.add(subtitle4);// Pace Type
        this.add(dropbox2);
        this.add(heightInfo);
        this.add(weightInfo);
        this.add(ageInfo);
        this.add(confirm);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showConfirmDialog(this, "Cancel not implemented yet");

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SignupState state = (SignupState) evt.getNewValue();
        if (state.getUsernameError() != null) {
            JOptionPane.showMessageDialog(this, state.getUsernameError());
        }

    }
}
