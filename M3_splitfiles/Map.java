import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.Random;

public class Map {
    private MapRegion sunRegion, mercuryRegion, venusRegion, earthRegion, marsRegion;
    private MapRegion jupiterRegion, saturnRegion, uranusRegion, neptuneRegion, plutoRegion;
    private MapRegion[] mapRegionArray = {sunRegion, mercuryRegion, venusRegion, earthRegion, marsRegion,
            jupiterRegion, saturnRegion, uranusRegion, neptuneRegion, plutoRegion};

    public Map() {
        this.sunRegion = new MapRegion("Sun");
        this.mercuryRegion = new MapRegion("Mercury");
        this.venusRegion = new MapRegion("Venus");
        this.earthRegion = new MapRegion("Earth");
        this.marsRegion = new MapRegion("Mars");
        this.jupiterRegion = new MapRegion("Jupiter");
        this.saturnRegion = new MapRegion("Saturn");
        this.uranusRegion = new MapRegion("Uranus");
        this.neptuneRegion = new MapRegion("Neptune");
        this.plutoRegion = new MapRegion("Pluto");
        for (MapRegion mapRegion:mapRegionArray) {
            generateRandomCoordinates(mapRegion);
        }
    }

    public void generateRandomCoordinates(MapRegion mapRegion) { // THIS NEEDS WORK
        Random random = new Random();
        int randomxValue, randomyValue;
        boolean enoughxDistanceSatisfied = false;
        boolean enoughyDistanceSatisfied = false;
        ArrayList<Integer> xCoordinatesArray = new ArrayList<>(10);
        ArrayList<Integer> yCoordinatesArray = new ArrayList<>(10);
        while (!(enoughxDistanceSatisfied && enoughyDistanceSatisfied)) {
            randomxValue = random.nextInt(400) - 200;
            randomyValue = random.nextInt(400) - 200;
            for (Integer element:xCoordinatesArray) {
                if (java.lang.Math.abs(element - randomxValue) <= 5) {
                    enoughxDistanceSatisfied = false;
                    break;
                }
            }
            for (Integer element:yCoordinatesArray) {
                if (java.lang.Math.abs(element - randomyValue) <= 5) {
                    enoughyDistanceSatisfied = false;
                    break;
                }
            }
        }
        mapRegion.setCoordinates(randomxValue, randomyValue);
    }

}