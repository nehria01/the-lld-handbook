package VendingMachine.machine;

import VendingMachine.enums.Coin;
import VendingMachine.enums.StateType;
import VendingMachine.service.InventoryService;
import VendingMachine.state.StateFactory;
import VendingMachine.state.VendingMachineState;

public class VendingMachine {
    public VendingMachineState currentState;
    public StateFactory stateFactory;
    public Coin currentCoin;
    public String currentProductId;

    public VendingMachine(InventoryService inventoryService) {
        this.stateFactory = new StateFactory(this, inventoryService);
        this.currentState = stateFactory.getState(StateType.READY);
    }

    public void insertCoin(Coin coin) {
        currentState.insertCoin(coin);
    }

    public void chooseProduct(String productId) {
        currentState.chooseProduct(productId);
    }

    public void dispenseProduct() {
        currentState.dispense(currentCoin, currentProductId);
    }

    public void reset() {
        currentCoin = null;
        currentProductId  = null;
        currentState = stateFactory.getState(StateType.READY);
    }
}
