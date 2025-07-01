package VendingMachineSystem.state;

import VendingMachineSystem.Coin;
import VendingMachineSystem.machine.VendingMachine;
import VendingMachineSystem.exception.VendingMachineException;

import java.util.List;

public class HasCoinState implements VendingMachineState{
    private VendingMachine vendingMachine;

    public HasCoinState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void selectItem(String itemCode) throws VendingMachineException {
        throw new VendingMachineException("No product selected; cannot insert coin");
    }

    @Override
    public void insertCoin(Coin coin) throws VendingMachineException {
        System.out.println("You inserted coin " + coin);
        vendingMachine.addUserCoin(coin);
    }

    @Override
    public void cancelTransaction() throws VendingMachineException {
        System.out.println("Cancelling transaction please collect any deposited coins from the coin tray");
        dispenseCoin(vendingMachine.getUserCoins());
        vendingMachine.resetMachineState();
    }

    @Override
    public void dispenseItem() throws VendingMachineException {
        vendingMachine.setCurrentState(vendingMachine.getDispensingState());
        vendingMachine.getCurrentState().dispenseItem();
    }

    @Override
    public void dispenseCoin(List<Coin> coins) throws VendingMachineException {
        vendingMachine.removeCoinsFromInventory(coins);
        for(Coin coin : coins) {
            System.out.println("Dispensing Coin :: " + coin);
        }
    }
}
