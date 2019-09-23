import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameController {

    public WelcomeScreen welcomeScreen;
    public ConfigurationScreen configurationScreen;
    public ConfigurationDisplayScreen configurationDisplayScreen;

    public GameController() {
        welcomeScreen = new WelcomeScreen(this);
        configurationScreen = new ConfigurationScreen(this);
        configurationDisplayScreen = new ConfigurationDisplayScreen(this);
    }

    public void showWelcomeScreen() {
        welcomeScreen.setVisible(true);
        configurationScreen.setVisible(false);
        configurationDisplayScreen.setVisible(false);
    }
    public void showConfigurationScreen() {
        welcomeScreen.setVisible(false);
        configurationScreen.setVisible(true);
        configurationDisplayScreen.setVisible(false);
    }
    public void showConfigurationDisplayScreen() {
        welcomeScreen.setVisible(false);
        configurationScreen.setVisible(false);
        configurationDisplayScreen.setVisible(true);
    }

    public static void main(String[] args) {
        GameController gameController = new GameController();
        gameController.showWelcomeScreen();
    }
}