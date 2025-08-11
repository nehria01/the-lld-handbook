package VendingMachine.state;

import VendingMachine.enums.Coin;

public interface VendingMachineState {
    void insertCoin(Coin coin);
    void chooseProduct(String productId);
    void dispense(Coin coin, String productId);
}
