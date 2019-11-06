import javax.swing.*;
import java.awt.*;

public class TraderUI {
    private GameController gameController;
    private MapRegion previousRegion;
    private MapRegion desiredRegion;
    private JFrame traderFrame;
    private JButton buyButton;
    private JButton ignoreButton;
    private JButton robButton;
    private JButton negotiateButton;
    private JPanel traderContainer;
    private Item availableItem;
    private JLabel currTotalDisp;
    private int availableQuant;
    private int currNegotiatedPrice;

    public TraderUI(GameController gameController, MapRegion previousRegion,
                    MapRegion desiredRegion) {
        this.gameController = gameController;
        this.previousRegion = previousRegion;
        this.desiredRegion = desiredRegion;
        configure();
    }


    public void configure() {
        availableItem = Item.values()[(int) (Math.random() * 10)];
        availableQuant = (int) ((Math.random() * 10) / 3);
        if (availableQuant <= 0) {
            availableQuant = 1;
        }
        currNegotiatedPrice = (int) (availableItem.getBasePrice() * availableQuant * 0.9);
        currTotalDisp = new JLabel("Trader offers " + availableQuant + " "
                + availableItem.getName() + " for " + currNegotiatedPrice + " total.");
        traderFrame = new JFrame("Trader Encountered!");
        buyButton = new JButton("Buy Trader");
        ignoreButton = new JButton("Ignore Trader");
        robButton = new JButton("Rob Trader");
        negotiateButton = new JButton("Negotiate Trader");
        traderContainer = new JPanel(new GridLayout(4, 2));
        traderFrame.setSize(400, 400);
        traderContainer.add(buyButton);
        traderContainer.add(ignoreButton);
        traderContainer.add(robButton);
        traderContainer.add(negotiateButton);
        traderContainer.add(currTotalDisp);
        traderFrame.setVisible(true);
        buyButton.addActionListener(e -> {
            if (gameController.getPlayer().getShip().getCargoSpaceRemaining() >= availableQuant) {
                if (gameController.getPlayer().getCredit() >= currNegotiatedPrice) {
                    //add the item to the ship inventory
                    gameController.getPlayer().setCredit(gameController.getPlayer()
                            .getCredit() - currNegotiatedPrice);
                    gameController.getPlayer().getShip().addCargo(availableItem, availableQuant);
                    JOptionPane.showConfirmDialog(traderFrame, "Successfully purchased "
                                    + availableItem.getName() + " for "
                                    + currNegotiatedPrice + "!",
                            "Purchase Confirmed", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(traderFrame, "You don't have enough "
                                    + "credits!",
                            "Not Enough Credits", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(traderFrame, "You don't have enough "
                        + "cargo space!", "Not enough space", JOptionPane.ERROR_MESSAGE);
            }
            traderFrame.setVisible(false);
            gameController.getPlayer().setCurrentLocation(desiredRegion);
            gameController.getLocationUI().configure(desiredRegion);
            gameController.showLocationUI();
        });
        ignoreButton.addActionListener(e -> {
            JOptionPane.showConfirmDialog(traderFrame, "Trader ignored, you have "
                            + "proceeded to " + desiredRegion.getRegionName() + "."
                    , "Trader Ignored", JOptionPane.INFORMATION_MESSAGE);
            traderFrame.setVisible(false);
            gameController.getPlayer().setCurrentLocation(desiredRegion);
            gameController.getLocationUI().configure(desiredRegion);
            gameController.showLocationUI();
        });
        robButton.addActionListener(e -> {
            if (Math.random() * gameController.getPlayer().getFighterSkill() > 5) {
                if (gameController.getPlayer().getShip()
                        .getCargoSpaceRemaining() >= availableQuant) {
                    JOptionPane.showConfirmDialog(traderFrame, "Successfully robbed "
                                    + "trader of their "
                                    + availableItem.getName() + "!",
                            "Robbery Confirmed", JOptionPane.INFORMATION_MESSAGE);
                    //add the item to the ship inventory
                    gameController.getPlayer().getShip().addCargo(availableItem, availableQuant);
                } else {
                    int partialTotal = gameController.getPlayer().getShip()
                            .getCargoSpaceRemaining();
                    JOptionPane.showConfirmDialog(traderFrame, "Successfully robbed "
                                    + "trader of " + partialTotal + " of their "
                                    + availableItem.getName() + "!",
                            "Robbery Confirmed", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showConfirmDialog(traderFrame, "Failed to rob "
                                + "trader of their "
                                + availableItem.getName() + "! You got beat up and lost 5 health!",
                        "Robbery Failed", JOptionPane.INFORMATION_MESSAGE);
                gameController.getPlayer().getShip().setHealthRemaining(gameController
                        .getPlayer().getShip().getHealthRemaining() - 5);
            }
            traderFrame.setVisible(false);
            gameController.getPlayer().setCurrentLocation(desiredRegion);
            gameController.getLocationUI().configure(desiredRegion);
            gameController.showLocationUI();
        });
        negotiateButton.addActionListener(e -> {
            if (Math.random() * gameController.getPlayer().getMerchantSkill() > 5) {
                //price decreases by 35% upon success
                currNegotiatedPrice = (int) (currNegotiatedPrice * 0.65);
                updateCurrTotalDisp();
                //      if (gameController.getPlayer().getShip().getCargoSpaceRemaining() >= 1) {
                //     if (gameController.getPlayer().getCredit() > 0.5 * currNegotiatedPrice) {
                //                   //add the item to the ship inventory
                //                 gameController.getPlayer().setCredit(gameController.getPlayer()
                //                                .getCredit() - (int) (0.5 * currNegotiatedPrice));
                //    gameController.getPlayer().getShip().addCargo(availableItem, availableQuant);
                //                   }
                //                }
            } else {
                //price increases by 50% if failure
                currNegotiatedPrice = (int) (currNegotiatedPrice * 1.5);
                updateCurrTotalDisp();

            }
            //disable button regardless of success
            negotiateButton.setEnabled(false);
            //traderFrame.setVisible(false);
            //gameController.getPlayer().setCurrentLocation(desiredRegion);
            //gameController.getLocationUI().configure(desiredRegion);
            //gameController.showLocationUI();
        });


    }

    public void updateCurrTotalDisp() {
        currTotalDisp.setText("Trader offers " + availableQuant + " "
                + availableItem.getName() + " for " + currNegotiatedPrice + " total.");
    }
}
