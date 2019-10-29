
import javax.swing.*;

import java.awt.*;

import java.awt.event.*;

import javax.swing.event.*;


public class ConfigurationScreen extends JFrame {

    private JFrame configurationScreenFrame;
    private JLabel name;
    private JLabel difficulty;
    private JLabel pilot;
    private JLabel fighter;
    private JLabel merchant;
    private JLabel engineer;
    private JLabel pointsAvailable;
    private JLabel creditsAvailable;
    private JTextField tfName;
    private JTextField tfPilot;
    private JTextField tfFighter;
    private JTextField tfMerchant;
    private JTextField tfEngineer;
    private int skillPoints;
    private int diffLevel;
    private int credits;
    private String diffStr;
    private JButton confirmButton;
    private ButtonGroup difficultyButtonGroup;
    private JPanel radioPanel;
    private JPanel southPanel;
    private GameController gameController;

    public ConfigurationScreen(GameController gameController) {
        this.gameController = gameController;
        configure();
    }

    public void setVisible(boolean b) {
        configurationScreenFrame.setVisible(b);
    }

    public void configure() {
        configurationScreenFrame = new JFrame("Configuration Screen");
        configurationScreenFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        configurationScreenFrame.setSize(400, 400);
        configurationScreenFrame.setLocationRelativeTo(null);
        name = new JLabel("Name");
        difficulty = new JLabel("Difficulty");
        pilot = new JLabel("Pilot");
        fighter = new JLabel("Fighter");
        merchant = new JLabel("Merchant");
        engineer = new JLabel("Engineer");
        pointsAvailable = new JLabel("Skill points available: ");
        creditsAvailable = new JLabel("Credits available: ");
        tfName = new JTextField(10);
        tfPilot = new JTextField("0", 4);
        tfFighter = new JTextField("0", 4);
        tfMerchant = new JTextField("0", 4);
        tfEngineer = new JTextField("0", 4);
        armTextFields();
        skillPoints = 0;
        diffLevel = 1;
        credits = 0;
        JPanel difficultyPanel = new JPanel();
        difficultyPanel.setLayout(new GridLayout(9, 2));
        difficultyPanel.add(name);
        difficultyPanel.add(tfName);
        difficultyPanel.add(difficulty);
        JRadioButton easyRadioButton = new JRadioButton("Easy");
        easyRadioButton.setActionCommand("easy");
        JRadioButton mediumRadioButton = new JRadioButton("Medium");
        mediumRadioButton.setActionCommand("medium");
        JRadioButton hardRadioButton = new JRadioButton("Hard");
        hardRadioButton.setActionCommand("hard");
        difficultyButtonGroup = new ButtonGroup();
        difficultyButtonGroup.add(easyRadioButton);
        difficultyButtonGroup.add(mediumRadioButton);
        difficultyButtonGroup.add(hardRadioButton);
        easyRadioButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                skillPoints = 20;
                diffLevel = 1;
                credits = 1000;
                diffStr = "Easy";
                updateSkillsAvail();
                updateCredits();
            }
        });
        mediumRadioButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                skillPoints = 16;
                diffLevel = 2;
                credits = 500;
                diffStr = "Medium";
                updateSkillsAvail();
                updateCredits();
            }
        });
        hardRadioButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                skillPoints = 12;
                diffLevel = 3;
                credits = 200;
                diffStr = "Hard";
                updateSkillsAvail();
                updateCredits();
            }
        });
        radioPanel = new JPanel();
        radioPanel.setLayout(null);
        radioPanel.add(easyRadioButton);
        radioPanel.add(mediumRadioButton);
        radioPanel.add(hardRadioButton);
        easyRadioButton.setBounds(0, 0, 60, 30);
        mediumRadioButton.setBounds(60, 0, 80, 30);
        hardRadioButton.setBounds(140, 0, 70, 30);
        difficultyPanel.add(radioPanel);
        difficultyPanel.add(pilot);
        difficultyPanel.add(tfPilot);
        difficultyPanel.add(fighter);
        difficultyPanel.add(tfFighter);
        difficultyPanel.add(merchant);
        difficultyPanel.add(tfMerchant);
        difficultyPanel.add(engineer);
        difficultyPanel.add(tfEngineer);
        difficultyPanel.add(creditsAvailable);
        southPanel = new JPanel();
        southPanel.setLayout(new GridLayout(1, 2));
        confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(e -> {
            int pilot = parseInt(tfPilot);
            int fighter = parseInt(tfFighter);
            int merchant = parseInt(tfMerchant);
            int engineer = parseInt(tfEngineer);
            int sum = pilot + fighter + merchant + engineer;
            if (tfName.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter your name!",
                        "Message", JOptionPane.ERROR_MESSAGE);
                throw new NullPointerException("Please enter your name!");
            }
            if ((diffLevel == 1 && sum > 20) || (diffLevel == 2 && sum > 16)
                    || (diffLevel == 3 && sum > 12)) {
                JOptionPane.showMessageDialog(null, "You don't have enough skill points!",
                        "Message", JOptionPane.ERROR_MESSAGE);
            } else if ((diffLevel == 1 && sum < 20) || (diffLevel == 2 && sum < 16)
                    || (diffLevel == 3 && sum < 12)) {
                JOptionPane.showMessageDialog(null, "Please use all your skill points!",
                        "Message", JOptionPane.ERROR_MESSAGE);
            } else {
                String name = tfName.getText();
                JLabel displayName = new JLabel();
                JLabel displaySkill = new JLabel();
                JLabel displayCredit = new JLabel();
                displayName.setText("name: " + name);
                displaySkill.setText("<html>Pilot Skill Points: " + pilot
                        + "<br>Fighter Skill Points:" + fighter
                        + "<br>Merchant Skill Points: " + merchant
                        + "<br>Engineer Skill Points: " + engineer + "</html>");
                displayCredit.setText("credits: " + credits);
                // set the possibility of encountering a bandit, policy, or a trader
                if (diffStr.equals("Hard")) {
                    gameController.getConfirmTravelUI().setBanditPossibility(0.75);
                    gameController.getConfirmTravelUI().setPolicyPossibility(0.75);
                    gameController.getConfirmTravelUI().setTraderPossibility(0.25);
                } else if (diffStr.equals("Medium")) {
                    gameController.getConfirmTravelUI().setBanditPossibility(0.5);
                    gameController.getConfirmTravelUI().setPolicyPossibility(0.5);
                    gameController.getConfirmTravelUI().setTraderPossibility(0.5);
                } else {
                    gameController.getConfirmTravelUI().setBanditPossibility(0.25);
                    gameController.getConfirmTravelUI().setPolicyPossibility(0.25);
                    gameController.getConfirmTravelUI().setTraderPossibility(0.75);
                }
                gameController.setPlayer(new Player(fighter, engineer, pilot, merchant, credits));
                gameController.getPlayer().setCurrentShip(new Ship("WOOD"));
                gameController.getConfigurationDisplayScreen().setInformation(displayName,
                        displaySkill,
                        displayCredit, new JLabel("Difficulty: " + diffStr));
                gameController.showConfigurationDisplayScreen();
            }
        });
        southPanel.add(confirmButton);
        southPanel.add(pointsAvailable);
        configurationScreenFrame.add(difficultyPanel, BorderLayout.CENTER);
        configurationScreenFrame.add(southPanel, BorderLayout.SOUTH);
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

    public void updateSkillsAvail() {
        int[] pointTotal = {parseInt(tfPilot), parseInt(tfFighter), parseInt(tfMerchant),
                parseInt(tfEngineer)};
        int sum = 0;
        for (int ea : pointTotal) {
            sum += ea;
        }
        int available = skillPoints - sum;
        pointsAvailable.setText("Skill points available: " + available);
    }

    public void updateCredits() {
        creditsAvailable.setText("Credits available: " + credits);
    }

    public void armTextFields() {
        tfEngineer.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                updateSkillsAvail();
            }

            public void removeUpdate(DocumentEvent e) {
                updateSkillsAvail();
            }

            public void insertUpdate(DocumentEvent e) {
                updateSkillsAvail();
            }
        });
        tfMerchant.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                updateSkillsAvail();
            }

            public void removeUpdate(DocumentEvent e) {
                updateSkillsAvail();
            }

            public void insertUpdate(DocumentEvent e) {
                updateSkillsAvail();
            }
        });
        tfFighter.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                updateSkillsAvail();
            }

            public void removeUpdate(DocumentEvent e) {
                updateSkillsAvail();
            }

            public void insertUpdate(DocumentEvent e) {
                updateSkillsAvail();
            }
        });
        tfPilot.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                updateSkillsAvail();
            }

            public void removeUpdate(DocumentEvent e) {
                updateSkillsAvail();
            }

            public void insertUpdate(DocumentEvent e) {
                updateSkillsAvail();
            }
        });
    }
}
