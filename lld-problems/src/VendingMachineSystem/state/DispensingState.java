package VendingMachineSystem.state;

import VendingMachineSystem.Coin;
import VendingMachineSystem.VendingMachine;
import VendingMachineSystem.exception.VendingMachineException;
import VendingMachineSystem.inventory.Product;

public class DispensingState implements VendingMachineState {
    private VendingMachine vendingMachine;

    public DispensingState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void selectItem(java.lang.String itemCode, int itemCount) throws VendingMachineException {
        throw new VendingMachineException("Item already selected");
    }

    @Override
    public void insertCoin(Coin coin) throws VendingMachineException {
        throw new VendingMachineException("Coins inserted");
    }

    @Override
    public void cancelTransaction() throws VendingMachineException {
        System.out.println("Cancelling transaction, please collect coins");
        dispenseCoin();
    }

    @Override
    public void dispenseItem() throws VendingMachineException {
        Product string = vendingMachine.getInventory().getProduct(vendingMachine.getSelectedItemCode());
        int quantity = vendingMachine.getSelectedProductQuantity();
        double amount = string.getPrice() * quantity;

        double userAmount = vendingMachine.getUserCoinsList().stream()
                .mapToInt(Coin::getValue)
                .sum();

        if(userAmount < amount) {
            System.out.println("Insufficient funds please collect your coins");
            dispenseCoin();
        } else
            System.out.print("Dispensing items");

        vendingMachine.resetMachineState();
    }

    @Override
    public void dispenseCoin() throws VendingMachineException {
        for(Coin coin : vendingMachine.getUserCoinsList())
            System.out.println("Dispensing Coin :: " + coin);
    }
}
