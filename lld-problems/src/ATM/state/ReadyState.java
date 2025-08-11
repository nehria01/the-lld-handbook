package ATM.state;

import ATM.enums.Operation;
import ATM.enums.StateType;
import ATM.machine.ATM;
import ATM.model.Card;

public class ReadyState implements ATMState {
    private final ATM atm;

    public ReadyState(ATM atm) {
        this.atm = atm;
    }

    @Override
    public void insertCard(Card card) {
        System.out.println("Card Inserted");
        atm.currentCard = card;
        atm.currentState = atm.stateFactory.getState(StateType.HAS_CARD);
    }

    @Override
    public void authenticate(String pin) {
        System.out.println("No card inserted. Please insert a card first.");
    }

    @Override
    public void chooseOperation(Operation op, int... args) {
        System.out.println("No card inserted. Please insert a card first.");
    }

    @Override
    public void ejectCard() {
        System.out.println("No card to eject.");
    }
}
