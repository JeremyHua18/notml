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
    private JPanel mainPanel;
    private JPanel buyPanel;
    private JPanel sellPanel;
    private JPanel statusPanel;
    private JLabel buyPrice;
    private JLabel buyQuantity;
    private JLabel buyBalance;
    private JLabel buyAfterBalance;
    private JLabel sellPrice;
    private JLabel sellQuantity;
    private JLabel sellBalance;
    private JLabel sellAfterBalance;
    private JLabel cargo, error;
    private GridBagConstraints constraints = new GridBagConstraints();



    private int credits, space, totalBill, totalGain, spaceNeeded;
    private JLabel creditsAvailable, spaceAvailable;
    private JButton confirmSellButton, confirmBuyButton, exitMarketButton;
    private JLabel potentialTotalGain, potentialTotalBill, potentialSpaceNeeded;
    private JLabel shipInfo;

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
        this.market = planet.getMarket();
        this.gameController = gameController;
        this.player = gameController.getPlayer();
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

        //establish references for textfields
        for (int i = 0; i < 10; i++) {
            tfNumBuy[i] = new JTextField("0",4);
        }
        for (int j = 0; j < 10; j++) {
            tfNumSell[j] = new JTextField("0",4);
        }
        //set up tf listeners
        armTextFields();

        //create main frame
        marketFrame = new JFrame(planet.getRegionName()+ "'s Market");
        this.mainPanel = new JPanel(new GridBagLayout());
        constraints.fill = GridBagConstraints.HORIZONTAL;

        //create credit label and add
        creditsAvailable = new JLabel("Credits: " + player.getCredit());
        this.addAtXYWidth(creditsAvailable, 0, 0, 2);

        //create cargo label and add
        spaceAvailable = new JLabel("Cargo space: "
                + player.getCurrentShip().getCargoSpaceRemaining());
        this.addAtXYWidth(spaceAvailable, 0, 1, 2);

        //create ship label and add
        shipInfo = new JLabel(player.getShip().toString());
        this.addAtXYWidth(shipInfo, 0, 5, 2);

        //create bill and saleTotal labels
        potentialTotalBill = new JLabel("Your purchase will be 0 credits.");
        potentialTotalGain = new JLabel("Your sale will be 0 credits.");

        //make sure boxes are evenly shared
        constraints.weightx = 0.5;

        sellPanel = new JPanel(new GridLayout(0, 4));
        buyPanel = new JPanel(new GridLayout(0, 4));
        exitMarketButton = new JButton("Exit market");
        confirmBuyButton = new JButton("Confirm buy");
        confirmSellButton = new JButton("Confirm sell");

        this.addAtXYWidth(new JLabel("SELL"), 0, 2, 1);
        this.addAtXYWidth(new JLabel("BUY"), 1, 2, 1);

        //BUY panel
        formatBuy();

        //SELL panel
        formatSale();

        this.addAtXYWidth(sellPanel, 0, 3, 1);
        this.addAtXYWidth(buyPanel, 1, 3, 1);

        confirmBuyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                if (totalBill > player.getCredit()) {
                    JOptionPane.showMessageDialog(marketFrame, "You don't have sufficient credits!",
                            "Message", JOptionPane.ERROR_MESSAGE);
                } else if (totalBill == 0) {
                    JOptionPane.showMessageDialog(marketFrame, "Please enter wanted amount!",
                            "Message", JOptionPane.ERROR_MESSAGE);
                    //throw new NullPointerException("Please enter wanted amount!");
                } else if (spaceNeeded > player.getShip().getCargoSpaceRemaining()){
                    JOptionPane.showMessageDialog(marketFrame, "You don't have sufficient space!",
                            "Message", JOptionPane.ERROR_MESSAGE);
                } else {
                    int count = 0;
                    for (Item item: planet.getAvailableItems().keySet()) {
                        new BuyItemCommand(player, market, item, parseInt(tfNumBuy[count]));
                        count++;
                    }
                    formatSale();
                    formatBuy();
//                    resetBuyList();
//                    clearBuyWindow();
                }
            }
        });

        confirmSellButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                List<Integer> cargo = player.getShip().getCargo();
                int count = 0;
                //switch all item.getKey() to count?
                for (Item item: planet.getAvailableItems().keySet()) {
                    int sellAmount = parseInt(tfNumSell[count]);
                    System.out.println(sellAmount);
                    //the number of this item in the inventory(cargo) enough to sell
                    //keep this as .getKey() because the ship inventory doesn't
                    //necessarily line up with market sellTFs
                    if (sellAmount != 0 && cargo.get(item.getKey()) < sellAmount) {
                        JOptionPane.showMessageDialog(marketFrame, "You don't have enough "
                                        + item.getName() + "!",
                                "Not Enough To Sell", JOptionPane.ERROR_MESSAGE);
                    } else if (sellAmount != 0) {
                        market.sell(item, sellAmount);
                        //keep .getKey() here because ship inventory doesn't necessarily
                        //line up with the sellTFs
                        player.getShip().removeCargo(Item.values()[item.getKey()], sellAmount);
                        player.setCredit(totalGain + player.getCredit());
                        formatBuy();
                        formatSale();
                    }
                    count++;
                }
            }
        });

        exitMarketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                gameController.getPlayer().setJustStarted(false);
                gameController.showLocationUI();
            }
        });
        this.addAtXYWidth(confirmSellButton, 0, 4, 1);
        this.addAtXYWidth(confirmBuyButton, 1, 4, 1);
        this.addAtXYWidth(exitMarketButton, 0, 6, 2);

        marketFrame.add(mainPanel);

        marketFrame.setVisible(true);

        marketFrame.setDefaultCloseOperation(marketFrame.EXIT_ON_CLOSE);

        marketFrame.setLocationRelativeTo(null);

        marketFrame.setSize(1200, 400);
    }

    public void formatBuy() {
        updateCargoCredits();
        updateShipLabel();
        int count = 0;
        buyPanel.removeAll();
        //establish headers
        buyPanel.add(new JLabel("Item"));
        buyPanel.add(new JLabel("Price"));
        buyPanel.add(new JLabel("# available"));
        buyPanel.add(new JLabel("# to buy"));
        for (Item item : planet.getAvailableItems().keySet()) {
            int count1 = count;
            if (market == null) {
                System.out.println("market is null");
            }
            if (item == null) {
                System.out.println("item is null");
            }
            if (market.isBuyable(item)) {
                //System.out.println(item.getName());
                itemNameLabel = new JLabel(item.getName());
                //itemNameLabel.setBounds(20, 20 + count1 * 30, 100,30);
                itemPriceLabel = new JLabel(String.valueOf(market.getBuyPrice(item)));
                priceBuyPerUnit[count] = market.getBuyPrice(item);
                //itemPriceLabel.setBounds(40, 20 + count1 * 30, 100, 30);
                buyPanel.add(itemNameLabel);
                buyPanel.add(itemPriceLabel);
                buyPanel.add(new JLabel("" + market.getQuantity(item)));
                buyPanel.add(tfNumBuy[count]);
                //tfNumBuy[item.getKey()].setBounds(60, 20 + count1 * 30, 100,30);
                priceBuyPerUnit[count] = market.getBuyPrice(item);
            }
            count++;
            //need to move this outside of loop
            //armTextFields();
        }
        buyPanel.add(potentialTotalBill);
    }

    private void formatSale() {
        updateCargoCredits();
        updateShipLabel();
        int count = 0;
        sellPanel.removeAll();
        //establish headers
        sellPanel.add(new JLabel("Item"));
        sellPanel.add(new JLabel("Value"));
        sellPanel.add(new JLabel("# in cargo"));
        sellPanel.add(new JLabel("# to sell"));
        for (Item item : planet.getAvailableItems().keySet()) {
            int count1 = count;
            if (market.isSellable(item)) {
                //System.out.println(item.getName());
                itemNameLabel = new JLabel(item.getName());
                //itemNameLabel.setBounds(420, 20 + count1 * 30, 100,30);
                itemPriceLabel = new JLabel(String.valueOf(market.getSellPrice(item)));
                //itemPriceLabel.setBounds(440, 20 + count1 * 30, 100, 30);
                sellPanel.add(itemNameLabel);
                sellPanel.add(itemPriceLabel);
                sellPanel.add(new JLabel("" + player.getShip().countItemInCargo(item)));
                sellPanel.add(tfNumSell[count]);
                //tfNumSell[item.getKey()].setBounds(460, 20 + count1 * 30, 100,30);
                priceSellPerUnit[count] = market.getSellPrice(item);
//                System.out.println(market.getSellPrice(item));
//                System.out.println(market.getBuyPrice(item));

            }
            count++;
            //need to move this outside of loops
            //armTextFields();

        }
        sellPanel.add(potentialTotalGain);
    }

    /**
     * print out both totalbill and space required for buy function
     */
    public void updateTotalBill() {
        totalBill = 0;
        spaceNeeded = 0;
        for(int i = 0; i < tfNumBuy.length; i++) {
            totalPriceBuy[i] = parseInt(tfNumBuy[i]) * priceBuyPerUnit[i];
            spaceNeeded = spaceNeeded + parseInt(tfNumBuy[i]);
        }
        for(int num : totalPriceBuy) {
            totalBill = totalBill + num;
        }
        potentialTotalBill.setText("Your purchase will be " + totalBill +
                " credits.");
    }

    public void updateTotalGain() {
        totalGain = 0;
        for(int i = 0; i < tfNumSell.length; i++) {
            totalPriceSell[i] = parseInt(tfNumSell[i]) * priceSellPerUnit[i];
        }
        totalGain = 0;
        for( int num : totalPriceSell) {
            totalGain = totalGain + num;
        }
        potentialTotalGain.setText("Your total sale will be " + totalGain + " credits.");
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

    public void updateShipLabel() {
        shipInfo.setText(player.getShip().toString());
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

    private void updateCargoCredits() {
        creditsAvailable.setText("Credits: " + player.getCredit());
        spaceAvailable.setText("Cargo space: "
                + player.getCurrentShip().getCargoSpaceRemaining());
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

    private void addAtXYWidth(JComponent label, int x, int y, int width) {
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridwidth = width;
        label.setBorder(BorderFactory.createLineBorder(Color.black));
        if (label instanceof JLabel) {
            JLabel temp = (JLabel) label;
            temp.setHorizontalAlignment(JLabel.CENTER);

        }
        mainPanel.add(label, constraints);
    }
}


