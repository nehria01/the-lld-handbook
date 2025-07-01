package VendingMachineSystem.state;

import VendingMachineSystem.Coin;
import VendingMachineSystem.exception.VendingMachineException;

public interface VendingMachineState {
    void selectItem(String itemCode, int itemCount) throws VendingMachineException;;
    void insertCoin(Coin coin) throws VendingMachineException; ;
    void cancelTransaction() throws VendingMachineException;;
    void dispenseItem() throws VendingMachineException;;
    void dispenseCoin() throws VendingMachineException;
}
