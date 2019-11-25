import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndGameUI {
    private JFrame endGameFrame;
    private JButton restartButton, leaveButton;
    private GameController gameController;
    private JLabel loseMessage, winMessage;
    private JPanel messagePanel;
    private boolean winFlag;

    public EndGameUI(boolean winFlag, GameController gameController) {
        this.winFlag = winFlag;
        this.gameController = gameController;
        configure(winFlag);
    }

    public void setVisible(boolean b) { endGameFrame.setVisible(b); }

    public void configure(boolean winFlag){
        endGameFrame = new JFrame("end");
        endGameFrame.setSize(400, 400);
        endGameFrame.setLayout(null);
        loseMessage = new JLabel("Sorry. You lose the game!");
        winMessage = new JLabel("Congratulations! You win the game!");
        messagePanel = new JPanel(new GridLayout(1, 1));
        if (winFlag) {
            messagePanel.add(winMessage);
        } else {
            messagePanel.add(loseMessage);
        }
        restartButton = new JButton("restart");
        restartButton.setBounds(150, 200, 100, 30);
        leaveButton = new JButton("leave");
        leaveButton.setBounds(150,250,100,30);
        messagePanel.setBounds(20,20,300,300);
        endGameFrame.add(leaveButton);
        endGameFrame.add(restartButton);
        endGameFrame.add(messagePanel);


        leaveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        restartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameController.setPlayer(null);
                gameController = new GameController();
                endGameFrame.setVisible(false);
                gameController.showWelcomeScreen();
                gameController.showConfigurationScreen();
            }
        });


        endGameFrame.setLocationRelativeTo(null);

    }
}