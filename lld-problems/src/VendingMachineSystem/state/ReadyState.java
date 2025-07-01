package VendingMachineSystem.state;

import VendingMachineSystem.Coin;
import VendingMachineSystem.VendingMachine;
import VendingMachineSystem.exception.VendingMachineException;

public class IdleState implements VendingMachineState{
    private VendingMachine vendingMachine;

    public IdleState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void selectItem(String itemCode, int itemCount) throws VendingMachineException {
        boolean reserveStock = vendingMachine.getInventory().reserveStock(itemCode, itemCount);
        if(!reserveStock)
            throw new VendingMachineException("Product out of stock !");
        vendingMachine.setSelectedItemCode(itemCode);
        vendingMachine.setCurrentState(vendingMachine.getHasCoinState());
        vendingMachine.setSelectedProductQuantity(itemCount);
        System.out.println("You selected item :: " + vendingMachine.getInventory().getProduct(itemCode).getName() + " quantity :: " + itemCount);
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
    public void dispenseCoin() throws VendingMachineException {
        throw new VendingMachineException("No coins to be dispensed");
    }
}
