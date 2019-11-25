import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormRepair {
    private JFrame repairForm;
    private JButton btnConfirm;
    private JLabel lblQuestion;
    private JLabel lblNote;
    private JButton btnMax;
    private JButton btnNothing;
    private JTextField amountTf;
    private int amountNum;
    private int repairPrice;
    private GameController gc;

    public void setVisible(boolean b) { repairForm.setVisible(b); }

    public FormRepair(GameController gc) {
        //initialize
        this.gc = gc;
        repairForm = new JFrame("fuel");
        repairForm.setSize(700,300);
        repairForm.setLayout(null);
        repairPrice = (int) 3000 / gc.getPlayer().getEngineerSkill();
        lblNote = new JLabel("Based on your engineer skill, repair will cost " + repairPrice + " credits per point.");
        lblNote.setLocation(25,20);
        lblNote.setSize(500,30);
        lblQuestion = new JLabel("How much do you want to spend on repairing?");
        lblQuestion.setLocation(25,50);
        lblQuestion.setSize(300,30);
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
        repairForm.add(lblQuestion);
        repairForm.add(lblNote);
        repairForm.add(btnConfirm);
        repairForm.add(btnMax);
        repairForm.add(btnNothing);
        repairForm.add(amountTf);


        btnConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int currCredit = gc.getPlayer().getCredit();
                int currHealth = gc.getPlayer().getShip().getHealthRemaining();
                amountNum = parseInt(amountTf);
                //health is full
                if (currHealth == gc.getPlayer().getShip().getHealth()) {
                    JOptionPane.showMessageDialog(repairForm, "There's no need to do repair!",
                            "Done", JOptionPane.INFORMATION_MESSAGE);
                } else if (amountNum / repairPrice > gc.getPlayer().getShip().getHealth() - currHealth) {
                    JOptionPane.showMessageDialog(repairForm, "You don't have enough health capacity!",
                            "Message", JOptionPane.ERROR_MESSAGE);
                } else if (amountNum < repairPrice) {
                    JOptionPane.showMessageDialog(repairForm, "Your offer is not enough to do one repair!",
                            "Message", JOptionPane.ERROR_MESSAGE);
                }else if (amountNum <= gc.getPlayer().getCredit()) {
                    int amountRepair = (int) amountNum / repairPrice;
                    int bill = (int) amountRepair * repairPrice;
                    gc.getPlayer().getShip().setHealthRemaining(currHealth + amountRepair);
                    gc.getPlayer().setCredit(currCredit - amountRepair * repairPrice);
                    JOptionPane.showMessageDialog(repairForm, "Your ship has been repaired! You are charged "
                                    + bill + "credits",
                            "Done", JOptionPane.INFORMATION_MESSAGE);
                    repairForm.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "You don't have enough credits!",
                            "Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnNothing.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repairForm.setVisible(false);
            }
        });

        btnMax.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int currCredit = gc.getPlayer().getCredit();
                int currHealth = gc.getPlayer().getShip().getHealthRemaining();
                if (currHealth == gc.getPlayer().getShip().getHealth()) {
                    JOptionPane.showMessageDialog(repairForm, "There's no need to do repair!",
                            "Done", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    int repairGain = Math.min(gc.getPlayer().getShip().getHealth() - currHealth, (int) currCredit / repairPrice);
                    int repairCost = repairGain * repairPrice;
                    gc.getPlayer().getShip().setHealthRemaining(currHealth + repairGain);
                    gc.getPlayer().setCredit(currCredit - repairCost);
                    JOptionPane.showMessageDialog(repairForm, "We tried our best to get your ship repaired!",
                            "Done", JOptionPane.INFORMATION_MESSAGE);
                    repairForm.setVisible(false);
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
