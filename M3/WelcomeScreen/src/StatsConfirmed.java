import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StatsConfirmed extends JFrame {
    JPanel panel;
    JLabel label;
    JButton startButton;
    GridLayout gridLayout;

    StatsConfirmed(String title, WelcomeController c, EnterStats statsInfo) {
        super(title);
        setSize(400, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gridLayout = new GridLayout();
        setLayout(gridLayout);
        label = new JLabel("WELCOME TO SPACE TRADER!");
        startButton = new JButton("START");

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
            }
        });
        add(label);
        add(startButton);
    }
}