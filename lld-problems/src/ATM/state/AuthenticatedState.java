package ATM.state;

import ATM.enums.Operation;
import ATM.enums.StateType;
import ATM.machine.ATM;
import ATM.model.Card;
import ATM.service.BankService;

public class AuthenticatedState implements ATMState {
    private final ATM atm;
    private final BankService bankService;

    public AuthenticatedState(ATM atm, BankService bankService) {
        this.atm = atm;
        this.bankService = bankService;
    }

    @Override
    public void insertCard(Card card) {
        System.out.println("A card is already inserted.");
    }

    @Override
    public void authenticate(String pin) {
        System.out.println("Already authenticated.");
    }

    @Override
    public void chooseOperation(Operation op, int... args) {
        Card card = atm.currentCard;
        switch (op) {
            case VIEW_BALANCE -> {
                int balance = bankService.getBalance(card.cardNumber);
                System.out.println("Current balance: " + balance);
            }
            case WITHDRAW -> {
                int amount = args[0];
                if(atm.getBalance() < amount) {
                    System.out.println("Insufficient funds in ATM ");
                } else {
                    int accountBalance = bankService.getBalance(card.cardNumber);
                    if(accountBalance < amount)
                        System.out.println("Insufficient funds in account ");
                    else {
                        bankService.withdraw(card.cardNumber, amount);
                        atm.dispenseCash(amount);
                    }
                }
            }
            default -> System.out.println("Operation not supported.");
        }
        ejectCard();
    }

    @Override
    public void ejectCard() {
        System.out.println("Ejecting Card");
        atm.currentCard = null;
        atm.currentState = atm.stateFactory.getState(StateType.READY);
    }
}
