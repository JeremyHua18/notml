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

    private int FighterSkill, EngineerSkill, MerchantSkill, PilotSkill, credit;

    //private Point location = new Point()

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public int getCredit() {
        return credit;
    }

    public void setFighterSkill(int FighterSkill) {
        this.FighterSkill = FighterSkill;
    }

    public int getFighterSkill() {
        return FighterSkill;
    }

    public void setEngineerSkill(int EngineerSkill) {
        this.EngineerSkill = EngineerSkill;
    }

    public int getEngineerSkill() {
        return EngineerSkill;
    }

    public void setMerchantSkill(int MerchantSkill) {
        this.MerchantSkill = MerchantSkill;
    }

    public int getMerchantSkill() {
        return MerchantSkill;
    }

    public void setPilotSkill(int PilotSkill) {
        this.PilotSkill = PilotSkill;
    }

    public int getPilotSkill() {
        return PilotSkill;
    }
}
