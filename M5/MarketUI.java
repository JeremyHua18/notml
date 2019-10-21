import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MarketUI {
    private JFrame marketFrame;
    private GameController gameController;
    private Player player;
    private MapRegion planet;
    private Market market;
    private List<MapRegion> solarSystem;
    private JPanel marketPanel, buyPanel, sellPanel, statusPanel;
    private JLabel buyPrice, buyQuantity, buyBalance, buyAfterBalance;
    private JLabel sellPrice, sellQuantity, sellBalance, sellAfterBalance;
    private JLabel cargo, error;



    private int credits, space, totalBill, totalGain, spaceNeeded;
    private JLabel creditsAvailable, spaceAvailable;
    private JButton confirmSellButton, confirmBuyButton, exitMarketButton;
    private JLabel tradeItem1, tradeItem2, tradeItem3, tradeItem4, tradeItem5, tradeItem6, tradeItem7, tradeItem8,
            tradeItem9, tradeItem10;
    private JLabel priceBuy1, priceBuy2, priceBuy3, priceBuy4, priceBuy5, priceBuy6, priceBuy7, priceBuy8, priceBuy9,
            priceBuy10, priceSell1, priceSell2, priceSell3, priceSell4, priceSell5, priceSell6, priceSell7, priceSell8,
            priceSell9, priceSell10;
    private JLabel inventoryItem1, inventoryItem2, inventoryItem3, inventoryItem4, inventoryItem5, inventoryItem6,
            inventoryItem7, inventoryItem8, inventoryItem9, inventoryItem10;
    private JTextField tfNumBuy1, tfNumBuy2, tfNumBuy3, tfNumBuy4, tfNumBuy5, tfNumBuy6, tfNumBuy7, tfNumBuy8,
            tfNumBuy9, tfNumBuy10, tfNumSell1, tfNumSell2, tfNumSell3, tfNumSell4, tfNumSell5, tfNumSell6, tfNumSell7,
            tfNumSell8, tfNumSell9, tfNumSell10;
    private JLabel potentialTotalGain, potentialTotalBill, potentialSpaceNeeded;

    private JLabel itemNameLabel, itemPriceLabel;
    //private JTextField tfItemAmount;

    /**
     * JTextField Array from index 0 to 9 or item index 0 to 9
     */
    JTextField[] tfNumBuy = new JTextField[10];
    JTextField[] tfNumSell = new JTextField[10];
    /**
     * every entry in totalPriceBuy and Sell is the amount multiply price for a single item
     * pricePerUnit: price per unit for each item
     */
    int[] totalPriceBuy = new int[10];
    int[] priceBuyPerUnit = new int[10];
    int[] totalPriceSell = new int[10];
    int[] priceSellPerUnit = new int[10];



    public MarketUI(GameController gameController, MapRegion planet) {
        this.planet = planet;
        //this.market = new Market(planet);
        this.gameController = gameController;
        //planet.setMarket();
        //market = planet.getMarket();
        configure();
    }

    public void setVisible(boolean b) {
        marketFrame.setVisible(b);
    }

    public void configure() {
        /**
         * want to keep updating the potential bill, potential gain and space required;
         */
        //armTextFields();

        for (int i = 0; i < 10; i++) {
            tfNumBuy[i] = new JTextField("0",4);
        }
        for (int j = 0; j < 10; j++) {
            tfNumSell[j] = new JTextField("0",4);
        }

        marketFrame = new JFrame(planet.getRegionName()+ "'s Market");
        marketFrame.setSize(900, 900);
        marketFrame.setLocationRelativeTo(null);
        marketFrame.add(sellPanel, BorderLayout.EAST);
        marketFrame.add(buyPanel, BorderLayout.WEST);
        marketFrame.add(exitMarketButton, BorderLayout.SOUTH);
        marketFrame.add(statusPanel, BorderLayout.NORTH);


        statusPanel = new JPanel();
        statusPanel.add(creditsAvailable, BorderLayout.WEST);
        statusPanel.add(spaceAvailable, BorderLayout.EAST);

        int count = 0;
        buyPanel = new JPanel();
        for (Item item : planet.getAvailableItems().keySet()) {
            int count1 = count;
            if (market.isBuyable(item)) {
                itemNameLabel = new JLabel(item.getName());
                itemNameLabel.setBounds(20, 20 + count1 * 30, 100,30);
                itemPriceLabel = new JLabel(String.valueOf(market.getBuyPrice(item)));
                itemPriceLabel.setBounds(40, 20 + count1 * 30, 100, 30);
                buyPanel.add(itemNameLabel);
                buyPanel.add(itemPriceLabel);
                buyPanel.add(tfNumBuy[item.getKey()]);
                tfNumBuy[item.getKey()].setBounds(60, 20 + count1 * 30, 100,30);
                priceBuyPerUnit[item.getKey()] = market.getBuyPrice(item);
            }
            count++;
            armTextFields();
        }

        count = 0;
        sellPanel = new JPanel();
        for (Item item : planet.getAvailableItems().keySet()) {
            int count1 = count;
            if (market.isSellable(item)) {
                itemNameLabel = new JLabel(item.getName());
                itemNameLabel.setBounds(420, 20 + count1 * 30, 100,30);
                itemPriceLabel = new JLabel(String.valueOf(market.getSellPrice(item)));
                itemPriceLabel.setBounds(440, 20 + count1 * 30, 100, 30);
                sellPanel.add(itemNameLabel);
                sellPanel.add(itemPriceLabel);
                sellPanel.add(tfNumSell[item.getKey()]);
                tfNumSell[item.getKey()].setBounds(460, 20 + count1 * 30, 100,30);
                priceSellPerUnit[item.getKey()] = market.getSellPrice(item);
            }
            count++;
            armTextFields();
        }

        confirmBuyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                if (totalBill > player.getCredit()) {
                    JOptionPane.showMessageDialog(null, "You don't have sufficient credits!",
                            "Message", JOptionPane.ERROR_MESSAGE);
                } else if (totalBill == 0) {
                    JOptionPane.showMessageDialog(null, "Please enter wanted amount!",
                            "Message", JOptionPane.ERROR_MESSAGE);
                    throw new NullPointerException("Please enter wanted amount!");
                } else if (spaceNeeded > player.getShip().getCargoSpaceRemaining()){
                    JOptionPane.showMessageDialog(null, "You don't have sufficient space!",
                            "Message", JOptionPane.ERROR_MESSAGE);
                } else {
                    for (Item item: planet.getAvailableItems().keySet()) {
                        BuyItemCommand cmd = new BuyItemCommand(player, market, item, parseInt(tfNumBuy[item.getKey()]));
                    }
                    resetBuyList();
                    clearBuyWindow();
                }
            }
        });

        confirmSellButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                List<Integer> cargo = player.getShip().getCargo();
                for (Item item: planet.getAvailableItems().keySet()) {
                    int sellAmount = parseInt(tfNumSell[item.getKey()]);
                    //the number of this item in the inventory(cargo) enough to sell
                    if (cargo.get(item.getKey()) < sellAmount) {
                        JOptionPane.showMessageDialog(null, "You don't have enough items!",
                                "Message", JOptionPane.ERROR_MESSAGE);
                    } else {
                        market.sell(item, sellAmount);
                        player.getShip().removeCargo(Item.values()[item.getKey()], sellAmount);
                        player.setCredit(totalGain + player.getCredit());
                        resetSellList();
                        clearSellWindow();
                    }
                }
            }
        });

        exitMarketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                gameController.getPlayer().setJustStarted(false);
                gameController.showLocationUI(planet);
            }
        });


    }

    /**
     * print out both totalbill and space required for buy function
     */
    public void updateTotalBill() {
        for(int i = 0; i < tfNumBuy.length; i++) {
            totalPriceBuy[i] = parseInt(tfNumBuy[i]) * priceBuyPerUnit[i];
            spaceNeeded = spaceNeeded + parseInt(tfNumBuy[i]);
        }
        for( int num : totalPriceBuy) {
            totalBill = totalBill + num;
        }
        potentialTotalBill.setText("Your Total Bill Today will be " + totalBill +
                "required " + spaceNeeded + " space");
    }

    public void updateTotalGain() {
        for(int i = 0; i < tfNumSell.length; i++) {
            totalPriceSell[i] = parseInt(tfNumSell[i]) * priceSellPerUnit[i];
        }
        totalGain = 0;
        for( int num : totalPriceSell) {
            totalGain = totalGain + num;
        }
        potentialTotalGain.setText("Your Total Bill Today will be " + totalGain);
    }

    public void armTextFields() {
        for(JTextField numBuy: tfNumBuy) {
            numBuy.getDocument().addDocumentListener(new DocumentListener() {
                public void insertUpdate(DocumentEvent e) {
                    updateTotalBill();
                }
                public void removeUpdate(DocumentEvent e) {
                    updateTotalBill();
                }
                public void changedUpdate(DocumentEvent e) {
                    updateTotalBill();
                }
            });
        }
        for(JTextField numSell: tfNumSell) {
            numSell.getDocument().addDocumentListener(new DocumentListener() {
                public void insertUpdate(DocumentEvent e) {
                    updateTotalGain();
                }
                public void removeUpdate(DocumentEvent e) {
                    updateTotalGain();
                }
                public void changedUpdate(DocumentEvent e) {
                    updateTotalGain();
                }
            });
        }
    }

    public static int parseInt(JTextField tf) {
        if (tf == null || tf.getText().trim().isEmpty()) {
            return 0;
        } else {
            try {
                return Integer.parseInt(tf.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter valid number!",
                        "Message", JOptionPane.ERROR_MESSAGE);
                throw new IllegalArgumentException("Please enter valid number!");
            }
        }
    }


    private void resetBuyList() {
        configure();
    }

    private void resetSellList() {
        //don't know if right or not
        configure();
    }


    private void clearBuyWindow() {
        //more to implement
        for(JTextField tf: tfNumBuy) {
            tf.setText("0");
        }
        creditsAvailable.setText(String.valueOf(player.getCredit()));
    }


    private void clearSellWindow() {
        //more to implement
        for(JTextField tf: tfNumSell) {
            tf.setText("0");
        }
        creditsAvailable.setText(String.valueOf(player.getCredit()));
    }
}


