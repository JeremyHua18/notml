import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EnterStats extends JFrame {
    JPanel panel;
    JLabel pleaseChoose;
    JLabel label;
    JButton startButton;
    GridLayout gridLayout;
    TextField nameEntry;
    JRadioButton easy;
    JRadioButton medium;
    JRadioButton hard;
    ButtonGroup bgDifficulty;

    EnterStats(String title, WelcomeController c) {
        super(title);
        setSize(400, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel();
        gridLayout = new GridLayout();
        setLayout(gridLayout);
        easy = new JRadioButton("EASY");
        medium = new JRadioButton("MEDIUM");
        hard = new JRadioButton("HARD");
        label = new JLabel("This is the stats screen");
        startButton = new JButton("enter stats");

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                c.showWelcome();
            }
        });
        bgDifficulty = new ButtonGroup();
        bgDifficulty.add(easy);
        bgDifficulty.add(medium);
        bgDifficulty.add(hard);
        panel.add(easy);
        panel.add(medium);
        panel.add(hard);

        add(panel);
        add(label);
        add(startButton);
    }
}