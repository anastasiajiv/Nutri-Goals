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
    private final CardLayout cardLayout;
    private final JPanel views;

    JLabel username;
    JLabel userID;
    JButton preferences;
    JButton trackedNutrients;

    final JButton logOut;

    /**
     * A window with a title and a JButton.
     */
    public LoggedInView(LoggedInViewModel loggedInViewModel, CardLayout cardLayout, JPanel views) {
        this.loggedInViewModel = loggedInViewModel;
        this.cardLayout = cardLayout;
        this.views = views;
        this.loggedInViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Logged In Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel usernameInfo = new JLabel("Currently logged in: ");
        username = new JLabel();
        userID = new JLabel();

        JPanel buttons = new JPanel();

        preferences = new JButton(loggedInViewModel.PREFERENCES_BUTTON_LABEL);
        buttons.add(preferences);
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

        // adding the JButton for the TrackedNutrients view and use case
        trackedNutrients = new JButton(loggedInViewModel.TRACKED_NUTRIENTS_BUTTON_LABEL);
        buttons.add(trackedNutrients);
        trackedNutrients.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(trackedNutrients)) {
                            // ...
                        }
                    }
                }
        );

        logOut = new JButton(loggedInViewModel.LOGOUT_BUTTON_LABEL);
        buttons.add(logOut);

        logOut.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(usernameInfo);
        this.add(username);
        this.add(userID);
        this.add(buttons);
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
}
