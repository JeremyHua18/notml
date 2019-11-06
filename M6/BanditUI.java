import javax.swing.*;

import java.awt.*;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.event.*;
public class BanditUI {
    private GameController gameController;
    private MapRegion previousRegion;
    private MapRegion desiredRegion;
    private JFrame banditFrame;
    private JPanel banditContainer;
    private JButton payButton;
    private JButton fleeButton;
    private JButton fightButton;
    private ImageIcon bandit;
    private JLabel banditLabel;

    public BanditUI(GameController gameController, MapRegion previousRegion,
                    MapRegion desiredRegion) {
        this.gameController = gameController;
        this.previousRegion = previousRegion;
        this.desiredRegion = desiredRegion;
        configure();

    }


    public void configure() {
        banditFrame = new JFrame("Bandit Encountered!");
        payButton = new JButton("Pay Bandit");
        fleeButton = new JButton("Flee Bandit");
        fightButton = new JButton("Fight Bandit");
        try {
            bandit = new ImageIcon(getClass().getResource("bandit.png"));
            banditLabel = new JLabel(bandit);
        } catch (NullPointerException ex) {
            System.out.println("Planet image failed to load.");
        }
        banditContainer = new JPanel(new GridLayout(2, 2));
        banditFrame.setSize(400, 400);
        banditContainer.add(payButton);
        banditContainer.add(fleeButton);
        banditContainer.add(fightButton);
        banditContainer.add(banditLabel);
        banditFrame.add(banditContainer);
        banditFrame.setVisible(true);
        payButton.addActionListener(e -> {
            if (gameController.getPlayer().getCredit() >= 200) {
                gameController.getPlayer().setCredit(gameController.getPlayer().getCredit() - 200);
                JOptionPane.showMessageDialog(banditFrame, "You paid 200 credits"
                                + " to escape!",
                        "Robbed", JOptionPane.INFORMATION_MESSAGE);
            } else if (gameController.getPlayer().getShip().getCargoSpace() != gameController
                    .getPlayer().getShip().getCargoSpaceRemaining()) {
                gameController.getPlayer().getShip()
                        .setCargoList(new ArrayList<>(Collections.nCopies(Item.values()
                                .length, 0)));
                gameController.getPlayer().getShip()
                        .setCargoSpaceRemaining(gameController.getPlayer()
                                .getShip().getCargoSpace());
                JOptionPane.showMessageDialog(banditFrame, "Your inventory was "
                                + "stolen, at least you have extra room now!",
                        "Robbed", JOptionPane.INFORMATION_MESSAGE);
            } else {
                if (gameController.getPlayer().getShip().getHealthRemaining() >= 5) {
                    gameController.getPlayer().getShip().setHealthRemaining(gameController
                            .getPlayer().getShip().getHealthRemaining() - 5);
                    JOptionPane.showMessageDialog(banditFrame, "You took 5 damage"
                                    + " before escaping to " + desiredRegion.getRegionName()
                                    + "!",
                            "Robbed", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    gameController.getPlayer().getShip().setHealthRemaining(0);
                    JOptionPane.showMessageDialog(banditFrame, "Your ship has been "
                                    + "destroyed! Enjoy the cold vacuum of space.",
                            "Robbed", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            banditFrame.setVisible(false);
            gameController.getPlayer().setCurrentLocation(desiredRegion);
            gameController.getLocationUI().configure(desiredRegion);
            gameController.showLocationUI();
        });
        fleeButton.addActionListener(e -> {
            if (Math.random() * gameController.getPlayer().getPilotSkill() < 7) {
                gameController.getPlayer().setCredit(0);
                gameController.getPlayer().getShip().setHealthRemaining(gameController.getPlayer()
                        .getShip().getHealthRemaining() - 5);
                JOptionPane.showMessageDialog(banditFrame, "You got away, but"
                                + " lost 5 health in the process.",
                        "Robbed", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(banditFrame, "Got away safely!",
                        "Robbed", JOptionPane.INFORMATION_MESSAGE);
            }
            banditFrame.setVisible(false);
            gameController.getPlayer().setCurrentLocation(previousRegion);
            gameController.getLocationUI().configure(previousRegion);
            gameController.showLocationUI();
        });
        fightButton.addActionListener(e -> {
            if (Math.random() * gameController.getPlayer().getFighterSkill() > 7) {
                gameController.getPlayer().setCredit(gameController.getPlayer().getCredit() + 100);
                JOptionPane.showMessageDialog(banditFrame, "You fought and won- you"
                                + " gained 100 credits!",
                        "Robbed", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(banditFrame, "You lost the fight, and lost"
                                + " 5 health in addition to all of your credits.",
                        "Robbed", JOptionPane.INFORMATION_MESSAGE);
                gameController.getPlayer().setCredit(0);
                gameController.getPlayer().getShip().setHealthRemaining(gameController.getPlayer()
                        .getShip().getHealthRemaining() - 5);
            }
            banditFrame.setVisible(false);
            gameController.getPlayer().setCurrentLocation(desiredRegion);
            gameController.getLocationUI().configure(desiredRegion);
            gameController.showLocationUI();
        });


    }

}
