import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Gui {
    private JFrame welcome;
    private JFrame config;
    private JFrame display;
    private JLabel wcLabel = new JLabel();
    private JLabel name = new JLabel("Name");
    private JLabel difficulty = new JLabel("Difficulty");
    private JLabel pilot = new JLabel("Pilot");
    private JLabel fighter = new JLabel("Fighter");
    private JLabel merchant = new JLabel("Merchant");
    private JLabel engineer = new JLabel("Engineer");

    //config
    //text fields for inputs
    JTextField tfName = new JTextField(10);
    JTextField tfPilot = new JTextField(4);
    JTextField tfFighter = new JTextField(4);
    JTextField tfMerchant = new JTextField(4);
    JTextField tfEngineer = new JTextField(4);

    // default values
    private int credit = 0;
    private int diffLevel = 1;

    //display screen
    private JLabel dpName = new JLabel();
    private JLabel dpSkill = new JLabel();

    private JButton confirmBtn;
    private ButtonGroup dfGroup;

    public Gui(){
        prepareGUI();
    }

    public static void main(String args[]) {
        Gui gui = new Gui();
        gui.showEventDemo();
    }

    private void prepareGUI() {
        //creating the frame
        welcome = new JFrame("Space Trader");
        config = new JFrame("Space Trader");
        display = new JFrame("Space Trader");
        //setting the frame
        welcome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        welcome.setSize(400,400);
        welcome.setLocationRelativeTo(null);
        config.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        config.setSize(400,400);
        config.setLocationRelativeTo(null);
        display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        display.setSize(400,400);

        display.setLocationRelativeTo(null);
        welcome.add(wcLabel);

    }

    private void showEventDemo() {
        //welcome
        wcLabel.setText("Welcome to Trader Space!");
        welcome.setLayout(null);
        JButton startBtn = new JButton("start");
        startBtn.setActionCommand("start");
        startBtn.setSize(100, 30);
        startBtn.addActionListener(new ButtonClickListener());
        wcLabel.setBounds(120,160,200,30);
        startBtn.setBounds(150,185,100,30);
        welcome.add(startBtn);
        welcome.setVisible(true);


        JPanel dPanel = new JPanel();
        dPanel.setLayout(new GridLayout(9, 2));
        dPanel.add(name);
        dPanel.add(tfName);
        dPanel.add(difficulty);
        JRadioButton ez = new JRadioButton("easy");
        ez.setActionCommand("easy");
        JRadioButton md = new JRadioButton("medium");
        md.setActionCommand("medium");
        JRadioButton hd = new JRadioButton("hard");
        hd.setActionCommand("hard");
        dfGroup = new ButtonGroup();
        dfGroup.add(ez);
        dfGroup.add(md);
        dfGroup.add(hd);

        ez.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                // TODO Auto-generated method stub
                credit = 1000;
                diffLevel = 1;
            }
        });

        md.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                credit = 500;
                diffLevel = 2;

            }
        });

        hd.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                credit = 100;
                diffLevel = 3;
            }
        });

        JPanel radioJPanel = new JPanel();
        radioJPanel.setLayout(null);
        radioJPanel.add(ez);
        radioJPanel.add(md);
        radioJPanel.add(hd);
        ez.setBounds(0, 0, 60, 30);
        md.setBounds(60, 0, 80, 30);
        hd.setBounds(140, 0, 70, 30);
        dPanel.add(radioJPanel);
        dPanel.add(pilot);
        dPanel.add(tfPilot);
        dPanel.add(fighter);
        dPanel.add(tfFighter);
        dPanel.add(merchant);
        dPanel.add(tfMerchant);
        dPanel.add(engineer);
        dPanel.add(tfEngineer);

        JPanel jPanelSouth;
        jPanelSouth = new JPanel();
        jPanelSouth.setLayout(new GridLayout(1, 2));

        confirmBtn = new JButton("confirm");
        confirmBtn.setActionCommand("confirm");
        confirmBtn.addActionListener(new ButtonClickListener());

        jPanelSouth.add(confirmBtn);

        config.add(dPanel,BorderLayout.CENTER);
        config.add(jPanelSouth,BorderLayout.SOUTH);

        //config

        display.setLayout(null);
        dpName.setBounds(120,160,200,30);
        dpSkill.setBounds(100,185,200,100);
        display.add(dpName);
        display.add(dpSkill);
    }

    //deal with default values and so on
    public static int parseInt(JTextField f) {
        if (f == null || f.getText().trim().isEmpty()) {
            return 0;
        } else {
            try {
                return Integer.parseInt(f.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter valid number!",
                        "Message", JOptionPane.ERROR_MESSAGE);
                throw new IllegalArgumentException("Please enter valid number!");
            }
        }
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.equals("start")) {
                welcome.setVisible(false);
                config.setVisible(true);
            }


            if (command.equals("confirm")) {
                int pilot = parseInt(tfPilot);
                int fighter = parseInt(tfFighter);
                int merchant = parseInt(tfMerchant);
                int engineer = parseInt(tfEngineer);
                int sum = pilot + fighter + merchant + engineer;

                if(tfName.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter your name!",
                            "Message", JOptionPane.ERROR_MESSAGE);
                    throw new NullPointerException("Please enter your name!");
                }

                if ((diffLevel == 1 && sum > 1000) || (diffLevel == 2 && sum > 500)
                        || (diffLevel == 3 && sum > 100)) {
                    JOptionPane.showMessageDialog(null, "You don't have enough credits!",
                            "Message", JOptionPane.ERROR_MESSAGE);
                } else if ((diffLevel == 1 && sum < 1000) || (diffLevel == 2 && sum < 500)
                        || (diffLevel == 3 && sum < 100)) {
                    JOptionPane.showMessageDialog(null, "Please use all your credits!",
                            "Message", JOptionPane.ERROR_MESSAGE);
                } else {
                    String name = tfName.getText();
                    dpName.setText("name: " + name);
                    dpSkill.setText("<html>Pilot Skill Points: " + pilot
                            + "<br>Fighter Skill Points:" + fighter
                            + "<br>Merchant Skill Points: " + merchant
                            + "<br>Engineer Skill Points:</html>" + engineer);
                    config.setVisible(false);
                    display.setVisible(true);
                }
            }
        }
    }
}