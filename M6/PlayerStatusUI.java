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
        playerPanel = new JPanel(new GridLayout(3, 2));
        formatInventoryPanel();
        formatPlayerPanel();
        refreshData = new JButton("REFRESH");
        this.addAtXYWidth(inventoryPanel, 0, 0, 1);
        this.addAtXYWidth(playerPanel, 1, 0, 1);
        this.addAtXYWidth(refreshData, 0, 1, 2);
        statusFrame.add(topLevel);
        refreshData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                formatInventoryPanel();
                formatPlayerPanel();
            }
        });
        statusFrame.setVisible(true);

        statusFrame.setDefaultCloseOperation(statusFrame.EXIT_ON_CLOSE);

        statusFrame.setLocationRelativeTo(null);

        statusFrame.setSize(1200, 250);
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
        playerPanel.removeAll();
        playerPanel.add(new JLabel("Status info"));
        playerPanel.add(new JLabel(player.getShip().toString()));
        System.out.println(player.getShip().toString());

        playerPanel.add(new JLabel("Credits:"));
        playerPanel.add(new JLabel("" + player.getCredit()));

        playerPanel.add(new JLabel("Location:"));
        playerPanel.add(new JLabel(player.getCurrentLocation().toString()));
        System.out.println(player.getCurrentShip().toString());
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
