package ATM.state;
import ATM.enums.Operation;

import ATM.enums.StateType;
import ATM.machine.ATM;
import ATM.model.Card;
import ATM.service.BankService;

public class HasCardState implements ATMState {
    private final ATM atm;
    private final BankService bankService;

    public HasCardState(ATM atm, BankService bankService) {
        this.atm = atm;
        this.bankService = bankService;
    }

    @Override
    public void insertCard(Card card) {
        System.out.println("A card is already inserted.");
    }

    @Override
    public void authenticate(String pin) {
        Card card = atm.currentCard;
        if(bankService.authenticate(card.cardNumber, pin)) {
            System.out.println("Authentication successful.");
            atm.currentState = atm.stateFactory.getState(StateType.AUTHENTICATED);
        } else {
            System.out.println("Invalid PIN");
            ejectCard();
        }
    }

    @Override
    public void chooseOperation(Operation op, int... args) {
        System.out.println("Please authenticate before choosing an operation.");
    }

    @Override
    public void ejectCard() {
        System.out.println("Ejecting Card");
        atm.currentCard = null;
        atm.currentState = atm.stateFactory.getState(StateType.READY);
    }
}

