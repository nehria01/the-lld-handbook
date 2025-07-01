package VendingMachineSystem;

import VendingMachineSystem.exception.VendingMachineException;
import VendingMachineSystem.inventory.Inventory;
import VendingMachineSystem.inventory.Product;

public class VendingMachineTestDrive {
        public static void main(String[] args) {
            // Step 1: Setup Inventory
            Inventory inventory = new Inventory();
            inventory.addProduct(new Product("A1", "Coke", 25.0, 10), 10);
            inventory.addProduct(new Product("B2", "Pepsi", 35.0, 5), 5);
            inventory.addProduct(new Product("C3", "Water", 15.0, 20), 20);

            // Step 2: Initialize Vending Machine
            VendingMachine vendingMachine = VendingMachine.getInstance();
            vendingMachine.setInventory(inventory);
            vendingMachine.resetMachineState(); // ensures state is Idle

            // Step 3: Simulate a customer interaction
            try {
                // Selecting product
                System.out.println("\n>> Selecting 1 unit of Coke (A1)");
                vendingMachine.getCurrentState().selectItem("A1", 2);

                // Inserting coins
                vendingMachine.getCurrentState().insertCoin(Coin.QUARTER);

                // Dispensing item
                vendingMachine.getCurrentState().dispenseItem();

            } catch (VendingMachineException e) {
                System.err.println("!! ERROR: " + e.getMessage());
            }

            // Step 4: Simulate cancellation
            try {
                System.out.println("\n>> Selecting 1 unit of Water (C3)");
                vendingMachine.getCurrentState().selectItem("C3", 1);
                vendingMachine.setSelectedProductQuantity(1);

                vendingMachine.getCurrentState().insertCoin(Coin.QUARTER);
                vendingMachine.getCurrentState().cancelTransaction();

            } catch (VendingMachineException e) {
                System.err.println("!! ERROR: " + e.getMessage());
            }
        }
}