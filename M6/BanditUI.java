import javax.swing.*;

import java.awt.*;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.event.*;
public class BanditUI {
    private GameController gameController;
    private MapRegion previousRegion, desiredRegion;
    JFrame banditFrame;
    JButton payButton, fleeButton, fightButton;

    public BanditUI(GameController gameController, MapRegion previousRegion, MapRegion desiredRegion) {
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
        banditFrame.setSize(400,400);
        payButton.setBounds(75, 180, 200, 30);
        fleeButton.setBounds(75, 280, 200, 30);
        fightButton.setBounds(75,380,200,30);
        banditFrame.add(payButton);
        banditFrame.add(fleeButton);
        banditFrame.add(fightButton);
        banditFrame.setVisible(true);
        payButton.addActionListener(e -> {
            if (gameController.getPlayer().getCredit() >= 200) {
                gameController.getPlayer().setCredit(gameController.getPlayer().getCredit() - 200);
            } else if (gameController.getPlayer().getShip().getCargoSpace() != gameController.getPlayer().getShip().getCargoSpaceRemaining() ){
                gameController.getPlayer().getShip().setCargoList(new ArrayList<>(Collections.nCopies(Item.values().length, 0)));
                gameController.getPlayer().getShip().setCargoSpaceRemaining(gameController.getPlayer().getShip().getCargoSpace());
            } else {
                if (gameController.getPlayer().getShip().getHealthRemaining() >= 5) {
                    gameController.getPlayer().getShip().setHealthRemaining(gameController.getPlayer().getShip().getHealthRemaining() - 5);
                } else {
                    gameController.getPlayer().getShip().setHealthRemaining(0);
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
                gameController.getPlayer().getShip().setHealthRemaining(gameController.getPlayer().getShip().getHealthRemaining() - 5);
            }
            banditFrame.setVisible(false);
            gameController.getPlayer().setCurrentLocation(previousRegion);
            gameController.getLocationUI().configure(previousRegion);
            gameController.showLocationUI();
        });
        fightButton.addActionListener(e -> {
            if (Math.random() * gameController.getPlayer().getFighterSkill() > 7) {
                gameController.getPlayer().setCredit(gameController.getPlayer().getCredit() + 50);
            } else {
                gameController.getPlayer().setCredit(0);
                gameController.getPlayer().getShip().setHealthRemaining(gameController.getPlayer().getShip().getHealthRemaining() - 5);
            }
            banditFrame.setVisible(false);
            gameController.getPlayer().setCurrentLocation(desiredRegion);
            gameController.getLocationUI().configure(desiredRegion);
            gameController.showLocationUI();
        });


    }

}
