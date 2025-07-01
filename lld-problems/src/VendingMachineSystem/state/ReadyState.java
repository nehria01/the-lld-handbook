package VendingMachineSystem.state;

import VendingMachineSystem.Coin;
import VendingMachineSystem.machine.VendingMachine;
import VendingMachineSystem.exception.VendingMachineException;

import java.util.List;

public class ReadyState implements VendingMachineState{
    private VendingMachine vendingMachine;

    public ReadyState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void selectItem(String itemCode) throws VendingMachineException {
        boolean reserveStock = vendingMachine.getInventory().reserveStock(itemCode);
        if(!reserveStock)
            throw new VendingMachineException("Product out of stock !");
        vendingMachine.setSelectedItemCode(itemCode);
        vendingMachine.setCurrentState(vendingMachine.getHasCoinState());
        System.out.println("You selected item :: " + vendingMachine.getInventory().getProduct(itemCode).getName());
    }

    @Override
    public void insertCoin(Coin coin) throws VendingMachineException {
        throw new VendingMachineException("No product selected; cannot insert coin");
    }

    @Override
    public void cancelTransaction() throws VendingMachineException {
        throw new VendingMachineException("No product selected; cannot cancel transaction");
    }

    @Override
    public void dispenseItem() throws VendingMachineException {
        throw new VendingMachineException("No product selected; cannot dispense product");
    }

    @Override
    public void dispenseCoin(List<Coin> coins) throws VendingMachineException {
        throw new VendingMachineException("No coins to be dispensed");
    }
}
