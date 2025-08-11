package VendingMachine.state;

import VendingMachine.machine.VendingMachine;
import VendingMachine.enums.Coin;
import VendingMachine.enums.StateType;

public class ReadyState implements  VendingMachineState{
    private final VendingMachine vendingMachine;

    public ReadyState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void insertCoin(Coin coin) {
        vendingMachine.currentCoin = coin;
        vendingMachine.currentState = vendingMachine.stateFactory.getState(StateType.HAS_COIN);
    }

    @Override
    public void chooseProduct(String productId) {
        System.out.println("Please insert coin first");
    }

    @Override
    public void dispense(Coin coin, String productId) {
        System.out.println("Please insert coin first");
    }
}
