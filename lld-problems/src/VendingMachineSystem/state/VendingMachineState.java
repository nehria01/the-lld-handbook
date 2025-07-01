package VendingMachineSystem.state;

import VendingMachineSystem.Coin;
import VendingMachineSystem.exception.VendingMachineException;

import java.util.List;

public interface VendingMachineState {
    void selectItem(String itemCode) throws VendingMachineException;;
    void insertCoin(Coin coin) throws VendingMachineException; ;
    void cancelTransaction() throws VendingMachineException;;
    void dispenseItem() throws VendingMachineException;;
    void dispenseCoin(List<Coin> coins) throws VendingMachineException;
}
