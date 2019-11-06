import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class ConfigurationDisplayScreen {
    private JFrame configurationDisplayScreenFrame;
    private JLabel displayName;
    private JLabel displaySkill;
    private JLabel displayCredits;
    private JLabel astroDisp2;
    private JLabel displayDiff;
    private ImageIcon astronaut2;
    private GameController gameController;
    private JButton startGameButton;
    //private Map universe;
    public ConfigurationDisplayScreen(GameController gameController) {
        this.gameController = gameController;
        //universe = new Map();
        configure();
    }

    public void setInformation(JLabel displayName, JLabel displaySkill, JLabel displayCredits,
        JLabel displayDiff) {
        this.displayName = displayName;
        this.displaySkill = displaySkill;
        this.displayCredits = displayCredits;
        this.displayDiff = displayDiff;
        displayName.setBounds(70, 100, 200, 30);
        displaySkill.setBounds(70, 120, 200, 100);
        displayCredits.setBounds(70, 200, 200, 30);
        displayDiff.setBounds(70, 115, 200, 30);
        this.astronaut2 = new ImageIcon(getClass().getResource("astronaut2.png"));
        this.astroDisp2 = new JLabel(astronaut2);
        astroDisp2.setSize(200, 200);
        astroDisp2.setBounds(30, 20, 100, 80);
        configurationDisplayScreenFrame.add(astroDisp2);
        configurationDisplayScreenFrame.add(displayName);
        configurationDisplayScreenFrame.add(displaySkill);
        configurationDisplayScreenFrame.add(displayCredits);
        configurationDisplayScreenFrame.add(displayDiff);

    }

    public void configure() {
        configurationDisplayScreenFrame = new JFrame("Configuration Display Screen");
        configurationDisplayScreenFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        configurationDisplayScreenFrame.setSize(400, 400);
        configurationDisplayScreenFrame.setLocationRelativeTo(null);
        configurationDisplayScreenFrame.setLayout(null);
        startGameButton = new JButton("Enter Game!");
        startGameButton.setSize(200, 30);
        startGameButton.setBounds(115, 250, 150, 30);
        configurationDisplayScreenFrame.add(startGameButton);
        Random random = new Random();
        int firstInt = random.nextInt(10);
        startGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MapRegion startingLocation = gameController.getTravelUI().getUniverse()
                        .getMapRegionArray().get(firstInt);
                gameController.getPlayer().setCurrentLocation(startingLocation);
                gameController.setConfirmTravelUI(new ConfirmTravelUI("default", gameController,
                        startingLocation));
                gameController.setMarketUI(new MarketUI(gameController, startingLocation));
                gameController.showLocationUI(startingLocation);
                new PlayerStatusUI(gameController);
            }
        });

    }

    public void setVisible(boolean b) {
        configurationDisplayScreenFrame.setVisible(b);
    }

}