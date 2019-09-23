import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ConfigurationDisplayScreen {
    private JFrame configurationDisplayScreenFrame;
    private JLabel displayName, displaySkill;
    private GameController gameController;
    public ConfigurationDisplayScreen(GameController gameController) {
        this.gameController = gameController;
        configure();
    }

    public void setInformation(JLabel displayName, JLabel displaySkill) {
        this.displayName = displayName;
        this.displaySkill = displaySkill;
        displayName.setBounds(120,160,200,30);
        displaySkill.setBounds(100,185,200,100);
        configurationDisplayScreenFrame.add(displayName);
        configurationDisplayScreenFrame.add(displaySkill);
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