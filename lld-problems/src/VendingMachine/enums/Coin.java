package VendingMachine.enums;

public enum Coin {
    TEN(10),
    TWENTY(20),
    FIFTY(50),
    HUNDRED(100);

    private int val;

    Coin(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public static Coin fromValue(int value) {
        for (Coin c : Coin.values()) {
            if (c.val == value) return c;
        }
        throw new IllegalArgumentException("Invalid coin value: " + value);
    }
}
