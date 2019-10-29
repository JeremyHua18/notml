public class BuyItemCommand{

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

    public void doIt() {
        int newBalance = thePlayer.getCredit() - theQuantity * theMarketplace.getBuyPrice(theItem);
        theMarketplace.buy(theItem, theQuantity);
        thePlayer.getShip().addCargo(theItem, theQuantity);
        thePlayer.setCredit(newBalance);
    }
}
