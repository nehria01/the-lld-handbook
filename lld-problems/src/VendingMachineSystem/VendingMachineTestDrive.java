package VendingMachineSystem;

import VendingMachineSystem.exception.VendingMachineException;
import VendingMachineSystem.inventory.Inventory;
import VendingMachineSystem.inventory.Product;
import VendingMachineSystem.machine.VendingMachine;

public class VendingMachineTestDrive {
        public static void main(String[] args) {
            // Step 1: Setup Inventory
            Inventory inventory = new Inventory();
            inventory.addProduct(new Product("A1", "Coke", 25.0), 10);
            inventory.addProduct(new Product("B2", "Pepsi", 35.0), 5);
            inventory.addProduct(new Product("C3", "Water", 15.0), 20);

            // Step 2: Initialize Vending Machine
            VendingMachine vendingMachine = VendingMachine.getInstance();
            vendingMachine.setInventory(inventory);
            vendingMachine.resetMachineState(); // ensures state is Idle

            // Step 3: Simulate a customer interaction
            try {
                // Selecting product
                System.out.println("\n>> Selecting 1 unit of Coke (A1)");
                vendingMachine.selectItem("A1");

                // Inserting coins
                vendingMachine.insertCoin(Coin.TWENTY);
                vendingMachine.insertCoin(Coin.TEN);

                // Dispensing item
                vendingMachine.dispenseItem();

            } catch (VendingMachineException e) {
                System.err.println("!! ERROR: " + e.getMessage());
            }

            System.out.println();
            // Step 4: Simulate cancellation
            try {
                System.out.println("\n>> Selecting 1 unit of Water (C3)");
                vendingMachine.selectItem("C3");

                vendingMachine.insertCoin(Coin.TWENTY);
                vendingMachine.cancelTransaction();

            } catch (VendingMachineException e) {
                System.err.println("!! ERROR: " + e.getMessage());
            }
        }
}