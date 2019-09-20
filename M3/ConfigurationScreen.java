import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ConfigurationScreen {
    public ConfigurationScreen() {
        JFrame frame = new JFrame("SpaceTrader: Configuration Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);

        JLabel welcomeText = new JLabel("Welcome to the SpaceTrader Configuration Screen");
        welcomeText.setFont(new Font("Times New Roman", Font.BOLD, 20));

        // enter character name
        JLabel welcomeText2 = new JLabel("Enter your desired name:");
        welcomeText2.setFont(new Font("Times New Roman", Font.PLAIN, 14));

        // select difficulty: easy, medium, hard

        JButton startGameButton = new JButton("Start Game");
        startGameButton.setFont(new Font("Times New Roman", Font.BOLD, 20));

        JPanel vpanel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(vpanel, BoxLayout.Y_AXIS);
        vpanel.setLayout(boxLayout);
        vpanel.add(welcomeText);
        vpanel.add(welcomeText2);
        vpanel.add(startGameButton);

        frame.getContentPane().add(vpanel);
        frame.setVisible(true);

        startGameButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                // exit out of current screen
                // Go to configuration screen (instantiate a config screen and show it)
                ConfigurationScreen configscreen = new ConfigurationScreen();
                configscreen.show();
            }
        });




        // allocate skill points to different skills: Pilot, Fighter, Merchant, Engineer
            // Easy = 16 skill points, Medium = 12 points, Hard = 8 skill points

    }
}