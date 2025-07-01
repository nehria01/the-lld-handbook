package ATMSystem.state;

import ATMSystem.Card;
import ATMSystem.OperationType;

public interface ATMState {
    void insertCard(String cardNumber);
    void insertPin(String pin);
    void selectOperation(OperationType operationType, double... args);
    void ejectCard();
}
