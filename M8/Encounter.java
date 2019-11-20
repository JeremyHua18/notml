public class Encounter {
    private GameController gameController;
    private MapRegion previousRegion;
    private MapRegion desiredRegion;

    public Encounter(GameController gameController, MapRegion previousRegion,
                     MapRegion desiredRegion) {
        this.gameController = gameController;
        this.previousRegion = previousRegion;
        this.desiredRegion = desiredRegion;
        if (gameController.getPlayer().getBanditPossibility() > Math.random()) {
            gameController.setNpcOpen(true);
            launchBanditUI();
        } else if (gameController.getPlayer().getPolicePossibility() > Math.random()
                && gameController.getPlayer().getShip().getCargoSpaceRemaining()
                != gameController.getPlayer().getShip().getCargoSpace()) {
            gameController.setNpcOpen(true);
            launchPoliceUI();

        } else if (gameController.getPlayer().getTraderPossibility() > Math.random()) {
            gameController.setNpcOpen(true);
            launchTraderUI();
        } else {
            launchNormal();
        }
    }

    public void launchBanditUI() {
        new BanditUI(gameController, previousRegion, desiredRegion);
    }

    public void launchPoliceUI() {
        new PoliceUI(gameController, previousRegion, desiredRegion);
    }

    public void launchTraderUI() {
        new TraderUI(gameController, previousRegion, desiredRegion);
    }

    public void launchNormal() {
        gameController.getPlayer().setCurrentLocation(desiredRegion);
        gameController.getLocationUI().configure(desiredRegion);
        gameController.showLocationUI();
    }

}
