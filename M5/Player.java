/*
Player should have int attributes for each of the four skills from M3,
and assign them according to the player’s selections during game configuration
An attribute for current Region where the Player is located (in MapRegion)
An attribute for the player’s current number of credits
Getter/Setter methods for these attributes
 */

public class Player {

    private int fighterSkill;
    private int engineerSkill;
    private int merchantSkill;
    private int pilotSkill;
    private int credit;
    private boolean justStarted;
    private MapRegion currentLocation;
    private boolean hasTraveled;
    private Ship currentShip;

    public Ship getCurrentShip() {
        return currentShip;
    }

    public void setCurrentShip(Ship currentShip) {
        this.currentShip = currentShip;
    }


    public Player(int fighterSkill, int engineerSkill,
            int merchantSkill, int pilotSkill, int credit) {
        this.fighterSkill = fighterSkill;
        this.engineerSkill = engineerSkill;
        this.merchantSkill = merchantSkill;
        this.pilotSkill = pilotSkill;
        this.credit = credit;
        this.justStarted = true;
        this.hasTraveled = false;
        this.currentLocation = null;
        this.currentShip = null;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public int getCredit() {
        return credit;
    }

    public Ship getShip() {return currentShip;}

    public void setFighterSkill(int fighterSkill) {
        this.fighterSkill = fighterSkill;
    }

    public int getFighterSkill() {
        return fighterSkill;
    }

    public void setEngineerSkill(int engineerSkill) {
        this.engineerSkill = engineerSkill;
    }

    public int getEngineerSkill() {
        return engineerSkill;
    }

    public void setMerchantSkill(int merchantSkill) {
        this.merchantSkill = merchantSkill;
    }

    public int getMerchantSkill() {
        return merchantSkill;
    }

    public void setPilotSkill(int pilotSkill) {
        this.pilotSkill = pilotSkill;
    }

    public int getPilotSkill() {
        return pilotSkill;
    }

    public boolean isJustStarted() {
        return justStarted;
    }

    public void setJustStarted(boolean started) {
        this.justStarted = started;
    }

    public MapRegion getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(MapRegion currentLocation) {
        this.currentLocation = currentLocation;
    }

    public int calcTravelCosts(MapRegion newRegion) {
        int distX = currentLocation.getxCoordinate() - newRegion.getxCoordinate();
        int distY = currentLocation.getyCoordinate() - newRegion.getyCoordinate();
        double totalDist = (Math.sqrt(Math.pow(distX, 2) + Math.pow(distY, 2)));
        return (int) (totalDist * (1 - (pilotSkill / 20.0)));
    }

    public boolean hasTraveled() {
        return hasTraveled;
    }

    public void setHasTraveled(boolean travelValue) {
        this.hasTraveled = travelValue;
    }
}
