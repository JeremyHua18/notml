import java.util.HashMap;
import java.util.Random;
import java.util.HashMap;
import java.util.Map;

public class MapRegion {
    private int xCoordinate;
    private int yCoordinate;
    private TechLevel techLevel;
    private String regionName;
    private HashMap<Item, Integer> availableItems;
    private  Market market;

    final int AMOUNT[] = {10, 10, 10, 10, 10, 10, 10, 10, 10, 10};

    public MapRegion(String regionName) {
        setRandomTechLevel();
        this.regionName = regionName;
        this.setRandomTechLevel();
        availableItems = new HashMap<>();
        for (int i = 0; i <AMOUNT.length; i++) {
            availableItems.put(Item.values()[i], AMOUNT[i]);
        }
        market = new Market(this);
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

    protected enum TechLevel {
        PREAG(0, "Preag"),
        AGRICULTURE(1, "Agricultural"),
        MEDIEVAL(2, "Medieval"),
        RENAISSANCE(3, "Renaissance"),
        INDUSTRIAL(5, "Industrial"),
        MODERN(6, "Modern"),
        FUTURISTIC(7, "Futuristic");

        private int techValue;
        private String text;

        TechLevel(int techValue, String text) {
            this.techValue = techValue;
            this.text = text;
        }

        public String toString() {
            return this.text;
        }

        public int getTechValue() {
            return this.techValue;
        }
    }

    private void setRandomTechLevel() {
        TechLevel[] array = {TechLevel.PREAG, TechLevel.AGRICULTURE, TechLevel.MEDIEVAL,
                TechLevel.RENAISSANCE, TechLevel.INDUSTRIAL, TechLevel.MODERN,
                TechLevel.FUTURISTIC};
        Random random = new Random();
        int randomNumber = random.nextInt(7);
        this.techLevel = array[randomNumber];
    }

    public HashMap<Item, Integer> getAvailableItems() {return availableItems; }

    public void setAvailableItems(HashMap<Item, Integer> availableItems) { this.availableItems = availableItems; }

    public Market getMarket() {
        return market;
    }

    public void setMarket(Market newMarket) {
        market = newMarket;
    }
}