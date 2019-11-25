import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormBuyFuel {
    private JFrame buyFuelForm;
    private JButton btnConfirm;
    private JLabel lblQuestion;
    private JLabel lblNote;
    private JButton btnMax;
    private JButton btnNothing;
    private JTextField amountTf;
    private int amountNum;
    private int fuelPrice;
    private GameController gc;

    public void setVisible(boolean b) { buyFuelForm.setVisible(b); }

    public FormBuyFuel(GameController gc) {
        //initialize
        this.gc = gc;
        fuelPrice = (int) (50 / (gc.getPlayer().getPilotSkill() + 1));
        buyFuelForm = new JFrame("fuel");
        buyFuelForm.setSize(500,300);
        buyFuelForm.setLayout(null);
        lblQuestion = new JLabel("How much do you want to spend on fuel?");
        lblQuestion.setLocation(25,50);
        lblQuestion.setSize(300,30);
        lblNote = new JLabel("Based on your pilot skill, fuel will cost " + fuelPrice + " credits per unit.");
        lblNote.setLocation(25,20);
        lblNote.setSize(450,30);
        btnConfirm = new JButton("confirm");
        this.btnConfirm.setLocation(150, 150);
        this.btnConfirm.setSize(100, 20);
        btnNothing = new JButton("nothing");
        this.btnNothing.setLocation(150, 200);
        this.btnNothing.setSize(100, 20);
        btnMax = new JButton("MAX");
        this.btnMax.setLocation(150, 100);
        this.btnMax.setSize(100, 22);
        amountTf = new JTextField("0", 4);
        amountTf.setLocation(350,60);
        amountTf.setSize(40,20);
        buyFuelForm.add(lblQuestion);
        buyFuelForm.add(btnConfirm);
        buyFuelForm.add(btnMax);
        buyFuelForm.add(btnNothing);
        buyFuelForm.add(amountTf);
        buyFuelForm.add(lblNote);


        btnConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int currCredit = gc.getPlayer().getCredit();
                int currFuel = gc.getPlayer().getShip().getFuelCapacityRemaining();
                amountNum = parseInt(amountTf);
                if (currFuel == gc.getPlayer().getShip().getFuelCapacity()) {
                    JOptionPane.showMessageDialog(buyFuelForm, "There's no need to refuel!",
                            "Done", JOptionPane.INFORMATION_MESSAGE);
                } else if (amountNum < fuelPrice) {
                    JOptionPane.showMessageDialog(buyFuelForm, "Your offer is not enough to do one repair!",
                            "Message", JOptionPane.ERROR_MESSAGE);
                } else if((amountNum / fuelPrice) > (gc.getPlayer().getShip().getFuelCapacity() - currFuel)) {
                    JOptionPane.showMessageDialog(buyFuelForm, "You don't have enough fuel capacity!",
                            "Message", JOptionPane.ERROR_MESSAGE);
                } else if (amountNum <= gc.getPlayer().getCredit()) {
                    int amountRefuel = (int) amountNum / fuelPrice;
                    int bill = (int) amountRefuel * fuelPrice;
                    gc.getPlayer().getShip().setFuelCapacityRemaining(currFuel + amountRefuel);
                    gc.getPlayer().setCredit(currCredit - bill);
                    JOptionPane.showMessageDialog(buyFuelForm, "Refuel is done! You are charged "
                                    + bill + " credits",
                            "Done", JOptionPane.INFORMATION_MESSAGE);
                    buyFuelForm.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "You don't have enough credits!",
                            "Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnNothing.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buyFuelForm.setVisible(false);
            }
        });

        btnMax.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int currCredit = gc.getPlayer().getCredit();
                int currFuel = gc.getPlayer().getShip().getFuelCapacityRemaining();
                if (currFuel == gc.getPlayer().getShip().getFuelCapacity()) {
                    JOptionPane.showMessageDialog(buyFuelForm, "There's no need to refuel!",
                            "Done", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    int fuelGain = Math.min(gc.getPlayer().getShip().getFuelCapacity() - currFuel, (int) currCredit / fuelPrice);
                    int fuelCost = fuelGain * fuelPrice;
                    gc.getPlayer().getShip().setFuelCapacityRemaining(currFuel + fuelGain);
                    gc.getPlayer().setCredit(currCredit - fuelCost);
                    JOptionPane.showMessageDialog(buyFuelForm, "Maximum refuel is done!",
                            "Done", JOptionPane.INFORMATION_MESSAGE);
                    buyFuelForm.setVisible(false);
                }
            }
        });
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



}
