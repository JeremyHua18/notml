import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WelcomeScreen {
    private JFrame welcomeFrame;
    private JLabel welcomeText;
    private JLabel welcomeText2;
    private JButton startButton;
    private GameController gameController;

    public WelcomeScreen(GameController gameController) {
        this.gameController = gameController;
        configure();
    }

    public void setVisible(boolean b) {
        welcomeFrame.setVisible(b);
    }

    public void configure() {
        welcomeFrame = new JFrame("Space Trader");
        welcomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        welcomeFrame.setSize(400,400);
        welcomeFrame.setLayout(null);

        welcomeText = new JLabel("Welcome to Space Trader!");
        welcomeText.setBounds(120,140,200,30);

        welcomeText2 = new JLabel("Come Explore the Universe!");
        welcomeText2.setBounds(120,160,200,30);

        startButton = new JButton("Start");
        //startButton.setActionCommand("start");
        startButton.setSize(100, 30);
        startButton.setBounds(150,185,100,30);

        welcomeFrame.add(welcomeText);
        welcomeFrame.add(welcomeText2);
        welcomeFrame.add(startButton);

        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameController.showConfigurationScreen();
            }
        });
    }



}