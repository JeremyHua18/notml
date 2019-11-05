import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;

public class TraderUI {
    private GameController gameController;
    private MapRegion previousRegion, desiredRegion;
    JFrame traderFrame;
    JButton buyButton, ignoreButton, robButton, negotiateButton;

    public TraderUI(GameController gameController, MapRegion previousRegion, MapRegion desiredRegion) {
        this.gameController = gameController;
        this.previousRegion = previousRegion;
        this.desiredRegion = desiredRegion;
        configure();
    }


    public void configure() {
        traderFrame = new JFrame("Trader Encountered!");
        buyButton = new JButton("Buy Trader");
        ignoreButton = new JButton("Ignore Trader");
        robButton = new JButton("Rob Trader");
        negotiateButton = new JButton("Negotiate Trader");
        traderFrame.setSize(400,400);
        buyButton.setBounds(75, 180, 200, 30);
        ignoreButton.setBounds(75, 280, 200, 30);
        robButton.setBounds(75,380,200,30);
        negotiateButton.setBounds(75,480,200,30);
        traderFrame.add(buyButton);
        traderFrame.add(ignoreButton);
        traderFrame.add(robButton);
        traderFrame.add(negotiateButton);
        traderFrame.setVisible(true);
        buyButton.addActionListener(e -> {
            if (gameController.getPlayer().getShip().getCargoSpaceRemaining() > 1) {
                if (gameController.getPlayer().getCredit() >= 50) {
                    //add the item to the ship inventory
                    gameController.getPlayer().setCredit(gameController.getPlayer().getCredit() - 50);
                    gameController.getPlayer().getShip().setCargoSpaceRemaining(gameController.getPlayer().getShip().getCargoSpaceRemaining() - 1);
                }

            }
            traderFrame.setVisible(false);
            gameController.getPlayer().setCurrentLocation(desiredRegion);
            gameController.getLocationUI().configure(desiredRegion);
            gameController.showLocationUI();
        });
        ignoreButton.addActionListener(e -> {
            traderFrame.setVisible(false);
            gameController.getPlayer().setCurrentLocation(desiredRegion);
            gameController.getLocationUI().configure(desiredRegion);
            gameController.showLocationUI();
        });
        robButton.addActionListener(e -> {
            if (Math.random() * gameController.getPlayer().getFighterSkill() > 5) {
                if (gameController.getPlayer().getShip().getCargoSpaceRemaining() > 1) {
                    //add the item to the ship inventory
                    gameController.getPlayer().getShip().setCargoSpaceRemaining(gameController.getPlayer().getShip().getCargoSpaceRemaining() - 1);
                }
            } else {
                gameController.getPlayer().getShip().setHealthRemaining(gameController.getPlayer().getShip().getHealthRemaining() - 5);
            }
            traderFrame.setVisible(false);
            gameController.getPlayer().setCurrentLocation(desiredRegion);
            gameController.getLocationUI().configure(desiredRegion);
            gameController.showLocationUI();
        });
        negotiateButton.addActionListener(e -> {
            if (Math.random() * gameController.getPlayer().getMerchantSkill() > 5) {
                if (gameController.getPlayer().getShip().getCargoSpaceRemaining() > 1) {
                    if (gameController.getPlayer().getCredit() > 0.5 * 50) {
                        //add the item to the ship inventory
                        gameController.getPlayer().setCredit(gameController.getPlayer().getCredit() - 50);
                        gameController.getPlayer().getShip().setCargoSpaceRemaining(gameController.getPlayer().getShip().getCargoSpaceRemaining() - 1);
                    }
                }
            }
            traderFrame.setVisible(false);
            gameController.getPlayer().setCurrentLocation(desiredRegion);
            gameController.getLocationUI().configure(desiredRegion);
            gameController.showLocationUI();
        });


    }
}
