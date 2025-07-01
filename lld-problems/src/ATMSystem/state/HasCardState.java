package ATMSystem.state;

import ATMSystem.ATM;
import ATMSystem.Card;
import ATMSystem.OperationType;

public class HasCardState implements ATMState{
    ATM atm;

    public HasCardState(ATM atm) {
        this.atm = atm;
    }

    @Override
    public void insertCard(String cardNumber) {
        System.out.println("Exception occurred :: Card already present.");
    }

    @Override
    public void insertPin(String pin) {
        if(atm.authenticateCardPin(pin)) {
            System.out.println("User authenticated");
            atm.setCurrentState(atm.getAuthenticatedState());
        }
        else
            System.out.println("Exception occurred :: Invalid Pin.");
    }

    @Override
    public void selectOperation(OperationType operationType, double... args) {
        System.out.println("Exception occurred :: Not Authorized.");
    }

    @Override
    public void ejectCard() {
        System.out.println("Ejected card :: Please collect your card");
        atm.setCurrentCard(null);
        atm.setCurrentState(atm.getReadyState());
    }
}
