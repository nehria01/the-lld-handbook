package VendingMachineSystem;

public enum Coin {
    ONE(1),
    FIVE(5),
    TEN(10),
    TWENTY(20);

    private final int value;

    Coin(int val) {
        this.value = val;
    }

    public int getValue() {
        return value;
    }
}
