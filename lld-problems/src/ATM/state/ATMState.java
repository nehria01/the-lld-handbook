package ATM.state;

import ATM.enums.Operation;
import ATM.model.Card;

public interface ATMState {
    void insertCard(Card card);
    void authenticate(String pin);
    void chooseOperation(Operation op, int... args);
    void ejectCard();
}
