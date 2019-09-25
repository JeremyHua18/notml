import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WelcomeScreen {
    private JFrame welcomeFrame;
    private JLabel welcomeText;
    private JLabel welcomeText2;
    private JButton startButton;
    private ImageIcon astronaut;
    private JLabel astroDisp;
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
        welcomeFrame.setSize(400, 400);
        welcomeFrame.setLayout(null);

        welcomeText = new JLabel("Welcome to Space Trader!");
        welcomeText.setBounds(125, 140, 200, 30);

        welcomeText2 = new JLabel("Come Explore the Universe!");
        welcomeText2.setBounds(120, 160, 200, 30);

        startButton = new JButton("Start");
        startButton.setSize(100, 30);
        startButton.setBounds(150, 200, 100, 30);

        this.astronaut = new ImageIcon(getClass().getResource("astronaut.png"));
        this.astroDisp = new JLabel(astronaut);
        astroDisp.setSize(200, 200);
        astroDisp.setBounds(150, 50, 100, 80);

        welcomeFrame.add(welcomeText);
        welcomeFrame.add(welcomeText2);
        welcomeFrame.add(startButton);

        welcomeFrame.add(astroDisp);

        welcomeFrame.setLocationRelativeTo(null);

        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameController.showConfigurationScreen();
            }
        });
    }



}