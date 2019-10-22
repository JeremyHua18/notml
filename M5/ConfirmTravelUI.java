import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfirmTravelUI extends JFrame {
    private JLabel infoLabel;
    private JLabel questionLabel;
    //optional warning label
    private JLabel warningLabel;
    private JButton confirmTravel;
    private JButton returnToTravelUI;
    private GridBagConstraints constraints = new GridBagConstraints();
    private GameController gc;

    /*
    ____________________
    |   "it will cost   |
    |    [       ]      |  JLabel inside of first row of big gridLayout
    |  to travel here"  |
    |___________________|
    |  "are you sure    |
    |   you wish to     |  JLabel inside of second row of big gridLayout
    |   travel here?"   |
    |___________________|
    |    Optional       |
    |   warning box     |  JLabel potentially inside of 3rd row of big GL
    |___________________|
    |         |         |
    |   yes   |   no    |  JPanel with gridlayout(1, 2) in 3rd or 4th row
    |_________|_________|
     */

    public ConfirmTravelUI(String name, GameController gc, MapRegion desiredPlanet) {
        super(name);
        this.gc = gc;
        setLayout(new GridBagLayout());
        configure(desiredPlanet);
    }

    private void configure(MapRegion desiredPlanet) {
        int fuelCost = gc.getPlayer().calcTravelCosts(desiredPlanet);
        JPanel mainPanel = new JPanel();
        //only need 3 rows if no warning, 4 otherwise
        int rowsNeeded = 3;
        boolean addWarning = false;
        this.infoLabel = new JLabel("<html>It will cost "
                + gc.getPlayer().calcTravelCosts(desiredPlanet) + " fuel to travel to "
                + "</br>" + desiredPlanet.toString() + ".</html>");
        this.questionLabel = new JLabel("Do you wish to confirm travel?");
        if (fuelCost > gc.getPlayer().getCurrentShip().getFuelCapacityRemaining()) {
            rowsNeeded++;
            warningLabel = new JLabel("WARNING: You do not have enough fuel"
                    + " to travel here.");
            warningLabel.setForeground(Color.RED);
            addWarning = true;
        }
        GridBagLayout uiManager = new GridBagLayout();
        mainPanel.setLayout(uiManager);
        constraints.fill = GridBagConstraints.HORIZONTAL;

        constraints.weightx = 0.5;
        constraints.ipady = 35;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        infoLabel.setHorizontalAlignment(JLabel.CENTER);
        mainPanel.add(infoLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        questionLabel.setHorizontalAlignment(JLabel.CENTER);
        mainPanel.add(questionLabel, constraints);

        if (addWarning) {
            constraints.gridx = 0;
            constraints.gridy = 2;
            warningLabel.setHorizontalAlignment(JLabel.CENTER);
            mainPanel.add(warningLabel, constraints);
        }

        constraints.gridwidth = 1;
        constraints.gridx = 0;
        constraints.gridy = rowsNeeded - 1;
        constraints.insets = new Insets(0, 0, 20, 20);
        this.confirmTravel = new JButton("yes");
        mainPanel.add(confirmTravel, constraints);

        constraints.gridx = 1;
        constraints.weightx = 0.5;
        constraints.gridy = rowsNeeded - 1;
        returnToTravelUI = new JButton("no");
        mainPanel.add(returnToTravelUI, constraints);

        if(addWarning) {
            confirmTravel.setEnabled(false);
        }

        confirmTravel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                gc.getPlayer().getCurrentShip().burnFuel(fuelCost);
                gc.getPlayer().setHasTraveled(true);
                gc.getPlayer().setCurrentLocation(desiredPlanet);
                gc.getLocationUI().congifure(desiredPlanet);
                gc.showLocationUI();
            }
        });

        returnToTravelUI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                gc.getPlayer().setHasTraveled(true);
                gc.showTravelUI();
            }
        });

        this.setLocationRelativeTo(null);

        this.setSize(400, 400);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.setVisible(true);

        this.add(mainPanel);
    }

}
