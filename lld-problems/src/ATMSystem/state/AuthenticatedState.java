package ATMSystem.state;

import ATMSystem.ATM;
import ATMSystem.Card;
import ATMSystem.OperationType;

public class AuthenticatedState implements ATMState{
    ATM atm;

    public AuthenticatedState(ATM atm) {
        this.atm = atm;
    }

    @Override
    public void insertCard(String cardNumber) {
        System.out.println("Exception occurred :: Card already present.");
    }

    @Override
    public void insertPin(String pin) {
        System.out.println("Exception occurred :: Card Pin already authenticated.");
    }

    @Override
    public void selectOperation(OperationType operationType, double... args) {
        try {
            switch (operationType) {
                case CHECK_BALANCE :
                    atm.checkBalance();
                    break;

                case DEPOSIT:
                    atm.deposit(args[0]);
                    break;

                case WITHDRAW:
                    atm.withdraw(args[0]);
                    break;
            }
        } catch (RuntimeException e) {
            System.out.println("Exception occurred while processing request :: " + e.getMessage());
        } finally {
            //ejectCard();
        }
    }

    @Override
    public void ejectCard() {
        System.out.println("Ejected card :: Please collect your card");
        atm.setCurrentCard(null);
        atm.setCurrentState(atm.getReadyState());
    }
}
