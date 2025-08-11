package ATM.state;

import ATM.enums.StateType;
import ATM.machine.ATM;
import ATM.service.BankService;

public class StateFactory {
    private final ATM atm;
    private final BankService bankService;

    private final ATMState readyState;
    private final ATMState hasCardState;
    private final ATMState authenticatedState;

    public StateFactory(ATM atm, BankService bankService) {
        this.atm = atm;
        this.bankService = bankService;

        this.readyState = new ReadyState(atm);
        this.hasCardState = new HasCardState(atm, bankService);
        this.authenticatedState = new AuthenticatedState(atm, bankService);
    }

    public ATMState getState(StateType type) {
        return switch(type) {
            case READY -> readyState;
            case HAS_CARD -> hasCardState;
            case AUTHENTICATED -> authenticatedState;
        };
    }
}
