import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeScreen extends JFrame {
    JPanel welcomePanel;
    JPanel startPanel;
    JLabel label;
    JButton startButton;
    GridLayout gridLayout;

    WelcomeScreen(String title, WelcomeController c) {
        super(title);
        setSize(400, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gridLayout = new GridLayout(2, 1);
        setLayout(gridLayout);
        label = new JLabel("WELCOME TO SPACE TRADER!", SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.BOTTOM);
        startButton = new JButton("START NEW GAME");

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                c.showStats();
            }
        });
        startPanel = new JPanel();
        startPanel.add(startButton);
        add(label);
        add(startPanel);
    }
}