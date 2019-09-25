import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ConfigurationDisplayScreen {
    private JFrame configurationDisplayScreenFrame;
    private JLabel displayName, displaySkill, displayCredits;
    private GameController gameController;
    public ConfigurationDisplayScreen(GameController gameController) {
        this.gameController = gameController;
        configure();
    }

    public void setInformation(JLabel displayName, JLabel displaySkill, JLabel displayCredits) {
        this.displayName = displayName;
        this.displaySkill = displaySkill;
        this.displayCredits = displayCredits;
        displayName.setBounds(70, 60, 200, 30);
        displaySkill.setBounds(70, 100, 200, 100);
        displayCredits.setBounds(70, 210, 200, 30);
        configurationDisplayScreenFrame.add(displayName);
        configurationDisplayScreenFrame.add(displaySkill);
        configurationDisplayScreenFrame.add(displayCredits);
    }

    public void configure() {
        configurationDisplayScreenFrame = new JFrame("Configuration Display Screen");
        configurationDisplayScreenFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        configurationDisplayScreenFrame.setSize(400,400);
        configurationDisplayScreenFrame.setLocationRelativeTo(null);
        configurationDisplayScreenFrame.setLayout(null);


    }

    public void setVisible(boolean b) {
        configurationDisplayScreenFrame.setVisible(b);
    }
}