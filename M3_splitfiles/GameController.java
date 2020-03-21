import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameController {

    public WelcomeScreen welcomeScreen;
    public ConfigurationScreen configurationScreen;
    public ConfigurationDisplayScreen configurationDisplayScreen;
    public TravelUI travelUI;
    public LocationUI locationUI;
    private Player player;

    public GameController() {
        welcomeScreen = new WelcomeScreen(this);
        configurationScreen = new ConfigurationScreen(this);
        configurationDisplayScreen = new ConfigurationDisplayScreen(this);
        travelUI = new TravelUI(this);
        player = null;
    }

    public void showWelcomeScreen() {
        welcomeScreen.setVisible(true);
    }
    public void showConfigurationScreen() {
        welcomeScreen.setVisible(false);
        configurationScreen.setVisible(true);
    }
    public void showConfigurationDisplayScreen() {
        configurationScreen.setVisible(false);
        configurationDisplayScreen.setVisible(true);
    }
    public void showTravelUI() {
        configurationDisplayScreen.setVisible(false);
        locationUI.setVisible(false);
        travelUI.setVisible(true);
    }
    public void showLocationUI(MapRegion aPlanet) {
        configurationDisplayScreen.setVisible(false);
        locationUI = new LocationUI(this, aPlanet);
        travelUI.setVisible(false);
        locationUI.setVisible(true);
    }
    public Player getPlayer() {
        return player;
    }
    public void setPlayer(Player setNewPlayer) {
        player = setNewPlayer;
    }

    public static void main(String[] args) {
        GameController gameController = new GameController();
        gameController.showWelcomeScreen();
    }
}