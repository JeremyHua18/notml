import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LocationUI {
    private JFrame locationFrame;
    private MapRegion location;
    private JButton goToUniverseButton;
    private GameController gameController;
    private JLabel name, coor, techLevelLabel;

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

        name = new JLabel(location.getRegionName());
        name.setBounds(150, 140, 200, 30);

        coor = new JLabel("(" + location.getxCoordinate() + "," + location.getyCoordinate() + ")");
        coor.setBounds(150, 160, 200, 30);

        techLevelLabel = new JLabel("Tech Level: " + String.valueOf(location.getTechLevel()));
        techLevelLabel.setBounds(150, 180, 200, 30);

        goToUniverseButton = new JButton("Go back to map");
        goToUniverseButton.setSize(100, 30);
        goToUniverseButton.setBounds(75, 250, 200, 30);


        locationFrame.add(name);
        locationFrame.add(coor);
        locationFrame.add(goToUniverseButton);
        locationFrame.add(techLevelLabel);


        goToUniverseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameController.showTravelUI();
            }
        });




    }

}
