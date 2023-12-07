package src.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import src.interface_adapters.logged_in.LoggedInState;
import src.interface_adapters.logged_in.LoggedInViewModel;


public class LoggedInView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "logged in";
    private final LoggedInViewModel loggedInViewModel;

    private final JLabel title;

    private final JLabel usernameInfo;
    private final CardLayout cardLayout;
    private final JPanel views;

    JLabel username;
    JLabel userID;
    JButton preferences;
    JButton trackedNutrients;

    JButton weightGoals;

    JButton getmealplan;


    JLabel usernameTitle;

    JLabel userIDTitle;

    JLabel instructions;
    JLabel instructions2;

    final JButton logOut;

    /**
     * A window with a title and a JButton.
     */
    public LoggedInView(LoggedInViewModel loggedInViewModel, CardLayout cardLayout, JPanel views) {
        this.loggedInViewModel = loggedInViewModel;
        this.cardLayout = cardLayout;
        this.views = views;

        this.loggedInViewModel.addPropertyChangeListener(this);

        title = new JLabel("Logged In Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Serif", Font.PLAIN, 40));

        usernameInfo = new JLabel("Currently logged in: ");
        usernameInfo.setFont(new Font("Serif", Font.PLAIN, 30));
        usernameTitle = new JLabel("Username: ");
        usernameTitle.setFont(new Font("Serif", Font.PLAIN, 23));
        username = new JLabel();
        username.setFont(new Font("Serif", Font.PLAIN, 23));
        userIDTitle = new JLabel("UserID: ");
        userIDTitle.setFont(new Font("Serif", Font.PLAIN, 23));
        userID = new JLabel();
        userID.setFont(new Font("Serif", Font.PLAIN, 23));

        //buttons = new JPanel();
        instructions = new JLabel("Please click on each button to set your information, needs and preferences.");
        instructions.setFont(new Font("Serif", Font.PLAIN, 23));
        preferences = new JButton(loggedInViewModel.PREFERENCES_BUTTON_LABEL);
        preferences.setPreferredSize(new Dimension(200, 50));

        instructions2 = new JLabel("After confirming your choices, generate your daily recipes!");
        weightGoals = new JButton(loggedInViewModel.weightGOAL_BUTTONS_LABEL);
        weightGoals.setPreferredSize(new Dimension(200, 50));

        instructions2.setFont(new Font("Serif", Font.PLAIN, 23));
        getmealplan = new JButton(loggedInViewModel.MEALPLAN_BUTTON_LABEL);
        getmealplan.setPreferredSize(new Dimension(200, 50));


        preferences.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(preferences)){
                            cardLayout.show(views, "preferences");
                        }

                    }
                }
        );


        weightGoals.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt2) {
                        if (evt2.getSource().equals(weightGoals)) {
                            cardLayout.show(views, "Weight Goals");
                        }
                    }
                }
        );


        getmealplan.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt2) {
                        if (evt2.getSource().equals(getmealplan)) {
                            cardLayout.show(views, "Meal Plan");
                        }
                    }
                }

        );

        // adding the JButton for the TrackedNutrients view and use case
        trackedNutrients = new JButton(loggedInViewModel.TRACKED_NUTRIENTS_BUTTON_LABEL);
        trackedNutrients.setPreferredSize(new Dimension(200, 50));
        trackedNutrients.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(trackedNutrients)) {
                            cardLayout.show(views, "tracked nutrients");
                        }
                    }
                }
        );

        logOut = new JButton(loggedInViewModel.LOGOUT_BUTTON_LABEL);
        logOut.setPreferredSize(new Dimension(200, 50));
        //buttons.add(logOut);

        logOut.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(logOut)){
                            cardLayout.show(views, "Welcome Page");
                        }
                    }
                }
        );

        Color color = new Color(173, 216, 230);
        this.setBackground(color);
        this.add(title);
        this.add(usernameInfo);
        this.add(username);
        this.add(userID);
        this.add(instructions);
        this.add(preferences);
        this.add(weightGoals);
        this.add(trackedNutrients);
        this.add(instructions2);
        this.add(getmealplan);
        this.add(logOut);
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LoggedInState state = (LoggedInState) evt.getNewValue();
        username.setText(state.getUsername());
        userID.setText(Integer.toString(state.getUserID()));
    }

    // setting the location of all the buttons and labels in the code
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        title.setBounds(getWidth()/ 2 - title.getWidth() / 2,
                getHeight() / 2 - title.getHeight()/ 2 - 380,
                title.getWidth(), title.getHeight());

        usernameInfo.setBounds(getWidth()/ 2 - usernameInfo.getWidth() / 2,
                getHeight() / 2 - usernameInfo.getHeight()/ 2 - 300,
                usernameInfo.getWidth(), usernameInfo.getHeight());

        usernameTitle.setBounds(getWidth()/ 2 - usernameTitle.getWidth() / 2 - 100,
                getHeight() / 2 - usernameTitle.getHeight()/ 2  - 260,
                usernameTitle.getWidth(), usernameTitle.getHeight());

        username.setBounds(getWidth()/ 2 - username.getWidth() / 2,
                getHeight() / 2 - username.getHeight()/ 2  - 260,
                username.getWidth(), username.getHeight());

        userIDTitle.setBounds(getWidth()/ 2 - userIDTitle.getWidth() / 2 - 100,
                getHeight() / 2 - userIDTitle.getHeight()/ 2  - 230,
                userIDTitle.getWidth(), userIDTitle.getHeight());

        userID.setBounds(getWidth()/ 2 - userID.getWidth() / 2,
                getHeight() / 2 - userID.getHeight()/ 2  - 230,
                userID.getWidth(), userID.getHeight());

        instructions.setBounds(getWidth()/ 2 - instructions.getWidth() / 2,
                getHeight() / 2 - instructions.getHeight()/ 2  - 150,
                instructions.getWidth(), instructions.getHeight());

        preferences.setBounds(getWidth()/ 2 - preferences.getWidth() / 2 - 300,
                getHeight() / 2 - preferences.getHeight()/ 2  - 60,
                preferences.getWidth(), preferences.getHeight());

        weightGoals.setBounds(getWidth()/ 2 - weightGoals.getWidth() / 2 ,
                getHeight() / 2 - weightGoals.getHeight()/ 2  - 60,
                weightGoals.getWidth(), weightGoals.getHeight());

        trackedNutrients.setBounds(getWidth()/ 2 - trackedNutrients.getWidth() / 2 + 300,
                getHeight() / 2 - trackedNutrients.getHeight()/ 2  - 60,
                trackedNutrients.getWidth(), trackedNutrients.getHeight());

        instructions2.setBounds(getWidth()/ 2 - instructions2.getWidth() / 2,
                getHeight() / 2 - instructions2.getHeight()/ 2  + 15,
                instructions2.getWidth(), instructions2.getHeight());

        getmealplan.setBounds(getWidth()/ 2 - getmealplan.getWidth() / 2,
                getHeight() / 2 - getmealplan.getHeight()/ 2 + 80,
                getmealplan.getWidth(), getmealplan.getHeight());

        logOut.setBounds(getWidth()/ 2 - logOut.getWidth() / 2 , getHeight() / 2 - logOut.getHeight()/ 2 + 200,
                logOut.getWidth(), logOut.getHeight());
    }
}
