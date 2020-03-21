public class GameController {

    private WelcomeScreen welcomeScreen;
    private ConfigurationScreen configurationScreen;
    private ConfigurationDisplayScreen configurationDisplayScreen;
    private TravelUI travelUI;
    private LocationUI locationUI;
    private Player player;

    public GameController() {
        setWelcomeScreen(new WelcomeScreen(this));
        setConfigurationScreen(new ConfigurationScreen(this));
        setConfigurationDisplayScreen(new ConfigurationDisplayScreen(this));
        setTravelUI(new TravelUI(this));
        player = null;
    }

    public void showWelcomeScreen() {
        getWelcomeScreen().setVisible(true);
    }

    public void showConfigurationScreen() {
        getWelcomeScreen().setVisible(false);
        getConfigurationScreen().setVisible(true);
    }

    public void showConfigurationDisplayScreen() {
        getConfigurationScreen().setVisible(false);
        getConfigurationDisplayScreen().setVisible(true);
    }

    public void showTravelUI() {
        getConfigurationDisplayScreen().setVisible(false);
        getLocationUI().setVisible(false);
        getTravelUI().setVisible(true);
    }

    public void showLocationUI(MapRegion aPlanet) {
        getConfigurationDisplayScreen().setVisible(false);
        setLocationUI(new LocationUI(this, aPlanet));
        getTravelUI().setVisible(false);
        getLocationUI().setVisible(true);
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

    public WelcomeScreen getWelcomeScreen() {
        return welcomeScreen;
    }

    public void setWelcomeScreen(WelcomeScreen welcomeScreen) {
        this.welcomeScreen = welcomeScreen;
    }

    public ConfigurationScreen getConfigurationScreen() {
        return configurationScreen;
    }

    public void setConfigurationScreen(ConfigurationScreen configurationScreen) {
        this.configurationScreen = configurationScreen;
    }

    public ConfigurationDisplayScreen getConfigurationDisplayScreen() {
        return configurationDisplayScreen;
    }

    public void setConfigurationDisplayScreen(
            ConfigurationDisplayScreen configurationDisplayScreen) {
        this.configurationDisplayScreen = configurationDisplayScreen;
    }

    public TravelUI getTravelUI() {
        return travelUI;
    }

    public void setTravelUI(TravelUI travelUI) {
        this.travelUI = travelUI;
    }

    public LocationUI getLocationUI() {
        return locationUI;
    }

    public void setLocationUI(LocationUI locationUI) {
        this.locationUI = locationUI;
    }
}