package ATM.machine;

import ATM.enums.Operation;
import ATM.enums.StateType;
import ATM.model.Card;
import ATM.service.BankService;
import ATM.state.ATMState;
import ATM.state.StateFactory;

public class ATM {
    public ATMState currentState;
    public StateFactory stateFactory;
    public Card currentCard;

    int [] denominations;
    int [] currentNotes;

    public ATM(int [] denominations, int[] count, BankService bankService) {
        this.denominations = denominations;
        this.currentNotes = count;
        this.stateFactory = new StateFactory(this, bankService);
        this.currentState = stateFactory.getState(StateType.READY);
        this.currentCard = null;
    }

    public void insertCard(Card card) {
        currentState.insertCard(card);
    }

    public void authenticate(String pin) {
        currentState.authenticate(pin);
    }

    public void chooseOperation(Operation op, int... args) {
        currentState.chooseOperation(op, args);
    }

    public void ejectCard() {
        currentState.ejectCard();
    }

    public int getBalance() {
        int totalAmount = 0;
        for(int i=0;i<denominations.length;i++) {
            totalAmount += denominations[i] * currentNotes[i];
        }
        return totalAmount;
    }

    public void dispenseCash(int amount) {
        int [] res = withdrawMoney(amount);
        for(int i=0;i<res.length;i++) {
            int val = denominations[i];
            System.out.println("Dispensing " +res[i] + " notes of val "+val);
        }
    }

    private int [] withdrawMoney(int amount) {
        int [] res = new int[denominations.length];
        dfs(amount,0, res);
        for(int i=0;i< currentNotes.length;i++)
            currentNotes[i] -= res[i];
        return res;
    }

    private boolean dfs(int amount, int idx, int [] res) {
        if(amount == 0)
            return true;
        if(amount<0 || idx == res.length)
            return false;

        int currNoteValue = denominations[idx];
        int maxNoteCount = Math.min(currentNotes[idx], amount/currNoteValue);

        for(int count=maxNoteCount; count>=0; count--) {
            res[idx] = count;
            if(dfs(amount-currNoteValue*count, idx+1, res))
                return true;
        }

        res[idx]=0;
        return false;
    }
}
