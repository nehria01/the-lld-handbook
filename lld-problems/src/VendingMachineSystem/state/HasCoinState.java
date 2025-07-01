package VendingMachineSystem.state;

import VendingMachineSystem.Coin;
import VendingMachineSystem.VendingMachine;
import VendingMachineSystem.exception.VendingMachineException;

public class HasCoinState implements VendingMachineState{
    private VendingMachine vendingMachine;

    public HasCoinState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void selectItem(String itemCode, int itemCount) throws VendingMachineException {
        throw new VendingMachineException("No product selected; cannot insert coin");
    }

    @Override
    public void insertCoin(Coin coin) throws VendingMachineException {
        System.out.println("You inserted coin " + coin);
        vendingMachine.addUserCoin(coin);
        vendingMachine.setCurrentState(vendingMachine.getDispensingState());
    }

    @Override
    public void cancelTransaction() throws VendingMachineException {
        System.out.println("Cancelling transaction please collect any deposited coins from the coin tray");
        dispenseCoin();
        vendingMachine.resetMachineState();
    }

    @Override
    public void dispenseItem() throws VendingMachineException {
        throw new VendingMachineException("No product selected; cannot insert coin");
    }

    @Override
    public void dispenseCoin() throws VendingMachineException {
        for(Coin coin : vendingMachine.getUserCoinsList())
            System.out.println("Dispensing Coin :: " + coin);

    }
}
