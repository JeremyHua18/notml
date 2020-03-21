import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.Random;
import java.util.ArrayList;

public class Map {
    private ArrayList<MapRegion> mapRegionArray;
    private ArrayList<Integer> xCoorArray;
    private ArrayList<Integer> yCoorArray;
    private GameController gc;

    public Map(GameController gc) {
        this.gc = gc;
        MapRegion sunRegion = new MapRegion("Sun", gc);
        MapRegion mercuryRegion = new MapRegion("Mercury", gc);
        MapRegion venusRegion = new MapRegion("Venus", gc);
        MapRegion earthRegion = new MapRegion("Earth", gc);
        MapRegion marsRegion = new MapRegion("Mars", gc);
        MapRegion jupiterRegion = new MapRegion("Jupiter", gc);
        MapRegion saturnRegion = new MapRegion("Saturn", gc);
        MapRegion uranusRegion = new MapRegion("Uranus", gc);
        MapRegion neptuneRegion = new MapRegion("Neptune", gc);
        MapRegion plutoRegion = new MapRegion("Pluto", gc);
        mapRegionArray = new ArrayList<>();
        mapRegionArray.add(sunRegion);
        mapRegionArray.add(mercuryRegion);
        mapRegionArray.add(venusRegion);
        mapRegionArray.add(earthRegion);
        mapRegionArray.add(marsRegion);
        mapRegionArray.add(jupiterRegion);
        mapRegionArray.add(saturnRegion);
        mapRegionArray.add(uranusRegion);
        mapRegionArray.add(neptuneRegion);
        mapRegionArray.add(plutoRegion);
        xCoorArray = new ArrayList<>(10);
        yCoorArray = new ArrayList<>(10);

        for (MapRegion mapRegion : mapRegionArray) {
            generateRandomCoordinates(mapRegion);
        }
    }

    public ArrayList<MapRegion> getMapRegionArray() {
        return mapRegionArray;
    }


    public void generateRandomCoordinates(MapRegion mapRegion) {
        Random random = new Random();
        int randomxValue;
        int randomyValue;
        boolean enoughxDistanceSatisfied = true;
        boolean enoughyDistanceSatisfied = true;

        do {
            enoughxDistanceSatisfied = true;
            randomxValue = random.nextInt(400) - 200;
            for (int xCoor : xCoorArray) {
                if (Math.abs(xCoor - randomxValue) <= 5) {
                    enoughxDistanceSatisfied = false;
                }
            }

        } while (!enoughxDistanceSatisfied);

        do {
            enoughyDistanceSatisfied = true;
            randomyValue = random.nextInt(400) - 200;
            for (int yCoor : yCoorArray) {
                if (Math.abs(yCoor - randomyValue) <= 5) {
                    enoughyDistanceSatisfied = false;
                }
            }
        } while (!enoughyDistanceSatisfied);

        mapRegion.setCoordinates(randomxValue, randomyValue);

        xCoorArray.add(randomxValue);
        yCoorArray.add(randomyValue);
    }

}