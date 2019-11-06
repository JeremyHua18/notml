import javax.swing.*;

import java.awt.*;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.event.*;
public class PoliceUI {
    private GameController gameController;
    private MapRegion previousRegion;
    private MapRegion desiredRegion;
    private JFrame policeFrame;
    private JPanel policeContainer;
    private JButton forfeitButton;
    private JButton fleeButton;
    private JButton fightButton;

    public PoliceUI(GameController gameController, MapRegion previousRegion,
                    MapRegion desiredRegion) {
        this.gameController = gameController;
        this.previousRegion = previousRegion;
        this.desiredRegion = desiredRegion;
        configure();
    }


    public void configure() {
        policeFrame = new JFrame("Police Encountered!");
        forfeitButton = new JButton("Forfeit Police");
        fleeButton = new JButton("Flee Police");
        fightButton = new JButton("Fight Police");
        policeContainer = new JPanel(new GridLayout(2, 2));
        policeFrame.setSize(400, 400);
        policeFrame.add(forfeitButton);
        policeFrame.add(fleeButton);
        policeFrame.add(fightButton);
        policeFrame.setVisible(true);
        forfeitButton.addActionListener(e -> {
            gameController.getPlayer().getShip()
                    .setCargoList(new ArrayList<>(Collections.nCopies(Item.values().length, 0)));
            gameController.getPlayer().getShip()
                    .setCargoSpaceRemaining(gameController.getPlayer().getShip().getCargoSpace());
            policeFrame.setVisible(false);
            gameController.getPlayer().setCurrentLocation(desiredRegion);
            gameController.getLocationUI().configure(desiredRegion);
            gameController.showLocationUI();
        });
        fleeButton.addActionListener(e -> {
            if (Math.random() * gameController.getPlayer().getPilotSkill() < 7) {
                gameController.getPlayer().setCredit(gameController.getPlayer().getCredit() - 30);
                gameController.getPlayer().getShip().setHealthRemaining(gameController
                        .getPlayer().getShip().getHealthRemaining() - 5);
                gameController.getPlayer().getShip()
                        .setCargoList(new ArrayList<>(Collections.nCopies(Item.values()
                                .length, 0)));
                gameController.getPlayer().getShip().setCargoSpaceRemaining(gameController
                        .getPlayer().getShip().getCargoSpace());
            }
            policeFrame.setVisible(false);
            gameController.getPlayer().setCurrentLocation(previousRegion);
            gameController.getLocationUI().configure(previousRegion);
            gameController.showLocationUI();
        });
        fightButton.addActionListener(e -> {
            if (Math.random() * gameController.getPlayer().getFighterSkill() < 7) {
                gameController.getPlayer().setCredit(gameController.getPlayer().getCredit() - 30);
                gameController.getPlayer().getShip().setHealthRemaining(gameController
                        .getPlayer().getShip().getHealthRemaining() - 5);
                gameController.getPlayer().getShip()
                        .setCargoList(new ArrayList<>(Collections.nCopies(Item.values()
                                .length, 0)));
                gameController.getPlayer().getShip().setCargoSpaceRemaining(gameController
                        .getPlayer().getShip().getCargoSpace());
            }
            policeFrame.setVisible(false);
            gameController.getPlayer().setCurrentLocation(desiredRegion);
            gameController.getLocationUI().configure(desiredRegion);
            gameController.showLocationUI();
        });


    }

}
