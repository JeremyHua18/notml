import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ConfigurationDisplayScreen {
    private JFrame configurationDisplayScreenFrame;
    private JLabel displayName, displaySkill, displayCredits, astroDisp2;
    private ImageIcon astronaut2;
    private GameController gameController;
    public ConfigurationDisplayScreen(GameController gameController) {
        this.gameController = gameController;
        configure();
    }

    public void setInformation(JLabel displayName, JLabel displaySkill, JLabel displayCredits) {
        this.displayName = displayName;
        this.displaySkill = displaySkill;
        this.displayCredits = displayCredits;
        displayName.setBounds(70, 100, 200, 30);
        displaySkill.setBounds(70, 120, 200, 100);
        displayCredits.setBounds(70, 230, 200, 30);
        this.astronaut2 = new ImageIcon(getClass().getResource("astronaut2.png"));
        this.astroDisp2 = new JLabel(astronaut2);
        astroDisp2.setSize(200, 200);
        astroDisp2.setBounds(30, 20, 100, 80);
        configurationDisplayScreenFrame.add(astroDisp2);
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