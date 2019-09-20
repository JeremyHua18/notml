import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class gui{
    private JFrame welcome;
    private JFrame config;
    private JFrame display;
    private JLabel wcLabel = new JLabel();
    private JLabel name = new JLabel("name");
    private JLabel difficulty = new JLabel("difficulty");
    private JLabel pilot = new JLabel("Pilot");
    private JLabel fighter = new JLabel("Merchant");
    private JLabel merchant = new JLabel("merchant");
    private JLabel engineer = new JLabel("engineer");

    //config
    //text fields for inputs
    JTextField tfName = new JTextField(10);
    JTextField tfPilot = new JTextField(4);
    JTextField tfFighter = new JTextField(4);
    JTextField tfMerchant = new JTextField(4);
    JTextField tfEngineer = new JTextField(4);
    //count
    private int credit = 0;
    private int diffLevel = 1;

    //display screen
    private JLabel dpName = new JLabel();
    private JLabel dpSkill = new JLabel();

    private JButton confirmBtn;
    private ButtonGroup dfGroup;

    public gui(){
        prepareGUI();
    }

    public static void main(String args[]) {
        gui Gui = new gui();
        Gui.showEventDemo();
    }

    private void prepareGUI() {
        //creating the frame
        welcome = new JFrame("¿ªÊ¼ÆÁÄ»(start screen)");
        config = new JFrame("Space Trader");
        display = new JFrame("Space Trader");
        welcome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        welcome.setSize(400,400);
        welcome.setLocationRelativeTo(null);
        config.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        config.setSize(400,400);
        config.setLocationRelativeTo(null);
        display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        display.setSize(400,400);
        display.setLocationRelativeTo(null);

        //creating welcome and start
        /*JOptionPane.showMessageDialog(null, "Welcome to Space Trader!",
                "Message", JOptionPane.ERROR_MESSAGE);*/
        //JButton startBtn = new JButton("start");
        //JLabel labelW = new JLabel("Welcome to Space Trader");
        welcome.add(wcLabel);



        //display



    }

    private void showEventDemo() {
        //welcome
        wcLabel.setText("Welcom to Trader Space!");
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


    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.equals("start")) {
                welcome.setVisible(false);
                config.setVisible(true);
            }


            if (command.equals("confirm")) {
                int pilot = Integer.parseInt(tfPilot.getText());
                int fighter = Integer.parseInt(tfFighter.getText());
                int merchant = Integer.parseInt(tfMerchant.getText());
                int engineer = Integer.parseInt(tfEngineer.getText());
                int sum = pilot + fighter + merchant + engineer;

                if ((diffLevel == 1 && sum > 1000) || (diffLevel == 2 && sum > 500)
                        || (diffLevel == 3 && sum > 100)) {
                    JOptionPane.showMessageDialog(null, "You don't have enough credits!",
                            "Message", JOptionPane.ERROR_MESSAGE);
                } else {
                    String name = tfName.getText();
                    dpName.setText("name: " + name);
                    dpSkill.setText("<html>pilot skill points: " + pilot
                            + "<br>fighter skill points:" + fighter
                            + "<br>merchant skill points: " + merchant
                            + "<br>fighter skill points: </html>" + engineer);
                    config.setVisible(false);
                    display.setVisible(true);
                }
            }
        }
    }
}