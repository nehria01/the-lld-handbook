package VendingMachineSystem;

public enum Coin {
    PENNY(1),
    NICKEL(5),
    QUARTER(25);

    private final int value;

    Coin(int val) {
        this.value = val;
    }

    public int getValue() {
        return value;
    }
}
