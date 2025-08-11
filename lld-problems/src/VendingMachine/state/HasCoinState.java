package VendingMachine.state;

import VendingMachine.machine.VendingMachine;
import VendingMachine.enums.Coin;
import VendingMachine.enums.StateType;
import VendingMachine.service.InventoryService;

public class HasCoinState implements VendingMachineState{
    private final VendingMachine vendingMachine;
    private final InventoryService inventoryService;

    public HasCoinState(VendingMachine vendingMachine, InventoryService inventoryService) {
        this.vendingMachine = vendingMachine;
        this.inventoryService = inventoryService;
    }

    @Override
    public void insertCoin(Coin coin) {
        System.out.println("Coin already inserted");
    }

    @Override
    public void chooseProduct(String productId) {
        if(inventoryService.productAvailable(productId,1)) {
            vendingMachine.currentProductId = productId;
            vendingMachine.currentState = vendingMachine.stateFactory.getState(StateType.DISPENSE);
        } else {
            System.out.println("Product out of stock, please collect your coin");
            vendingMachine.reset();
        }
    }

    @Override
    public void dispense(Coin coin, String productId) {
        System.out.println("Please choose product");
    }
}
