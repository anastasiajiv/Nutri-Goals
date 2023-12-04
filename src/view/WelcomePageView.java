package src.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeListener;
import src.view.SignupView;
import src.interface_adapters.signup.SignupController;
import src.interface_adapters.signup.SignupState;
import src.interface_adapters.signup.SignupViewModel;
public class WelcomePageView extends JPanel {
    public final String viewName = "Welcome Page";
    private final JButton createAccount;
    private final JButton logIn;


    public WelcomePageView(final CardLayout cardLayout, final JPanel views){
        JLabel title = new JLabel("Nutri-GOALS");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitle = new JLabel("Nutri-GOALS is an application that allows you to get delicious personalized " +
                "recipes based on your needs and preferences.");
        JPanel buttons = new JPanel();
        createAccount = new JButton("Create Account");
        buttons.add(createAccount);
        logIn = new JButton("Log In");
        buttons.add(logIn);

        createAccount.addActionListener(
                new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent evt){
                        if (evt.getSource().equals(createAccount)){
                            cardLayout.show(views, "sign up");
                        }
                    }
                }
        );


        logIn.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(logIn)){
                            cardLayout.show(views, "log in");
                        }
                    }
                }
        );
        this.add(title);
        this.add(subtitle);
        this.add(createAccount);
        this.add(logIn);



    }







}
