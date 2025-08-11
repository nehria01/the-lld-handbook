package VendingMachine;


import VendingMachine.enums.Coin;
import VendingMachine.machine.VendingMachine;
import VendingMachine.model.Product;
import VendingMachine.service.InventoryService;

public class VendingMachineTestDrive {
    public static void main(String[] args) {
        InventoryService inventoryService = InventoryService.getInstance();
        VendingMachine vendingMachine = new VendingMachine(inventoryService);

        // Step 1: Load Inventory
        System.out.println("=== Loading Inventory ===");
        inventoryService.addProduct(new Product("Coke", "P1", 50), 5);
        inventoryService.addProduct(new Product("Pepsi", "P2", 40), 3);
        inventoryService.addProduct(new Product("Soda", "P3", 30), 2);

        // Load coins
        inventoryService.addCoin(Coin.TEN);
        inventoryService.addCoin(Coin.TEN);
        inventoryService.addCoin(Coin.TWENTY);
        inventoryService.addCoin(Coin.FIFTY);
        inventoryService.addCoin(Coin.HUNDRED);

        // Step 2: User inserts coin & buys product
        System.out.println("\n=== Scenario 1: Buy a Coke with exact change ===");
        vendingMachine.insertCoin(Coin.FIFTY);
        vendingMachine.chooseProduct("P1");
        vendingMachine.dispenseProduct();
        vendingMachine.reset();

        // Step 3: User inserts more than product price → expect change
        System.out.println("\n=== Scenario 2: Buy Pepsi with 100, expect change ===");
        vendingMachine.insertCoin(Coin.HUNDRED);
        vendingMachine.chooseProduct("P2");
        vendingMachine.dispenseProduct();
        vendingMachine.reset();

        // Step 4: Product sold out
        System.out.println("\n=== Scenario 3: Try to buy Soda when stock is 0 ===");
        vendingMachine.insertCoin(Coin.FIFTY);
        vendingMachine.chooseProduct("P3");
        vendingMachine.dispenseProduct();
        vendingMachine.reset();

        vendingMachine.insertCoin(Coin.FIFTY);
        vendingMachine.chooseProduct("P3");
        vendingMachine.dispenseProduct();
        vendingMachine.reset();

        // Now out of stock
        vendingMachine.insertCoin(Coin.FIFTY);
        vendingMachine.chooseProduct("P3"); // should indicate sold out
        vendingMachine.dispenseProduct();
        vendingMachine.reset();

        // Step 5: Refund scenario
        System.out.println("\n=== Scenario 4: Refund before product selection ===");
        vendingMachine.insertCoin(Coin.TWENTY);
        // Instead of choosing product, reset to simulate refund
        vendingMachine.reset();
    }
}