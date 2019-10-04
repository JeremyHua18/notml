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

    public Map() {
        MapRegion sunRegion = new MapRegion("Sun");
        MapRegion mercuryRegion = new MapRegion("Mercury");
        MapRegion venusRegion = new MapRegion("Venus");
        MapRegion earthRegion = new MapRegion("Earth");
        MapRegion marsRegion = new MapRegion("Mars");
        MapRegion jupiterRegion = new MapRegion("Jupiter");
        MapRegion saturnRegion = new MapRegion("Saturn");
        MapRegion uranusRegion = new MapRegion("Uranus");
        MapRegion neptuneRegion = new MapRegion("Neptune");
        MapRegion plutoRegion = new MapRegion("Pluto");
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

        for (MapRegion mapRegion:mapRegionArray) {
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

        mapRegion.setCoordinates(randomxValue,randomyValue);

        xCoorArray.add(randomxValue);
        yCoorArray.add(randomyValue);
    }

}