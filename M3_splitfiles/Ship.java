public class Ship {
    private ShipType shipType;
    private String shipName;
    private int cargoSpace;
    private int cargoSpaceRemaining;
    private int fuelCapacity;
    private int fuelCapacityRemaining;
    private int health;
    private int healthRemaining;

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

}