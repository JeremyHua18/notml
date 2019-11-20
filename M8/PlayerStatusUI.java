import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerStatusUI {
    private GameController gc;
    private Player player;
    private JFrame statusFrame;
    private JPanel inventoryPanel;
    private JPanel playerPanel;
    //new added
    private JPanel dockPanel;
    private JButton repairBtn;
    private JButton fuelBtn;
    private JLabel fuelCostLbl;

    private JLabel lblShipType;
    private JLabel lblSpaceRemaining;
    private JLabel lblFuelRemaining;
    private JLabel lblHealthRemaining;
    private JLabel lblCredits;
    private JLabel lblLocation;
    //
    private JButton refreshData;
    private JPanel topLevel;
    private GridBagConstraints constraints = new GridBagConstraints();
    public PlayerStatusUI(GameController gc) {
        this.gc = gc;
        this.player = gc.getPlayer();
        configure();
    }

    public void configure() {
        topLevel = new JPanel(new GridBagLayout());
        constraints.fill = GridBagConstraints.HORIZONTAL;
        statusFrame = new JFrame();
        inventoryPanel = new JPanel(new GridLayout(11, 2));
        playerPanel = new JPanel(new GridLayout(6, 2));
        //new added
        dockPanel = new JPanel(new GridLayout(4, 2));
        //
        formatInventoryPanel();
        //formatPlayerPanel();
        //
        formatDockPanel();
        //
        refreshData = new JButton("REFRESH");
        this.addAtXYWidth(inventoryPanel, 0, 0, 1);
        this.addAtXYWidth(playerPanel, 1, 0, 1);
        this.addAtXYWidth(refreshData, 0, 1, 2);
        //new added
        this.addAtXYWidth(dockPanel, 0, 2, 2);
        //
        statusFrame.add(topLevel);
        //
        playerPanel.add(new JLabel("Ship Type"));
        lblShipType = new JLabel("" + player.getCurrentShip().getShipName());
        playerPanel.add(lblShipType);
        playerPanel.add(new JLabel("Ship space remaining:"));
        lblSpaceRemaining = new JLabel("" + player.getCurrentShip().getCargoSpaceRemaining());
        playerPanel.add(lblSpaceRemaining);
        playerPanel.add(new JLabel("Ship fuel remaining:"));
        lblFuelRemaining = new JLabel("" + player.getCurrentShip().getFuelCapacityRemaining());
        playerPanel.add(lblFuelRemaining);
        playerPanel.add(new JLabel("Ship health remaining"));
        lblHealthRemaining = new JLabel("" + player.getCurrentShip().getHealthRemaining());
        playerPanel.add(lblHealthRemaining);
        playerPanel.add(new JLabel("Credits:"));
        lblCredits = new JLabel("" + player.getCredit());
        playerPanel.add(lblCredits);
        playerPanel.add(new JLabel("Location:"));
        lblLocation = new JLabel(player.getCurrentLocation().toString());
        playerPanel.add(lblLocation);
        //
        repairBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (gc.isNpcOpen()) {
                    FormRepair form = new FormRepair(gc);
                    form.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(statusFrame, "Cannot perform"
                                    + "repairs during npc encounter",
                            "Can't Repair", JOptionPane.INFORMATION_MESSAGE);
                }
                //formatPlayerPanel();
                //formatInventoryPanel();
            }
        });


        fuelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gc.isNpcOpen()) {
                    FormBuyFuel form = new FormBuyFuel(gc);
                    form.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(statusFrame, "Cannot perform"
                                    + "refuel during npc encounter",
                            "Can't Refuel", JOptionPane.INFORMATION_MESSAGE);
                }
                //formatPlayerPanel();
                //formatInventoryPanel();
            }
        });

        refreshData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                formatInventoryPanel();
                formatPlayerPanel();
                if (player.getShip().getHealthRemaining() <= 0
                        || (player.getCredit() == 0
                                && player.getShip().getCargoSpace()
                                == player.getShip().getCargoSpaceRemaining())) {
                    statusFrame.setVisible(false);
                    gc.showEndGameUI(false, gc);
                }
                if (player.getShip().containGoldenSnitch()) {
                    statusFrame.setVisible(false);
                    gc.showEndGameUI(true, gc);
                }
            }
        });
        statusFrame.setVisible(true);

        statusFrame.setDefaultCloseOperation(statusFrame.EXIT_ON_CLOSE);

        statusFrame.setLocationRelativeTo(null);

        statusFrame.setSize(1200, 250);
    }

    //new added
    public void formatDockPanel() {
        repairBtn = new JButton("repair");
        fuelBtn = new JButton("fuel");
        dockPanel.add(repairBtn);
        dockPanel.add(fuelBtn);
        dockPanel.setName("Dock");
    }

    public void formatInventoryPanel() {
        inventoryPanel.removeAll();
        inventoryPanel.add(new JLabel("Item name"));
        inventoryPanel.add(new JLabel("# in ship cargo"));
        for (Item item : Item.values()) {
            inventoryPanel.add(new JLabel(item.getName()));
            inventoryPanel.add(new JLabel("" + player.getShip().countItemInCargo(item)));
            System.out.println(player.getShip().countItemInCargo(item));
        }
    }

    public void formatPlayerPanel() {
        //playerPanel.removeAll();
        //playerPanel.add(new JLabel("Ship Type"));
        //playerPanel.add(new JLabel("" + player.getCurrentShip().getShipName()));
        lblShipType.setText("" + player.getCurrentShip().getShipName());
        //playerPanel.add(new JLabel("Ship space remaining:"));
        //playerPanel.add(new JLabel("" + player.getCurrentShip().getCargoSpaceRemaining()));
        lblSpaceRemaining.setText("" + player.getCurrentShip().getCargoSpaceRemaining());
        //playerPanel.add(new JLabel("Ship fuel remaining:"));
        //playerPanel.add(new JLabel("" + player.getCurrentShip().getFuelCapacityRemaining()));
        lblFuelRemaining.setText("" + player.getCurrentShip().getFuelCapacityRemaining());
        //playerPanel.add(new JLabel("Ship health remaining"));
        //playerPanel.add(new JLabel("" + player.getCurrentShip().getHealthRemaining()));
        lblHealthRemaining.setText("" + player.getCurrentShip().getHealthRemaining());
        //playerPanel.add(new JLabel("Credits:"));
        //playerPanel.add(new JLabel("" + player.getCredit()));
        lblCredits.setText("" + player.getCredit());
        //playerPanel.add(new JLabel("Location:"));
        //playerPanel.add(new JLabel(player.getCurrentLocation().toString()));
        lblLocation.setText(player.getCurrentLocation().toString());

        System.out.println(player.getCurrentShip().toString());
        System.out.println("credits: " + player.getCredit());
        System.out.println(player.toString());
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
        topLevel.add(label, constraints);
    }

}
