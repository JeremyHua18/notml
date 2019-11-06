public class BuyItemCommand {

    private Player thePlayer;
    private Market theMarketplace;
    private Item theItem;
    private int theQuantity;

    public BuyItemCommand(Player p, Market m, Item i, int q) {
        thePlayer = p;
        theMarketplace = m;
        theItem = i;
        theQuantity = q;
        doIt();
    }

    public BuyItemCommand(Player p, Item i, int q) {
        thePlayer = p;
        theItem = i;
        theQuantity = q;
        doItWithoutMarket();
    }

    public void doIt() {
        int newBalance = thePlayer.getCredit() - theQuantity * theMarketplace.getBuyPrice(theItem);
        theMarketplace.buy(theItem, theQuantity);
        thePlayer.getShip().addCargo(theItem, theQuantity);
        thePlayer.setCredit(newBalance);
    }

    public void doItWithoutMarket() {
        thePlayer.getShip().addCargo(theItem, theQuantity);
        //player gets 10% discount for buying in bulk
        thePlayer.setCredit(thePlayer.getCredit()
                - (int) (theItem.getBasePrice() * theQuantity * 0.9));
    }
}
