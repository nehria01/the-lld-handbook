package VendingMachineSystem.state;

import VendingMachineSystem.Coin;
import VendingMachineSystem.machine.VendingMachine;
import VendingMachineSystem.exception.VendingMachineException;
import VendingMachineSystem.inventory.Product;

import java.util.List;

public class DispensingState implements VendingMachineState {
    private VendingMachine vendingMachine;

    public DispensingState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void selectItem(java.lang.String itemCode) throws VendingMachineException {
        throw new VendingMachineException("Item already selected");
    }

    @Override
    public void insertCoin(Coin coin) throws VendingMachineException {
        throw new VendingMachineException("Coins inserted");
    }

    @Override
    public void cancelTransaction() throws VendingMachineException {
        System.out.println("Cancelling transaction, please collect coins");
        dispenseCoin(vendingMachine.getUserCoins());
    }

    @Override
    public void dispenseItem() throws VendingMachineException {
        Product product = vendingMachine.getInventory().getProduct(vendingMachine.getSelectedItemCode());
        double amount = product.getPrice();

        double userAmount = vendingMachine.getUserCoins().stream()
                .mapToInt(Coin::getValue)
                .sum();

        //process change
        if(userAmount > amount) {
            double change = userAmount - amount;
            if(vendingMachine.getVendingMachineBalance() < change) {
                System.out.println("Not enough funds to process change, please collect your coins");
                dispenseCoin(vendingMachine.getUserCoins());
            }

        }
        if(userAmount < amount) {
            System.out.println("Insufficient funds please collect your coins");
            dispenseCoin(vendingMachine.getUserCoins());
        } else
            System.out.print("Dispensing items");

        vendingMachine.resetMachineState();
    }

    @Override
    public void dispenseCoin(List<Coin> coins) throws VendingMachineException {
        for(Coin coin : coins)
            System.out.println("Dispensing Coin :: " + coin);
    }
}
