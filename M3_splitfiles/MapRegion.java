import javax.print.DocFlavor;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.Random;

public class MapRegion {
    private int xCoordinate, yCoordinate;
    private TechLevel techLevel;
    private String regionName;

    public MapRegion(String regionName) {
        setRandomTechLevel();
        this.regionName = regionName;
        this.setRandomTechLevel();
    }

    public int getxCoordinate() {
        return this.xCoordinate;
    }

    public int getyCoordinate() {
        return this.yCoordinate;
    }

    public String getRegionName() {
        return regionName;
    }

    public TechLevel getTechLevel() {
        return techLevel;
    }

    public void setCoordinates(int x, int y) {
        this.xCoordinate = x;
        this.yCoordinate = y;
    }

    private enum TechLevel {
        PREAG, AGRICULTURE, MEDIEVAL, RENAISSANCE, INDUSTRIAL, MODERN, FUTURISTIC;
    }

    private void setRandomTechLevel() {
        TechLevel[] array = {TechLevel.PREAG, TechLevel.AGRICULTURE, TechLevel.MEDIEVAL,
                TechLevel.RENAISSANCE, TechLevel.INDUSTRIAL, TechLevel.MODERN, TechLevel.FUTURISTIC};
        Random random = new Random();
        int randomNumber = random.nextInt(7);
        this.techLevel = array[randomNumber];
    }



}