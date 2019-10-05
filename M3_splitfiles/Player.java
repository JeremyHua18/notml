import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.awt.Point;
/*
Player should have int attributes for each of the four skills from M3,
and assign them according to the player’s selections during game configuration
An attribute for current Region where the Player is located (in MapRegion)
An attribute for the player’s current number of credits
Getter/Setter methods for these attributes
 */

public class Player{

    private int fighterSkill, engineerSkill, merchantSkill, pilotSkill, credit;
    private boolean justStarted;
    //private Point location = new Point()
    public Player(int fighterSkill, int engineerSkill
            , int merchantSkill, int pilotSkill, int credit) {
        this.fighterSkill = fighterSkill;
        this.engineerSkill = engineerSkill;
        this.merchantSkill = merchantSkill;
        this.pilotSkill = pilotSkill;
        this.justStarted = true;
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
