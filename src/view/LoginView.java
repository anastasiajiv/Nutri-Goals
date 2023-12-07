package src.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import src.interface_adapters.login.LoginController;
import src.interface_adapters.login.LoginState;
import src.interface_adapters.login.LoginViewModel;
import src.interface_adapters.signup.SignupState;
public class LoginView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "log in";
    private final LoginViewModel loginViewModel;

    final JTextField usernameInputField = new JTextField(15);
    private final JLabel usernameErrorField = new JLabel();

    final JPasswordField passwordInputField = new JPasswordField(15);
    private final JLabel passwordErrorField = new JLabel();

    final JButton logIn;
    final JButton cancel;
    private final LoginController loginController;


    public LoginView(LoginViewModel loginViewModel, LoginController controller) {

        this.loginController = controller;
        this.loginViewModel = loginViewModel;

        this.loginViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Login Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);


        LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel("Username"), usernameInputField);
        usernameInfo.setBackground(new Color(173, 216, 230));
        usernameInfo.setFont(new Font("Serif", Font.PLAIN, 23));
        LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel("Password"), passwordInputField);
        passwordInfo.setBackground(new Color(173, 216, 230));
        passwordInfo.setFont(new Font("Serif", Font.PLAIN, 23));

        JPanel buttons = new JPanel();
        logIn = new JButton(loginViewModel.LOGIN_BUTTON_LABEL);
        buttons.add(logIn);
        cancel = new JButton(loginViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);
        buttons.setBackground(new Color(173, 216, 230));

        logIn.addActionListener(            // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(logIn)) {
                            LoginState currentState = loginViewModel.getState();

                            loginController.execute(
                                    currentState.getUsername(),
                                    currentState.getPassword()
                            );
                        }
                    }
                }
        );
        //dont forget to implement this
        //cancel.addActionListener(this);

        usernameInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                LoginState currentState = loginViewModel.getState();
                currentState.setUsername(usernameInputField.getText() + e.getKeyChar());
                loginViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        passwordInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        LoginState currentState = loginViewModel.getState();
                        currentState.setPassword(passwordInputField.getText() + e.getKeyChar());
                        loginViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });


        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        Color color = new Color(173, 216, 230);
        this.setBackground(color);
        this.add(title);
        this.add(usernameInfo);
        this.add(usernameErrorField);
        this.add(passwordInfo);
        this.add(passwordErrorField);
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
        LoginState state = (LoginState) evt.getNewValue();
        setFields(state);
    }

    private void setFields(LoginState state) {
        usernameInputField.setText(state.getUsername());

    }
}
