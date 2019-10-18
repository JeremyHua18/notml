import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Ship {
    private ShipType shipType;
    private String shipName;
    private int cargoSpace;
    private int cargoSpaceRemaining;
    private int fuelCapacity;
    private int fuelCapacityRemaining;
    private int health;
    private int healthRemaining;
    private List<Integer> cargo;

    protected enum ShipType {
        WOOD("Wood",3,200,20), PLASTIC("Plastic",3,200,20), FIBERGLASS("Fiberglass",3,200,20), STEEL("Steel",3,200,20),
        CARBONFIBER("Carbonfiber",3,200,20), DIAMOND("Diamond",3,200,20);
        private String name;
        private int cargo;
        private int fuel;
        private int health;
        public String getName() {return name;}
        public int getCargo() {return cargo;}
        public int getFuel() {return fuel;}
        public int getHealth() {return health;}
        private ShipType(String name, int cargo, int fuel, int health) {
            this.name = name;
            this.cargo = cargo;
            this.fuel = fuel;
            this.health = health;
        }
    }

    public Ship(String wantedShipType) {
        cargo = new ArrayList<>(Collections.nCopies(Item.values().length, 0));
        ShipType shipType = ShipType.valueOf(wantedShipType);
        this.shipType = shipType;
        this.shipName = shipType.getName();
        this.cargoSpace = shipType.getCargo();
        this.cargoSpaceRemaining = shipType.getCargo();
        this.fuelCapacity = shipType.getFuel();
        this.fuelCapacityRemaining = shipType.getFuel();
        this.health = shipType.getHealth();
        this.healthRemaining = shipType.getHealth();
    }

    public void addCargo(Item item, int quantity) {
        int curr = this.cargo.get(item.getValue());
        cargo.set(item.getValue(), curr + quantity);
    }

    public void removeCargo(Item item, int quantity) {
        int curr = this.cargo.get(item.getValue());
        cargo.set(item.getValue(), curr - quantity);
    }

    public int countItemInCargo(Item item) {
        return this.cargo.get(item.getValue());
    }

    public List<Integer> getCargo() {
        return this.cargo;
    }

    public int getCargoSpace() {
        return this.cargoSpace;
    }

    public void setCargo(List<Integer> cargo) {
        this.cargo = cargo;
    }

    public int getCurrentCargo() {
        int sum = 0;
        for (int i : cargo) {
            sum += i;
        }
        return sum;
    }

    public ShipType getType() {
        return this.shipType;
    }









}