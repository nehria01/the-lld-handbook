package ATMSystem.state;

import ATMSystem.ATM;
import ATMSystem.Card;
import ATMSystem.OperationType;

public class ReadyState implements ATMState{
    ATM atm;

    public ReadyState(ATM atm) {
        this.atm = atm;
    }

    @Override
    public void insertCard(String cardNumber) {
        System.out.println("Card detected.");
        atm.setCard(cardNumber);
        atm.setCurrentState(atm.getHasCardState());
    }

    @Override
    public void insertPin(String pin) {
        System.out.println("Exception occurred :: No card present.");
    }

    @Override
    public void selectOperation(OperationType operationType, double... args) {
        System.out.println("Exception occurred :: No card present.");
    }

    @Override
    public void ejectCard() {
        System.out.println("Exception occurred :: No card present.");
    }
}
