import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameController {

    public WelcomeScreen welcomeScreen;
    public ConfigurationScreen configurationScreen;
    public ConfigurationDisplayScreen configurationDisplayScreen;
    public TravelUI travelUI;
    public LocationUI locationUI;

    public GameController() {
        welcomeScreen = new WelcomeScreen(this);
        configurationScreen = new ConfigurationScreen(this);
        configurationDisplayScreen = new ConfigurationDisplayScreen(this);
        travelUI = new TravelUI(this);
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
        locationUI = new LocationUI(this, aPlanet);
        travelUI.setVisible(false);
        locationUI.setVisible(true);
    }

    public static void main(String[] args) {
        GameController gameController = new GameController();
        gameController.showWelcomeScreen();
    }
}