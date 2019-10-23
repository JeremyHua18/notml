public class GameController {

    private WelcomeScreen welcomeScreen;
    private ConfigurationScreen configurationScreen;
    private ConfigurationDisplayScreen configurationDisplayScreen;
    private TravelUI travelUI;
    private LocationUI locationUI;
    private Player player;
    private ConfirmTravelUI confirmTravelUI;

    private MarketUI marketUI;

    public GameController() {
        setWelcomeScreen(new WelcomeScreen(this));
        setConfigurationScreen(new ConfigurationScreen(this));
        setConfigurationDisplayScreen(new ConfigurationDisplayScreen(this));
        setTravelUI(new TravelUI(this));
        setConfirmTravelUI(null);
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
        confirmTravelUI.setVisible(false);
        //marketUI.setVisible(false);
    }

    public void showLocationUI(MapRegion aPlanet) {
        getConfigurationDisplayScreen().setVisible(false);
        setLocationUI(new LocationUI(this, aPlanet));
        getTravelUI().setVisible(false);
        getLocationUI().setVisible(true);
        confirmTravelUI.setVisible(false);
        marketUI.setVisible(false);
    }

    //overloads method to use player's planet rather than passing a planet from button listener
    public void showLocationUI() {
        configurationDisplayScreen.setVisible(false);
        //setLocationUI(new LocationUI(this, player.getCurrentLocation()));
        travelUI.setVisible(false);
        locationUI.setVisible(true);
        confirmTravelUI.setVisible(false);
        marketUI.setVisible(false);
    }

    //only use this one if you need to create a NEW travelUI (i.e. this will make
    //lots of separate travelUI screens otherwise)
    public void showConfirmTravelUI(MapRegion desiredPlanet) {
        confirmTravelUI = new ConfirmTravelUI("Confirm Travel", this, desiredPlanet);
        confirmTravelUI.setVisible(true);
    }

    public void showMarketUI(MapRegion mapRegion) {
        marketUI = new MarketUI(this, mapRegion);
        marketUI.setVisible(true);
        locationUI.setVisible(false);
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

    public ConfirmTravelUI getConfirmTravelUI() {
        return confirmTravelUI;
    }

    public void setConfirmTravelUI(ConfirmTravelUI confirmTravelUI) {
        this.confirmTravelUI = confirmTravelUI;
    }

    public MarketUI getMarketUI() {
        return marketUI;
    }

    public void setMarketUI(MarketUI marketUI) {
        this.marketUI = marketUI;
    }
}