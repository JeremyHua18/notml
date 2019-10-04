import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TravelUI {
    private JFrame universeFrame;
    private GameController gameController;
    private Map universe;
    private Player player;

    public TravelUI(GameController gameController) {
        this.universe = new Map();
        this.gameController = gameController;
        configure();
    }

    public void setVisible(boolean b) {
        universeFrame.setVisible(b);
    }
    public void configure() {
        universeFrame = new JFrame("The map of the Universe");
        universeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        universeFrame.setSize(800, 800);
        universeFrame.setLayout(null);
        JButton locationButton;
        JLabel locationLabel;
        int count = 0;

        for (MapRegion location : universe.getMapRegionArray()) {
            int count1 = count;
            locationLabel = new JLabel("(" + universe.getMapRegionArray().get(count1).getxCoordinate() + ", " + universe.getMapRegionArray().get(count1).getyCoordinate() + ")");
            locationLabel.setBounds(50, 20 + count * 30, 100,30);
            locationButton = new JButton(location.getRegionName() + " Enter Here");
            locationButton.setBounds(150,20 + count * 30,200,30);
            universeFrame.add(locationButton);
            universeFrame.add(locationLabel);
            locationButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gameController.showLocationUI(universe.getMapRegionArray().get(count1));
                }
            });
            count++;
        }

        /*sunRegionButton = new JButton("Sun Enter here");
        sunRegionButton.setBounds(150,20,200,30);

        mercuryRegionButton = new JButton("Mercury Enter here");
        mercuryRegionButton.setBounds(150,70,200,30);

        venusRegionButton = new JButton("Venus Enter here");
        universeFrame.add(sunRegionButton);
        universeFrame.add(mercuryRegionButton);
        universeFrame.add(venusRegionButton);
        universeFrame.add(earthRegionButton);
        universeFrame.add(marsRegionButton);
        universeFrame.add(jupiterRegionButton);
        universeFrame.add(saturnRegionButton);
        universeFrame.add(uranusRegionButton);
        universeFrame.add(neptuneRegionButton);
        universeFrame.add(plutoRegionButton);



        sunRegionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameController.showLocationUI(new MapRegion("sun"));
            }
        });

        mercuryRegionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameController.showLocationUI(universe.getMapRegionArray().get(0));
            }
        });

        venusRegionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameController.showLocationUI(universe.getMapRegionArray().get(1));
            }
        });

        earthRegionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameController.showLocationUI(universe.getMapRegionArray().get(2));
            }
        });

        marsRegionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameController.showLocationUI(universe.getMapRegionArray().get(3));
            }
        });

        jupiterRegionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameController.showLocationUI(universe.getMapRegionArray().get(4));
            }
        });

        saturnRegionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameController.showLocationUI(universe.getMapRegionArray().get(5));
            }
        });

        uranusRegionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameController.showLocationUI(universe.getMapRegionArray().get(6));
            }
        });

        neptuneRegionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameController.showLocationUI(universe.getMapRegionArray().get(7));
            }
        });

        saturnRegionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameController.showLocationUI(universe.getMapRegionArray().get(5));
            }
        });*/




    }
}
