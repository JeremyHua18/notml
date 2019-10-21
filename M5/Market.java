import java.util.HashMap;
import java.util.Random;

public class Market {
    //keep track
    private HashMap<Item, Integer> availableItems;
    private HashMap<Item, Integer> priceList;
    private MapRegion planet;
    private final double depreciation = 0.8;


    public Market(MapRegion planet) {
        this.planet = planet;
        availableItems = planet.getAvailableItems();
        priceList = new HashMap<>();
        for (Item value : Item.values()) {
            priceList.put(value, getPrice(value));
        }
    }

    private int getPrice(Item item) {
        int price = item.getBasePrice();
        price += (this.planet.getTechLevel().getTechValue() - item.getMaxProduceTech())
                * item.getIncreasePerLevel();
        return price;
    }

    public int getBuyPrice(Item item) {
        int cost = priceList.get(item);
        return cost;
    }

    public int getSellPrice(Item item) {
        int cost = (int)(getBuyPrice(item) * depreciation);
        return cost;
    }

    public boolean isBuyable(Item item) {
        return this.planet.getTechLevel().getTechValue() >= item.getMinProduceTech() &&
                availableItems.get(item) > 0;
    }

    public boolean isSellable(Item item) {
        return this.planet.getTechLevel().getTechValue() >= item.getMinUseTech();
    }

    public int getQuantity(Item item) {
        if (availableItems.containsKey(item)) {
            return availableItems.get(item);
        } else {
            return 0;
        }
    }

    public int buy(Item item, int quantity) {
        availableItems.put(item, availableItems.get(item) - quantity);
        return getBuyPrice(item) * quantity;
    }

    public int sell(Item item, int quantity) {
        if (!availableItems.containsKey(item)) {
            availableItems.put(item, 0);
        }
        availableItems.put(item, availableItems.get(item) + quantity);
        return getSellPrice(item) * quantity;
    }
}
