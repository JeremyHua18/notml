import javax.swing.*;

public class WelcomeController {
    WelcomeScreen welcomeScreen;
    EnterStats statsScreen;
    StatsConfirmed confirmScreen;
    public WelcomeController() {
        welcomeScreen = new WelcomeScreen("SpaceTrader", this);
        statsScreen = new EnterStats("SpaceTrader", this);
        confirmScreen = new StatsConfirmed("SpaceTrader", this, statsScreen);
    }
    public void showWelcome() {
        welcomeScreen.setVisible(true);
        statsScreen.setVisible(false);
        confirmScreen.setVisible(false);
    }
    public void showStats() {
        welcomeScreen.setVisible(false);
        statsScreen.setVisible(true);
        confirmScreen.setVisible(false);
    }
    public void showConfirmed() {
        welcomeScreen.setVisible(false);
        statsScreen.setVisible(false);
        confirmScreen.setVisible(true);
    }

    public static void main(String[] args) {
        WelcomeController wc = new WelcomeController();
        wc.showWelcome();
    }
}
