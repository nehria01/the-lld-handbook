package VendingMachine.state;

import VendingMachine.machine.VendingMachine;
import VendingMachine.enums.Coin;
import VendingMachine.service.InventoryService;

import java.util.HashMap;
import java.util.Map;

public class DispenseState implements VendingMachineState {
    private final VendingMachine vendingMachine;
    private final InventoryService inventoryService;

    public DispenseState(VendingMachine vendingMachine, InventoryService inventoryService) {
        this.vendingMachine = vendingMachine;
        this.inventoryService = inventoryService;
    }

    @Override
    public void insertCoin(Coin coin) {
        System.out.println("Coin already inserted");
    }

    @Override
    public void chooseProduct(String productId) {
        System.out.println("Product already chosen");
    }

    @Override
    public void dispense(Coin coin, String productId) {
        if (productId == null) {
            System.out.println("No product selected");
            return;
        }

        int productCost = inventoryService.getProductPrice(productId);
        int amount = coin.getVal();
        if (amount < productCost) {
            System.out.println("Insufficient amount");
            return;
        }

        int changeNeeded = amount - productCost;
        if (!canProcessChange(changeNeeded)) {
            System.out.println("Cannot process change, transaction cancelled");
            return;
        }
        System.out.println("Please collect item from items tray");
        if (changeNeeded > 0) {
            System.out.println("Processing change please collect from coins tray");
            processChange(changeNeeded);
        }

        inventoryService.reduceStock(productId);
        vendingMachine.reset();
    }

    private boolean canProcessChange(int amount) {
        int[] denomination = inventoryService.getDenominations();
        int[] coinCount = inventoryService.getCoinsCount();

        for(int i = denomination.length-1;i>=0 && amount>0;i--) {
            int value = denomination[i];
            int count = coinCount[i];

            if(value>amount) continue;
            int numCoins = Math.min(count, amount/value);
            amount -= value * numCoins;
        }

        return amount == 0;
    }

    private void processChange(int amount) {
        int[] denomination = inventoryService.getDenominations();
        int[] coinCount = inventoryService.getCoinsCount();
        int[] res = new int[denomination.length];

        for(int i = denomination.length-1;i>=0&& amount>0;i--) {
            int value = denomination[i];
            int count = coinCount[i];

            if(value>amount) continue;
            int numCoins = Math.min(count, amount/value);
            res[i] = numCoins;
            amount -= value * numCoins;
        }

        Map<Coin, Integer> changeMap = new HashMap<>();
        for (int i = 0; i < denomination.length; i++) {
            if (res[i] > 0) {
                Coin coin = Coin.fromValue(denomination[i]);
                System.out.println("Dispensing coin " + coin.getVal());
                changeMap.put(coin, res[i]);
            }
        }
        inventoryService.reduceCoin(changeMap);
    }

    private void reduceStock(String productId) {
        inventoryService.reduceStock(productId);
    }


}
