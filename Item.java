import java.util.HashMap;
import java.util.Map;

public enum Item {
    WATER(0, "Water", 0, 0, 2, 30, 3),
    FRUIT(1, "FRUIT", 0, 0, 0, 250, 10),
    FOOD(2, "Food", 1, 0, 1, 100, 50),
    TOOL(3, "Tool", 2, 2, 3, 350, 20),
    DOG(4, "Dog", 3, 1, 6, 250, 10),
    WEAPON(5, "Weapons", 3, 1, 5, 1250, -75),
    MEDICINE(6, "Medicine", 4, 1, 6, 650, -20),
    MACHINES(7, "Machines", 4, 3, 5, 900, -30),
    CAT(8, "Cat", 5, 0, 5, 3500, 125),
    ROBOTS(9, "Robots", 6, 4, 7, 5000, -150);

    private final int key;
    private final String name;
    //Minimum Tech Level to Produce this : can't buy on planets below this level)
    private final int minProduceTech;
    //Minimum Tech Level to use: can't sell on planets below this level)
    private final int minUseTech;
    //Tech Level that produces the most
    private final int maxProduceTech;
    private final int basePrice;
    //Price increase per tech level
    private final int increasePerLevel;

    private Item(int key, String name, int minProduceTech, int minUseTech, int maxProduceTech, int base,
                 int increasePerLevel) {
        this.key = key;
        this.name = name;
        this.minProduceTech = minProduceTech;
        this.minUseTech = minUseTech;
        this.maxProduceTech = maxProduceTech;
        this.basePrice = base;
        this.increasePerLevel = increasePerLevel;
    }



    public int getValue() {
        return key;
    }
    public String getName() {
        return name;
    }
    public int getKey() {
        return key;
    }
    public int getMinProduceTech() {
        return minProduceTech;
    }
    public int getMinUseTech() {
        return minUseTech;
    }
    public int getMaxProduceTech() {
        return maxProduceTech;
    }
    public int getBasePrice() {
        return basePrice;
    }
    public int getIncreasePerLevel() {
        return increasePerLevel;
    }
}
