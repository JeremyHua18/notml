import java.util.HashMap;

public class Market {
    //keep track
    private HashMap<Item, Integer> availableItems;
    private HashMap<Item, Integer> priceList;
    private MapRegion planet;
    private GameController gc;
    private final double depreciation = 0.8;


    public Market(MapRegion planet, GameController gc) {
        this.planet = planet;
        availableItems = planet.getAvailableItems();
        priceList = new HashMap<>();
        for (Item value : Item.values()) {
            priceList.put(value, getPrice(value));
        }
        this.gc = gc;
    }

    private int getPrice(Item item) {
        int price = item.getBasePrice();
        price += (this.planet.getTechLevel().getTechValue() - item.getMaxProduceTech())
                * item.getIncreasePerLevel();
        //keep prices from being negative
        if (price <= 10) {
            price = 10;
        }
        return price;
    }

    public int getBuyPrice(Item item) {
        int cost = priceList.get(item);
        cost -= (gc.getPlayer().getMerchantSkill() * 10);
        if (cost <= 10) {
            cost = 10;
        }
        return cost;
    }

    public int getSellPrice(Item item) {
        //item is depreciated by 0.8, but this is mitigated by merchant skill up to 120% of
        //base price in this region
        //int cost = (int)(priceList.get(item)
        //        * (depreciation + 0.02 * gc.getPlayer().getMerchantSkill()));
        int basePrice = priceList.get(item);
        double adjustedSellPrice = basePrice * 0.7;
        adjustedSellPrice = adjustedSellPrice * (1 + gc.getPlayer().getMerchantSkill() * 0.02);
        return (int) adjustedSellPrice;
    }

    public boolean isBuyable(Item item) {
        return this.planet.getTechLevel().getTechValue() >= item.getMinProduceTech()
                && availableItems.get(item) > 0;
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