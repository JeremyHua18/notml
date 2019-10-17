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
    private Ship ship;

    public Player(int fighterSkill, int engineerSkill,
            int merchantSkill, int pilotSkill, int credit, Ship ship) {
        this.fighterSkill = fighterSkill;
        this.engineerSkill = engineerSkill;
        this.merchantSkill = merchantSkill;
        this.pilotSkill = pilotSkill;
        this.justStarted = true;
        this.credit = credit;
        this.ship = ship;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public int getCredit() {
        return credit;
    }

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
}
