package src.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class WelcomePageView extends JPanel {
    public final String viewName = "Welcome Page";
    private final JLabel title;
    private final JLabel subtitle;
    private final JButton createAccount;
    private final JButton logIn;

    public WelcomePageView(final CardLayout cardLayout, final JPanel views){
        title = new JLabel("NUTRI-GOALS");
        title.setHorizontalAlignment(title.CENTER);
        title.setVerticalAlignment(title.CENTER);
        title.setFont(new Font("Euphemia", Font.PLAIN, 100));
        subtitle = new JLabel("<html>Achieving your nutrient <br> goals has never seemed easier <br> with NUTRI-GOALS' <br> delicious" +
                "recipes and goal <br> tracking system.<html>");
        subtitle.setHorizontalAlignment(subtitle.CENTER);
        subtitle.setVerticalAlignment(subtitle.CENTER);
        subtitle.setFont(new Font("Cheri", Font.PLAIN, 40));

        JPanel buttons = new JPanel();
        createAccount = new JButton("Create Account");
        createAccount.setPreferredSize(new Dimension(200, 50));
        buttons.add(createAccount);
        logIn = new JButton("Log In");
        logIn.setPreferredSize(new Dimension(200, 50));
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

        //Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //Dimension screenSize = new Dimension(400, 300);
        //this.setPreferredSize(screenSize);
        Color color = new Color(173, 216, 230);
        this.setBackground(color);
        //this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //gridBagConstraints.anchor = GridBagConstraints.NORTH;
        this.add(title);

        this.add(subtitle);

        this.add(createAccount);

        this.add(logIn);



    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        title.setBounds(getWidth()/ 2 - title.getWidth() / 2 - 290, getHeight() / 2 - title.getHeight()/ 2 - 200,
                title.getWidth(), title.getHeight());
        subtitle.setBounds(getWidth()/ 2 - title.getWidth() / 2 - 210,
                getHeight() / 2 - subtitle.getHeight()/ 2  - 10,
                subtitle.getWidth(), subtitle.getHeight());

        createAccount.setBounds(getWidth()/ 2 - createAccount.getWidth() / 2 - 500,
                getHeight() / 2 - createAccount.getHeight()/ 2 + 170,
                createAccount.getWidth(), createAccount.getHeight());
        logIn.setBounds(getWidth()/ 2 - logIn.getWidth() / 2 - 100, getHeight() / 2 - logIn.getHeight()/ 2 + 170,
                createAccount.getWidth(), logIn.getHeight());
    }





}
