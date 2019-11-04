import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.event.*;
public class BanditUI extends JFrame{
    private JFrame BanditFrame;
    private JPanel banditPanel;
    private double policePossibility, traderPossibility;
    private JLabel payOption, fleeOption, fightOption;
    private JButton payButton, fleeButton, fightButton;
    private MapRegion destination, previousRegion;
    private GameController gameController;


    public BanditUI(GameController gameController) {
        this.gameController = gameController;
        configure();
    }

    public void setVisible(boolean b) {
        BanditFrame.setVisible(b);
    }

    public void setDestination(MapRegion destination) {
        this.destination = destination;
    }

    public void setPreviousRegion(MapRegion previousRegion) {
        this.previousRegion = previousRegion;
    }


    public void configure() {
        BanditFrame = new JFrame("Bandit Encountered!");
        banditPanel = new JPanel();
        BanditFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BanditFrame.setSize(400, 400);
        BanditFrame.setLocationRelativeTo(null);
        payOption = new JLabel("Pay the bandit!");
        fleeOption = new JLabel("Run away!");
        fightOption = new JLabel("Fight the bandit!");
        payButton = new JButton("Pay the bandit!");
        fleeButton = new JButton("Run away!");
        fightButton = new JButton("Fight the bandit!");

        payButton.setBounds(0, 400, 100, 30);
        fleeButton.setBounds(60, 400, 100, 30);
        fightButton.setBounds(140, 400, 100, 30);

        payButton.addActionListener(e -> {
            if (gameController.getPlayer().getCredit() >= 100) {
                gameController.getPlayer().setCredit(gameController.getPlayer().getCredit() - 100);
            } else if (!gameController.getPlayer().getShip().getCargo().isEmpty()) {
                gameController.getPlayer().getShip().setCargo(new ArrayList<>(Collections.nCopies(Item.values().length, 0)));
            } else {
                gameController.getPlayer().getShip().setHealthRemaining(gameController.getPlayer().getShip().getHealthRemaining() - 5);
            }
            gameController.getPlayer().setCurrentLocation(destination);
            gameController.showLocationUI();
        });

        fleeButton.addActionListener(e -> {
            double fleeSuccessRate = 20 * Math.random();
            if (gameController.getPlayer().getPilotSkill() > fleeSuccessRate) {
                int fuelCost = gameController.getPlayer().calcTravelCosts(destination);
                gameController.getPlayer().getCurrentShip().burnFuel(fuelCost);
                gameController.showLocationUI(previousRegion);
            } else {
                // The bandit takes all the credits and cause 5 unit of damage
                gameController.getPlayer().setCredit(0);
                gameController.getPlayer().getShip().setHealthRemaining(gameController.getPlayer().getShip().getHealthRemaining() - 5);
            }
        });

        fightButton.addActionListener(e -> {
            double fightSuccessRate = 20 * Math.random();
            int winnedCredit = (int)(20 * Math.random());
            if (gameController.getPlayer().getFighterSkill() > fightSuccessRate) {
                gameController.getPlayer().setCredit(gameController.getPlayer().getCredit() + winnedCredit);
                int fuelCost = gameController.getPlayer().calcTravelCosts(destination);
                gameController.getPlayer().getCurrentShip().burnFuel(fuelCost);
                gameController.showLocationUI(destination);
            } else {
                // The bandit takes all the credits and cause 5 unit of damage
                gameController.getPlayer().setCredit(0);
                gameController.getPlayer().getShip().setHealthRemaining(gameController.getPlayer().getShip().getHealthRemaining() - 5);
            }
        });

        banditPanel.add(payButton);
        banditPanel.add(fightButton);
        banditPanel.add(fleeButton);
        BanditFrame.add(banditPanel, BorderLayout.CENTER);



    }




}
