import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

class LocationUI {
    private JFrame locationFrame;
    private MapRegion location;
    private JButton goToUniverseButton, goToMarketButton;
    private GameController gameController;
    private JLabel name, coor, techLevelLabel, welcomeLabel;

    public LocationUI(GameController gameController, MapRegion location) {
        this.location = location;
        this.gameController = gameController;
        congifure();
    }
    public void setVisible(boolean b) {
        locationFrame.setVisible(b);
    }

    public void congifure() {
        locationFrame = new JFrame(location.getRegionName());
        locationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        locationFrame.setSize(400, 400);
        locationFrame.setLayout(null);
        if (gameController.getPlayer().isJustStarted()) {
            welcomeLabel = new JLabel("<html>Welcome to the universe,<br/>your journey" +
                    " begins in the " + location.getRegionName() + " region.</html>");
            welcomeLabel.setBounds(75, 50, 400, 60);
            locationFrame.add(welcomeLabel);
        }
        name = new JLabel("<html>You're currently in the<br/>" + location.getRegionName() + " region.");
        name.setBounds(75, 140, 200, 30);

        coor = new JLabel("Coordinates: (" + location.getxCoordinate() + "," + location.getyCoordinate() + ")");
        coor.setBounds(75, 163, 200, 30);

        techLevelLabel = new JLabel("Tech Level: " + String.valueOf(location.getTechLevel()));
        techLevelLabel.setBounds(75, 180, 200, 30);

        goToUniverseButton = new JButton("Travel to a new region");
        //goToUniverseButton.setSize(100, 30);
        goToUniverseButton.setBounds(75, 250, 200, 30);

        goToMarketButton = new JButton("Enter " + location.getRegionName() + "'s market");
        //goToMarketButton.setSize(100, 30);
        goToMarketButton.setBounds(75, 285, 200, 30);


        locationFrame.add(name);
        locationFrame.add(coor);
        locationFrame.add(goToUniverseButton);
        locationFrame.add(goToMarketButton);
        locationFrame.add(techLevelLabel);


        goToUniverseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameController.getPlayer().setJustStarted(false);
                gameController.showTravelUI();
            }
        });


        locationFrame.setLocationRelativeTo(null);
    }

}
