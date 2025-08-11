package VendingMachine.state;

import VendingMachine.machine.VendingMachine;
import VendingMachine.enums.StateType;
import VendingMachine.service.InventoryService;

public class StateFactory {
    private final VendingMachine vendingMachine;
    private final InventoryService inventoryService;

    private final VendingMachineState readyState;
    private final VendingMachineState hasCoinState;
    private final VendingMachineState dispenseState;

    public StateFactory(VendingMachine vendingMachine, InventoryService inventoryService) {
        this.inventoryService = inventoryService;
        this.vendingMachine = vendingMachine;

        this.readyState = new ReadyState(vendingMachine);
        this.hasCoinState = new HasCoinState(vendingMachine, inventoryService);
        this.dispenseState = new DispenseState(vendingMachine, inventoryService);
    }

    public VendingMachineState getState(StateType type) {
        return switch(type) {
            case READY -> readyState;
            case HAS_COIN -> hasCoinState;
            case DISPENSE -> dispenseState;
        };
    }
}
