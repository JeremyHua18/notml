import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TravelUI {
    private JFrame universeFrame;
    private GameController gameController;
    private Map universe;
    private Player player;
    private ImageIcon starBG;
    private ImageIcon planet;
    private JLabel starBGdisp;
    private JLabel planetDisp;
    private JLabel planetName;

    public TravelUI(GameController gameController) {
        this.universe = new Map();
        this.gameController = gameController;
        configure();
    }

    public void setVisible(boolean b) {
        universeFrame.setVisible(b);
    }

    public void configure() {
        starBG = new ImageIcon(getClass().getResource("starBG.gif"));
        starBGdisp = new JLabel(starBG);
        starBGdisp.setSize(starBG.getIconWidth(), starBG.getIconHeight());
        starBGdisp.setBounds(0, 350, 500, 567);

        universeFrame = new JFrame("The map of the Universe");
        universeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        universeFrame.setSize(510, 950);
        universeFrame.setLayout(null);
        //universeFrame.add(starBGdisp);
        JButton locationButton;
        JLabel locationLabel;
        JLabel techLevelLabel;
        JLabel planetDisp;
        JLabel planetName;
        ImageIcon planet;

        int count = 0;

        for (MapRegion location : universe.getMapRegionArray()) {
            int count1 = count;
            int x = universe.getMapRegionArray().get(count1).getxCoordinate();
            int y = universe.getMapRegionArray().get(count1).getyCoordinate();
            locationLabel = new JLabel("(" + x + ", " + y + ")");
            locationLabel.setBounds(20, 20 + count * 30, 100, 30);
            techLevelLabel =
                    new JLabel("Tech Level: "
                            + universe.getMapRegionArray().get(count1).getTechLevel());
            techLevelLabel.setBounds(330, 20 + count * 30, 200, 30);
            locationButton = new JButton("Travel to " + location.getRegionName());
            locationButton.setBounds(130, 20 + count * 30, 200, 30);
            try {
                planet = new ImageIcon(getClass().getResource(location.getRegionName() + ".png"));
                planetDisp = new JLabel(planet);
                planetDisp.setBounds(x + 200 - planet.getIconWidth() / 2,
                        y + 500 + planet.getIconHeight() / 2, 150, 150);
                universeFrame.add(planetDisp);
            } catch (NullPointerException ex) {
                System.out.println("Planet image failed to load.");
            }
            universeFrame.add(locationButton);
            universeFrame.add(locationLabel);
            universeFrame.add(techLevelLabel);
            locationButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gameController.showLocationUI(universe.getMapRegionArray().get(count1));
                }
            });
            count++;
        }
        universeFrame.setLocationRelativeTo(null);
    }
}
